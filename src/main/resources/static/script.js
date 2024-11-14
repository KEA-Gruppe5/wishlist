document.getElementById("fetchProductBtn").addEventListener("click", function() {
    const url = document.getElementById("sUrl").value.trim();

    if (url) {
        $.ajax({
            url: '/scrape',
            type: 'POST',
            data: { url: url },
            success: function(response) {
                document.getElementById("name").value = response.name || "";
                document.getElementById("description").value = response.description || "";
                document.getElementById("imgUrl").value = response.imgUrl || "";
                document.getElementById("price").value = response.price || "";
                document.getElementById("url").value = url || "";
            },
            error: function() {
                alert("Error fetching product data.");
            }
        });
    } else {
        alert("Please enter a valid URL.");
    }
});