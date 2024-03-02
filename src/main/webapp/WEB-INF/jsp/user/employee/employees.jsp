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
    <form action="/employees" method="get">

        <input type="hidden" name="page" value="${param.page == '' ? '1' : param.page}"/>

        <label for="sortBy">Sort By:</label>
        <select name="sortBy" id="sortBy">

            <option value="bySurname" <c:if test="${sortType == 'bySurname'}">selected</c:if>>Surname</option>
            <option value="byEmail" <c:if test="${sortType == 'byEmail'}">selected</c:if>>Email</option>
            <option value="byHireDate" <c:if test="${sortType == 'byHireDate'}">selected</c:if>>Hire Date</option>
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

        <label for="positionFiltering"> Position: </label>
        <select id="positionFiltering" name="positionCode">
            <option disabled selected value> -- select an option --</option>
            <c:forEach items="${positions}" var="position">
                <option value="${position.code}"
                        <c:if test="${position.code == currentPositionCode}">selected</c:if>>
                        ${position.name}
                </option>
            </c:forEach>
            <option value=""> none</option>
        </select>

        <label for="procedureFiltering"> Procedure: </label>
        <select id="procedureFiltering" name="procedureCode">
            <option disabled selected value> -- select an option --</option>
            <c:forEach items="${procedures}" var="procedure">
                <option value="${procedure.code}"
                        <c:if test="${procedure.code == currentProcedureCode}">selected</c:if>>
                        ${procedure.name}
                </option>
            </c:forEach>
            <option value=""> none</option>
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
                onclick="goToPage(${param.page + 1})" ${param.page * param.pageSize >= employeePageable.totalSize ? 'disabled' : ''}>
            Next
        </button>

        <input type="submit" class="show-button" value="Show">

        <input hidden="hidden" name="action" value="employees">
    </form>

    <table class="timecard">
        <caption>Users</caption>
        <thead>
        <tr>
            <%--            <th id="id">User`s id</th>--%>
            <th id="name">Name and surname</th>
            <th id="email">Email</th>
            <th id="phoneNumber">Phone</th>
            <th id="birthDate">Birth date</th>
            <th id="hireDate">Hire date</th>
            <th id="position">Position</th>
            <th id="procedure">Procedure</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employeePageable.elements}" var="employee">
            <tr>
                    <%--                <td>${employee.id}</td>--%>
                <td>${employee.name} ${employee.surname}</td>
                <td>${employee.email}</td>
                <td>${employee.phoneNumber}</td>
                <td>${employee.birthDate}</td>
                <td>${employee.hireDate}</td>
                <td>
                    <c:forEach
                            items="${employee.positions}"
                            var="position">
                        ${position.name}<br/>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach
                            items="${employee.procedures}"
                            var="procedure">
                        ${procedure.name}<br/>
                    </c:forEach>
                </td>
                <td>
                    <form action="employees/delete/${employee.id}" method="post">
                        <input type="submit" class="delete-button" value="Delete"
                               onclick="return confirm('Are you sure?');"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>