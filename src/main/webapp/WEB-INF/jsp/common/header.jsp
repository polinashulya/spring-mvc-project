<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <style type="text/css">
        <%@ include file="/WEB-INF/css/header.css" %>
    </style>
</head>
<body>
<header class="header">
    <div class="button-container">
        <a class="button" href="/">Main page</a>

        <sec:authorize access="isAuthenticated()">
            <a class="button" href="/employees">Employees</a>
            <sec:authorize access="!hasAuthority('ROLE_CLIENT')">
                <a class="button" href="/clients">Clients</a>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <a class="button" href="/employees/adding_form">Add new employee</a>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <a class="button" href="/clients/adding_form">Add new client</a>
            </sec:authorize>
        </sec:authorize>

        <sec:authorize access="isAnonymous()">
            <a class="button" href="/login">Login</a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="button">Logout</button>
            </form>
        </sec:authorize>
    </div>
</header>
</body>
</html>
