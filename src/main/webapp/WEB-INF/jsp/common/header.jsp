<%--<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <style type="text/css">--%>
<%--        <%@ include file="/WEB-INF/css/header.css" %>--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<header class="header">--%>
<%--    <div class="button-container">--%>
<%--        <a class="button" href="/">Main page</a>--%>

<%--        <sec:authorize access="isAuthenticated()">--%>
<%--            <a class="button" href="/employees">Employees</a>--%>
<%--            <sec:authorize access="!hasAuthority('ROLE_CLIENT')">--%>
<%--                <a class="button" href="/clients">Clients</a>--%>
<%--            </sec:authorize>--%>
<%--            <sec:authorize access="hasAuthority('ROLE_ADMIN')">--%>
<%--                <a class="button" href="/employees/adding_form">Add new employee</a>--%>
<%--            </sec:authorize>--%>
<%--            <sec:authorize access="hasAuthority('ROLE_ADMIN')">--%>
<%--                <a class="button" href="/clients/adding_form">Add new client</a>--%>
<%--            </sec:authorize>--%>
<%--        </sec:authorize>--%>

<%--        <sec:authorize access="isAnonymous()">--%>
<%--            <a class="button" href="/login">Login</a>--%>
<%--        </sec:authorize>--%>
<%--        <sec:authorize access="isAuthenticated()">--%>
<%--            <form action="/logout" method="post">--%>
<%--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--                <button type="submit" class="button">Logout</button>--%>
<%--            </form>--%>
<%--        </sec:authorize>--%>
<%--    </div>--%>
<%--</header>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container">
            <a class="navbar-brand" href="/">Main page</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="/employees">Employees</a>
                        </li>
                        <sec:authorize access="!hasAuthority('ROLE_CLIENT')">
                            <li class="nav-item">
                                <a class="nav-link" href="/clients">Clients</a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                            <li class="nav-item">
                                <a class="nav-link" href="/employees/adding_form">Add new employee</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/clients/adding_form">Add new client</a>
                            </li>
                        </sec:authorize>
                    </sec:authorize>
                </ul>
                <ul class="navbar-nav">
                    <sec:authorize access="isAnonymous()">
                        <li class="nav-item">
                            <a class="nav-link" href="/login">Login</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <form class="form-inline" action="/logout" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn logout-button">Logout</button>
                            </form>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</header>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
