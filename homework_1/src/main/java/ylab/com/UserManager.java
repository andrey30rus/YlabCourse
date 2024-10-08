package ylab.com;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users = new HashMap<>();

    public void register(String email, String password, String name) {
        // Логика регистрации
    }

    public User login(String email, String password) {
        // Логика авторизации
        return null;
    }

    public void editUserProfile(User user, String newName, String newEmail, String newPassword) {
        // Логика редактирования профиля
    }

    public void deleteUser(String email) {
        // Логика удаления аккаунта
    }
}
