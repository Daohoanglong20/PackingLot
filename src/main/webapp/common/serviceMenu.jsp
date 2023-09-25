<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<button onclick="showMenuTrip()" class="text-left function-name "><span><i class="fas fa-plane-departure"></i>Trip manager</span><span
        class="icon2"><i id="icon-function-trip" class="fas fa-angle-left "></i></span></button>
<div class="menu border-bottom ">
    <div class="list-menu" id="list-menu-trip" style="display: none;">
        <ul>
            <li><a href="<%=request.getContextPath()%>/triplist" class="list-menu-item "><span><i
                    class="fas fa-list "></i></span>Trip list</a><br></li>
            <li><a href="<%=request.getContextPath()%>/addtrip" class="list-menu-item "><span><i
                    class="fas fa-plus "></i></span>Add Trip</a></li>
        </ul>
    </div>
</div>

<button onclick="showMenuTicket()" class="text-left function-name "><span><i class="fas fa-ticket-alt"></i>Ticket manager</span><span
        class="icon2"><i id="icon-function-ticket" class="fas fa-angle-left "></i></span></button>
<div class="menu border-bottom ">
    <div class="list-menu" id="list-menu-ticket" style="display: none;">
        <ul>
            <li><a href="<%=request.getContextPath()%>/ticketlist" class="list-menu-item "><span><i
                    class="fas fa-list "></i></span>Ticket list</a><br></li>
            <li><a href="<%=request.getContextPath()%>/addTicket" class="list-menu-item "><span><i
                    class="fas fa-plus "></i></span>Add Ticket</a></li>
        </ul>
    </div>
</div>