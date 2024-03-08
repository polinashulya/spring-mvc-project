<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/components/baseTagLibs.jsp" %>


<%@ include file="/WEB-INF/jsp/forms/elements/password.jsp" %>
<hr/>
<!-- matchingPassword -->
<div class="row">
    <spring:bind path="matchingPassword">
        <div class="col-sm-2">
            <label>Enter confirmation password:</label>
        </div>
        <div class="col-sm-5">
            <div class="form-group input-group ${status.error ? 'has-error' : ''}"
                 id="show_hide_password_matching">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                </div>
                <form:input type="password" path="matchingPassword" class="form-control"
                            placeholder="Password"/>
                <div class="input-group-addon">
                    <span class="input-group-text">
                        <a href="">
                            <i id="eyeMatch" class="fa fa-eye-slash" aria-hidden="true"></i>
                        </a>
                    </span>
                </div>
            </div>
        </div>
        <div class="col-sm-4 has-error" style="overflow: hidden;">
            <form:errors path="matchingPassword"/>
        </div>
    </spring:bind>
</div>
<hr/>