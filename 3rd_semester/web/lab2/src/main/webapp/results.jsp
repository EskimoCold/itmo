<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LabWork</title>
    <link rel="stylesheet" href="style.css">
    <script defer src="scripts/script.js"></script>
</head>
<body>
<header>
    <div id="student-info">
        <h1>Суджян Эдуард Эдуардович | Группа: P3221 | Вариант: 27356</h1>
    </div>
</header>

<div id="result-div" class="enclosing">
    <table id="resultTable">
        <thead>
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>RESULT</th>
            <th>TIME</th>
            <th>EXECUTION TIME</th>
        </tr>
        </thead>
        <tbody id="resultBody">
        <tr>
            <td>${resultRow.getX()}</td>
            <td>${resultRow.getY()}</td>
            <td>${resultRow.getR()}</td>
            <td>${resultRow.getHit()}</td>
            <td>${resultRow.getTimestamp()}</td>
            <td>${resultRow.getExecutionTime()}</td>
        </tr>
        </tbody>
    </table>
</div>
<div id="button-enclosing" class="enclosing">
    <input type="button" id="return-button" value="Вернуться"/>
</div>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const returnButton = document.getElementById("return-button");
        returnButton.onclick = () => {
            window.location.href = "index.jsp";
        }
    })
</script>
</body>
</html>