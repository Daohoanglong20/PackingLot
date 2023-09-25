<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Parking lot</title>


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
                        <h1>Update Parking lot</h1>
                        <div class=" border-top">

                            <div class="form-add">

                                <form name="" method="POST"
                                      action="<%=request.getContextPath()%>/UpdatePackinglotServlet"
                                      onsubmit="return checkAddParkingLot()">

                                    <c:if test="${success!=null}">
                                        <div class="col-sm-7 alert alert-success text-center" role="alert">
                                            <p>${success}</p>
                                            <a href="<%=request.getContextPath()%>/ViewPackinglotServlet">View Parking
                                                lot list</a>
                                        </div>
                                    </c:if>
                                    <c:if test="${fail!=null}">
                                        <div class="col-sm-7 alert alert-danger text-center" role="alert">
                                            <p>${fail}</p>
                                            <a href="<%=request.getContextPath()%>/ViewPackinglotServlet">View Parking
                                                lot list</a>
                                        </div>
                                    </c:if>
                                    <input type="hidden" class="form-control" name="parkId" required placeholder="ID"
                                           value="${parkingLot.parkId }" readonly>
                                    <div class="form-group row">
                                        <label for="parkingName" class="col-sm-2 col-form-label">Parking
                                            name <span class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" id="parkingName" name="parkingName"
                                                   required placeholder="Enter Parking name"
                                                   value="${parkingLot.parkName }">
                                            <div id="messParking" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="parkPlace" class="col-sm-2 col-form-label">Place<span
                                                class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <select class="form-control " name=parkPlace id="parkPlace">
                                                <option value="Khu Dong" ${parkingLot.parkPlace == "Khu Dong" ? 'selected="selected"' : ''}>
                                                    Khu Dong
                                                </option>
                                                <option value="Khu Tay" ${parkingLot.parkPlace == "Khu Tay" ? 'selected="selected"' : ''}>
                                                    Khu Tay
                                                </option>
                                                <option value="Khu Nam" ${parkingLot.parkPlace == "Khu Nam" ? 'selected="selected"' : ''}>
                                                    Khu Nam
                                                </option>
                                                <option value="Khu Bac" ${parkingLot.parkPlace == "Khu Bac" ? 'selected="selected"' : ''}>
                                                    Khu Bac
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="area" class="col-sm-2 col-form-label">Area
                                            <span class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" id="area" required name="area"
                                                   placeholder="Enter price" value="${parkingLot.parkArea }">
                                            <div id="messArea" hidden class="invalid-feedback"></div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="mb-0 pt-1 pb-1 font-weight-bold">(m2)</p>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="price" class="col-sm-2 col-form-label">Price
                                            <span class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" id="price" required name="price"
                                                   placeholder="Enter price" value="${parkingLot.parkPrice }">
                                            <div id="messPrice" hidden class="invalid-feedback"></div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="mb-0 pt-1 pb-1 font-weight-bold">(VNĐ)</p>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="status" class="col-sm-2 col-form-label">Status
                                            <span class="important">(*)</span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" id="status" required name="status"
                                                   placeholder="Enter status" value="${parkingLot.parkStatus }"
                                                   name=status>
                                            <div id="messStatus" hidden class="invalid-feedback"></div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="mb-0 pt-1 pb-1 font-weight-bold"></p>
                                        </div>
                                    </div>

                                    <div class="col-sm-7 text-center mt-4">
                                        <a href="<%=request.getContextPath()%>/ViewPackinglotServlet"
                                           type="button" class="btn btn-info"><i class="fas fa-backward"></i>Back</a>
                                        <button type="reset" class="btn btn-warning">
                                            <i class="fas fa-undo-alt"></i>Reset
                                        </button>
                                        <button type="submit" class="btn btn-success mb-0">
                                            <i class="fas fa-plus"></i>Update
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
<script src="<%=request.getContextPath()%>/assets/js/jquery_3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/assets//js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
</body>

</html>
