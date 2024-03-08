<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/components/baseTagLibs.jsp" %>


<!-- password -->
<div class="row">
    <spring:bind path="password">
        <div class="col-sm-${param.labelCol eq null ? '2': param.labelCol}">
<%--            <label for="password">Введите пароль:</label>--%>
        </div>
        <div class="col-sm-${param.bodyCol eq null ? '5': param.bodyCol}">
            <div class="form-group input-group ${status.error ? 'has-error' : ''}"
                 id="show_hide_password">
                <div class="input-group-prepend">
                    <span class="input-group-text">
                        <i class="fa fa-lock"></i>
                    </span>
                </div>
                <form:input type="password"
                            path="password"
                            class="form-control"
                            placeholder="Password"/>
                <div class="input-group-addon">
                    <span class="input-group-text">
                        <a href="">
                            <i id="eye" class="fa fa-eye-slash" aria-hidden="true"></i>
                        </a>
                    </span>
                </div>
            </div>
        </div>
        <div class="col-sm-${param.errorCol eq null ? '4': param.errorCol} has-error" style="overflow: hidden;">
            <form:errors path="password"/>
        </div>
    </spring:bind>
</div>