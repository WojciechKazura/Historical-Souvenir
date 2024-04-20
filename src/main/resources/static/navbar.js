let hamburgerButton = document.getElementById("hamburger");
let list = document.getElementById("list");

let isListUnfolded = false;

hamburgerButton.addEventListener("click", () => {
  if (isListUnfolded) {
    list.classList.remove("list-unfolded");
    isListUnfolded = false;
  } else {
    list.classList.add("list-unfolded");
    isListUnfolded = true;
  }
});
