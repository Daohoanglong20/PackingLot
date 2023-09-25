<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="assets/css/styleLogin.css">
    <title>Login</title>
</head>
<body>
<div id="Login">
    <div class="container">
        <div id="Login-row" class="row justify-content-center align-items-center">
            <div id="Login-column" class="col-md-6">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}">
                            ${message}
                    </div>
                </c:if>
                <div id="Login-box" class="col-md-12">
                    <form action="<c:url value="/Login"/>" id="Login-form" class="form" method="post">
                        <h3 class="text-center text-info">Login</h3>

                        <div></div>
                        <div class="form-group">
                            <label for="account" class="text-info">Account:</label><br>
                            <input type="text" name="account" id="account" class="form-control"
                                   placeholder="Enter account" required>
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Password:</label><br>
                            <input type="password" name="password" id="password" class="form-control"
                                   placeholder="Enter password" required>
                        </div>
                        <div class="form-group">
                            <label><input id="remember" name="remember"
                                          type="checkbox" ${remember == "ON"?"checked":""}>Remember me</label><br>
                            <input type="hidden" value="Login" name="action"/>
                            <button type="submit" class="btn btn-info btn-md">Đăng nhập</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>