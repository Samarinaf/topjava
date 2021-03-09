package ru.javawebinar.topjava.service.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;
import ru.javawebinar.topjava.web.Profiles;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.UserTestData.getNew;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void getWithMeals() {
        User actual = service.getWithMeals(ADMIN_ID);
        List<Meal> meals = actual.getMeals();
        USER_MATCHER.assertMatch(actual, admin);
        MEAL_MATCHER.assertMatch(meals, adminMeal2, adminMeal1);
    }

    @Test
    public void getWithEmptyMeals() {
        User newUser = service.create(getNew());
        User userWithMeals = service.getWithMeals(newUser.id());
        USER_MATCHER.assertMatch(userWithMeals, newUser);
        Assert.assertTrue(userWithMeals.getMeals().isEmpty());
    }
}
