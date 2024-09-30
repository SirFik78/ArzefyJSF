// Logic for sign out
document.addEventListener("DOMContentLoaded", function() {
    // Sign out button
    document.getElementById("signOutBtn").addEventListener("click", function() {
        showSignOutPopup(); // Call the popup function instead of redirecting directly
    });

    // Logic for adding an item
    document.getElementById("addItemBtn").addEventListener("click", function() {
        var itemName = document.getElementById("itemName").value;
        var itemPrice = document.getElementById("itemPrice").value;

        if (itemName && itemPrice) {
            // Simulate a backend call to add an item (replace with actual AJAX call)
            alert("Adding item: " + itemName + " with price: " + itemPrice);
            // Here you would send the data to your server (using AJAX or Fetch API)

            // Clear input fields after adding
            document.getElementById("itemName").value = "";
            document.getElementById("itemPrice").value = "";
            // Optionally, refresh the item list or table here
        } else {
            alert("Please fill out all fields.");
        }
    });

    // Logic for updating an item
    document.getElementById("updateItemBtn").addEventListener("click", function() {
        var itemId = document.getElementById("itemId").value;
        var itemName = document.getElementById("updateItemName").value;
        var itemPrice = document.getElementById("updateItemPrice").value;

        if (itemId && itemName && itemPrice) {
            // Simulate a backend call to update an item (replace with actual AJAX call)
            alert("Updating item ID: " + itemId + " to name: " + itemName + " and price: " + itemPrice);
            // Here you would send the updated data to your server (using AJAX or Fetch API)

            // Clear input fields after updating
            document.getElementById("itemId").value = "";
            document.getElementById("updateItemName").value = "";
            document.getElementById("updateItemPrice").value = "";
            // Optionally, refresh the item list or table here
        } else {
            alert("Please fill out all fields.");
        }
    });
});

// Logic for deleting an item
function deleteItem(itemId) {
    if (confirm("Are you sure you want to delete this item?")) {
        // Simulate a backend call to delete an item (replace with actual AJAX call)
        alert("Deleting item ID: " + itemId);
        // Here you would send a request to your server to delete the item

        // Optionally, refresh the item list or table here
    }
}

// Logic for updating an item (when clicking on the "Update" button in the table)
function updateItem(itemId) {
    // Simulate fetching item details (replace with actual AJAX call to get item data)
    var currentItem = {
        idItem: itemId,
        namaItem: "Sample Item Name", // Fetch actual item name from server
        price: 1000 // Fetch actual item price from server
    };
    
    // Populate the update fields with fetched values
    document.getElementById("itemId").value = currentItem.idItem;
    document.getElementById("updateItemName").value = currentItem.namaItem; // Pre-fill item name
    document.getElementById("updateItemPrice").value = currentItem.price; // Pre-fill item price

    alert("Preparing to update item with ID: " + itemId);
}

// Function to show sections based on navbar clicks
function showSection(section) {
    // Hide all sections
    document.getElementById('manageItems').style.display = 'none';
    document.getElementById('riwayatPembelian').style.display = 'none';
    document.getElementById('updateItemContainer').style.display = 'none'; // Add this line if needed

    // Show the selected section
    document.getElementById(section).style.display = 'block';
}

// Show Manage Items by default
window.onload = function() {
    showSection('manageItems');
};

// Function to show sign-out confirmation popup
function showSignOutPopup() {
    document.getElementById('signOutPopup').style.display = 'block';
}

// Function to close the sign-out confirmation popup
function losePopup() {
    document.getElementById('signOutPopup').style.display = 'none';
}

// Logic for handling sign-out
function handleSignOut() {
    // Redirect to the login page after confirming sign out
    alert("Signing out...");
    window.location.href = "index.xhtml";
}

function showUpdateItemPopup() {
    document.getElementById('updateItemContainer').style.display = 'block';
    document.getElementById('updateForm:itemId').value = id;
    document.getElementById('updateForm:updateItemName').value = name;
    document.getElementById('updateForm:updateItemPrice').value = price;
}

function hideUpdateItem() {
    document.getElementById('updateItemContainer').style.display = 'none';
}
