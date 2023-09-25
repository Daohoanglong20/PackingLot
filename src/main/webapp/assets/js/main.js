function checkAddParkingLot() {
    var priceCheck = checkPrice_10();
    var areaCheck = checkArea();
    var parkingCheck = checkParkingName();

    return (parkingCheck && areaCheck && priceCheck);
}

function checkAddEmployee() {
    var passCheck = checkPassword();
    var accountCheck = checkAccount();
    var emailCheck = checkEmail();
    var phoneCheck = checkPhone();
    var addessCheck = checkAddress();
    var fullnameCheck = checkFullname();

    return (fullnameCheck && addessCheck && phoneCheck && emailCheck && accountCheck && passCheck);
}

function checkAddCar() {
    var colorCheck = checkColor();
    var carTypeCheck = checkType();
    var licenseCheck = checkLicense();

    return (colorCheck && carTypeCheck && licenseCheck);
}

function checkAddTrip() {
    var destinationCheck = checkDestination();
    var departureTimeCheck = checkDepartureTime();
    var driverCheck = checkDriver();
    var carTypeCheck = checkCarType();
    var ticketCheck = checkTicket();
    var departureDateCheck = checkDepartureDate();

    return (destinationCheck && departureTimeCheck && driverCheck && carTypeCheck && ticketCheck && departureDateCheck);
}

function checkAddTicket() {
    return checkCustomer();
}

function checkDepartureDate() {
    var departureDateStr = document.getElementById("departureDate");
    var messDepartureDate = document.getElementById("messDepartureDate");

    var departureDateArray = departureDateStr.value.split("-");

    var departureDate = new Date(departureDateArray[0], --departureDateArray[1], departureDateArray[2]);
    var currentDate = new Date();


    if (departureDate < currentDate) {
        departureDateStr.classList.add("is-invalid");
        messDepartureDate.removeAttribute('hidden');
        messDepartureDate.innerHTML = 'Departure Date must be larger than current date!';
        departureDateStr.focus();

        return false;
    } else {
        departureDateStr.classList.remove("is-invalid");
        messDepartureDate.setAttribute("hidden", "");
        return true;
    }
}

function checkCustomer() {
    var customer = document.getElementById("customer");
    var messCustomer = document.getElementById("messCustomer");

    if (customer.value.length > 50) {
        customer.classList.add("is-invalid");
        messCustomer.removeAttribute('hidden');
        messCustomer.innerHTML = 'The Customer name length must be less than or equal 50 characters.';
        customer.focus();

        return false;
    } else {
        customer.classList.remove("is-invalid");
        messCustomer.setAttribute("hidden", "");
        return true;
    }
}

function checkTicket() {
    var ticket = document.getElementById("ticket");
    var messTicket = document.getElementById("messTicket");

    if (ticket.value.length > 50) {
        ticket.classList.add("is-invalid");
        messTicket.removeAttribute('hidden');
        messTicket.innerHTML = 'The Maximun online ticket number is 50 digits.';
        ticket.focus();

        return false;
    } else {
        ticket.classList.remove("is-invalid");
        messTicket.setAttribute("hidden", "");
        return true;
    }
}

function checkCarType() {
    var carType = document.getElementById("carType");
    var messCarType = document.getElementById("messCarType");

    if (carType.value.length > 50) {
        carType.classList.add("is-invalid");
        messCarType.removeAttribute('hidden');
        messCarType.innerHTML = 'The Car type length must be less than or equal 50 characters.';
        carType.focus();

        return false;
    } else {
        carType.classList.remove("is-invalid");
        messCarType.setAttribute("hidden", "");
        return true;
    }
}

function checkDriver() {
    var driver = document.getElementById("driver");
    var messDriver = document.getElementById("messDriver");

    if (driver.value.length > 50) {
        driver.classList.add("is-invalid");
        messDriver.removeAttribute('hidden');
        messDriver.innerHTML = 'The Driver length must be less than or equal 50 characters.';
        driver.focus();

        return false;
    } else {
        driver.classList.remove("is-invalid");
        messDriver.setAttribute("hidden", "");
        return true;
    }
}


