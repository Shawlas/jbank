package ghostface.dev.registry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;


public final class CPF implements CharSequence {

    public static @NotNull String format(@NotNull String s) {
        @NotNull String cpf = s.replace(".", "").replace("-", "");

        if (cpf.length() != 11 || !cpf.matches("^[0-9]{11}$")) {
            throw new IllegalArgumentException("invalid CPF String");
        }

        @NotNull StringBuilder builder = new StringBuilder();

        builder.append(cpf, 0, 3).append(".")
                .append(cpf, 3, 6).append(".")
                .append(cpf, 6, 9).append("-")
                .append(cpf,9, 11);

        return builder.toString();
    }

    public static boolean validate(@NotNull String string) {
        @NotNull String cpf = string.replace(".", "").replace("-", "");

        if (!cpf.matches("^[0-9]{11}$")) {
            return false;
        }

        return computeIndex10(cpf) && computeIndex11(cpf);
    }

    private static boolean computeIndex10(@NotNull String cpf) {
        if (cpf.length() != 11) return false;

        int position = Character.getNumericValue(cpf.charAt(9));
        int multiplier = 10;
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i)) * multiplier;
            sum += digit;
            multiplier--;
        }

        int result = (sum * 10) % 11;

        return result == 10 ? 0 == position : result == position;
    }

    private static boolean computeIndex11(@NotNull String cpf) {
        if (cpf.length() != 11) return false;

        int position = Character.getNumericValue(cpf.charAt(10));
        int multiplier = 11;
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            int n = Character.getNumericValue(cpf.charAt(i)) * multiplier;
            multiplier--;
            sum += n;
        }

        int result = (sum * 10) % 11;

        return result == 10? 0 == position : result == position;
    }

    // Objects

    private final @NotNull String string;

    public CPF(@NotNull String string) {
        if (!validate(string)) throw new IllegalArgumentException("The string '" + string + "' is not valid CPF");
        this.string = string.replace(".","").replace("-", "");
    }

    public @NotNull String getFormated() {
        return format(string);
    }

    @Override
    public @NotNull String toString() {
        return string;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull CPF cpf = (CPF) object;
        return Objects.equals(string, cpf.string);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string);
    }

    // Charsequence Implementations

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
