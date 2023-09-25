<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class=" col-sm-9 text-right">
    <span class="account">Welcome ${sessionScope.employee.name}</span>
    <span><a href="<%=request.getContextPath()%>/log-out"><i class="fas fa-sign-out-alt"></i>Logout</a></span>
</div>
