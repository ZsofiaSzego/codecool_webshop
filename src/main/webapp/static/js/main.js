main();

function main(){
filterProducts();

}

function filterProducts(){
    let btn = document.querySelector('#filter');
    btn.addEventListener('click', function (){
        showFiltered();
    })
}

function getSelectedSupplier(){
    return document.querySelector("#supplier").value;
}

function getSelectedCategory(){
    return document.querySelector("#category").value;
}
function showFiltered(){
    let selectedSupplier = getSelectedSupplier();
    let selectedCategory = getSelectedCategory();
    let cards = document.querySelectorAll('.card');
    for (let card of cards) {
            let supplierId = card.getAttribute('data-supplier');
            let categoryId = card.getAttribute('data-category');
            if (supplierId === selectedSupplier || 0 == selectedSupplier) {
                console.log("s: " + supplierId + " " + selectedSupplier);
                if (selectedCategory == 0 || categoryId === selectedCategory){
                    console.log(categoryId + " " + selectedCategory);
                }
                else card.style.display = "none";
                card.style.visibility = "none";
            }
            else {
                card.style.display = "none";
                card.style.visibility = "none";
            }
    }
}

function showCart(){
    let cart = document.querySelector('#cart_item_container')
    console.log(cart.style.visibility);
    console.log(cart.style.display);
    if (cart.style.visibility === "visible"){
        cart.style.display = "none";
        cart.style.visibility = "hidden";
    } else {
        cart.style.display = "block";
        cart.style.visibility = "visible";
    }
}







