<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
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
<h1>Welcome to the main page</h1>
<c:redirect url = "mainWindow?action=main"/>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>