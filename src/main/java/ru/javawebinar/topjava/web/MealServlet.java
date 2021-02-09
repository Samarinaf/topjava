package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.InMemoryMealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static final int CALORIES_PER_DAY = 2000;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private MealDao mealDao;

    @Override
    public void init() throws ServletException {
        this.mealDao = new InMemoryMealDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "meals";

        switch (action) {
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                boolean delete = mealDao.delete(id);
                log.debug(delete ? "delete meal with id = {}" : "meal with id = {} not found", id);
                response.sendRedirect("meals");
                break;
            case "edit":
                log.debug("redirect to editMeal.jsp");
                int mealId = Integer.parseInt(request.getParameter("id"));
                Meal meal = mealDao.find(mealId);
                request.setAttribute("meal", meal);
                request.setAttribute("title", "Edit meal");
                request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
                break;
            case "meals":
                log.debug("filtering and redirect to meals.jsp");
                List<Meal> meals = mealDao.findAll();
                List<MealTo> mealsTo = MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
                request.setAttribute("meals", mealsTo);
                request.setAttribute("formatter", FORMATTER);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "add":
                log.debug("redirect to editMeal.jsp");
                Meal newMeal = new Meal();
                request.setAttribute("meal", newMeal);
                request.setAttribute("title", "Add meal");
                request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
                break;
            default:
                log.debug("unknown action, redirect to meals");
                response.sendRedirect("meals");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = new Meal(id, dateTime, description, calories);
        log.debug(id == 0 ? "add new meal" : "update meal with id {}", id);
        mealDao.save(meal);

        response.sendRedirect("meals");
    }
}
