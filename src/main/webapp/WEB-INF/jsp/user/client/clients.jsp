r
<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style type="text/css">
        <%@ include file="/WEB-INF/css/table.css" %>
    </style>
</head>
<body>
<div>
    <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
</div>
<div id="main">
    <c:if test="${deletionStatus == 404}">
        <div class="alert alert-warning">
            User not found.
        </div>
    </c:if>
    <form action="/clients" method="get">

        <input type="hidden" name="page" value="${param.page == '' ? '1' : param.page}"/>

        <label for="sortBy">Sort By:</label>
        <select name="sortBy" id="sortBy">
            <option value="byEmail" <c:if test="${sortType == 'byEmail'}">selected</c:if>>Email</option>
            <option value="bySurname" <c:if test="${sortType == 'bySurname'}">selected</c:if>>Surname</option>
            <option value="byBirthDate" <c:if test="${sortType == 'byBirthDate'}">selected</c:if>>Birth Date</option>
        </select>

        <label for="sortType">Sort Type:</label>
        <select name="sortType" id="sortType">
            <option value="ASC" <c:if test="${sortType == 'ASC'}">selected</c:if>>
                Ascending
            </option>
            <option value="DESC" <c:if test="${sortType == 'DESC'}">selected</c:if>>
                Descending
            </option>
        </select>

        <label for="searchText">Search:</label>
        <input type="text" id="searchText" name="search" value="${param.search}" placeholder="Search text">

        <select name="pageSize" onchange="this.form.submit()">
            <option value="5" <c:if test="${param.pageSize == 5}">selected</c:if>>5 per page</option>
            <option value="10" <c:if test="${param.pageSize == 10}">selected</c:if>>10 per page</option>
            <option value="20" <c:if test="${param.pageSize == 20}">selected</c:if>>20 per page</option>
        </select>

        <script>
            function goToPage(page) {
                if (page > 0) {
                    document.querySelector("input[name='page']").value = page;
                    document.querySelector("form").submit();
                }
            }
        </script>

        <button type="button"
                onclick="goToPage(${param.page - 1})" ${param.page <= 1 || param.page==null  ? 'disabled' : ''}>Previous
        </button>
        <button type="button"
                onclick="goToPage(${param.page + 1})" ${param.page * param.pageSize >= clientPageable.totalSize ? 'disabled' : ''}>
            Next
        </button>

        <input type="submit" class="show-button" value="Show">

        <input hidden="hidden" name="action" value="clients">
    </form>

    <table class="timecard">
        <caption>Users</caption>
        <thead>
        <tr>
            <th id="name">Name and surname</th>
            <th id="email">Email</th>
            <th id="phoneNumber">Phone number</th>
            <th id="birthDate">Birth date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clientPageable.elements}" var="client">
            <tr>
                <td>${client.name} ${client.surname}</td>
                <td>${client.email}</td>
                <td>${client.phoneNumber}</td>
                <td>${client.birthDate}</td>
                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                    <td>
                        <form action="clients/delete/${client.id}" method="post">
                            <input type="submit" class="delete-button" value="Delete"
                                   onclick="return confirm('Are you sure?');"/>
                        </form>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>