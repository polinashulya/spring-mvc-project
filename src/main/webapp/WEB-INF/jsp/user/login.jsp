<%--<jsp:useBean id="contextPath" scope="request" type=""/>--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8" %>--%>
<%--<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>--%>
<%--<%@ include file="/WEB-INF/components/baseTagLibs.jsp" %>--%>


<%--<t:genericpage>--%>

<%--     <jsp:attribute name="style">--%>
<%--         <link href="${contextPath}/css/login.css" rel="stylesheet">--%>
<%--    </jsp:attribute>--%>

<%--    <jsp:body>--%>

<%--        <div class="container-fluid">--%>

<%--            <form method="POST" action="${contextPath}/user/login">--%>

<%--                <div class="row">--%>

<%--                    <div class="col login-nav">--%>

<%--                    </div>--%>

<%--                    <div class="col">--%>

<%--                        <div style="margin-top: 35%; margin-left: 15%; margin-bottom: -25%; width: 50%;">--%>

<%--                            <div style="text-align: center;">--%>
<%--                                <h1 class="form-heading">--%>
<%--                                    Вход:--%>
<%--                                </h1>--%>
<%--                            </div>--%>

<%--                            <div class="form-group form-style ${error != null ? 'has-error' : ''}">--%>
<%--                                <span>${message}</span>--%>

<%--                                <label for="username">Имя пользователя:</label>--%>
<%--                                <div class="form-group input-group">--%>
<%--                                    <div class="input-group-prepend">--%>
<%--                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>--%>
<%--                                    </div>--%>
<%--                                    <input id="username" name="username" type="text" class="form-control"--%>
<%--                                           placeholder="имя пользователя"--%>
<%--                                           autofocus="true"/>--%>
<%--                                </div>--%>
<%--                                <hr/>--%>

<%--                                <label for="password">Пароль:</label>--%>
<%--                                <div class="form-group input-group">--%>
<%--                                    <div class="input-group-prepend">--%>
<%--                                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>--%>
<%--                                    </div>--%>
<%--                                    <input id="password" name="password" type="password" class="form-control"--%>
<%--                                           placeholder="пароль"/>--%>
<%--                                </div>--%>
<%--                                <hr/>--%>
<%--                                <div class="checkbox mb-3">--%>
<%--                                    <span>${error}</span>--%>
<%--                                </div>--%>
<%--                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--                                <br>--%>
<%--                                <button class="btn btn-lg btn-primary btn-block" type="submit">--%>
<%--                                    Войти--%>
<%--                                </button>--%>
<%--                                <br>--%>

<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </form>--%>
<%--        </div>--%>
<%--    </jsp:body>--%>

<%--</t:genericpage>--%>




<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h2>Login with Username and Password</h2>
<form method="POST" action="/login">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username">
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
    </div>
    <div>
        <button type="submit">Log in</button>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<div>
    <p>If you are not registered, please <a href="/user/sign-up">sign up</a>.</p>
</div>
</body>
</html>