function checkDepartureTime() {
    var departureTime = document.getElementById("departureTime");
    var messDepartureTime = document.getElementById("messDepartureTime");

    if (departureTime.value.length > 50) {
        departureTime.classList.add("is-invalid");
        messDepartureTime.removeAttribute('hidden');
        messDepartureTime.innerHTML = 'The Departutre time length must be less than or equal 50 characters.';
        departureTime.focus();

        return false;
    } else {
        departureTime.classList.remove("is-invalid");
        messDepartureTime.setAttribute("hidden", "");
        return true;
    }
}

function checkDestination() {
    var destination = document.getElementById("destination");
    var messDes = document.getElementById("messDes");

    if (destination.value.length > 50) {
        destination.classList.add("is-invalid");
        messDes.removeAttribute('hidden');
        messDes.innerHTML = 'The Destination length must be less than or equal 50 characters.';
        destination.focus();

        return false;
    } else {
        destination.classList.remove("is-invalid");
        messDes.setAttribute("hidden", "");
        return true;
    }
}

function checkParkingName() {
    var parkingName = document.getElementById("parkingName");
    var messParking = document.getElementById("messParking");

    if (parkingName.value.length > 50) {
        parkingName.classList.add("is-invalid");
        messParking.removeAttribute('hidden');
        messParking.innerHTML = 'The Parking name length must be less than or equal 50 characters.';
        parkingName.focus();

        return false;
    } else {
        parkingName.classList.remove("is-invalid");
        messParking.setAttribute("hidden", "");
        return true;
    }
}

function checkArea() {
    var area = document.getElementById("area");
    var messArea = document.getElementById("messArea");

    if (area.value.length > 20) {
        area.classList.add("is-invalid");
        messArea.removeAttribute('hidden');
        messArea.innerHTML = 'The Area number length must be less than or equal 20 digits.';
        area.focus();

        return false;
    } else {
        area.classList.remove("is-invalid");
        messPmessAreaarking.setAttribute("hidden", "");
        return true;
    }
}

function checkPrice_10() {
    var price = document.getElementById("price");
    var messPrice = document.getElementById("messPrice");

    if (price.value.length > 10) {
        price.classList.add("is-invalid");
        messPrice.removeAttribute('hidden');
        messPrice.innerHTML = 'The Price number length must be less than or equal 10 digits.';
        price.focus();

        return false;
    } else {
        bookpriceingOfficeName.classList.remove("is-invalid");
        messPrice.setAttribute("hidden", "");
        return true;
    }
}

function checkLicense() {
    var license = document.getElementById("license");
    var messLicense = document.getElementById("messLicense");

    if (license.value.length > 50) {
        license.classList.add("is-invalid");
        messLicense.removeAttribute('hidden');
        messLicense.innerHTML = 'The License number length must be less than or equal 50 characters.';
        license.focus();

        return false;
    } else {
        license.classList.remove("is-invalid");
        messLicense.setAttribute("hidden", "");
        return true;
    }
}

function checkColor() {
    var carCorlor = document.getElementById("carCorlor");
    var messCarColor = document.getElementById("messCarColor");

    if (carCorlor.value.length > 50) {
        carCorlor.classList.add("is-invalid");
        messCarColor.removeAttribute('hidden');
        messCarColor.innerHTML = 'The Car color number length must be less than or equal 50 characters.';
        carCorlor.focus();

        return false;
    } else {
        carCorlor.classList.remove("is-invalid");
        messCarColor.setAttribute("hidden", "");
        return true;
    }
}

