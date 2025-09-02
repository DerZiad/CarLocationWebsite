voitures = []

function showReservationModal(id) {
    console.log("Fetching details for car ID:", id);
    fetch("/api/cars/" + id, {
        method: "GET",
        headers: { "Accept": "application/json" }
    })
    .then(response => response.json())
    .then(car => {
        console.log(car)
        document.getElementById('carImageModal').src = "data:image/jpeg;base64," + car.base64Image;
        document.getElementById('brandModal').innerHTML = car.brand;
        document.getElementById('categoryModal').innerHTML = car.category;
        document.getElementById('priceModal').innerHTML = car.price;
        document.getElementById('carId').value = id;
        if (car.year) {
            document.getElementById('yearModal').innerHTML = car.year;
        }
    })
    .catch(() => {
        // Optionally show an error message
    });
}

// Load cars on page load
window.addEventListener('DOMContentLoaded', function() {
    fetch("/api/cars/", {
        method: "GET",
        headers: { "Accept": "application/json" }
    })
    .then(response => response.json())
    .then(response => {
        voitures = response;
    });

    var reserveBtn = document.getElementById('reserveButton');
    if (reserveBtn) {
        reserveBtn.addEventListener('click', function() {
            var usernameInput = document.getElementById('username');
            var username = usernameInput ? usernameInput.value : undefined;
            if (username !== undefined) {
                var startDateRaw = document.getElementById('startDate').value;
                var endDateRaw = document.getElementById('endDate').value;
                var carId = document.getElementById('carId').value;
                var errorRes = document.getElementById('errorReservation');
                var confirmation = document.getElementById('confirmationReservation');
                if (!startDateRaw || !endDateRaw) {
                    errorRes.innerHTML = "Please select both start and end dates.";
                    return;
                }
                // Convert YYYY-MM-DD to DD-MM-YYYY
                function formatDate(dateStr) {
                    var parts = dateStr.split("-");
                    return parts[2] + "-" + parts[1] + "-" + parts[0];
                }
                var startDate = formatDate(startDateRaw);
                var endDate = formatDate(endDateRaw);

                errorRes.innerHTML = "";
                var datas = {
                    'startDate': startDate,
                    'endDate': endDate,
                    'idVoiture': carId
                };
                fetch("/api/reserve", {
                    method: "POST",
                    headers: {
                        "Accept": "application/json",
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(datas)
                })
                .then(response => {
                    if (response.ok) {
                        confirmation.innerHTML = "You will receive a confirmation email from our managers.";
                    }
                });
            } else {
                window.location.replace("/login");
            }
        });
    }
});