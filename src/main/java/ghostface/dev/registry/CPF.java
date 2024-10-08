package ghostface.dev.registry;

import org.jetbrains.annotations.NotNull;


public final class CPF implements CharSequence {

    public static boolean isValid(long cpf) {
        return calculate(cpf);
    }

    // TODO: waiting for Clean Code to arrive
    public static boolean isValid(@NotNull String string) {
        if (string.length() == 14) {
            if (string.split("\\.").length != 3) {
                return false;
            }

            @NotNull String[] allparts = string.split("\\.");
            @NotNull String[] firtPart = new String[2];
            @NotNull String[] lastPart = allparts[2].split("-");

            firtPart[0] = allparts[0];
            firtPart[1] = allparts[1];

            if (firtPart[0].length() != 3 || firtPart[0].isBlank()) {
                return false;
            } else if (firtPart[1].length() != 3 || firtPart[1].isBlank()) {
                return false;
            }

            if (lastPart[0].length() != 3 || lastPart[0].isBlank()) {
                return false;
            } else if (lastPart[1].length() != 2 || lastPart[1].isBlank()) {
                return false;
            }

            try {
                long cpf = Long.parseLong(firtPart[0].concat(firtPart[1]).concat(lastPart[0]).concat(lastPart[1]));
                return calculate(cpf);
            } catch (NumberFormatException e) {
                return false;
            }

        } else if (string.length() == 11) {
            try {
                long cpf = Long.parseLong(string);
                return calculate(cpf);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    // Todo: this is not so bad, i guess
    private static boolean calculate(long cpf) {
        @NotNull String s = String.valueOf(cpf);
        if (s.length() != 11) return false;

        // Check the first part (penultimate cpf index)
        int multiplier = 10;
        int result = 0;

        for (int i = 0; i < 9; i++) {
            int n = Integer.parseInt(Character.toString(s.charAt(i))) * multiplier;
            result = result + n;
            multiplier--;
        }

        int number = Integer.parseInt(Character.toString(s.charAt(9)));

        if ((result * 10) % 11 == 10) {
            if (number != 0) return false;
        } else if ((result * 10) % 11 != number) {
            return false;
        }

        // Check the part 2 (last cpf index)
        multiplier = 11;
        result = 0;

        for (int i = 0; i < 10; i++) {
            int n = Integer.parseInt(Character.toString(s.charAt(i))) * multiplier;
            result = result + n;
            multiplier--;
        }

        number = Integer.parseInt(Character.toString(s.charAt(10)));

        if ((result * 10) % 11 == 10 ) {
            return number == 0;
        } else
            return (result * 10) % 11 == number;
    }

    // Objects

    private final @NotNull String string;

    // Todo: constructor with long
    public CPF(@NotNull String string) {
        if (!isValid(string)) throw new IllegalArgumentException("Invalid CPF");
        this.string = string;
    }

    public long getLong() {
        return Long.getLong(string);
    }

    // Charsequence Implementations

    @Override
    public @NotNull String toString() {
        return string.replace(".", "").replace("-", "");
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @Override
    public @NotNull CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }
}
