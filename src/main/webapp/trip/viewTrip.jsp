<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trip List</title>


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
                    <!-- Menu trip start -->
                    <jsp:include page="../common/serviceMenu.jsp"/>
                </div>

                <div class=" right col-sm-10">
                    <div class="right-content">
                        <h1>Trip list</h1>
                        <div class=" border-top">
                            <!-- <div class="message text-center">
                            <p>Don't have any trip in system</p>
                            <a href="trip/addTrip.jsp">Add new trip now.</a>
                        </div> -->

                            <!-- filter start -->
                            <div class="filter ">
                                <form method="get" action="">
                                    <div class="row d-flex ">
                                        <div class="col-sm-7 pr-0  ">
                                            <div class="input-group mb-3 mt-3 justify-content-end">
                                                <div class="input-group-prepend ">
                                                    <button class="btn button-search" type="submit"
                                                            id="button-addon1">
                                                        <i class="fas fa-search"></i>
                                                    </button>
                                                </div>
                                                <input type="text" class="filter-search mr-2"
                                                       placeholder="Trip Search"
                                                       aria-label="Example text with button addon"
                                                       aria-describedby="button-addon1">
                                            </div>
                                        </div>
                                        <div class="col-sm-5 pl-0">
                                            <div class="input-group mb-3 mt-3 justify-content-end">
                                                <button class="btn btn-info float-left " type="button"
                                                        onclick="searchDestination()">Search
                                                </button>
                                                <select class="form-control ml-4 rounded">
                                                    <option value="">01</option>
                                                    <option value="">02</option>
                                                    <option value="">03</option>
                                                    <option value="">04</option>
                                                    <option value="">05</option>
                                                    <option value="">06</option>
                                                    <option value="">07</option>
                                                    <option value="">08</option>
                                                    <option value="">09</option>
                                                    <option value="">10</option>
                                                    <option value="">...</option>
                                                </select> <select class="form-control ml-2 rounded">
                                                <option value="">01</option>
                                                <option value="">02</option>
                                                <option value="">03</option>
                                                <option value="">04</option>
                                                <option value="">05</option>
                                                <option value="">06</option>
                                                <option value="">07</option>
                                                <option value="">08</option>
                                                <option value="">09</option>
                                                <option value="">10</option>
                                                <option value="">...</option>
                                            </select> <select class="form-control ml-2 rounded">
                                                <option value="">2018</option>
                                                <option value="">2019</option>
                                                <option value="">2020</option>
                                                <option value="">2021</option>
                                            </select>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- filter end -->
                            <%--message start--%>
                            <c:if test="${success!=null}">
                                <div class="col-sm-12 alert alert-success" role="alert">
                                    <p class="text-center">${success}</p>
                                </div>
                            </c:if>
                            <c:if test="${fail!=null}">
                                <div class="col-sm-12 alert alert-danger" role="alert">
                                    <p class="text-center">${fail}</p>
                                </div>
                            </c:if>
                            <%--message end--%>
                            <div class="content-list">
                                <table class="table table-striped table-bordered" id="table">
                                    <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Destination</th>
                                        <th>Departure time</th>
                                        <th>Driver</th>
                                        <th>Car type</th>
                                        <th>Booked ticket number</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody id="content-trip">
                                    <c:forEach items="${triplist}" var="t">
                                        <input type="hidden" name="pageTripCurrentIndex"
                                               id="pageTripCurrentIndex" value="${pageTripCurrentIndex}">

                                        <tr>
                                            <th class="id">${t.tripID}</th>
                                            <td class="name">${t.destination}</td>
                                            <td>${t.departureTime}</td>
                                            <td>${t.driver}</td>
                                            <td>${t.carType}</td>
                                            <td>${t.bookedTicketNumber}</td>
                                            <td>
                                                <a
                                                        href="<%=request.getContextPath()%>/tripdetails?id=${t.tripID}">
                                                    <i class="fa fa-pen" aria-hidden="true"></i>&nbsp;Edit
                                                </a>&ensp;
                                                <a class="delete" href="#confirm" data-toggle="modal"><i
                                                        class="fas fa-trash-alt"></i>Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                            <!-- Confirm start-->
                            <div class="modal fade" id="confirm" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <form name="" method="post" action="<%=request.getContextPath()%>/deletetrip">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle">Confirm</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close"><span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <input type="hidden" id="idTrip" name="id" value="">
                                                <h6 id="modal_body"></h6>

                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                    Close
                                                </button>
                                                <button type="submit" class="btn btn-danger" id="delete">Delete</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- Confirm end-->

                            <!-- paging start -->
                            <nav class="text-center">
                                <ul class="pagination mt-4">
                                    <c:choose>
                                        <c:when test="${bulen==1}">
                                            <li class="page-item ${currentPage == 1 ?"disabled":""}"><a
                                                    class="page-link"
                                                    href="SearchParkinglotServlet?pageIndex=${currentPage-1}"
                                            >Previous</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item ${currentPage == 1 ?"disabled":""}"><a
                                                    class="page-link"
                                                    href="triplist?pageIndex=${currentPage-1}"
                                            >Previous</a>
                                            </li>
                                            <br/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${bulen==1}">
                                            <c:forEach begin="1" end="${numberPage }" var="i">
                                                <li class="page-item ${currentPage==i?"active":""}"><a
                                                        class="page-link"
                                                        href="SearchParkinglotServlet?pageIndex=${i }">${i }</a>
                                                </li>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach begin="1" end="${numberPage }" var="i">
                                                <li class="page-item ${currentPage==i?"active":""}"><a
                                                        class="page-link"
                                                        href="triplist?pageIndex=${i }">${i }</a>
                                                </li>
                                            </c:forEach>
                                            <br/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${bulen==1}">
                                            <li class="page-item ${currentPage == numberPage ? "disabled":""}"><a
                                                    class="page-link"
                                                    href="SearchParkinglotServlet?pageIndex=${currentPage+1}"
                                            >Next</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item ${currentPage == numberPage ?"disabled":""}"><a
                                                    class="page-link"
                                                    href="triplist?pageIndex=${currentPage+1}"
                                            >Next</a>
                                            </li>
                                            <br/>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </nav>
                            <!-- paging end -->

                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>
<script src="<%=request.getContextPath()%>/assets/js/jquery_3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/assets//js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>

<script type="text/javascript">
    $(".delete").click(function (event) {
        var $row = $(this).closest("tr"); // Find the row
        var $id = $row.find(".id").text();
        var $name = $row.find(".name").text();

        // var id = $("#empID").val();
        $("#idTrip").val($.trim($id));
        var name = $("#empName").val();
        var str = "Are you sure to delete (No:  " + $id + " - " + "Destination: " + $name + ")?";
        $("#modal_body").html(str);
    });
</script>

<script type="text/javascript"
        src="<%=request.getContextPath()%>/assets/js/script.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/assets/js/button.js"></script>
</body>

</html>
