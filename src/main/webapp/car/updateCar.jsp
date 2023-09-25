<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Car</title>


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
                        <h1>Update Car</h1>
                        <div class=" border-top">

                            <div class="form-add">
                                <form name="" method="post" action="<%=request.getContextPath()%>/updateCar"
                                      onsubmit="return checkAddCar()">
                                    <c:if test="${success!=null}">
                                        <div class="col-sm-7 alert alert-success text-center" role="alert">
                                            <p>${success}</p>
                                            <a href="<%=request.getContextPath()%>/ListCar">View Car list</a>
                                        </div>
                                    </c:if>
                                    <c:if test="${fail!=null}">
                                        <div class="col-sm-7 alert alert-danger text-center" role="alert">
                                            <p>${fail}</p>
                                            <a href="<%=request.getContextPath()%>/ListCar">View Car list</a>
                                        </div>
                                    </c:if>
                                    <div class="form-group row">
                                        <label for="license" class="col-sm-2 col-form-label">License plate <span
                                                class="important">(*)</span></label>
                                        <div class="col-sm-5">
                                            <input type="text" name="license" class="form-control " readonly
                                                   id="license" required placeholder="Enter License plate"
                                                   value="${car.lincensePlate}">
                                            <div id="messLicense" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="carType" class="col-sm-2 col-form-label">Car type</label>
                                        <div class="col-sm-5">
                                            <input type="text" name="carType" class="form-control" id="carType"
                                                   placeholder="Enter car type" value="${car.carType}">
                                            <div id="messCarType" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="carCorlor" class="col-sm-2 col-form-label">Car color</label>
                                        <div class="col-sm-5">
                                            <input type="text" name="carColor" class="form-control" id="carCorlor"
                                                   placeholder="Enter car color" value="${car.carColor}">
                                            <div id="messCarColor" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="company" class="col-sm-2 col-form-label">Company<span
                                                class="important">(*)</span> </label>
                                        <div class="col-sm-5">
                                            <select class="form-control " id="company" name="company">
                                                <option value="Hoang Long" ${car.company == "Hoang Long"?"selected":"" }>
                                                    Hoang Long
                                                </option>
                                                <option value="Cam Van" ${car.company == "Cam Van"?"selected":"" }>Cam
                                                    Van
                                                </option>
                                                <option value="Phuong Trang" ${car.company == "Phuong Trang"?"selected":"" }>
                                                    Phuong Trang
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="parkinglot" class="col-sm-2 col-form-label">Parking lot<span
                                                class="important">(*)</span> </label>
                                        <div class="col-sm-5">
                                            <select class="form-control " id="parkinglot" name="parkinglot">
                                                <option value="1" ${car.parkId == 1?"selected":"" }>Bai so 1</option>
                                                <option value="2" ${car.parkId == 2?"selected":"" }>Bai so 2</option>
                                                <option value="3" ${car.parkId == 3?"selected":"" }>Bai so 3</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-7 text-center mt-4">
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
<script src="<%=request.getContextPath()%>/assets/js/jquery_3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/assets//js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
</body>

</html>
