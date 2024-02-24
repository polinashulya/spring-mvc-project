<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:bind path="email">
    <div>
        <label for="email">
            <sup>*</sup> Email
        </label>
        <form:input
                id="email"
                value="test@gmail.com"
                type="text"
                path="email"
                class="form-control"
                placeholder="Email must start with a letter, be between 4 and 19 characters, and should not contain spaces"
                minlength="1"
                maxlength="20"
                autofocus="true"/>
        <div class="error">
            <form:errors path="email"/>
        </div>
    </div>
</spring:bind>