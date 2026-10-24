import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** Reads users from a file and prints the valid users in username order. */
public class UsersApp {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();

        // Read and validate every input row.
        try (Scanner reader = new Scanner(new File("users.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.trim().split("\\s+");

                // The assignment defines every row as a username and a password.
                if (parts.length != 2) {
                    System.err.println(line + " Please enter a valid Email as username");
                    continue;
                }

                try {
                    User user = new User(parts[0], parts[1]);
                    users.add(user);
                } catch (IllegalArgumentException exception) {
                    System.err.println(line + " " + exception.getMessage());
                }
            }
        } catch (FileNotFoundException exception) {
            System.err.println("Cannot read input file: users.txt");
            return;
        }

        // Sort the valid users alphabetically by username.
        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));

        for (User user : users) {
            System.out.println(user);
        }
    }
}
