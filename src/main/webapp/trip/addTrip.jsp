<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Trip</title>


    <link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css"
          rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/assets/css/style.css"
          rel="stylesheet" type="text/css">
    <script src="https://kit.fontawesome.com/8cc0745621.js"
            crossorigin="anonymous"></script>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 header">
            <div class="row">
                <div class="col-sm-3">
                    <span><i class="fas fa-plane-departure"></i>Trip</span>
                </div>
                <jsp:include page="../common/header.jsp"/>
            </div>
        </div>

        <div class="content container-fluid">
            <div class="row">

                <div class="left col-sm-2">
                    <!-- Menu service -->
                    <jsp:include page="../common/serviceMenu.jsp"/>
                </div>

                <div class=" right col-sm-10">
                    <div class="right-content">
                        <h1>Add Trip</h1>
                        <div class=" border-top">

                            <div class="form-add">
                                <form name="addForm" method="post"
                                      action="<%=request.getContextPath()%>/addtrip"
                                      onsubmit="return checkAddTrip()">
                                    <c:if test="${success!=null}">
                                        <div class="col-sm-7 alert alert-success text-center" role="alert">
                                            <p>${success}</p>
                                            <a href="<%=request.getContextPath()%>/triplist">View Trip list</a>
                                        </div>
                                    </c:if>
                                    <c:if test="${fail!=null}">
                                        <div class="col-sm-7 alert alert-danger text-center" role="alert">
                                            <p>${fail}</p>
                                            <a href="<%=request.getContextPath()%>/triplist">View Trip list</a>
                                        </div>
                                    </c:if>
                                    <div class="form-group row">
                                        <label for="destination" class="col-sm-2 col-form-label">Destination
                                            <span class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input name="destination" type="text" class="form-control"
                                                   id="destination" required placeholder="Enter destination"
                                                   value="">
                                            <div id="messDes" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="departureTime" class="col-sm-2 col-form-label">Departure
                                            time<span class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input name="departuretime" type="text" id="departureTime"
                                                   onfocus="(this.type='time')" class="form-control"
                                                   placeholder="--:-- --"/>
                                            <div id="messDepartureTime" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="driver" class="col-sm-2 col-form-label">Driver<span
                                                class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input name="driver" id="driver" class="form-control"
                                                   type="text" required placeholder="Enter driver">
                                            <div id="messDriver" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="carType" class="col-sm-2 col-form-label">Car
                                            type<span class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input name="cartype" type="text" class="form-control"
                                                   id="carType" placeholder="Enter car type">
                                            <div id="messCarType" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="ticket" class="col-sm-2 col-form-label">Maximun
                                            online ticket number<span class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input name="maxnumberticket" type="text"
                                                   class="form-control" id="ticket"
                                                   onfocus="(this.type='number')" min="1" required
                                                   placeholder="0">
                                            <div id="messTicket" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="departureDate" class="col-sm-2 col-form-label">Departure
                                            date<span class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input name="departuredate" id="departureDate"
                                                   class="form-control" type="text" required
                                                   onfocus="(this.type='date')" placeholder="dd/mm/yyyy">
                                            <div id="messDepartureDate" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-7 text-center">
                                        <button type="reset" class="btn btn-warning">
                                            <i class="fas fa-undo-alt"></i>Reset
                                        </button>
                                        <button type="submit" class="btn btn-success mb-0">
                                            <i class="fas fa-plus"></i>Add
                                        </button>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>

<script>
    function checkAddTrip() {
        var destination = document.addForm.destination.value;
        var driver = document.addForm.driver.value;
        var cartype = document.addForm.cartype.value;
        var numberticket = document.addForm.maxnumberticket.value;
        if (destination.match(/^ *$/) !== null) {
            document.getElementById("destinationError").innerHTML = "Destination is invalid";
            alert("Destination is invalid");
            document.addForm.destination.value = '';
            return false;
        } else if (driver.match(/^ *$/) !== null) {
            document.getElementById("driverError").innerHTML = "Driver is invalid";
            alert("Driver is invalid");
            document.addForm.driver.value = '';
            document.getElementById("destinationError").innerHTML = "";
            return false;
        } else if (cartype.match(/^ *$/) !== null) {
            document.getElementById("cartypeError").innerHTML = "Cartype is invalid";
            alert("Cartype is invalid");
            document.addForm.cartype.value = '';
            document.getElementById("destinationError").innerHTML = "";
            document.getElementById("driverError").innerHTML = "";
            return false;
        } else if (isNaN(numberticket) || numberticket < 0
            || numberticket.match(/^ *$/) !== null) {
            document.getElementById("ticketError").innerHTML = "Ticket Number must be Numeric value only";
            alert("Maximum online ticket number is invalid");
            document.addForm.maxnumberticket.value = '';
            document.getElementById("destinationError").innerHTML = "";
            document.getElementById("driverError").innerHTML = "";
            document.getElementById("cartypeError").innerHTML = "";
            return false;
        } else {
            return true;
        }
    }
</script>
<script src="<%=request.getContextPath()%>/assets/js/jquery_3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/assets//js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>

<script type="text/javascript"
        src="<%=request.getContextPath()%>/assets/js/script.js"></script>
</body>

</html>
