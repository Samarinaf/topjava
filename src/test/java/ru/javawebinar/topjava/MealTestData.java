package ru.javawebinar.topjava;

import org.assertj.core.api.Assertions;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 10;

    public static final Meal userMeal = new Meal(USER_MEAL_ID, LocalDateTime.of(2021, Month.JANUARY, 22, 0, 0), "Поздний ужин", 500);
    public static final Meal userMeal1 = new Meal(USER_MEAL_ID + 1, LocalDateTime.of(2021, Month.JANUARY, 21, 7, 0), "Завтрак", 600);
    public static final Meal userMeal2 = new Meal(USER_MEAL_ID + 2, LocalDateTime.of(2021, Month.JANUARY, 21, 12, 0), "Обед", 750);
    public static final Meal userMeal3 = new Meal(USER_MEAL_ID + 3, LocalDateTime.of(2021, Month.JANUARY, 21, 18, 30), "Ужин", 500);
    public static final Meal userMeal4 = new Meal(USER_MEAL_ID + 4, LocalDateTime.of(2021, Month.JANUARY, 20, 7, 50), "Завтрак", 500);
    public static final Meal userMeal5 = new Meal(USER_MEAL_ID + 5, LocalDateTime.of(2021, Month.JANUARY, 20, 13, 0), "Обед", 800);
    public static final Meal userMeal6 = new Meal(USER_MEAL_ID + 6, LocalDateTime.of(2021, Month.JANUARY, 20, 17, 0), "Полдник", 250);
    public static final Meal userMeal7 = new Meal(USER_MEAL_ID + 7, LocalDateTime.of(2021, Month.JANUARY, 20, 21, 20), "Ужин", 500);
    public static final Meal adminMeal = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2021, Month.JANUARY, 21, 7, 0), "Breakfast", 400);
    public static final Meal adminMeal1 = new Meal(ADMIN_MEAL_ID + 1, LocalDateTime.of(2021, Month.JANUARY, 21, 12, 0), "Brunch", 450);
    public static final Meal adminMeal2 = new Meal(ADMIN_MEAL_ID + 2, LocalDateTime.of(2021, Month.JANUARY, 21, 14, 30), "Lunch", 500);
    public static final Meal adminMeal3 = new Meal(ADMIN_MEAL_ID + 3, LocalDateTime.of(2021, Month.JANUARY, 21, 17, 50), "Dinner", 700);
    public static final Meal adminMeal4 = new Meal(ADMIN_MEAL_ID + 4, LocalDateTime.of(2021, Month.JANUARY, 20, 13, 0), "Breakfast", 800);
    public static final Meal adminMeal5 = new Meal(ADMIN_MEAL_ID + 5, LocalDateTime.of(2021, Month.JANUARY, 20, 17, 0), "Lunch", 600);
    public static final Meal adminMeal6 = new Meal(ADMIN_MEAL_ID + 6, LocalDateTime.of(2021, Month.JANUARY, 20, 21, 20), "Dinner", 500);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.JANUARY, 30, 7, 0), "testMeal", 222);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal);
        updated.setDateTime(LocalDateTime.of(2021, Month.JANUARY, 1, 11, 11));
        updated.setDescription("updatedMeal");
        updated.setCalories(111);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        Assertions.assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
