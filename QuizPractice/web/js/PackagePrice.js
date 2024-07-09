function showPopUp(value) {
    $("#addPackage").html(value);
    const popup = document.querySelector("#modalPackage");
    if (popup.style.display === "none" || popup.style.display === "") {
        popup.style.display = "block";
    }
}

function hidePopUp() {
    const popup = document.querySelector("#modalPackage");
    if (popup.style.display === "block") {
        popup.style.display = "none";
    }
    $("#packageName").val("");
    $("#packageDuration").val("");
    $("#salePrice").val("");
    $("#price").val("");
    $("#originalPrice").val("");
//    $("#statusPackagePrice").val("");
}

function showPopUpEdit(e, value) {
    $("#addPackage").html(value);
    const  $row = $(e.target).closest('tr'); // Get the closest row to the clicked button
    const id = $row.find('td:nth-child(1)').text();
    const packageName = $row.find('td:nth-child(2)').text();
    const duration = $row.find('td:nth-child(3)').text();
    const price = $row.find('td:nth-child(4)').text();
    const salePrice = $row.find('td:nth-child(5)').text();
    const originalPrice = $row.find('td:nth-child(6)').text();
//    const status = $row.find('td:nth-child(7)').text();


    $("#packageId").val(id);
    $("#packageName").val(packageName);
    $("#packageDuration").val(duration);
    $("#salePrice").val(salePrice);
    $("#price").val(price);
    $("#originalPrice").val(originalPrice);
//    $("#statusPackagePrice").val(status);

    const popup = document.querySelector("#modalPackage");
    if (popup.style.display === "none" || popup.style.display === "") {
        popup.style.display = "block";
    }
}

function validatePackageName(e) {
    const content = $(e).val();
    if (content.trim() === "") {
        $("#namePkMsg").css('display', 'block');
    } else {
        $("#namePkMsg").css('display', 'none');
    }
}

function validateDuration(e) {
    const content = $(e).val().trim(); // Get the trimmed value of the input field

    if (content === "") {
        $("#durationMsg").text("Duration cannot be empty.").css('display', 'block'); // Show the message if content is empty
    } else {
        // Hide the message if content is not empty
        $("#durationMsg").css('display', 'none');

        // Validate if content is a number and >= 0
        const numericValue = parseFloat(content);
        if (!isNaN(numericValue) && numericValue > 0) {
            // Valid input
        } else {
            $("#durationMsg").text("Please enter a valid number > 0.").css('display', 'block'); // Show error message for invalid input
        }
    }
}

function validateSalePrice(e) {
    const content = $(e).val().trim(); // Get the trimmed value of the input field

    if (content === "") {
        $("#salePriceMsg").text("Sale price cannot be empty.").css('display', 'block'); // Show the message if content is empty
    } else {
        $("#salePriceMsg").css('display', 'none'); // Hide the message if content is not empty

        // Validate if content is a number and >= 0
        const numericValue = parseFloat(content);
        if (!isNaN(numericValue) && numericValue >= 0) {
            // Valid input
        } else {
            $("#salePriceMsg").text("Please enter a valid number >= 0.").css('display', 'block'); // Show error message for invalid input
        }
    }

}

function validatePrice(e) {
    const content = $(e).val(); // Assuming `e` is the event or element from which you are getting the value

    if (content.trim() === "") {
        $("#priceMsg").text("Price cannot be empty.").css('display', 'block');
    } else {
        $("#priceMsg").css('display', 'none');

        // Validate if content is a number and >= 0
        const numericValue = parseFloat(content);
        if (!isNaN(numericValue) && numericValue >= 0) {
            // Valid input
        } else {
            $("#priceMsg").text("Please enter a valid number >= 0.").css('display', 'block');
        }
    }

}

function validateOriginalPrice(e) {
    const content = $(e).val(); // Assuming `e` is the event or element from which you are getting the value

    if (content.trim() === "") {
        $("#originalPriceMsg").css('display', 'block').text("Original cannot be empty.");
    } else {
        $("#originalPriceMsg").css('display', 'none');

        // Validate if content is a number and >= 0
        const numericValue = parseFloat(content);
        if (!isNaN(numericValue) && numericValue >= 0) {
            // Valid input
        } else {
            $("#originalPriceMsg").css('display', 'block').text("Please enter a valid number >= 0.");
        }
    }
}

function addPackagePrice(e) {
    const name = $("#packageName").val();
    const duration = $("#packageDuration").val();
    const salePrice = $("#salePrice").val();
    const price = $("#price").val();
    const originalPrice = $("#originalPrice").val();
//    const status = $("#statusPackagePrice").val();
    if (name === "" || duration === "" || salePrice == "" || price == ""
            || originalPrice == "") {
        $(e).css('cursor', 'not-allowed');
        $(e).prop('disabled', true);
    } else {
        const numDuration = parseFloat(duration);
        const numSalePrice = parseFloat(salePrice);
        const numPrice = parseFloat(price);
        const numOriginalPrice = parseFloat(originalPrice);
        if (numDuration > 0 && numPrice >= 0 && numSalePrice >= 0 && numOriginalPrice >= 0) {
            $(e).prop('disabled', false);
            $(e).css('cursor', 'pointer');
        } else {
            $(e).css('cursor', 'not-allowed');
            $(e).prop('disabled', true);
        }
    }
}

function addNew() {
    var path;
    const type = $("#addPackage").html();
    var title;
    if (type === "Edit") {
        title = "Update package price successfully!";
        path = "EditPricePackage";
    } else {
        title = "Add new package price successfully!";
        path = "AddPricePackage";
    }
    const id = $("#packageId").val();
    const name = $("#packageName").val();
    const duration = $("#packageDuration").val();
    const salePrice = $("#salePrice").val();
    const price = $("#price").val();
    const originalPrice = $("#originalPrice").val();
//    const status = $("#statusPackagePrice").val();
    $.ajax({
        url: path,
        type: 'POST',
        data: {
            id: id,
            name: name,
            duration: duration,
            salePrice: salePrice,
            price: price,
            originalPrice: originalPrice
//            status: status
        },
        beforeSend: function () {
            $("#loading").css('display', 'flex');
        },
        complete: function () {
            $("#loading").css('display', 'none');
        }, success: function (data) {
            Swal.fire({
                title: title,
                icon: "success"
            }).then(result => {
                if (result.isConfirmed) {
                    window.location.reload();
                }
            });

        }
    });
}

