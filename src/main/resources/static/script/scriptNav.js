const nav = document.querySelector(".main-header");
let lastScrollY = window.scrollY; //0 esta na pos inicial

window.addEventListener("scroll", () => {
    if (lastScrollY < window.scrollY){
        nav.classList.add("nav--hidden");
        console.log(window.scrollY);
    }else{
        nav.classList.remove("nav--hidden");
        console.log(window.scrollY);
    }

    lastScrollY = window.scrollY;
})