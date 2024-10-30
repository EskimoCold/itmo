<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
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

<main>
    <table>
        <tr>
            <td id="left-panel">
                <form id="pointForm" method="POST">
                    <div class="input-group">
                        <label for="x">X:</label>
                        <select id="x" name="x">
                            <option value="-2">-2</option>
                            <option value="-1.5">-1.5</option>
                            <option value="-1">-1</option>
                            <option value="-0.5">-0.5</option>
                            <option value="0">0</option>
                            <option value="0.5">0.5</option>
                            <option value="1">1</option>
                            <option value="1.5">1.5</option>
                            <option value="2">2</option>
                        </select>
                    </div>
                    <div class="input-group">
                        <label for="y">Y Coordinate (-3; 5):</label>
                        <input type="number" id="y" name="y" min="-3" max="5" step="0.1">
                    </div>
                    <div class="input-group">
                        <label for="r">R(1; 4):</label>
                        <input type="number" id="r" name="r" min="1" max="4" step="0.1">
                    </div>
                    <button type="submit" id="submit-results">Check Point</button>
                </form>

                <div id="error-message"></div>
            </td>
            <td id="right-panel">
                <div id="plotArea">
                    <svg height="300" width="300" xmlns="http://www.w3.org/2000/svg" id="svg-container" class="no-select">
                        <!-- Axes -->
                        <line stroke="black" x1="0" x2="300" y1="150" y2="150"></line> <!-- X-axis -->
                        <line stroke="black" x1="150" x2="150" y1="0" y2="300"></line> <!-- Y-axis -->

                        <!-- Arrows on the axes -->
                        <polygon fill="black" points="150,0 144,15 156,15"></polygon> <!-- Y-axis arrow -->
                        <polygon fill="black" points="300,150 285,144 285,156"></polygon> <!-- X-axis arrow -->

                        <!-- Labels -->
                        <text x="160" y="15">Y</text> <!-- Y-axis label -->
                        <text x="285" y="140">X</text> <!-- X-axis label -->

                        <!-- Tick marks -->
                        <line stroke="black" x1="50" x2="50" y1="145" y2="155"></line> <!-- -R tick -->
                        <line stroke="black" x1="100" x2="100" y1="145" y2="155"></line> <!-- -R/2 tick -->
                        <line stroke="black" x1="200" x2="200" y1="145" y2="155"></line> <!-- R/2 tick -->
                        <line stroke="black" x1="250" x2="250" y1="145" y2="155"></line> <!-- R tick -->
                        <line stroke="black" x1="145" x2="155" y1="50" y2="50"></line> <!-- R tick on Y-axis -->
                        <line stroke="black" x1="145" x2="155" y1="100" y2="100"></line> <!-- R/2 tick on Y-axis -->
                        <line stroke="black" x1="145" x2="155" y1="200" y2="200"></line> <!-- -R/2 tick on Y-axis -->
                        <line stroke="black" x1="145" x2="155" y1="250" y2="250"></line> <!-- -R tick on Y-axis -->

                        <!-- Tick labels -->
                        <text x="195" y="140">R/2</text> <!-- X-axis R/2 label -->
                        <text x="245" y="140">R</text> <!-- X-axis R label -->
                        <text x="90" y="140">-R/2</text> <!-- X-axis -R/2 label -->
                        <text x="40" y="140">-R</text> <!-- X-axis -R label -->
                        <text x="160" y="105">R/2</text> <!-- Y-axis R/2 label -->
                        <text x="160" y="55">R</text> <!-- Y-axis R label -->
                        <text x="160" y="205">-R/2</text> <!-- Y-axis -R/2 label -->
                        <text x="160" y="255">-R</text> <!-- Y-axis -R label -->

                        <!-- Shapes -->
                        <!-- Right triangle in the top-right quadrant -->
                        <polygon points="150,150 250,150 150,50" fill="blue" fill-opacity="0.6"></polygon>

                        <!-- Quarter circle in the bottom-left quadrant -->
                        <path d="M150,250 A100,100 0 0,1 50,150 L150,150" fill="blue" fill-opacity="0.6"></path>

                        <!-- Rectangle in the top-left quadrant -->
                        <polygon points="100,50 150,50 150,150 100,150" fill="blue" fill-opacity="0.6"></polygon>
                    </svg>
                </div>
            </td>
        </tr>
    </table>
    <table id="resultTable">
        <thead>
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Result</th>
            <th>Current Time</th>
            <th>Script Execution Time</th>
        </tr>
        </thead>
        <tbody id="resultBody">
        <c:forEach var="row"
                   items="${resultTable.getHits()}">
            <tr>
                <td>${row.getX()}</td>
                <td>${row.getY()}</td>
                <td>${row.getR()}</td>
                <td>${row.getHit()}</td>
                <td>${row.getTimestamp()}</td>
                <td>${row.getExecutionTime()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>