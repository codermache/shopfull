<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.BillDetail"%>
<%@page import="java.util.Vector"%>
<%@page import="dal.BillDAO"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Bill Orders</title>
    </head>
    <body>
        <!-- Header -->
        <%@include file="Panner.jsp" %>
        <!-- Body -->
        
        <div class="col-lg-8 table-responsive mb-5">
            <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">All Bill Orders for User ID: <%= request.getParameter("userId") %></h1>
            
             <!-- Form tìm kiếm -->
        <form action="BillMember" method="GET">
            <label for="searchDate">Search by Date:</label>
            <input type="date" id="searchDate" name="searchDate" required>
            <button type="submit">Search</button>
        </form>
            <table class="table table-bordered text-center mb-0">
                <thead class="bg-secondary text-dark">
                    <tr>
                        <th>Bill ID</th>
                        <th>Customer Name</th>
                        <th>Created Date</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>SubTotal</th>
                    </tr>
                </thead>
                <tbody class="align-middle">
                    <c:set var="total" value="0" />
                    <c:forEach var="billDetail" items="${billDetails}">
                        <tr>
                            <td class="align-middle">${billDetail.id}</td>
                            <td class="align-middle">${billDetail.customerName}</td>
                            <td class="align-middle">${billDetail.created_date}</td>
                            <td class="align-middle">
                                <img src="${billDetail.image_url}" alt="" style="width: 50px" />
                                ${billDetail.productName}
                            </td>
                            <td class="align-middle">${billDetail.productQuantity}</td>
                            <td class="align-middle">${Math.round(billDetail.subTotal)*1.0}</td>
                        </tr>
                        <c:set var="subtotal" value="${billDetail.subTotal}" />
                        <c:set var="total" value="${total + subtotal}" />
                    </c:forEach>
                </tbody>
            </table>
            <div class="d-flex justify-content-between mt-2">
                <h5 class="font-weight-bold">Total</h5>
                <h5 class="font-weight-bold">$${Math.round(total)*1.0}</h5>
            </div>
        </div>
        <!-- Footer -->
        <%@include file="Footer.jsp" %>
    </body>
</html>
