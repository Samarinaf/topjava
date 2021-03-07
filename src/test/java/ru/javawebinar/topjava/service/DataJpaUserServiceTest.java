package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.Profiles;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest{

    @Test
    public void getWithMeals() {
        User actual = service.getWithMeals(ADMIN_ID);
        List<Meal> meals = actual.getMeals();
        USER_MATCHER.assertMatch(actual, admin);
        MEAL_MATCHER.assertMatch(meals, adminMeal2, adminMeal1);
    }
}
