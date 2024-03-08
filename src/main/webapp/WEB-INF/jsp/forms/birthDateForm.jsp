<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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