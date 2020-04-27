var i = 25;

function drawAxis() {
    let h = canvas.height;
    let w = canvas.width;
    ctx.strokeStyle = "black";
    ctx.lineWidth = 2;
    ctx.beginPath();
    ctx.moveTo(w / 2, h);
    ctx.lineTo(w / 2, 0);
    ctx.lineTo(w / 2 + 3, 7);
    ctx.moveTo(w / 2, 0);
    ctx.lineTo(w / 2 - 3, 7);
    drawDigitsX(ctx, i, w, h);
    ctx.stroke();
    ctx.beginPath();
    ctx.moveTo(0, h / 2);
    ctx.lineTo(w, h / 2);
    ctx.lineTo(w - 7, h / 2 + 3);
    ctx.moveTo(w, h / 2);
    ctx.lineTo(w - 7, h / 2 - 3);
    drawDigitsY(ctx, i, w, h);
    ctx.stroke();
    ctx.strokeStyle = "grey";
    ctx.lineWidth = 1;
    ctx.moveTo(w / 2 - 4 * i, h / 2 - 3 * i);
    ctx.lineTo(w / 2 + 4 * i, h / 2 - 3 * i);
    ctx.lineTo(w / 2 + 4 * i, h / 2 + 3 * i);
    ctx.lineTo(w / 2 - 4 * i, h / 2 + 3 * i);
    ctx.lineTo(w / 2 - 4 * i, h / 2 - 3 * i);
    ctx.stroke();
}

function drawDigitsX(ctx, i, w, h) {
    let t = w / 2;
    for (let j = 0; j < 5; j++) {
        t += i;
        ctx.moveTo(t, h / 2 + 3);
        ctx.lineTo(t, h / 2 - 3)
    }
    t = w / 2;
    for (let j = 0; j < 5; j++) {
        t -= i;
        ctx.moveTo(t, h / 2 + 3);
        ctx.lineTo(t, h / 2 - 3)
    }
}

function drawDigitsY(ctx, i, w, h) {
    let t = h / 2;
    for (let j = 0; j < 5; j++) {
        t += i;
        ctx.moveTo(w / 2 + 3, t);
        ctx.lineTo(w / 2 - 3, t);
    }
    t = h / 2;
    for (let j = 0; j < 5; j++) {
        t -= i;
        ctx.moveTo(w / 2 + 3, t);
        ctx.lineTo(w / 2 - 3, t);
    }
}

function drawArea(r) {
    let h = canvas.height;
    let w = canvas.width;
    ctx.strokeStyle = "#ffd5ec";
    ctx.fillStyle = "#ffd5ec";
    ctx.beginPath();
    i = 25;
    ctx.arc(w / 2, h / 2, r * i, 3 / 2 * Math.PI, Math.PI * 2);
    ctx.lineTo(w / 2, h / 2);
    ctx.lineTo(w / 2, h / 2 - r * i);
    ctx.lineTo(w / 2 - r * i, h / 2);
    ctx.lineTo(w / 2 - r * i / 2, h / 2);
    ctx.lineTo(w / 2 - r * i / 2, h / 2 + r * i);
    ctx.lineTo(w / 2, h / 2 + r * i);
    ctx.lineTo(w / 2, h / 2);
    ctx.fill();
}

function drawPoint(x, y, color) {
    ctx.strokeStyle = "000000";
    ctx.beginPath();
    ctx.fillStyle = color;
    ctx.arc(canvas.width / 2 + x * 25, canvas.height / 2 - y * 25, 2, 0, Math.PI * 2, true);
    ctx.fill();
    ctx.stroke();
}

function clearCanvas() {
    canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
}

function drawPointsFromTable() {
    let table = document.getElementById("result-table").getElementsByTagName("tbody")[0];
    if (table.children[0].children[0].innerText !== "")
        for (let row of table.children) {
            let x = Number(row.children[0].innerText);
            let y = Number(row.children[1].innerText);
            drawPoint(x, y, isPointInArea(x, y, Number(radius)) ? "lime" : "red");
        }
}

function isPointInArea(x, y, r) {
    if (x > 0 && y < 0)
        return false;
    else if (x <= 0 && y >= 0)
        return y <= x + r;
    else
        return ((x >= 0 && y >= 0 && x * x + y * y <= r * r) || (x <= 0 && y <= 0 && x >= -r / 2 && y >= -r));
}

function drawResize() {
    table = document.getElementById("table-form:result-table").childNodes[3];
    counter = 0;
    drawStep();
}

function drawStep() {
    console.log(counter);
    if (counter < table.children.length) {
        let row = table.children[counter];
        if (!row.children[0].innerText) return;
        let x = Number(row.children[0].innerText);
        let y = Number(row.children[1].innerText);
        document.getElementById("CanvasX").value = x;
        document.getElementById("CanvasY").value = y;
        counter++;
    }
}
