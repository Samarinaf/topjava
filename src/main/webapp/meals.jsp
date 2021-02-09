<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="meals?action=add">Add meal</a>
<style type="text/css">
    TABLE {
        border-collapse: collapse;
    }
    TH {
        text-align: center;
    }
    TH, TD {
        border: 1px solid black;
        padding: 4px;
    }
</style>
<br><br>
    <table>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan=2>Action</th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <tr style="color:${meal.excess ? '#FF0000' : '#008000'}">
                <td>${meal.dateTime.format(formatter)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>
                    <a href="meals?action=edit&id=${meal.id}">Update</a>
                </td>
                <td>
                    <a href="meals?action=delete&id=${meal.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