function checkType() {
    var carType = document.getElementById("carType");
    var messCarType = document.getElementById("messCarType");

    if (carType.value.length > 50) {
        carType.classList.add("is-invalid");
        messCarType.removeAttribute('hidden');
        messCarType.innerHTML = 'The Car type number length must be less than or equal 50 characters.';
        carType.focus();

        return false;
    } else {
        license.classList.remove("is-invalid");
        messCarType.setAttribute("hidden", "");
        return true;
    }
}

function checkAccount() {
    var account = document.getElementById("account");
    var messAcc = document.getElementById("messAcc");

    if (account.value.length > 50) {
        account.classList.add("is-invalid");
        messAcc.removeAttribute('hidden');
        messAcc.innerHTML = 'The Account length must be less than or equal 50 characters.';
        account.focus();

        return false;
    } else {
        account.classList.remove("is-invalid");
        messAcc.setAttribute("hidden", "");
        return true;
    }
}

function checkAddress() {
    var address = document.getElementById("address");
    var messAddress = document.getElementById("messAddress");

    if (address.value.length > 50) {
        address.classList.add("is-invalid");
        messAddress.removeAttribute('hidden');
        messAddress.innerHTML = 'The Address length must be less than or equal 50 characters.';
        address.focus();

        return false;
    } else {
        address.classList.remove("is-invalid");
        messAddress.setAttribute("hidden", "");
        return true;
    }
}

function checkFullname() {
    var fullname = document.getElementById("name");
    var messName = document.getElementById("messName");

    if (fullname.value.length > 50) {
        fullname.classList.add("is-invalid");
        messName.removeAttribute('hidden');
        messName.innerHTML = 'The Fullname length must be less than or equal 50 characters.';
        fullname.focus();

        return false;
    } else {
        fullname.classList.remove("is-invalid");
        messName.setAttribute("hidden", "");
        return true;
    }
}

function showMenuEmployee() {
    var menuEmployee = document.getElementById("list-menu-employee");
    var btnEmp = document.getElementById("icon-function-employee");
    if (menuEmployee.style.display !== 'none') {
        menuEmployee.style.display = 'none';
        btnEmp.classList.remove("fas");
        btnEmp.classList.remove("fa-angle-down");

        btnEmp.classList.add("fas");
        btnEmp.classList.add("fa-angle-left");
    } else {
        menuEmployee.style.display = 'block';
        btnEmp.classList.remove("fas");
        btnEmp.classList.remove("fa-angle-left");

        btnEmp.classList.add("fas");
        btnEmp.classList.add("fa-angle-down");
    }
}

function showMenuTrip() {
    var menuTrip = document.getElementById("list-menu-trip");
    var btntrip = document.getElementById("icon-function-trip");
    if (menuTrip.style.display !== 'none') {
        menuTrip.style.display = 'none';
        btntrip.classList.remove("fas");
        btntrip.classList.remove("fa-angle-down");

        btntrip.classList.add("fas");
        btntrip.classList.add("fa-angle-left");
    } else {
        menuTrip.style.display = 'block';
        btntrip.classList.remove("fas");
        btntrip.classList.remove("fa-angle-left");

        btntrip.classList.add("fas");
        btntrip.classList.add("fa-angle-down");
    }
}

function showMenuTicket() {
    var menuTicket = document.getElementById("list-menu-ticket");
    var btnTicket = document.getElementById("icon-function-ticket");
    if (menuTicket.style.display !== 'none') {
        menuTicket.style.display = 'none';
        btnTicket.classList.remove("fas");
        btnTicket.classList.remove("fa-angle-down");

        btnTicket.classList.add("fas");
        btnTicket.classList.add("fa-angle-left");
    } else {
        menuTicket.style.display = 'block';
        btnTicket.classList.remove("fas");
        btnTicket.classList.remove("fa-angle-left");

        btnTicket.classList.add("fas");
        btnTicket.classList.add("fa-angle-down");
    }
}

