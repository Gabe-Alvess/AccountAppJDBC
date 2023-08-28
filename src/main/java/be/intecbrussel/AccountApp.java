package be.intecbrussel;

import be.intecbrussel.modal.Account;
import be.intecbrussel.modal.User;
import be.intecbrussel.service.LoginService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AccountApp {
    private static User currentUser;
    private static LoginService loginService = new LoginService();

    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        System.out.println("Hello visitor, welcome to the account app!\nWat do you want to do?");
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;

        loop:
        while (true) {
            System.out.println("Menu: 1. Register - 2. Login - 3. Exit app");

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine(); // Consuming the newline character after reading the number
            } else {
                System.out.println("ONLY NUMBERS ALLOWED!");
                scanner.nextLine(); // Consume the invalid input
                continue; // Restart the loop
            }

            switch (userChoice) {
                case -9 -> adminLogin();
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    break loop;
                }
                default -> System.out.println("WRONG INPUT!");
            }
        }
    }

    private static void loggedInMenu() {
        System.out.printf("Welcome %s %s.", currentUser.getFname(), currentUser.getLname());
        System.out.println("\nYou're successfully logged in!");

        Scanner scanner = new Scanner(System.in);
        int userChoice;

        loop:
        while (true) {
            System.out.println("Menu: 1. Account info - 2. Change Name - 3. Change email - 4. Change password - 5. Delete account - 6. Delete user info - 7. Logout");

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("ONLY NUMBERS ALLOWED!");
                scanner.nextLine();
                continue;
            }

            switch (userChoice) {
                case 1 -> accountInfo();
                case 2 -> changeName();
                case 3 -> changeEmail();
                case 4 -> changePass();
                case 5 -> {
                    if (deleteAccount()) {
                        break loop;
                    }
                }
                case 6 -> {
                    if (deleteUser()) {
                        break loop;
                    }
                }
                case 7 -> {
                    if (logout()) {
                        break loop;
                    }
                }
                default -> System.out.println("WRONG INPUT");
            }
        }
    }

    private static void adminMenu() {
        System.out.printf("Welcome admin %s %s!\n",currentUser.getFname(), currentUser.getLname());
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;

        loop:
        while (true) {
            System.out.println("Menu: 1. Add multiple users - 2. Logout");

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("ONLY NUMBERS ALLOWED!");
                scanner.nextLine();
                continue;
            }

            switch (userChoice) {
                case 1 -> multiRegister();
                case 2 -> {
                    if (logout()) {
                        break loop;
                    }
                }
                default -> System.out.println("WRONG INPUT!");
            }
        }
    }

    private static void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your first name?");
        String fname = scanner.nextLine();
        System.out.println("What is your last name?");
        String lname = scanner.nextLine();
        System.out.println("What is your email?");
        String email = scanner.nextLine();
        System.out.println("What is your password?");
        String passw = scanner.nextLine();

        while (passw.trim().length() < 6) {
            System.out.println("Password is too short! Minimum of 6 characters required.");
            System.out.print("Please insert a bigger password: ");
            passw = scanner.nextLine();
        }

        boolean success = loginService.register(fname, lname, email, passw);

        if (success) {
            System.out.printf("All done %s %s, your account has been successfully registered!\n", fname, lname);
        } else {
            System.out.println("SOMETHING WENT WRONG! (┬┬﹏┬┬)");
        }
    }

    private static void multiRegister() {
        List<User> userList = new ArrayList<>();

        for (int i = 5; i > 0; i--) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("first name");
            String fname = scanner.nextLine();

            System.out.println("last name");
            String lname = scanner.nextLine();

            System.out.println("email");
            String email = scanner.nextLine();

            System.out.println("password");
            String passw = scanner.nextLine();

            User user = new User(fname, lname, new Account(email, passw));
            userList.add(user);
        }

        boolean success = loginService.registerManyUsers(userList);

        if (success) {
            System.out.println("All users successfully registered! ╰(*°▽°*)╯");
        } else {
            System.out.println("SOMETHING WENT WRONG! (┬┬﹏┬┬)");
        }
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your email?");
        String email = scanner.nextLine();
        System.out.println("What is your password?");
        String passw = scanner.nextLine();

        Optional<User> user = loginService.loginUser(email, passw);

        if (user.isPresent()) {
            currentUser = user.get();
            loggedInMenu();
        } else {
            System.out.println("ACCOUNT NOT FOUND! (┬┬﹏┬┬)");
        }
    }

    private static void adminLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your email?");
        String email = scanner.nextLine();
        System.out.println("What is your password?");
        String passw = scanner.nextLine();

        Optional<User> user = loginService.loginUser(email, passw);
        String currentEmail = user.isPresent() ? user.get().getAccount().getEmail() : "notAdmin";

        if (user.isPresent() && currentEmail.equals("jean@jean.com")) {
            currentUser = user.get();
            adminMenu();
        } else {
            System.out.println("(╯▔皿▔)╯ YOU ARE NOT AN ADMIN GO AWAY! ");
        }
    }

    private static void changeName() {
        String userEmail = currentUser.getAccount().getEmail();
        Scanner scanner = new Scanner(System.in);

        System.out.print("First Name: ");
        String newFName = scanner.nextLine();
        System.out.print("Last Name: ");
        String newLName = scanner.nextLine();

        System.out.println("Do you really want to change your first name and last name? 1. Yes - 2. No");
        int input = 0;

        loop:
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("ONLY NUMBERS ALLOWED!");
                scanner.nextLine();
                continue;
            }

            switch (input) {
                case 1 -> {
                    boolean succes = loginService.changeName(newFName, newLName, userEmail);

                    if (succes) {
                        currentUser.setFname(newFName);
                        currentUser.setLname(newLName);
                        System.out.println("Information successfully changed! ╰(*°▽°*)╯");
                        System.out.printf("""
                                First Name: %s
                                Last Name: %s
                                """, currentUser.getFname(), currentUser.getLname()
                        );
                        break loop;
                    } else {
                        System.out.println("SOMETHING WENT WRONG ! (┬┬﹏┬┬)");
                    }
                }
                case 2 -> {
                    break loop;
                }
            }
        }
    }

    private static void changeEmail() {
        Scanner scanner = new Scanner(System.in);
        String currentEmail = currentUser.getAccount().getEmail();

        System.out.println("Please enter your password before proceeding");
        String actualPass = scanner.nextLine();

        while (!actualPass.equals(currentUser.getAccount().getPassw())) {
            System.out.println("Wrong password! Please try again");
            actualPass = scanner.nextLine();
        }

        System.out.println("Please enter your new email");
        String newEmail = scanner.nextLine();

        boolean success = loginService.changeUserEmail(currentEmail, newEmail);

        if (success) {
            System.out.println("Your email was successfully changed!");
            currentUser.getAccount().setEmail(newEmail);
        } else {
            System.out.println("SOMETHING WENT WRONG! (┬┬﹏┬┬)");
        }
    }

    private static void changePass() {
        Scanner scanner = new Scanner(System.in);
        String currentEmail = currentUser.getAccount().getEmail();

        System.out.println("Please enter your password before proceeding");
        String password = scanner.nextLine();

        while (!password.equals(currentUser.getAccount().getPassw())) {
            System.out.println("Wrong password! Please try again");
            password = scanner.nextLine();
        }

        System.out.println("Please enter your new password");
        String newPass = scanner.nextLine();

        while (newPass.trim().length() < 6) {
            System.out.println("Password is too short! Minimum of 6 characters required.");
            System.out.print("Please enter a bigger password: ");
            newPass = scanner.nextLine();
        }

        boolean succes = loginService.changeUserPassword(currentEmail, newPass);

        if (succes) {
            System.out.println("Your password was successfully changed!");
            currentUser.getAccount().setPassw(newPass);
        } else {
            System.out.println("SOMETHING WENT WRONG! (┬┬﹏┬┬)");
        }
    }

    private static void accountInfo() {
        String firstName = currentUser.getFname();
        String lastName = currentUser.getLname();
        String userEmail = currentUser.getAccount().getEmail();
        String userPass = currentUser.getAccount().getPassw();
        String hiddenPass = "X".repeat(userPass.length() - 3) + userPass.substring(userPass.length() - 3);

        System.out.printf("""
                First name: %s
                Last name: %s
                Email: %s
                Password: %s
                """, firstName, lastName, userEmail, hiddenPass
        );
    }

    private static boolean logout() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you really want to log out? 1.Yes - 2. No");
        int input = 0;
        boolean bool;

        loop:
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("ONLY NUMBERS ALLOWED!");
                scanner.nextLine();
                continue;
            }

            switch (input) {
                case 1 -> {
                    currentUser = null;
                    bool = true;
                    break loop;
                }
                case 2 -> {
                    bool = false;
                    break loop;
                }
                default -> System.out.println("WRONG INPUT!");
            }
        }

        return bool;
    }

    private static boolean deleteAccount() {
        Scanner scanner = new Scanner(System.in);
        String currentEmail = currentUser.getAccount().getEmail();

        System.out.println("Please enter your password before proceeding");
        String password = scanner.nextLine();

        while (!password.equals(currentUser.getAccount().getPassw())) {
            System.out.println("Wrong password! Please try again");
            password = scanner.nextLine();
        }

        System.out.println("Do you really want to delete your account? 1. Yes - 2. No");
        int input = 0;
        boolean bool;

        loop:
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("ONLY NUMBERS ALLOWED!");
                scanner.nextLine();
                continue;
            }

            switch (input) {
                case 1 -> {
                    boolean succes = loginService.deleteUserAccount(currentEmail);

                    if (succes) {
                        System.out.printf("We are sorry to see you go %s %s! (┬┬﹏┬┬)", currentUser.getFname(), currentUser.getLname());
                        System.out.println("\nYour account was successfully deleted.");
                        currentUser = null;
                        bool = true;
                        break loop;
                    } else {
                        System.out.println("SOMETHING WENT WRONG! (┬┬﹏┬┬)");
                    }
                }
                case 2 -> {
                    bool = false;
                    break loop;
                }
                default -> System.out.println("WRONG INPUT!");
            }
        }

        return bool;
    }

    private static boolean deleteUser() {
        Scanner scanner = new Scanner(System.in);
        String currentEmail = currentUser.getAccount().getEmail();

        System.out.println("Please enter your password before proceeding");
        String password = scanner.nextLine();

        while (!password.equals(currentUser.getAccount().getPassw())) {
            System.out.println("Wrong password! Please try again");
            password = scanner.nextLine();
        }

        System.out.println("Do you really want to delete your user info? 1. Yes - 2. No");
        int input = 0;
        boolean bool;

        loop:
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("ONLY NUMBERS ALLOWED!");
                scanner.nextLine();
                continue;
            }

            switch (input) {
                case 1 -> {
                    boolean succes = loginService.deleteUserInfo(currentEmail);

                    if (succes) {
                        System.out.printf("We are sorry to see you go %s %s! (┬┬﹏┬┬)", currentUser.getFname(), currentUser.getLname());
                        System.out.println("\nYour info was successfully deleted.");
                        currentUser = null;
                        bool = true;
                        break loop;
                    } else {
                        System.out.println("SOMETHING WENT WRONG! (┬┬﹏┬┬)");
                    }
                }
                case 2 -> {
                    bool = false;
                    break loop;
                }
                default -> System.out.println("WRONG INPUT!");
            }
        }

        return bool;
    }
}
