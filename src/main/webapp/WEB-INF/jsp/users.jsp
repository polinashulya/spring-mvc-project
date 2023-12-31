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
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
</div>
<div id="main">
    <form action="/users" method="get">

        <input type="hidden" name="page" value="${param.page == '' ? '1' : param.page}" />

        <label for="sortBy">Sort By:</label>
        <select name="sortBy" id="sortBy">
            <option value="byId" <c:if test="${sortType == 'byId'}">selected</c:if>>ID</option>
            <option value="byLogin" <c:if test="${sortType == 'byLogin'}">selected</c:if>>Login</option>
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

        <label for="countryFiltering"> Country: </label>
        <select id="countryFiltering" name="countryId">
            <option disabled selected value> -- select an option --</option>
            <c:forEach items="${countries}" var="country">
                <option value="${country.id}"
                        <c:if test="${country.id == currentCountryId}">selected</c:if>>
                        ${country.name}
                </option>
            </c:forEach>
            <option value=""> none</option>
        </select>

        <label for="searchText">Search:</label>
        <input type="text" id="searchText" name="search" value="${param.search}" placeholder="Search text">

        <!-- Поле для указания количества элементов на странице -->
        <select name="pageSize" onchange="this.form.submit()">
            <option value="5" <c:if test="${param.pageSize == 5}">selected</c:if>>5 per page</option>
            <option value="10" <c:if test="${param.pageSize == 10}">selected</c:if>>10 per page</option>
            <option value="20" <c:if test="${param.pageSize == 20}">selected</c:if>>20 per page</option>
        </select>

        <script>
            function goToPage(page) {
                if (page > 0) { // Проверяем, чтобы номер страницы был больше 0
                    document.querySelector("input[name='page']").value = page;
                    document.querySelector("form").submit();
                }
            }
        </script>

        <button type="button" onclick="goToPage(${param.page - 1})" ${param.page <= 1 || param.page==null  ? 'disabled' : ''}>Previous</button>
        <button type="button" onclick="goToPage(${param.page + 1})" ${param.page * param.pageSize >= totalUsers ? 'disabled' : ''}>Next</button>

        <input type="submit" class="show-button" value="Show">

        <input hidden="hidden" name="action" value="users">
    </form>

    <table class="timecard">
        <caption>Users</caption>
        <thead>
        <tr>
            <th id="id">User`s id</th>
            <th id="login">Login</th>
            <th id="name">name</th>
            <th id="secondName">Surname</th>
            <th id="country">Country</th>
            <th id="birthDate">Birth date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.country.name}</td>
                <td>${user.birthDate}</td>
                <td>
                    <button type="button" onclick="deleteUser(${user.id})" class="delete-button">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
<script>
    function deleteUser(userId) {
        console.log("deleteUser function called with userId:", userId);

        var confirmation = confirm("Are you sure you want to delete this user?");
        if (confirmation) {
            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "/users/" + userId, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 204) {
                        alert("Users is successfully deleted");
                        window.location.reload();
                    } else {
                        alert("User deletion error");
                    }
                }
            };
            xhr.send();
        }
    }
</script>
</html>