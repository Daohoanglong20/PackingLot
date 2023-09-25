<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dashboard border-bottom "><i class="fas fa-tachometer-alt"></i>Dashboard</div>
<button onclick="showMenuEmployee()" class="text-left function-name "><span><i class="fas fa-chart-bar "></i>Employee manager</span><span
        class="icon2"><i id="icon-function-employee" class="fas fa-angle-left "></i></span></button>

<div class="menu border-bottom ">
    <div class="list-menu" id="list-menu-employee" style="display: none;">
        <ul>
            <li id="viewEmp" class="active"><a href="<%=request.getContextPath()%>/staffEmployeeList"
                                               class="list-menu-item "><span><i class="fas fa-list "></i></span>Employee
                list</a><br></li>
            <li id="addEmp"><a href="<%=request.getContextPath()%>/employee/addEmployee.jsp"
                               class="list-menu-item "><span><i class="fas fa-plus "></i></span>Add Employee</a></li>
        </ul>
    </div>
</div>
