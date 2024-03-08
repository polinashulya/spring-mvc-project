<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/components/baseTagLibs.jsp" %>

<div class="row mb-4">
    <div class="col-6 mx-auto">
        <div class="row">
            <spring:bind path="image.imageFile">
                <form:input id="image" type="file" path="image.imageFile" onchange="loadFile(event)"/>
                <form:errors path="image.imageFile"/>
            </spring:bind>
            <spring:bind path="image.image">
                <form:input id="image" type="hidden" path="image.image"/>
                <form:errors path="image.image"/>
            </spring:bind>
        </div>
        <div class="row mt-3">
            <jsp:include page="/WEB-INF/jsp/forms/image/displayImage.jsp"/>
        </div>

    </div>
</div>

<script>
    const loadFile = function (event) {
        const output = document.getElementById('output');
        output.src = URL.createObjectURL(event.target.files[0]);
        output.onload = function () {
            URL.revokeObjectURL(output.src)
        }
    };
</script>