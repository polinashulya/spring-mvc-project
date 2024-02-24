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
    <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
</div>
<div id="container">
    <h1>Add new employee</h1>

    <form:form method="POST" modelAttribute="clientForm">
        <table>
            <tr>
                <td class="form-group">
                    <%@ include file="/WEB-INF/jsp/forms/emailForm.jsp" %>
                </td>

                <%@ include file="/WEB-INF/jsp/forms/userForm.jsp" %>

            </tr>
        </table>

        <input type="submit" value="Submit"/>
    </form:form>
</div>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
