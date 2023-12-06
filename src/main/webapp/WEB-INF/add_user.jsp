<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style type="text/css">
        <%@ include file="/WEB-INF/css/styles.css" %>
    </style>
</head>
<body>
<div>
    <%@ include file="/WEB-INF/header.jsp" %>
</div>
<div id="container">
    <h1>Add new user</h1>
    <form action="mainServlet?action=add_user" method="POST">
        <table>
            <tr>
                <td class="form-group">
                    <label for="login"><sup>*</sup> Login</label>
                    <input id="login" pattern="^[a-zA-Z][a-zA-Z0-9-_.]{4,19}$"
                           required type="text" placeholder="<c:out value='${user.login}'/>" name="login"
                           title="Login must start with a letter, be between 4 and 19 characters, and should not contain spaces.">
                </td>

                <td rowspan="1" class="form-group">
                    <label for="password"><sup>*</sup> Password</label>
                    <input id="password" pattern="^[a-zA-Z0-9-_.]{6,15}$"
                           required type="password" name="password">
                </td>
            </tr>

            <tr>
                <td rowspan="1" class="form-group ">
                    <label for="firstname"><sup>*</sup> Firstname</label>
                    <input id="firstname" pattern=".{2,30}"
                           required type="text" placeholder="<c:out value='${user.firstname}'/>" name="firstname">
                </td>

                <td rowspan="1" class="form-group ">
                    <label for="surname"><sup>*</sup> Surname</label>
                    <input id="surname" pattern=".{2,30}"
                           required type="text" placeholder="<c:out value='${user.surname}'/>" name="surname">
                </td>

                <td rowspan="1" class="form-group ">
                    <label for="birthDate"><sup>*</sup> Birth Date</label>
                    <input id="birthDate" pattern="\d{4}-\d{2}-\d{2}" max="<%= LocalDate.now() %>"
                           required type="date" placeholder="<c:out value='${user.birthDate}'/>" name="birthDate">
                </td>

                <td rowspan="1" class="form-group ">
                    <label for="country"> <sup>*</sup>Country: </label>
                    <select id="country" name="countryId">
                        <option disabled selected value> -- select an option --</option>
                        <c:forEach items="${countries}" var="country">
                            <option value="${country.id}">
                                    ${country.name}
                            </option>
                        </c:forEach>
                        <option value="">none</option>
                    </select>
                </td>
            </tr>
        </table>

        <input type="submit" value="Submit"/>
    </form>
</div>
<%@ include file="/WEB-INF/footer.jsp" %>
</body>
</html>
