
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profileSetting.css">
        <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900,900italic%7cMontserrat:400,700%7cOxygen:400,300,700' rel='stylesheet' type='text/css'>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/icon-fonts.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    </head>
    <body>
        <!-- Header -->
        <%@include file="Panner.jsp" %>
        <div class="container light-style flex-grow-1 container-p-y">

            <h4 class="font-weight-bold py-3 mb-4">
                Account settings
            </h4>
            <div class="card overflow-hidden">
                <div class="row no-gutters row-bordered row-border-light">
                    <div class="col-md-3 pt-0">
                        <div class="list-group list-group-flush account-settings-links">
                            <a class="list-group-item list-group-item-action active" data-toggle="list"
                               href="#account-general">General</a>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <c:if test="${sessionScope.user != null}">
                            <form id="userForm" method="post" action="updateUserProfile">
                                <div class="tab-content">
                                    <div class="tab-pane fade active show" id="account-general">
                                        <div class="card-body media align-items-center">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt
                                                 class="d-block ui-w-80">
                                        </div>
                                        <hr class="border-light m-0">
                                        <div class="card-body">
                                            <div class="form-group">
                                                <label class="form-label">Username</label>                     
                                                <input id="usernameInput" type="text" class="form-control mb-1" value="${sessionScope.user.username}" readonly>
                                                <p id="username-alert" class="text-danger"></p>
                                            </div>
                                            <div class="form-group">
                                                <label class="form-label">fullname</label>
                                                <input id="fullnameInput" type="text" class="form-control" value="${sessionScope.user.fullname}" readonly>
                                                <p id="fullname-alert" class="text-danger"></p>
                                            </div>
                                            <div class="form-group">
                                                <label class="form-label">E-mail</label>
                                                <input id="emailInput" type="text" class="form-control mb-1" value="${sessionScope.user.email}" readonly>
                                                <p id="email-alert" class="text-danger"></p>
                                            </div>
                                            <div class="form-group">
                                                <label class="form-label">phone</label>
                                                <input id="phoneInput" type="text" class="form-control" value="${sessionScope.user.phone}" readonly>
                                                <p id="phone-alert" class="text-danger"></p>
                                            </div>
                                            <div class="form-group">
                                                <label class="form-label">Address</label>
                                                <input id="addressInput" type="text" class="form-control" value="${sessionScope.user.address}" readonly>
                                                <p id="adress-alert" class="text-danger"></p>
                                            </div>
                                            <div class="text-right mt-3" style="padding-right: 20px;">
                                                <button id="editButton" type="button" class="btn btn-primary">Edit</button>&nbsp;
                                                <button id="saveButton" type="button" class="btn btn-success" style="display:none;">Save Change</button>
                                                <button id="cancelButton" type="button" class="btn btn-default" style="display:none;">Cancel</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </form>
                        </c:if>                
                    </div>
                </div>
            </div>

        </div>
        <!-- Footer -->
        <%@include file="Footer.jsp" %>



        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script>
            // JavaScript code để kiểm tra session timeout và hiển thị cảnh báo
            $(document).ready(function () {
                // Kiểm tra nếu session timeout
            <% if (request.getAttribute("sessionTimeout") != null) { %>
                alert("Session đã hết hạn, vui lòng đăng nhập lại.");
                // Chuyển hướng về trang login
                window.location.replace("${pageContext.request.contextPath}/login");
            <% } %>

                // JavaScript để chuyển đổi giữa chế độ xem và chỉnh sửa
                $('#editButton').click(function () {
                    $('#fullnameInput, #emailInput, #phoneInput, #addressInput').prop('readonly', false);
                    $('#editButton').hide();
                    $('#saveButton, #cancelButton').show();
                });

                $('#cancelButton').click(function () {
                    $('#fullnameInput, #emailInput, #phoneInput, #addressInput').val(function () {
                        return originalValues[this.id];
                    }).prop('readonly', true);
                    $('#editButton').show();
                    $('#saveButton, #cancelButton').hide();
                });

            });
        </script>
        <script>
            var originalValues = {
                username: "${sessionScope.user.username}",
                fullname: "${sessionScope.user.fullname}",
                email: "${sessionScope.user.email}",
                phone: "${sessionScope.user.phone}",
                address: "${sessionScope.user.address}"
            };
        </script>
        <script src="${pageContext.request.contextPath}/js/profileEdit.js"></script>
        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">


<!-- include jQuery -->
        <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <!-- include jQuery -->
        <script src="${pageContext.request.contextPath}/js/plugins.js"></script>
        <!-- include jQuery -->
        <script src="${pageContext.request.contextPath}/js/jquery.main.js"></script>


    </body>
</html>
