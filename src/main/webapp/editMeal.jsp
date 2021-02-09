<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Edit Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>${meal.id == null ? "Add meal" : "Edit meal"}</h2>
<br>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        <p>
            <label for="date">
                DateTime: <input type="datetime-local" id="date" name="dateTime" value="${meal.dateTime}" required>
            </label>
        </p>
        <p>
            <label for="descr">
                Description: <input type="text" id="descr" name="description" value="${meal.description}" required><br>
            </label>
        </p>
        <p>
            <label for="cal">
                Calories: <input type="number" id="cal" name="calories" value="${meal.calories}" required><br>
            </label>
        </p>
        <input type="submit" value="Save">
        <input type="button" onclick="window.history.back()" value="Cancel">
    </form>
<br>
</body>
</html>
