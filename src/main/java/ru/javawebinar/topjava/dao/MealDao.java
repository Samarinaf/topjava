package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {

    List<Meal> findAll();

    Meal save(Meal meal);

    boolean delete(int id);

    Meal find(int id);
}
