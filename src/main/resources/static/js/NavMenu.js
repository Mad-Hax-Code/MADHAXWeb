var openBtn = document.querySelector("#openBtn");
var closeBtn = document.querySelector("#closeBtn");
var navMenu = document.querySelector("#navMenu");

openBtn.addEventListener("click", toggleMenu);
closeBtn.addEventListener("click", closeMenu);

var menuOpen = false;
function toggleMenu() {
    if (!menuOpen) {
        openMenu();
    } else {
        closeMenu();
    }
}

function openMenu() {
    /* Might remove this:
    if (window.innerWidth > 1440){
        navMenu.style.width = "50%";
        navMenu.style.borderRight = "1px solid #000";
    } else {

    }
    */
    navMenu.style.width = "100%";
    menuOpen = true;
}

function closeMenu() {
    navMenu.style.width = "0";
    navMenu.style.borderRight = "none";
    menuOpen = false;
}