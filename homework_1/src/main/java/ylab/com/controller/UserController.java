package ylab.com.controller;

import ylab.com.dao.UserManager;
import ylab.com.models.User;

import java.util.Map;
import java.util.Scanner;

import static ylab.com.ui.ApplicationStarter.handleUserInput;
import static ylab.com.ui.ApplicationStarter.printWelcomeMessage;
import static ylab.com.utils.Utils.validateEmail;
import static ylab.com.utils.Utils.validatePassword;

public class UserController {

    private static UserManager userManager = new UserManager();
    private static User currentUser;

    public static void deleteUserAccount(User currentUser, Scanner scanner) {
        System.out.print("Вы уверены, что хотите удалить ваш аккаунт? (да/нет): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("да")) {
            userManager.deleteUser(currentUser.getEmail());
            System.out.println("Ваш аккаунт был успешно удален.");
            UserController.currentUser = null;
            printWelcomeMessage();
            handleUserInput();
        } else {
            System.out.println("Удаление аккаунта отменено.");
        }
    }

    public static void resetPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите новый пароль: ");
        String newPassword = scanner.nextLine().trim();
        currentUser.setPassword(newPassword);
        userManager.updateUserProfile(currentUser, currentUser.getName(), currentUser.getEmail(), newPassword);
        System.out.println("Пароль успешно изменен.");
    }

    public static void updateUser(User currentUser, Scanner scanner) {
        System.out.print("Введите новое имя: ");
        String newName = scanner.nextLine().trim();
        System.out.print("Введите новый email: ");
        String newEmail = scanner.nextLine().trim();
        System.out.print("Введите новый пароль: ");
        String newPassword = scanner.nextLine().trim();
        if (validateEmail(newEmail) && validatePassword(newPassword)) {
            userManager.updateUserProfile(currentUser, newName, newEmail, newPassword);
            System.out.println("Профиль успешно обновлен.");
        } else {
            System.out.println("Ошибка: Неправильный формат логина или пароля.");
        }

    }

    public static void deleteForeignAccount(User currentUser, Scanner scanner) {
        System.out.print("Введите email пользователя для удаления: ");
        String email = scanner.nextLine().trim();
        if (currentUser.getEmail().equals(email)) {
            return;
        }
        if (userManager.getUserByEmail(email) == null) {
            System.out.println("Пользователь отсутвует в системе");
            return;
        }
        userManager.deleteUser(email);
    }


    public static void blockUser(Scanner scanner) {
        System.out.print("Введите email пользователя для блокировки: ");
        String email = scanner.nextLine().trim();
        userManager.blockUser(email);
    }


    public static void viewAllUsers() {
        Map<String, User> users = userManager.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Нет зарегистрированных пользователей.");
        } else {
            System.out.println("Список пользователей:");
            users.forEach((email, user) -> {
                System.out.println("Имя: " + user.getName() + ", Email: " + email +
                        (user.isAdmin() ? " (Администратор)" : "") +
                        (user.isBlocked() ? " (Заблокирован)" : ""));
            });
        }
    }

}
