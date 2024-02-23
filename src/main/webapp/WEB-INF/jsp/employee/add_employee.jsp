<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <style type="text/css">
        <%@ include file="/WEB-INF/css/styles.css" %>
    </style>
</head>
<body>
<div>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
</div>
<div id="container">
    <h1>Add new employee</h1>

    <form:form method="POST" modelAttribute="employeeForm">
        <table>
            <tr>
                <td class="form-group">
                    <%@ include file="/WEB-INF/jsp/forms/emailForm.jsp" %>
                </td>

                <%@ include file="/WEB-INF/jsp/forms/userForm.jsp" %>

            </tr>

            <tr>

                <td rowspan="1" class="form-group ">
                    <spring:bind path="hireDate">
                        <label for="hireDate">
                            <sup>*</sup> Hire Date
                        </label>
                        <form:input
                                id="hireDate"
                                path="hireDate"
                                pattern="\d{4}-\d{2}-\d{2}"
                                max="<%= LocalDate.now() %>"
                                type="date"
                                name="hireDate"/>

                        <div class="error">
                            <form:errors path="hireDate"/>
                        </div>
                    </spring:bind>
                </td>

                <td rowspan="1" class="form-group">
                    <form:label path="positions">
                        <sup>*</sup>Positions:
                    </form:label>
                    <form:select
                            path="positions"
                            items="${positions}"
                            itemValue="code"
                            itemLabel="name"
                            multiple="true"
                    >
                        <form:option
                                value=""
                                label="-- select an option --"
                                disabled="true"/>
                    </form:select>
                </td>
                    <%--                <td rowspan="1" class="form-group">--%>
                    <%--                    <form:label path="procedures">--%>
                    <%--                        <sup>*</sup>Procedures:--%>
                    <%--                    </form:label>--%>
                    <%--                    <form:select--%>
                    <%--                            path="procedures"--%>
                    <%--                            items="${procedures}"--%>
                    <%--                            itemValue="code"--%>
                    <%--                            itemLabel="name"--%>
                    <%--                            multiple="true"--%>
                    <%--                    >--%>
                    <%--                        <form:option--%>
                    <%--                                value=""--%>
                    <%--                                label="-- select an option --"--%>
                    <%--                                disabled="true"/>--%>
                    <%--                    </form:select>--%>
                    <%--                </td>--%>
                <td rowspan="1" class="form-group">
                    <form:label path="procedures">
                        <sup>*</sup>Procedures:
                    </form:label>
                    <div>
                        <c:forEach items="${procedures}" var="procedure">
                            <form:checkbox
                                    path="procedures"
                                    value="${procedure.code}"
                                    label="${procedure.name}"/>
                        </c:forEach>
                    </div>
                </td>
            </tr>
        </table>

        <input type="submit" value="Submit"/>
    </form:form>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
