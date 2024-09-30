document.addEventListener("DOMContentLoaded", function() {
    const items = document.querySelectorAll(".item, .battle");
    const quantityInput = document.getElementById("quantity");
    const buyNowButton = document.querySelector(".buynow");
    const paymentMethods = document.querySelectorAll(".methode");
    let selectedItem = null, selectedPrice = 0, selectedPaymentMethod = "";

    // Set default quantity value
    quantityInput.value = 1;

    // Function to handle item selection
    function selectItem(item) {
        items.forEach(i => i.classList.remove("selected"));
        item.classList.add("selected");

        selectedItem = item.querySelector(".item-info p").innerText;
        selectedPrice = parseInt(item.getAttribute("data-price"), 10);

        document.getElementById("hidden-item").value = selectedItem;
        quantityInput.value = 1;
        calculatePayment();
    }

    // Function to calculate total payment
    function calculatePayment() {
        const quantity = parseInt(quantityInput.value, 10);
        const totalPayment = quantity * selectedPrice;

        const paymentText = isNaN(totalPayment) ? "Rp. 0" : 
            new Intl.NumberFormat('id-ID', {
                style: 'currency',
                currency: 'IDR',
                minimumFractionDigits: 0
            }).format(totalPayment);

        // Update the total payment display
        paymentMethods.forEach(method => {
            method.querySelector("p").textContent = paymentText;
        });

        // Also update the popup amount if the popup is already opened
        if (document.getElementById("popup-modal").style.display === "block") {
            updatePurchaseDetails(selectedItem, totalPayment);
        }
    }

    function selectPaymentMethod(element) {
        const method = element.querySelector("img").alt; // Get the alt text (payment method)
        document.getElementById('popup-method-img').src = element.querySelector("img").src; // Set image in popup
        document.getElementById('selected-payment-method').innerText = method; // Display method name
        document.getElementById("hidden-method-payment").value = method; // Store in hidden field
    }

    function updatePurchaseDetails(itemName, itemPrice) {
        document.getElementById('popup-item').innerText = itemName;
        document.getElementById('hidden-item').value = itemName;
        document.getElementById('popup-amount').innerText = `Rp. ${itemPrice.toLocaleString('id-ID')}`; // Use formatted total
        document.getElementById('hidden-price').value = itemPrice; // Store total price
        const quantity = parseInt(quantityInput.value, 10);
        const totalPrice = itemPrice * quantity;
        document.getElementById('hidden-price').value = totalPrice; // Update hidden price
    }

    // Validate the input quantity
    function validateInput() {
        let value = parseInt(quantityInput.value, 10);
        if (isNaN(value) || value < 1) {
            quantityInput.value = 1;
        } else if (value > 100) {
            quantityInput.value = 100;
        }
        calculatePayment();
    }

    // Add event listener for quantity input
    quantityInput.addEventListener("input", validateInput);

    // Add click event listeners to all items
    items.forEach(item => {
        item.addEventListener("click", function(event) {
            event.preventDefault();
            selectItem(item);
        });
    });

    // Handle payment method selection
    paymentMethods.forEach(method => {
        method.addEventListener("click", function() {
            paymentMethods.forEach(m => m.classList.remove("selected"));
            method.classList.add("selected");
            selectedPaymentMethod = method.querySelector("img").alt; // Store selected payment method
            document.getElementById("hidden-method-payment").value = selectedPaymentMethod; // Update hidden input
            selectPaymentMethod(method); // Update the popup display
        });
    });

    // Handle Buy Now button click
    if (buyNowButton) {
        buyNowButton.addEventListener("click", function(event) {
            event.preventDefault();

            // Collect necessary values
            const UID = document.getElementById("riotIdInput").value;
            const quantity = parseInt(quantityInput.value, 10);
            const price = selectedPrice * quantity;
            const methodPayment = document.getElementById("hidden-method-payment").value;

            // Validation checks
            if (!UID) {
                alert("Silakan masukkan UID Anda.");
                document.getElementById("riotIdInput").classList.add("error");
                document.getElementById("riotIdInput").scrollIntoView({ behavior: 'smooth', block: 'center' });
                return;
            }

            if (!selectedItem) {
                alert("Pilih Item");
                document.querySelector(".items-container").scrollIntoView({ behavior: 'smooth', block: 'center' });
                return;
            }

            if (!selectedPaymentMethod) {
                alert("Pilih Metode Pembayaran");
                document.querySelector(".items-container").scrollIntoView({ behavior: 'smooth', block: 'center' });
                return;
            }

            // Set popup values
            document.getElementById("popup-riot-id").textContent = UID;
            document.getElementById("popup-item").textContent = selectedItem;
            document.getElementById("popup-amount").textContent = `Rp. ${price.toLocaleString('id-ID')}`;
            document.getElementById("hidden-UID").value = UID;
            document.getElementById("hidden-quantity").value = quantity;
            document.getElementById("hidden-price").value = price;
            document.getElementById("hidden-method-payment").value = methodPayment;

            openPopup();
        });
    }

    function openPopup() {
        document.getElementById("popup-modal").style.display = "block";
    }

    function closePopup() {
        document.getElementById("popup-modal").style.display = "none";
    }

    // Close popup when clicking outside the modal
    window.onclick = function(event) {
        const modal = document.getElementById("popup-modal");
        if (event.target === modal) {
            closePopup();
        }
    };

    // Close button for the modal
    document.querySelector(".close").addEventListener("click", closePopup);
});


/*---------------------------------*/
$(document).ready(function() {
    $(".battle").hide();
    $("#pass").hide();
    
    $(document).on('click', '[data-category="starlight"]', function(){
        $('.item').hide();
        $("#ini").hide();
        $('.battle').show();
        $("#pass").show();
    });

    $(document).on('click', '[data-category="diamonds"]', function(){
        $('.item').show();
        $("#pass").hide();
        $("#ini").show();
        $('.battle').hide();
    });
});
