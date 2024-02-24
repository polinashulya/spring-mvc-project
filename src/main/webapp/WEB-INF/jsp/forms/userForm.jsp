<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <style type="text/css">
        <%@ include file="/WEB-INF/css/table.css" %>
    </style>
</head>
<td rowspan="1" class="form-group">
    <spring:bind path="password">
        <label for="password">
            <sup>*</sup> Password
        </label>
        <form:input
                id="password"
                value="12341234"
                path="password"
                pattern="^[a-zA-Z0-9-_.]{6,15}$"
                required="true"
                type="password"
                name="password"/>

        <div class="error">
            <form:errors path="password" cssClass="form-errors"/>
        </div>
    </spring:bind>
</td>

<td rowspan="1" class="form-group">
    <spring:bind path="phoneNumber">
        <label for="phoneNumber">
            <sup>*</sup> Phone Number
        </label>
        <form:input
                id="phoneNumber"
                value="12341234"
                path="phoneNumber"
                pattern="^\+?\d{0,13}"
                required="true"
                type="tel"
                name="phoneNumber"
                title="Phone number"/>

        <div class="error">
            <form:errors path="phoneNumber" cssClass="form-errors"/>
        </div>
    </spring:bind>
</td>

<tr>
    <td rowspan="1" class="form-group ">
        <spring:bind path="name">
            <label for="name">
                <sup>*</sup> Name
            </label>

            <form:input
                    id="name"
                    value="test"
                    path="name"
                    pattern=".{2,30}"
                    required="true"
                    type="text"
                    name="name"/>

            <div class="error">
                <form:errors path="name" cssClass="form-errors"/>
            </div>
        </spring:bind>
    </td>

    <td rowspan="1" class="form-group ">
        <spring:bind path="surname">
            <label for="surname">
                <sup>*</sup> Surname
            </label>

            <form:input
                    id="surname"
                    path="surname"
                    pattern=".{2,30}"
                    type="text"
                    name="surname"
                    value="testValue"/>

            <div class="error">
                <form:errors path="surname" cssClass="form-errors"/>
            </div>
        </spring:bind>
    </td>

    <td rowspan="1" class="form-group ">
        <spring:bind path="birthDate">
            <label for="birthDate">
                <sup>*</sup> Birth Date
            </label>
            <form:input
                    id="birthDate"
                    value="17.05.2005"
                    path="birthDate"
                    max="<%= LocalDate.now() %>"
                    type="date"
                    name="birthDate"/>

            <div class="error">
                <form:errors path="birthDate" cssClass="form-errors"/>
            </div>
        </spring:bind>
    </td>
