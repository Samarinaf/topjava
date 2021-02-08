package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {

    List<Meal> findAll();

    void save(Meal meal);

    void delete(Integer id);

    Meal find(Integer id);
}
