package ylab.com;

import java.time.LocalDate;
import java.util.List;

public class HabitManager {
    public void createHabit(User user, String title, String description, String frequency) {
        // Логика создания привычки
    }

    public void deleteHabit(User user, Habit habit) {
        // Логика удаления привычки
    }

    public void markHabitAsCompleted(User user, Habit habit, LocalDate date) {
        // Логика отметки выполнения привычки
    }

    public List<Habit> getHabits(User user) {
        // Логика получения списка привычек
        return List.of();
    }
}
