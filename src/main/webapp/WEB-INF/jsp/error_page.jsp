<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .error-container {
            text-align: center;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 40px;
        }

        h1 {
            color: #333;
        }

        p {
            color: #777;
        }
    </style>
</head>
<body>
<div class="error-container">
    <p>
        <a href="/">
            Go back to main page
        </a>
    </p>

    <c:if test="${not empty requestScope['error']}">
        <h1>Error Details:</h1>
        <p>${requestScope['error']}</p>
    </c:if>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>