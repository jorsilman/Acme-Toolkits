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
	
	<jstl:choose>
	<jstl:when test="${acme:anyOf(command, 'show,update,delete, publish')}">
	<acme:input-textbox code="patron.patronage.form.label.code" path="code" placeholder="XXX-000-X" readonly="TRUE"/>
	</jstl:when>
	<jstl:when test="${acme:anyOf(command, 'create') }">
	<acme:input-textbox code="patron.patronage.form.label.code" path="code" placeholder="XXX-000-X"/>
	</jstl:when>
	</jstl:choose>
	
	
	<acme:input-textarea code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<jstl:choose>
	<jstl:when test="${acme:anyOf(command, 'show,update,delete, publish')}">
	<acme:input-money code="patron.patronage.form.label.budgetInSC" path="priceInSC" readonly="true"/>
	</jstl:when>
	</jstl:choose>
	<acme:input-moment code="patron.patronage.form.label.startPeriodOfTime" path="startPeriodOfTime"/>
	<acme:input-moment code="patron.patronage.form.label.endPeriodOfTime" path="endPeriodOfTime"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>
	<jstl:choose>

	
	
	
		<jstl:when test="${acme:anyOf(command, 'show,update,delete,publish')}">
	<acme:input-textbox code="patron.patronage.list.label.publish" path="published" readonly="TRUE"/>
	</jstl:when>
	</jstl:choose>
	<br>
	<h2>Inventor:</h2>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') }">
			<acme:input-textbox code="patron.patronage.form.label.inventor-company" path="inventorCompany" readonly = "TRUE"/>
			<acme:input-textbox code="patron.patronage.form.label.inventor-statement" path="inventorStatement" readonly = "TRUE"/>
			<acme:input-textbox code="patron.patronage.form.label.inventor-link" path="inventorLink" readonly = "TRUE"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create')}">
			<acme:input-select code="patron.patronage.form.label.inventor" path="inventorId">
	   			<jstl:forEach items="${inventors}" var="inventor">
					<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getId()}" selected="${inventor.getId() == inventorId}"/>
				</jstl:forEach>
			</acme:input-select>
		</jstl:when>
	</jstl:choose>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish') && published != true}"> 
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
		<jstl:when test="${command=='create'}">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
		</jstl:when>
	</jstl:choose>	
</acme:form>
