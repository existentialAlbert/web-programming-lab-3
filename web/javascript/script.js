var X = true;
var R = true;
var radius;
var y = 0;
var offset = new Date().getTimezoneOffset();
var f = document.getElementById("main");
var warningBox = document.getElementById("warning_label");
canvas.addEventListener("click", handleCanvasClick);
ctx.clearRect(0, 0, canvas.width, canvas.height);
drawArea(radius);
drawAxis();

setTimeout(drawPointsFromTable, 0);

function validate() {
    let parameter = document.getElementById("main:y").value;
    //parameter = parameter.replace(",", ".");
    let str = "";
    console.log(parameter);
    Y = false;
    if (isNaN(parameter))
        str = "Y должен быть числом";
    else if (parameter.length === 0)
        str = "Введите число в интервале (-3;3)";
    else if (parameter > -3 && parameter < 3) {
        str = "Корректное значение!";
        Y = true;
        warningBox.style.color = "lightpink";

    } else
        str = "Y должен быть в диапозоне (-3;3)";
    if (!Y)
        warningBox.style.color = "red";
    warningBox.innerHTML = str;
    y = parameter;
    tryToBranch(f);
}

function tryToBranch(f) {
    if (Y && X && R)
        document.getElementById("main:submit").removeAttribute("disabled");
    else
        document.getElementById("main:submit").setAttribute("disabled", "true");
}

function sub() {
    return (Y && X && R);
}

function handleCanvasClick(event) {
    let x = (event.offsetX - canvas.width / 2) / 25;
    let y = (event.offsetY - canvas.height / 2) / (-25);
    if (x >= -4 && x <= 4 && y >= -5 && y <= 3) {
        document.getElementById("canvas-form:CanvasX").value = x;
        document.getElementById("canvas-form:CanvasY").value = y;
        a = '#{data.username}';
        if (a !== "")
            checkCanvas();
    }
}