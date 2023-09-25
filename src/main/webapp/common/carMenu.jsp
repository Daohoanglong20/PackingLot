<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dashboard border-bottom "><i class="fas fa-tachometer-alt"></i>CPO manager</div>

<button onclick="showMenuCar()" class="text-left function-name "><span><i class="fas fa-car"></i>Car manager</span><span
        class="icon2"><i id="icon-function-car" class="fas fa-angle-left "></i></span></button>
<div class="menu border-bottom ">
    <div class="list-menu" id="list-menu-car" style="display: none;">
        <ul>
            <li id="viewCar" class="${selectMenu=="viewCar"?"active":""}"><a
                    href="<%=request.getContextPath()%>/ListCar" class="list-menu-item "><span><i
                    class="fas fa-list "></i></span>Car list</a><br></li>
            <li id="addCar" class="${selectMenu=="addCar"?"active":""}"><a
                    href="<%=request.getContextPath()%>/car/addCar.jsp" class="list-menu-item "><span><i
                    class="fas fa-plus "></i></span>Add Car</a></li>
        </ul>
    </div>
</div>

<button onclick="showMenuBooking()" class="text-left function-name "><span><i class="fas fa-cart-arrow-down"></i>Booking office manager</span><span
        class="icon2"><i id="icon-function-booking" class="fas fa-angle-left "></i></span></button>
<div class="menu border-bottom ">
    <div class="list-menu" id="list-menu-booking" style="display: none;">
        <ul>
            <li id="viewBooking" class="${selectMenu=="viewBooking"?"active":""}"><a href="#"
                                                                                     class="list-menu-item "><span><i
                    class="fas fa-list "></i></span>Booking office list</a><br></li>
            <li id="addBooking" class="${selectMenu=="addBooking"?"active":""}"><a href="#"
                                                                                   class="list-menu-item "><span><i
                    class="fas fa-plus "></i></span>Add Booking office</a></li>
        </ul>
    </div>
</div>

<button onclick="showMenuParking()" class="text-left function-name "><span><i class="fas fa-map-marker-alt"></i>Parking lot manager</span><span
        class="icon2"><i id="icon-function-parking" class="fas fa-angle-left "></i></span></button>
<div class="menu border-bottom ">
    <div class="list-menu" id="list-menu-parking" style="display: none;">
        <ul>
            <li id="viewParking" class="${selectMenu=="viewParking"?"active":""}"><a
                    href="<%=request.getContextPath()%>/ViewPackinglotServlet" class="list-menu-item "><span><i
                    class="fas fa-list "></i></span>Parking lot list</a><br></li>
            <li id="addParking" class="${selectMenu=="addParking"?"active":""}"><a
                    href="<%=request.getContextPath()%>/parkinglot/addParkingLot.jsp" class="list-menu-item "><span><i
                    class="fas fa-plus "></i></span>Add Parking lot</a></li>
        </ul>
    </div>
</div>

