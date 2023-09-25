<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Parking lot List</title>


    <link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css"
          rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/assets/css/style.css"
          rel="stylesheet" type="text/css">
    <script src="https://kit.fontawesome.com/8cc0745621.js"
            crossorigin="anonymous"></script>
</head>

<body>
<div class="container-fluid">
    <div class="row">

        <div class="col-sm-12 header">
            <div class="row">
                <div class="col-sm-3">
                    <span><i class="fas fa-map-marker-alt"></i>Parking lot</span>
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
                        <h1>Parking lot list</h1>
                        <div class=" border-top">
                            <c:if test="${listP==null && keySearch == null}">
                                <div class="message text-center d-flex justify-content-center align-items-center"
                                     style="height: 350px;">
                                    <div><i class="fas fa-database fa-10x" style="color: #2a80dc"></i></div>
                                    <div class="ml-5">
                                        <p>Don't have any Parking lot in system</p>
                                        <a href="parkinglot/addParkingLot.jsp">Add new Parking lot now.</a>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${listP==null && keySearch != null}">
                                <!-- filter start -->
                                <div class="filter ">
                                    <form method="POST" action="<%=request.getContextPath()%>/SearchParkinglotServlet">
                                        <div class="row d-flex ">
                                            <div class="col-sm-8 pr-0  ">
                                                <div class="input-group mb-3 mt-3 justify-content-end">
                                                    <div class="input-group-prepend ">
                                                        <button class="btn button-search" type="submit"
                                                                id="button-addon1">
                                                            <i class="fas fa-search"></i>
                                                        </button>
                                                    </div>
                                                    <input type="text" class="filter-search"
                                                           placeholder="Parking lot Search"
                                                           name="search"
                                                           aria-label="Example text with button addon"
                                                           aria-describedby="button-addon1"
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
                                                            <option value="1" ${filter==1?"selected":""}>Place</option>
                                                            <option value="2" ${filter==2?"selected":""}>Status</option>
                                                        </select>
                                                    </div>
                                                    <button class="btn btn-info float-right mb-0  ml-3"
                                                            type="submit">Search
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

                            <c:if test="${listP!=null}">
                                <!-- filter start -->
                                <div class="filter ">
                                    <form method="POST" action="<%=request.getContextPath()%>/SearchParkinglotServlet">
                                        <div class="row d-flex ">
                                            <div class="col-sm-8 pr-0  ">
                                                <div class="input-group mb-3 mt-3 justify-content-end">
                                                    <div class="input-group-prepend ">
                                                        <button class="btn button-search" type="submit"
                                                                id="button-addon1">
                                                            <i class="fas fa-search"></i>
                                                        </button>
                                                    </div>
                                                    <input type="text" class="filter-search"
                                                           placeholder="Parking lot Search"
                                                           name="search"
                                                           aria-label="Example text with button addon"
                                                           aria-describedby="button-addon1"
                                                           value="${keySearch}">
                                                </div>
                                            </div>
                                            <div class="col-sm-4 pl-0">
                                                <div class="input-group mb-3 mt-3 justify-content-end">
                                                    <div class="input-group-prepend">
														<span class="input-group-text" id="basic-addon1"><i
                                                                class="fas fa-filter icon3"></i>Filter By
                                                        </span>
                                                    </div>
                                                    <div class="input-group-append ">
                                                        <select class="form-control " name="filter">
                                                            <option value="1" ${filter==1?"selected":""}>Place</option>
                                                            <option value="2" ${filter==2?"selected":""}>Status</option>
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
                                            <th>ID</th>
                                            <th>Parking lot</th>
                                            <th>Place</th>
                                            <th>Area</th>
                                            <th>Price</th>
                                            <th>Status</th>
                                            <th style="width: 30%;">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${listP }" var="o">
                                            <tr>
                                                <td class="id">${o.parkId }</td>
                                                <td class="name">${o.parkName }</td>
                                                <td>${o.parkPlace }</td>
                                                <td>${o.parkArea }</td>
                                                <td>${o.parkPrice }</td>
                                                <td>${o.parkStatus }</td>
                                                <td><a href="UpdatePackinglotServlet?id=${o.parkId }"><i
                                                        class="fas fa-pen"></i>Update</a> <a class="delete"
                                                                                             href="#confirm"
                                                                                             data-toggle="modal"><i
                                                        class="fas fa-trash-alt"></i>Delete</a></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>


                                <div class="modal fade" id="confirm" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <form name="" method="Post"
                                          action="<%=request.getContextPath()%>/DeletePackinglotServlet">
                                        <div class="modal-dialog modal-dialog-centered"
                                             role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLongTitle">Confirm</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <input type="hidden" id="idParking" value="" name="id">
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
                                <nav class="text-center">
                                    <ul class="pagination mt-4">
                                        <c:choose>
                                            <c:when test="${bulen==1}">
                                                <li class="page-item ${currentPage == 1 ?"disabled":""}"><a
                                                        class="page-link"
                                                        href="SearchParkinglotServlet?id=${currentPage-1}"
                                                >Previous</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item ${currentPage == 1 ?"disabled":""}"><a
                                                        class="page-link"
                                                        href="ViewPackinglotServlet?id=${currentPage-1}"
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
                                                            href="SearchParkinglotServlet?id=${i }">${i }</a>
                                                    </li>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach begin="1" end="${numberPage }" var="i">
                                                    <li class="page-item ${currentPage==i?"active":""}"><a
                                                            class="page-link"
                                                            href="ViewPackinglotServlet?id=${i }">${i }</a>
                                                    </li>
                                                </c:forEach>
                                                <br/>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${bulen==1}">
                                                <li class="page-item ${currentPage == numberPage ? "disabled":""}"><a
                                                        class="page-link"
                                                        href="SearchParkinglotServlet?id=${currentPage+1}"
                                                >Next</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item ${currentPage == numberPage ?"disabled":""}"><a
                                                        class="page-link"
                                                        href="ViewPackinglotServlet?id=${currentPage+1}"
                                                >Next</a>
                                                </li>
                                                <br/>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </nav>
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
</body>

<script type="text/javascript">
    $(".delete").click(
        function (event) {
            var $row = $(this).closest("tr"); // Find the row
            var $id = $row.find(".id").text();
            var $name = $row.find(".name").text();

            // var id = $("#empID").val();
            $("#idParking").val($.trim($id));
            var name = $("#empName").val();
            var str = "Are you sure to delete (ID:  " + $id + " - "
                + "Name: " + $name + ")?";
            $("#modal_body").html(str);
        });
</script>
</body>

</html>