function showMenuCar() {
    var menuCar = document.getElementById("list-menu-car");
    var btnCar = document.getElementById("icon-function-car");
    if (menuCar.style.display !== 'none') {
        menuCar.style.display = 'none';
        btnCar.classList.remove("fas");
        btnCar.classList.remove("fa-angle-down");

        btnCar.classList.add("fas");
        btnCar.classList.add("fa-angle-left");
    } else {
        menuCar.style.display = 'block';
        btnCar.classList.remove("fas");
        btnCar.classList.remove("fa-angle-left");

        btnCar.classList.add("fas");
        btnCar.classList.add("fa-angle-down");
    }
}

function showMenuBooking() {
    var menuBooking = document.getElementById("list-menu-booking");
    var btnBooking = document.getElementById("icon-function-booking");
    if (menuBooking.style.display !== 'none') {
        menuBooking.style.display = 'none';
        btnBooking.classList.remove("fas");
        btnBooking.classList.remove("fa-angle-down");

        btnBooking.classList.add("fas");
        btnBooking.classList.add("fa-angle-left");
    } else {
        menuBooking.style.display = 'block';
        btnBooking.classList.remove("fas");
        btnBooking.classList.remove("fa-angle-left");

        btnBooking.classList.add("fas");
        btnBooking.classList.add("fa-angle-down");
    }
}

function showMenuParking() {
    var menuParking = document.getElementById("list-menu-parking");
    var btnParking = document.getElementById("icon-function-parking");
    if (menuParking.style.display !== 'none') {
        menuParking.style.display = 'none';
        btnParking.classList.remove("fas");
        btnParking.classList.remove("fa-angle-down");

        btnParking.classList.add("fas");
        btnParking.classList.add("fa-angle-left");
    } else {
        menuParking.style.display = 'block';
        btnParking.classList.remove("fas");
        btnParking.classList.remove("fa-angle-left");

        btnParking.classList.add("fas");
        btnParking.classList.add("fa-angle-down");
    }
}

function checkLogin() {
    //Validate
    var passCheck = checkPassword();
    var accountCheck = checkAccount();

    return (accountCheck && passCheck);

}

function checkRegister() {
    var pass = document.getElementById("password").value;

    //Validate
    var repassCheck = checkRePassword(pass);
    var passCheck = checkPassword();
    var emailCheck = checkEmail();

    return (emailCheck && passCheck && repassCheck);

}


function checkEmail() {
    var PATTERN_EMAIL = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}/;
    var email = document.getElementById("email");
    var messEmail = document.getElementById("messEmail");
    if (email.value.length > 50) {
        email.classList.add("is-invalid");
        messEmail.removeAttribute('hidden');
        messEmail.innerHTML = 'The Email length must be less or equal 50 characters.';
        email.focus();
        return false;
    } else if (!PATTERN_EMAIL.test(email.value)) {
        email.classList.add("is-invalid");
        messEmail.removeAttribute('hidden');
        messEmail.innerHTML = 'The email entered is not a valid format.';
        email.focus();
        return false;
    } else {
        email.classList.remove("is-invalid");
        messEmail.setAttribute("hidden", "");
        return true;
    }
}

function checkPassword() {
    var pass = document.getElementById("password");
    var messPass = document.getElementById("messPass");

    if (pass.value.length > 20) {
        pass.classList.add("is-invalid");
        messPass.removeAttribute('hidden');
        messPass.innerHTML = 'The Password length must be less than or equal 20 characters.';
        pass.focus();

        return false;
    } else {
        pass.classList.remove("is-invalid");
        messPass.setAttribute("hidden", "");
        return true;
    }
}


function checkRePassword(pass) {
    var rePass = document.getElementById("repass");
    var messRePass = document.getElementById("messRePass");

    if (rePass.value === pass) {
        rePass.classList.remove("is-invalid");
        messRePass.setAttribute("hidden", "");
        return true;
    } else {
        rePass.classList.add("is-invalid");
        messRePass.removeAttribute('hidden');
        messRePass.innerHTML = 'Password must be confirmed.';
        rePass.focus();
        return false;
    }
}