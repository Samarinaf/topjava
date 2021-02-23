package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app-main.xml",
        "classpath:spring/spring-app-jdbc.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal created = service.create(getNew(), UserTestData.USER_ID);
        Integer createdId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(createdId);
        assertMatch(created, newMeal);
        assertMatch(service.get(createdId, UserTestData.USER_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        Assert.assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, userMeal.getDateTime(), "Duplicate", 100), UserTestData.USER_ID));
    }

    @Test
    public void get() {
        Meal meal = service.get(USER_MEAL_ID, UserTestData.USER_ID);
        assertMatch(meal, userMeal);
    }

    @Test
    public void getNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> service.get(UserTestData.NOT_FOUND, UserTestData.USER_ID));
    }

    @Test
    public void getByAnotherUser() {
        Assert.assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_ID, UserTestData.ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL_ID, UserTestData.USER_ID);
        Assert.assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_ID, UserTestData.USER_ID));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> service.delete(UserTestData.NOT_FOUND, UserTestData.USER_ID));
    }

    @Test
    public void deleteByAnotherUser() {
        Assert.assertThrows(NotFoundException.class, () -> service.delete(USER_MEAL_ID, UserTestData.ADMIN_ID));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, UserTestData.USER_ID);
        assertMatch(service.get(USER_MEAL_ID, UserTestData.USER_ID), getUpdated());
    }

    @Test
    public void updateByAnotherUser() {
        Assert.assertThrows(NotFoundException.class, () -> service.update(getUpdated(), UserTestData.ADMIN_ID));
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(UserTestData.USER_ID);
        assertMatch(all, userMeal, userMeal3, userMeal2, userMeal1, userMeal7, userMeal6, userMeal5, userMeal4);
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> adminMeals = service.getBetweenInclusive(LocalDate.of(2021, Month.JANUARY, 20),
                LocalDate.of(2021, Month.JANUARY, 20), UserTestData.ADMIN_ID);
        assertMatch(adminMeals, adminMeal6, adminMeal5, adminMeal4);

        List<Meal> userMeals = service.getBetweenInclusive(LocalDate.of(2021, Month.JANUARY, 10),
                LocalDate.of(2021, Month.JANUARY, 11), UserTestData.USER_ID);
        Assert.assertTrue(userMeals.isEmpty());
    }

    @Test
    public void getBetweenInclusiveAtMidnight() {
        List<Meal> meals = service.getBetweenInclusive(LocalDate.of(2021, Month.JANUARY, 22),
                LocalDate.of(2021, Month.JANUARY, 22), UserTestData.USER_ID);
        assertMatch(meals, userMeal);

        List<Meal> userMeals = service.getBetweenInclusive(LocalDate.of(2021, Month.JANUARY, 21),
                LocalDate.of(2021, Month.JANUARY, 21), UserTestData.USER_ID);
        assertMatch(userMeals, userMeal3, userMeal2, userMeal1);
    }

    @Test
    public void getBetweenInclusiveWithNullDates() {
        List<Meal> meals = service.getBetweenInclusive(null, null, UserTestData.ADMIN_ID);
        assertMatch(meals, adminMeal3, adminMeal2, adminMeal1, adminMeal, adminMeal6, adminMeal5, adminMeal4);
    }
}
