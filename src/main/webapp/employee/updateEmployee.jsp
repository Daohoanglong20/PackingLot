<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Employee</title>


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
                    <span><i class="fas fa-users"></i>Employee</span>
                </div>
                <jsp:include page="../common/header.jsp"/>
            </div>
        </div>

        <div class="content container-fluid">
            <div class="row">

                <div class="left col-sm-2">
                    <jsp:include page="../common/employeeMenu.jsp"/>
                </div>

                <div class=" right col-sm-10">
                    <div class="right-content">
                        <h1>Update Employee</h1>
                        <div class=" border-top">
                            <!-- form update start -->
                            <c:if test="${success!=null}">
                                <div class="col-sm-7 alert alert-success text-center" role="alert">
                                    <p>${success}</p>
                                    <a href="<%=request.getContextPath()%>/staffEmployeeList">View Employee list</a>
                                </div>
                            </c:if>
                            <c:if test="${fail!=null}">
                                <div class="col-sm-7 alert alert-danger text-center" role="alert">
                                    <p>${fail}</p>
                                    <a href="<%=request.getContextPath()%>/staffEmployeeList">View Employee list</a>
                                </div>
                            </c:if>
                            <div class="form-add">
                                <form name="" method="POST" action="<%=request.getContextPath()%>/staffUpdateController"
                                      onsubmit="return checkAddEmployee()">
                                    <input type="hidden" class="form-control" name="id" required
                                           placeholder="Enter full name" value="${emp.EID }" readonly>

                                    <div class="form-group row">
                                        <label for="name" class="col-sm-2 col-form-label">Full name <span
                                                class="important">(*)</span></label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="fname" id="name" required
                                                   placeholder="Enter full name" value="${emp.name }">
                                            <div id="messName" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="phone" class="col-sm-2 col-form-label">Phone number<span
                                                class="important">(*)</span></label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="phone" id="phone" required
                                                   placeholder="Enter phone number" value="${emp.phone }">
                                            <div id="messPhone" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="birhtdate" class="col-sm-2 col-form-label">Date of birth<span
                                                class="important">(*)</span> </label>
                                        <div class="col-sm-5">
                                            <input id="birhtdate" class="form-control" name="date" type="text" required
                                                   onfocus="(this.type='date')" placeholder="dd/mm/yyyy"
                                                   value="${emp.birthdate }">
                                            <div id="messBirthDate" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="sex" class="col-sm-2 col-form-label">Sex<span
                                                class="important">(*)</span></label>
                                        <div class="col-sm-5 pding-7" id="sex">
                                            <div class="custom-control custom-radio custom-control-inline ">
                                                <c:choose>
                                                    <c:when test="${emp.sex==true}">
                                                        <input type="radio" id="male" name="sex"
                                                               class="custom-control-input" value="0" checked>
                                                        <label class="custom-control-label" for="male">Male</label>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" id="male" name="sex"
                                                               class="custom-control-input" value="0">
                                                        <label class="custom-control-label" for="male">Male</label>
                                                        <br/>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                            <div class="custom-control custom-radio custom-control-inline">
                                                <c:choose>
                                                    <c:when test="${emp.sex==false}">
                                                        <input type="radio" id="female" name="sex"
                                                               class="custom-control-input" value="1" checked="checked">
                                                        <label class="custom-control-label" for="female">Female</label>

                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" id="female" name="sex"
                                                               class="custom-control-input" value="1">
                                                        <label class="custom-control-label" for="female">Female</label>
                                                        <br/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="address" class="col-sm-2 col-form-label">Address</label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="address" id="address"
                                                   placeholder="Enter address" value="${emp.address }">
                                            <div id="messAddress" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="email" class="col-sm-2 col-form-label">Email</label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="email" id="email"
                                                   placeholder="Enter email" value="${emp.email }">
                                            <div id="messEmail" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="account" class="col-sm-2 col-form-label">Account<span
                                                class="important">(*)</span> </label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control" name="account" id="account" required
                                                   placeholder="Enter account" value="${emp.account }">
                                            <div id="messAcc" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="password" class="col-sm-2 col-form-label">Password<span
                                                class="important">(*)</span> </label>
                                        <div class="col-sm-5">
                                            <input type="password" class="form-control" name="password" id="password"
                                                   required placeholder="Enter password"
                                                   value="${emp.password}">
                                            <div id="messPass" hidden class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="department" class="col-sm-2 col-form-label">Department<span
                                                class="important">(*)</span> </label>
                                        <div class="col-sm-5">
                                            <select class="form-control " name="department" id="department">
                                                <option value="Employee" ${emp.department=="Employee"?"selected":""}>
                                                    Employee
                                                </option>
                                                <option value="CPO" ${emp.department=="CPO"?"selected":""}>CPO</option>
                                                <option value="Service" ${emp.department=="Service"?"selected":""}>
                                                    Service
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-7 text-center">
                                        <a href="<%=request.getContextPath()%>/staffEmployeeList"
                                           type="button" class="btn btn-info"><i class="fas fa-backward"></i>Back</a>
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