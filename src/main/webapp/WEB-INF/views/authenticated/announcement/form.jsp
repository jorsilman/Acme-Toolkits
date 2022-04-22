<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>
	<acme:input-textbox code="authenticated.announcement.form.label.body" path="body"/>
	<acme:input-textbox code="authenticated.announcement.form.label.creationMoment" path="creationMoment"/>	
	<acme:input-select code="authenticated.announcement.form.label.critical" path="criticalFlag">
		<acme:input-option code="NOT CRITICAL" value="NOT CRITICAL" selected="${criticalFlag == false}"/>
		<acme:input-option code="CRITICAL" value="CRITICAL" selected="${criticalFlag == true}"/>
	</acme:input-select>
	<acme:input-textbox code="authenticated.announcement.form.label.link" path="link"/>
	
</acme:form>



