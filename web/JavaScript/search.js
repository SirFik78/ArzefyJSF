function penPopup() {
    document.getElementById("signOutPopup").style.display = "block";
}

function losePopup() {
    document.getElementById("signOutPopup").style.display = "none";
}

window.onclick = function(event) {
    var popup = document.getElementById("signOutPopup");
    if (event.target == popup) {
        popup.style.display = "none";
    }
};


/*------------------------------*/

$(document).ready(function() {
    $("#searchResults").hide();

    $("#searchInput").on("input", function() {
        const query = $(this).val().trim();
        if (query.length > 0) {
            searchGames(query);
        } else {
            $("#searchResults").hide();
        }
    });
});

function searchGames(query) {
    $.ajax({
        url: 'SearchServlet',
        type: 'GET',
        data: { query: query },
        success: function(response) {
            showSearchResults(response);
        },
        error: function() {
            console.error('Error fetching search results');
        }
    });
}

function showSearchResults(response) {
    const searchResults = $("#searchResults");
    searchResults.empty();

    if (response.length === 0) {
        searchResults.hide();
        return;
    }

    const games = response.split(';');
    games.forEach(game => {
        const [name, imageUrl, url] = game.split(',');

        const gameElement = $('<div class="search-result"></div>');
        
        const image = $('<img>').attr('src', imageUrl);
        const nameElement = $('<p></p>').text(name);
        
        gameElement.append(image);
        gameElement.append(nameElement);

        gameElement.on('click', function() {
            window.location.href = url; // Navigasi ke halaman yang benar
        });

        searchResults.append(gameElement);
    });

    searchResults.show();
}



    function openDeletePopup() {
        document.getElementById("deleteAccountPopup").style.display = "block";
    }

    function closePopup() {
        document.getElementById("deleteAccountPopup").style.display = "none";
    }

    
    /*-----------Untuk Notif------------*/
    
        function openSuccessModal() {
        document.getElementById('success-modal').style.display = 'block';
    }

    // Function to close success modal
    function closeSuccessModal() {
        document.getElementById('success-modal').style.display = 'none';
    }
    
    