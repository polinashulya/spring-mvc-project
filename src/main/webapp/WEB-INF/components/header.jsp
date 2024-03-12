<%--<%@page contentType="text/html" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>--%>
<%--<%@ include file="/WEB-INF/components/baseTagLibs.jsp" %>--%>

<%--<header class="header mt-auto">--%>

<%--    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">--%>
<%--        <a class="navbar-brand" style="color: #fff1a4" disabled="true">--%>
<%--            Контроль морских транспортно логических операций--%>
<%--        </a>--%>
<%--        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"--%>
<%--                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--            <span class="navbar-toggler-icon"></span>--%>
<%--        </button>--%>
<%--        <div class="collapse navbar-collapse" id="navbarCollapse">--%>
<%--            <ul class="navbar-nav mr-auto">--%>


<%--                <sec:authorize access="hasRole('ROLE_CARRIER')">--%>
<%--                    <li>--%>
<%--                        <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/carrier-owner/personal-cabinet">--%>
<%--                            Личный кабинет--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                    <c:if test="${isAccountActivated}">--%>
<%--                        <li>--%>
<%--                            <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/carrier-owner/company-page">--%>
<%--                                Страница компании--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/common/transport-orders">--%>
<%--                                История заказов--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                    </c:if>--%>
<%--                </sec:authorize>--%>


<%--                <sec:authorize access="hasRole('ROLE_STOCK_OWNER')">--%>
<%--                    <li>--%>
<%--                        <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/stock-owner/personal-cabinet">--%>
<%--                            Личный кабинет--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <c:if test="${isAccountActivated}">--%>
<%--                        <li>--%>
<%--                            <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/stock-owner/company-page">--%>
<%--                                Страница компании--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/common/transport-orders">--%>
<%--                                История заказов--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                    </c:if>--%>
<%--                </sec:authorize>--%>


<%--                <sec:authorize access="hasRole('ROLE_CLIENT')">--%>
<%--                    <li>--%>
<%--                        <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/client/personal-cabinet">--%>
<%--                            Личный кабинет--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                    <c:if test="${isAccountActivated}">--%>
<%--                        <li>--%>
<%--                            <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/client/transport-order-creation">--%>
<%--                                Создание заказа на перевозку--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/common/transport-orders">--%>
<%--                                История заказов--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                    </c:if>--%>
<%--                </sec:authorize>--%>


<%--                <sec:authorize access="hasRole('ROLE_DRIVER')">--%>
<%--                    <li>--%>
<%--                        <a class="nav-link"  style="color: #a4c8ff" href="${contextPath}/user/driver/personal-cabinet">--%>
<%--                            Личный кабинет--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/common/transport-orders">--%>
<%--                            История заказов--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </sec:authorize>--%>

<%--                <sec:authorize access="!isAuthenticated()">--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/login">--%>
<%--                            Логин--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/sign-up">--%>
<%--                            Регистрация--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </sec:authorize>--%>
<%--            </ul>--%>

<%--            <ul class="navbar-nav float-right">--%>

<%--                <sec:authorize access="hasAnyAuthority('ROLE_CARRIER', 'ROLE_STOCK_OWNER')">--%>

<%--                    <span id="newOrdersCount" class=" text-danger text-uppercase mr-5 pt-2">--%>

<%--                    </span>--%>

<%--                </sec:authorize>--%>

<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/companies">--%>
<%--                        Компании--%>
<%--                    </a>--%>
<%--                </li>--%>

<%--                <sec:authorize access="isAuthenticated()">--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" style="color: #a4c8ff" href="${contextPath}/user/logout">--%>
<%--                            Выйти--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </sec:authorize>--%>
<%--            </ul>--%>

<%--        </div>--%>

<%--    </nav>--%>
<%--</header>--%>
