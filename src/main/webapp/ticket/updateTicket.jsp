<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Trip</title>


    <link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/assets/css/style.css" rel="stylesheet" type="text/css">
    <script src="https://kit.fontawesome.com/fcb595c1bc.js" crossorigin="anonymous"></script>

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
                    <!-- menu -->
                    <jsp:include page="../common/serviceMenu.jsp"/>
                </div>
                <!-- left side end -->

                <div class=" right col-sm-10">
                    <div class="right-content">
                        <h1>Update Ticket</h1>
                        <div class=" border-top">
                            <c:if test="${success!=null}">
                                <div class="col-sm-7 alert alert-success text-center" role="alert">
                                    <p>${success}</p>
                                    <a href="<%=request.getContextPath()%>/ticketlist">View Ticket list</a>
                                </div>
                            </c:if>
                            <c:if test="${fail!=null}">
                                <div class="col-sm-7 alert alert-danger text-center" role="alert">
                                    <p>${fail}</p>
                                    <a href="<%=request.getContextPath()%>/ticketlist">View Ticket list</a>
                                </div>
                            </c:if>

                            <div class="form-add">
                                <form method="post" action="<%=request.getContextPath()%>/detailticket"
                                      onsubmit="return checkAddTicket()">
                                    <div class="form-group row">
                                        <label for="customer" class="col-sm-2 col-form-label">Customer <span
                                                class="important">(*)</span></label>
                                        <input type="hidden" name="ticketId" value="${ticketId}">
                                        <div class="col-sm-5">
                                            <input name="customer" type="text" class="form-control" id="customer"
                                                   required placeholder="Enter customer" value="${customerName}">
                                            <div id="messCustomer" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="bookingTime" class="col-sm-2 col-form-label">Booking time<span
                                                class="important">(*)</span></label>
                                        <div class="col-sm-5">
                                            <input name="time" type="text" id="bookingTime" onfocus="(this.type='time')"
                                                   class="form-control" placeholder="--:-- --" value="${time}"/>
                                            <div id="messBookingTime" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="trip" class="col-sm-2 col-form-label">Trip<span class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <select name="trip" class="form-control " id="trip">
                                                <c:forEach items="${tripList}" var="trip">
                                                    <option value="${trip.tripID}" ${tripName==trip.destination?"selected":""}>${trip.destination}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="lincense" class="col-sm-2 col-form-label">Lincense plate<span
                                                class="important">(*)</span></label>
                                        <div class="col-sm-5">
                                            <select name="licenseplate" class="form-control " id="lincense">
                                                <c:forEach items="${carList}" var="car">
                                                    <option value="${car.lincensePlate}" ${license==car.lincensePlate?"selected":""}>${car.lincensePlate}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-7 text-center">
                                        <button type="reset" class="btn btn-warning"><i class="fas fa-undo-alt"></i>Reset
                                        </button>
                                        <button type="submit" class="btn btn-success mb-0"><i class="fas fa-plus"></i>Update
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
    function checkAddTicket() {
        var customer = document.addForm.customer.value;
        if (customer.match(/^ *$/) !== null) {
            document.getElementById("customerError").innerHTML = "Customer's name is invalid";
            alert("Customer's name is invalid");
            document.addForm.customer.value = '';
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
