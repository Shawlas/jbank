package ghostface.dev.registry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import codes.laivy.address.domain.SLD;
import codes.laivy.address.domain.TLD;

import java.util.Objects;

public final class Email implements CharSequence {

    public static boolean isValid(@NotNull String string) {
        @NotNull String email = string.replace("(dot)", "\\.").replace("(at)", "@");

        if (email.length() > 254) {
            return false;
        } else if (email.split("@").length != 2) {
            return false;
        } else {
            @NotNull String[] parts = email.split("@");
            @NotNull String[] dots = parts[1].split("\\.");

            if (dots.length <2 || dots.length > 3) {
                return false;
            }

            @NotNull String name = parts[0];
            @NotNull String SLD;
            @NotNull String TLD;

            if (dots.length == 3) {
                SLD = dots[0] + "." + dots[1];
                TLD = dots[2];
            } else {
                SLD = dots[0];
                TLD = dots[1];
            }

            if (!name.matches("[a-zA-Z0-9._%+-]{1,64}")) {
                return false;
            } else if (!SLD.matches("^(?!-)[a-zA-Z0-9-_.]{1,63}(?<!-)$")) {
                return false;
            } else
                return TLD.matches("^[^0-9@#\"'\\\\$%]*$");
        }
    }

    public static @NotNull Email parse(@NotNull String string) {
        if (string.length() > 254) {
            throw new IllegalArgumentException("Email too long");
        }

        @NotNull String email = string.replace("(dot)", "\\.").replace("(at)", "@");

        if (isValid(email)) {
            @NotNull String[] parts = email.split("@");
            @NotNull String[] dots = parts[1].split("\\.");

            @NotNull String name = parts[0];
            @NotNull String sld;
            @NotNull String tld;

            if (dots.length == 3) {
                sld = dots[0] + "." + dots[1];
                tld = dots[2];
            } else {
                sld = dots[0];
                tld = dots[1];
            }

            try {
                return new Email(name, SLD.parse(sld), TLD.parse(tld));
            } catch (@NotNull Exception e) {
                throw new IllegalArgumentException("Cannot parse '" + string + "' as email");
            }

        } else {
            throw new IllegalArgumentException("Cannot parse '" + string + "' as email");
        }
    }

    // Object

    private final @NotNull String username;
    private final @NotNull SLD sld;
    private final @NotNull TLD tld;

    public Email(@NotNull String username, @NotNull SLD sld, @NotNull TLD tdl) {
        @NotNull String string = username + "@" + sld + "." + tdl;

        if (!isValid(string)) {
            throw new IllegalArgumentException("Email '" + string + "' is not valid");
        }

        this.username = username;
        this.sld = sld;
        this.tld = tdl;
    }

    public @NotNull String getUsername() {
        return username;
    }

    public @NotNull SLD getSLD() {
        return sld;
    }
    public @NotNull TLD getTDL() {
        return tld;
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
        return toString().subSequence(start,end);
    }
    @Override
    public @NotNull String toString() {
        return username + "@" + sld + "." + tld;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Email email = (Email) object;
        return Objects.equals(username, email.username) && Objects.equals(sld, email.sld) && Objects.equals(tld, email.tld);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, sld, tld);
    }
}
