<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style type="text/css">
        <%@ include file="/WEB-INF/css/table.css" %>
    </style>
</head>
<body>
<div class="container mt-3">
    <%@ include file="/WEB-INF/jsp/common/header.jsp" %>

    <h2 class="text-center mb-3">Clients</h2>

    <div id="main">

        <c:if test="${deletionStatus == 404}">
            <div class="alert alert-warning">User not found.</div>
        </c:if>

        <form action="/clients" method="get" class="mb-4">

            <input type="hidden" name="page" value="${param.page == '' ? '1' : param.page}"/>

            <div class="row">

                <div class="col-md-4">
                    <div class="form-group">
                        <label for="sortBy">Sort By:</label>
                        <select name="sortBy" id="sortBy" class="form-control">
                            <option value="bySurname" ${sortType == 'bySurname' ? 'selected' : ''}>Surname</option>
                            <option value="byEmail" ${sortType == 'byEmail' ? 'selected' : ''}>Email</option>
                            <option value="byBirthDate" ${sortType == 'byBirthDate' ? 'selected' : ''}>Birth Date</option>
                        </select>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="form-group">
                        <label for="sortType">Sort Type:</label>
                        <select name="sortType" id="sortType" class="form-control">
                            <option value="ASC" ${sortOrder == 'ASC' ? 'selected' : ''}>Ascending</option>
                            <option value="DESC" ${sortOrder == 'DESC' ? 'selected' : ''}>Descending</option>
                        </select>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="form-group">
                        <label for="searchText">Search:</label>
                        <input type="text" id="searchText" name="search" value="${param.search}" class="form-control"
                               placeholder="Search text">
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="form-group">
                        <label for="pageSize">Page size:</label>
                        <select name="pageSize" id="pageSize" class="form-control">
                            <option value="5" <c:if test="${param.pageSize == 5}">selected</c:if>>5 per page</option>
                            <option value="10" <c:if test="${param.pageSize == 10}">selected</c:if>>10 per page</option>
                            <option value="20" <c:if test="${param.pageSize == 20}">selected</c:if>>20 per page</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12 d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">Apply Filters</button>
                </div>
            </div>

        </form>

        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th id="name">Name and Surname</th>
                                <sec:authorize access="!hasAuthority('ROLE_CLIENT')">
                <th id="email">Email</th>
                <th id="phoneNumber">Phone</th>
                <th id="birthDate">Birth Date</th>
                                </sec:authorize>
                                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <th>Action</th>
                           </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${clientPageable.elements}" var="client">
                <tr>
                    <td>${client.name} ${client.surname}</td>
                                            <sec:authorize access="!hasAuthority('ROLE_CLIENT')">
                    <td>${client.email}</td>
                    <td>${client.phoneNumber}</td>
                    <td>${client.birthDate}</td>
                                            </sec:authorize>
                                            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                    <td>
                        <form action="client/delete/${client.id}" method="post">
                            <sec:csrfInput />
                            <button type="submit" class="btn btn-danger"
                                    onclick="return confirm('Are you sure you want to delete this client?');">Delete
                            </button>
                        </form>
                    </td>
                                            </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="form-row justify-content-center mt-4">
            <div class="col-auto">
                <button type="button" class="btn btn-outline-primary"
                        onclick="goToPage(${param.page - 1})" ${param.page <= 1 || param.page == null ? 'disabled' : ''}>
                    Previous
                </button>
                <button type="button" class="btn btn-outline-primary"
                        onclick="goToPage(${param.page + 1})" ${param.page * param.pageSize >= clientPageable.totalSize ? 'disabled' : ''}>
                    Next
                </button>
            </div>
        </div>

    </div>
</div>
<script>
    function goToPage(page) {
        if (page > 0) {
            document.querySelector("input[name='page']").value = page;
            document.querySelector("form").submit();
        }
    }
</script>
</body>
</html>
