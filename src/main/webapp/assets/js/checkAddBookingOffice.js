function checkAddBookingOffice() {
    var toDateCheck = checkToDate();
    var priceCheck = checkPrice();
    var phoneCheck = checkPhone();
    var nameCheck = checkName();

    return (toDateCheck && nameCheck && phoneCheck && priceCheck);
}

function checkToDate() {
    var fromDateStr = document.getElementById("fromDate");
    var toDateStr = document.getElementById("toDate");
    var messToDate = document.getElementById("messToDate");

    var fromDateArray = fromDateStr.value.split("-");
    var toDateArray = toDateStr.value.split("-");

    var fromDate = new Date(fromDateArray[0], --fromDateArray[1], fromDateArray[2]);
    var toDate = new Date(toDateArray[0], --toDateArray[1], toDateArray[2]);


    if (toDate < fromDate) {
        toDateStr.classList.add("is-invalid");
        messToDate.removeAttribute('hidden');
        messToDate.innerHTML = 'To Date must be larger than From Date!';
        toDateStr.focus();

        return false;
    } else {
        toDateStr.classList.remove("is-invalid");
        messToDate.setAttribute("hidden", "");
        return true;
    }
}

function checkPrice() {
    var price = document.getElementById("price");
    var messPrice = document.getElementById("messPrice");

    if (price.value.length > 20) {
        price.classList.add("is-invalid");
        messPrice.removeAttribute('hidden');
        messPrice.innerHTML = 'The Price number length must be less than or equal 20 digits.';
        price.focus();

        return false;
    } else {
        price.classList.remove("is-invalid");
        messPrice.setAttribute("hidden", "");
        return true;
    }
}

function checkPhone() {
    var phone = document.getElementById("phone");
    var messPhone = document.getElementById("messPhone");

    if (phone.value.length > 15) {
        phone.classList.add("is-invalid");
        messPhone.removeAttribute('hidden');
        messPhone.innerHTML = 'The Phone number length must be less than or equal 15 digits.';
        phone.focus();

        return false;
    } else {
        phone.classList.remove("is-invalid");
        messPhone.setAttribute("hidden", "");
        return true;
    }
}

function checkName() {
    var bookingOfficeName = document.getElementById("bookingOfficeName");
    var messBookingOfficeName = document.getElementById("messBookingOfficeName");

    if (bookingOfficeName.value.length > 50) {
        bookingOfficeName.classList.add("is-invalid");
        messBookingOfficeName.removeAttribute('hidden');
        messBookingOfficeName.innerHTML = 'The Booking office name number length must be less than or equal 50 characters.';
        bookingOfficeName.focus();

        return false;
    } else {
        bookingOfficeName.classList.remove("is-invalid");
        messBookingOfficeName.setAttribute("hidden", "");
        return true;
    }
}