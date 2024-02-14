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
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
</div>
<div id="container">
    <h1>Add new employee</h1>
    <form action="/employees" method="POST">
        <table>
            <tr>
                <td class="form-group">
                    <label for="email"><sup>*</sup> Email</label>
                    <input id="email" pattern="^[a-zA-Z][a-zA-Z0-9-_.]{4,19}$"
                           required type="text" placeholder="<c:out value='${employee.email}'/>" name="email"
                           title="Email must start with a letter, be between 4 and 19 characters, and should not contain spaces.">
                </td>

                <td rowspan="1" class="form-group">
                    <label for="password"><sup>*</sup> Password</label>
                    <input id="password" pattern="^[a-zA-Z0-9-_.]{6,15}$"
                           required type="password" name="password">
                </td>

                <td rowspan="1" class="form-group">
                    <label for="phoneNumber"><sup>*</sup> Phone Number</label>
                    <input id="phoneNumber" pattern="^\+?\d{0,13}"
                           required type="tel" placeholder="<c:out value='${employee.phoneNumber}'/>" name="phoneNumber" title="Phone number">
                </td>
            </tr>

            <tr>
                <td rowspan="1" class="form-group ">
                    <label for="name"><sup>*</sup> Name</label>
                    <input id="name" pattern=".{2,30}"
                           required type="text" placeholder="<c:out value='${employee.name}'/>" name="name">
                </td>

                <td rowspan="1" class="form-group ">
                    <label for="surname"><sup>*</sup> Surname</label>
                    <input id="surname" pattern=".{2,30}"
                           required type="text" placeholder="<c:out value='${employee.surname}'/>" name="surname">
                </td>

                <td rowspan="1" class="form-group ">
                    <label for="birthDate"><sup>*</sup> Birth Date</label>
                    <input id="birthDate" pattern="\d{4}-\d{2}-\d{2}" max="<%= LocalDate.now() %>"
                           required type="date" placeholder="<c:out value='${employee.birthDate}'/>" name="birthDate">
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

            <tr>

                <td rowspan="1" class="form-group ">
                    <label for="hireDate"><sup>*</sup> Hire Date</label>
                    <input id="hireDate" pattern="\d{4}-\d{2}-\d{2}" max="<%= LocalDate.now() %>"
                           required type="date" placeholder="<c:out value='${employee.hireDate}'/>" name="hireDate">
                </td>

                <td rowspan="1" class="form-group ">
                    <label for="position"> <sup>*</sup>Positions: </label>
                    <select id="position" name="positionId">
                        <option disabled selected value> -- select an option --</option>
                        <c:forEach items="${positions}" var="position">
                            <option value="${position.id}">
                                    ${position.name}
                            </option>
                        </c:forEach>
                        <option value="">none</option>
                    </select>
                </td>

                <td rowspan="1" class="form-group ">
                    <label for="procedure"> <sup>*</sup>Procedures: </label>
                    <select id="procedure" name="procedureId">
                        <option disabled selected value> -- select an option --</option>
                        <c:forEach items="${procedures}" var="procedure">
                            <option value="${procedure.id}">
                                    ${procedure.name}
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
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
