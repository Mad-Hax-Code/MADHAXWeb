const mainBlockLeft = document.querySelectorAll(".mainBlockLeft");
const mainBlockRight = document.querySelectorAll(".mainBlockRight");
const padLeft = document.querySelectorAll(".padLeft");
const padRight = document.querySelectorAll(".padRight");

window.addEventListener("load", pageLoadLayoutDecision);

window.addEventListener("resize", changeLayout);

function changeLayout() {
    if (window.innerWidth < 1000) {
        // change mainBlockLeft instances
        for (var i = 0; i < mainBlockLeft.length; i++) {
            mainBlockLeft[i].style.cssFloat = "none";
            mainBlockLeft[i].style.display = "block";
            mainBlockLeft[i].style.width = "100%";
        }
        // change mainBlockRight instances
        for (var i = 0; i < mainBlockRight.length; i++) {
            mainBlockRight[i].style.cssFloat = "none";
            mainBlockRight[i].style.display = "block";
            mainBlockRight[i].style.width = "100%";
        }
        for (var i = 0; i < padLeft.length; i++) {
            padLeft[i].style.padding = "30px 0 0 0";
        }
        for (var i = 0; i < padRight.length; i++) {
            padRight[i].style.padding = "30px 0 0 0";
        }
    } else {
        // change mainBlockLeft instances
        for (var i = 0; i < mainBlockLeft.length; i++) {
            mainBlockLeft[i].style.cssFloat = "left";
            mainBlockLeft[i].style.display = "inline";
            mainBlockLeft[i].style.width = "50%";
        }
        // change mainBlockRight instances
        for (var i = 0; i < mainBlockRight.length; i++) {
            mainBlockRight[i].style.cssFloat = "right";
            mainBlockRight[i].style.display = "inline";
            mainBlockRight[i].style.width = "50%";
        }
        // change padLeft instances
        for (var i = 0; i < padLeft.length; i++) {
            padLeft[i].style.padding = "30px 0 0 30px";
        }
        // change padRight instances
        for (var i = 0; i < padRight.length; i++) {
            padRight[i].style.padding = "30px 30px 0 0";
        }
    }
}

function pageLoadLayoutDecision() {
    if (window.innerWidth < 1000) {
        changeLayout();
    }
}