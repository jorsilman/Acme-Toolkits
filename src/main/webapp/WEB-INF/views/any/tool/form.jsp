<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	
	<acme:input-textbox code="any.tool.form.label.name" path="name"/>
	<acme:input-textbox code="any.tool.form.label.code" path="code"/>
	<acme:input-textbox code="any.tool.form.label.technology" path="technology"/>
	<acme:input-textbox code="any.tool.form.label.description" path="description"/>
	<acme:input-money code="any.tool.form.label.price" path="retailPrice"/>
	<acme:input-url code="any.tool.form.label.link" path="link"/>
	<acme:input-select code="any.tool.form.label.type" path="itemType">
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${itemType == 'COMPONENT'}"/>
		<acme:input-option code="TOOL" value="TOOL" selected="${itemType == 'TOOL'}"/>
	</acme:input-select>
</acme:form>