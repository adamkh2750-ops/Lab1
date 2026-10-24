/** Represents a system user with a validated email and password. */
public class User {
    private final String username;
    private final String password;

    // Email structure and password symbols allowed by the assignment.
    private static final String EMAIL_PATTERN =
            "[A-Za-z0-9._%+-]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.[A-Za-z]{2,}";
    private static final String PASSWORD_SYMBOLS = "!@#$%^&*()_+";

    /** Creates a user only when both values satisfy the validation rules. */
    public User(String username, String password) {
        validateUsername(username);
        validatePassword(password);
        this.username = username;
        this.password = password;
    }

    private static void validateUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Please enter a valid Email as username");
        }
        if (username.length() > 50) {
            throw new IllegalArgumentException("Username is too long, try something shorter");
        }
        if (!username.matches(EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Please enter a valid Email as username");
        }
    }

    private static void validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Please enter a valid password");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Your password is too short, add more characters");
        }
        if (password.length() > 12) {
            throw new IllegalArgumentException("Your password is too long, try a shorter one");
        }

        // A valid password must contain at least one item of each type.
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;

        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            if ((character >= 'A' && character <= 'Z')
                    || (character >= 'a' && character <= 'z')) {
                hasLetter = true;
            } else if (character >= '0' && character <= '9') {
                hasDigit = true;
            } else if (PASSWORD_SYMBOLS.indexOf(character) >= 0) {
                hasSymbol = true;
            } else {
                throw new IllegalArgumentException("Please enter a valid password");
            }
        }

        if (!hasLetter || !hasDigit || !hasSymbol) {
            throw new IllegalArgumentException("Please enter a valid password");
        }
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return username + " " + password;
    }
}
