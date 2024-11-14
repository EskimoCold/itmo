function raiseErrorForUser(message){
    const errorTag = document.getElementById("error-message");
    errorTag.innerHTML = message;
    errorTag.style.display = 'block';

    setTimeout(() => {
        errorTag.innerHTML = "";
        errorTag.style.display = 'none';
    }, 3000);
}

function sendRequest(x, y, r) {
    drawPoints(x, y)

    const url = `/lab2-1.0-SNAPSHOT/controller`;

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            x: x,
            y: y,
            r: r
        })
    })
        .then(data=> {
            console.log(data)
            return data
        })
        .then(data => {
            window.location.href = "results.jsp"
        })
        .catch(error => {
            raiseErrorForUser(error);
        })
}

function validate() {
    const x = document.getElementById("x").value;
    const y = parseFloat(document.getElementById("y").value);
    const r = parseFloat(document.getElementById("r").value);

    console.log('data: ', x,y,r)

    if (isNaN(y) || y <= -3 || y >= 5) {
        raiseErrorForUser("Y must be between -3 and 5.");
    }

    else if (isNaN(r) || r <= 1 || r >= 4) {
        raiseErrorForUser("R must be between 1 and 4.");
    }

    else {
        drawPoints(x, y)
        sendRequest(x, y, r);
    }
}

function drawPoints(x, y) {
    console.log('DRAWING at SVG coordinates:', x, y);

    const svg = document.getElementById("svg-container");
    const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    circle.setAttribute("cx", x);
    circle.setAttribute("cy", y);
    circle.setAttribute("r", "5");
    circle.setAttribute("fill", "red");
    svg.appendChild(circle);
}

document.addEventListener("DOMContentLoaded", () => {
    const svg = document.getElementById("svg-container");

    svg.addEventListener("click", function (event) {
        const point = svg.createSVGPoint();
        point.x = event.clientX;
        point.y = event.clientY;

        const svgPoint = point.matrixTransform(svg.getScreenCTM().inverse());

        console.log('x, y (SVG coordinates): ', svgPoint.x, svgPoint.y);

        const r = parseFloat(document.getElementById("r").value);

        if (!isNaN(r)) {
            const scaleValue = 150 / (1.5 * r);

            let scaledX = (svgPoint.x - 150) / scaleValue;
            let scaledY = (-svgPoint.y + 150) / scaleValue;

            drawPoints(svgPoint.x, svgPoint.y);
            sendRequest(scaledX, scaledY, r);
        } else {
            raiseErrorForUser("R is not set!");
        }
    });

    document.getElementById("submit-results").onclick = validate;
});
