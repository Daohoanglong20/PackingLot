<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car List</title>


    <link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/assets/css/style.css" rel="stylesheet" type="text/css">
    <script src="https://kit.fontawesome.com/fcb595c1bc.js" crossorigin="anonymous"></script>
</head>

<body>
<div class="container-fluid">
    <div class="row">

        <div class="col-sm-12 header">
            <div class="row">
                <div class="col-sm-3">
                    <span><i class="fas fa-car"></i>Car</span>
                </div>
                <jsp:include page="../common/header.jsp"/>
            </div>
        </div>

        <div class="content container-fluid">
            <div class="row">

                <div class="left col-sm-2">

                    <jsp:include page="../common/carMenu.jsp"/>
                </div>

                <div class=" right col-sm-10">
                    <div class="right-content">
                        <h1>Car list</h1>
                        <div class=" border-top">
                            <c:if test="${carList==null && keySearch == null}">
                                <div class="message text-center d-flex justify-content-center align-items-center"
                                     style="height: 350px;">
                                    <div><i class="fas fa-database fa-10x" style="color: #2a80dc"></i></div>
                                    <div class="ml-5">
                                        <p>Don't have any car in system</p>
                                        <a href="car/addCar.jsp">Add car now.</a>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${carList==null && keySearch != null}">
                                <!-- filter start -->
                                <div class="filter ">
                                    <form method="get" action="<%=request.getContextPath()%>/search-car">
                                        <div class="row d-flex ">
                                            <div class="col-sm-8 pr-0  ">
                                                <div class="input-group mb-3 mt-3 justify-content-end">
                                                    <div class="input-group-prepend ">
                                                        <button class="btn button-search" type="submit"
                                                                id="button-addon1"><i class="fas fa-search"></i>
                                                        </button>
                                                    </div>
                                                    <input type="text" class="filter-search" placeholder="Car Search"
                                                           aria-label="Example text with button addon"
                                                           aria-describedby="button-addon1"
                                                           name="keySearch"
                                                           value="${keySearch}">
                                                </div>
                                            </div>
                                            <div class="col-sm-4 pl-0">
                                                <div class="input-group mb-3 mt-3 justify-content-end">
                                                    <div class="input-group-prepend">
                                                    <span class="input-group-text" id="basic-addon2"><i
                                                            class="fas fa-filter icon3"></i>Filter By</span>
                                                    </div>
                                                    <div class="input-group-append ">
                                                        <select class="form-control " name="filter">
                                                            <option value="licensePlate" ${filter == "licensePlate"?"selected":""}>
                                                                License plate
                                                            </option>
                                                            <option value="company" ${filter == "company"?"selected":""}>
                                                                Company
                                                            </option>
                                                            <option value="carType" ${filter == "carType"?"selected":""}>
                                                                Car type
                                                            </option>
                                                            <option value="carColor" ${filter == "carColor"?"selected":""}>
                                                                Car color
                                                            </option>
                                                        </select>
                                                    </div>
                                                    <button class="btn btn-info float-right mb-0  ml-3" type="submit">
                                                        Search
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <div class="message text-center d-flex justify-content-center align-items-center"
                                     style="height: 350px;">
                                    <div><i class="fas fa-file-excel fa-10x" style="color: #2a80dc"></i></div>
                                    <div class="ml-5">
                                        <p>No matches</p>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${carList!=null}">
                                <!-- filter start -->
                                <div class="filter ">
                                    <form method="get" action="<%=request.getContextPath()%>/search-car">
                                        <div class="row d-flex ">
                                            <div class="col-sm-8 pr-0  ">
                                                <div class="input-group mb-3 mt-3 justify-content-end">
                                                    <div class="input-group-prepend ">
                                                        <button class="btn button-search" type="submit"
                                                                id="button-addon3"><i class="fas fa-search"></i>
                                                        </button>
                                                    </div>
                                                    <input type="text" class="filter-search" placeholder="Car Search"
                                                           aria-label="Example text with button addon"
                                                           aria-describedby="button-addon1"
                                                           name="keySearch"
                                                           value="${keySearch}">
                                                </div>
                                            </div>
                                            <div class="col-sm-4 pl-0">
                                                <div class="input-group mb-3 mt-3 justify-content-end">
                                                    <div class="input-group-prepend">
                                                    <span class="input-group-text" id="basic-addon1"><i
                                                            class="fas fa-filter icon3"></i>Filter By</span>
                                                    </div>
                                                    <div class="input-group-append ">
                                                        <select class="form-control " name="filter">
                                                            <option value="licensePlate" ${filter == "licensePlate"?"selected":""}>
                                                                License plate
                                                            </option>
                                                            <option value="company" ${filter == "company"?"selected":""}>
                                                                Company
                                                            </option>
                                                            <option value="carType" ${filter == "carType"?"selected":""}>
                                                                Car type
                                                            </option>
                                                            <option value="carColor" ${filter == "carColor"?"selected":""}>
                                                                Car color
                                                            </option>
                                                        </select>
                                                    </div>
                                                    <button class="btn btn-info float-right mb-0  ml-3" type="submit">
                                                        Search
                                                    </button>
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
                                            <th>License plate</th>
                                            <th>Car type</th>
                                            <th>Car color</th>
                                            <th>Company</th>
                                            <th>Parking lot</th>
                                            <th style="width: 30%;">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <jsp:useBean id="p" class="dao.Impl.ParkingLotDAOImpl"/>

                                        <c:forEach var="car" items="${carList}">
                                            <tr>
                                                <td class="id">${car.lincensePlate}</td>
                                                <td class="name">${car.carType}</td>
                                                <td>${car.carColor}</td>
                                                <td>${car.company}</td>
                                                <td>
                                                    <a href="<%=request.getContextPath()%>/UpdatePackinglotServlet?id=${car.parkId}">${p.getParkingLotById(car.parkId).parkName}</a>
                                                </td>
                                                <td>
                                                    <a href="<%=request.getContextPath()%>/updateCar?license=${car.lincensePlate}"><i
                                                            class="fas fa-pen"></i>Update</a>
                                                    <a class="delete" href="#confirm" data-toggle="modal"><i
                                                            class="fas fa-trash-alt"></i>Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>


                                <div class="modal fade" id="confirm" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <form name="" method="get" action="<%=request.getContextPath()%>/delete-car">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLongTitle">Confirm</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close"><span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <input type="hidden" id="idCar" name="license">
                                                    <h6 id="modal_body"></h6>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal">Close
                                                    </button>
                                                    <button type="submit" class="btn btn-danger" id="delete">Delete
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- Confirm end-->

                                <!-- paging start -->
                                <c:if test="${keySearch == null}">
                                    <nav class="text-center">
                                        <ul class="pagination ">
                                            <li class="page-item ${activeIndex == 1?"disabled":""}">
                                                <a class="page-link ${activeIndex == 1?"disable":""}"
                                                   href="ListCar?indexPage=${activeIndex-1}" tabindex="1">Previous</a>
                                            </li>
                                            <c:forEach begin="1" end="${endPage}" var="i">
                                                <li class="page-item ${activeIndex==i?"active":""}">
                                                    <a class="page-link" href="ListCar?indexPage=${i}">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item ${activeIndex == endPage?"disabled":""}">
                                                <a class="page-link ${activeIndex == endPage?"disable":""}"
                                                   href="ListCar?indexPage=${activeIndex+1}">Next</a>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:if>
                                <c:if test="${keySearch != null}">
                                    <nav class="text-center">
                                        <ul class="pagination ">
                                            <li class="page-item ${activeIndex == 1?"disabled":""}">
                                                <a class="page-link ${activeIndex == 1?"disable":""}"
                                                   href="search-car?indexPage=${activeIndex-1}&keySearch=${keySearch}&filter=${filter}"
                                                   tabindex="1">Previous</a>
                                            </li>
                                            <c:forEach begin="1" end="${endPage}" var="i">
                                                <li class="page-item ${activeIndex==i?"active":""}">
                                                    <a class="page-link"
                                                       href="search-car?indexPage=${i}&keySearch=${keySearch}&filter=${filter}">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item ${activeIndex == endPage?"disabled":""}">
                                                <a class="page-link ${activeIndex == endPage?"disable":""}"
                                                   href="search-car?indexPage=${activeIndex+1}&keySearch=${keySearch}&filter=${filter}">Next</a>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:if>
                                <!-- paging end -->
                            </c:if>
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
        $("#idCar").val($.trim($id));
        var name = $("#empName").val();
        var str = "Are you sure to delete (License plate:  " + $id + " - " + "Car type: " + $name + ")?";
        $("#modal_body").html(str);
    });
</script>
</body>

</html>
