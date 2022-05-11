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

<acme:form>
	<%-- <acme:input-textbox code="patron.patronage.list.label.status" path="status"/> --%>
	<acme:input-select code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="patron.patronage.form.label.PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="patron.patronage.form.label.ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="patron.patronage.form.label.DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textarea code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.creationDate" path="creationDate"/>
	<acme:input-moment code="patron.patronage.form.label.startPeriodOfTime" path="startPeriodOfTime"/>
	<acme:input-moment code="patron.patronage.form.label.endPeriodOfTime" path="endPeriodOfTime"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>
	<br>
	<h2>Inventor:</h2>
	<acme:input-textbox code="patron.patronage.form.label.inventor-company" path="inventorCompany"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor-statement" path="inventorStatement"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor-link" path="inventorLink"/>
	
	<acme:button code="inventor.patronage.form.button.create-report" action="/inventor/patronage-report/create?patronageId=${masterId}"/>
</acme:form>
