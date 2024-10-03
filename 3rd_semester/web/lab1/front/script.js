document.getElementById('pointForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const x = document.getElementById("x").value;
    const y = parseFloat(document.getElementById("y").value);
    const r = parseFloat(document.getElementById("r").value);

    if (isNaN(y) || y < -3 || y > 5) {
        alert("Y must be between -3 and 5.");
        return;
    }

    if (isNaN(r) || r < 1 || r > 4) {
        alert("R must be between 1 and 4.");
        return;
    }

    const formData = new FormData(this);
    var object = {};
    formData.forEach(function (value, key) {
        object[key] = value;
    });
    var jsonData = JSON.stringify(object);

    console.log(jsonData)

    fetch('/fcgi-bin/server.jar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: jsonData
    })
        .then(response => response.json())
        .then(data => {
            if (data.response) {
                alert(data.response)
            } else {
                drawPoints(data.x, data.y, data.r);

                const resultBody = document.getElementById("resultBody");
                const newRow = document.createElement("tr");

                newRow.innerHTML = `
                    <td>${data.x}</td>
                    <td>${data.y}</td>
                    <td>${data.r}</td>
                    <td>${data.in ? 'Inside' : 'Outside'}</td>
                    <td>${data.currentTime}</td>
                    <td>${data.execTime}</td>
                `;

                resultBody.appendChild(newRow);
            }
        })
        .catch(error => {
            alert(`Ошибка отправки данных: ${error}`);
        });
});


document.getElementById("r").addEventListener("change", drawGraph);

function drawGraph() {
    const r = document.getElementById("r").value;
    const canvas = document.getElementById("graphCanvas");
    const context = canvas.getContext("2d");

    context.clearRect(0, 0, canvas.width, canvas.height);

    context.fillStyle = "#f0f0f0";
    context.fillRect(0, 0, canvas.width, canvas.height);

    context.strokeStyle = "black";
    context.beginPath();
    context.moveTo(canvas.width / 2, 0);
    context.lineTo(canvas.width / 2, canvas.height);
    context.moveTo(0, canvas.height / 2);
    context.lineTo(canvas.width, canvas.height / 2);
    context.stroke();

    context.fillStyle = "blue";
    context.globalAlpha = 0.5;

    const scale = 80;

    // rectangle
    context.fillRect(canvas.width / 2 - scale * r / 2, canvas.height / 2 - scale * r, scale * r / 2, scale * r);

    // quarter circle
    context.beginPath();
    context.moveTo(canvas.width / 2, canvas.height / 2);
    context.arc(canvas.width / 2, canvas.height / 2, 2 * scale * r / 2, 0.5 * Math.PI, Math.PI, false);
    context.fill();

    // triangle
    context.beginPath();
    context.moveTo(canvas.width / 2, canvas.height / 2);
    context.lineTo(canvas.width / 2 + scale * r, canvas.height / 2);
    context.lineTo(canvas.width / 2, canvas.height / 2 - scale * r);
    context.fill();
}


function drawPoints(x, y, r) {
    const canvas = document.getElementById("graphCanvas");
    const context = canvas.getContext("2d");
    const scale = 80;

    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;
    const pointX = centerX + x * scale;
    const pointY = centerY - y * scale;

    context.fillStyle = "red";
    context.beginPath();
    context.arc(pointX, pointY, 3, 0, 2 * Math.PI);
    context.fill();
}
