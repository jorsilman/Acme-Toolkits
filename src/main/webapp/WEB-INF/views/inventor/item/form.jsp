<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	
	<acme:input-textbox code="inventor.item.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>
	<acme:input-textbox code="inventor.item.form.label.description" path="description"/>
	<acme:input-money code="inventor.item.form.label.price" path="retailPrice"/>
	<acme:input-textbox code="inventor.item.form.label.link" path="link"/>
	<acme:input-select code="inventor.item.form.label.itemType" path="type">
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${status == 'COMPONENT'}"/>
		<acme:input-option code="TOOL" value="TOOL" selected="${status == 'TOOL'}"/>
	</acme:input-select>
</acme:form>
