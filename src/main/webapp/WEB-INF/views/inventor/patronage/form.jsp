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
	<acme:input-textbox code="patron.patronage.form.label.code" path="code" readonly="true"/>
	<acme:input-textarea code="patron.patronage.form.label.legalStuff" path="legalStuff" readonly="true"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget" readonly="true"/>
	<acme:input-money code="patron.patronage.form.label.budgetInSC" path="priceInSC" readonly="true"/>
	<acme:input-moment code="patron.patronage.form.label.creationDate" path="creationDate" readonly="true"/>
	<acme:input-moment code="patron.patronage.form.label.startPeriodOfTime" path="startPeriodOfTime" readonly="true"/>
	<acme:input-moment code="patron.patronage.form.label.endPeriodOfTime" path="endPeriodOfTime" readonly="true"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link" readonly="true"/>
	<jstl:choose>
		<jstl:when test="${status == 'PROPOSED'}">
			<acme:submit  code="inventor.patronage.form.button.update.accept" action="/inventor/patronage/update"/><br>
		</jstl:when>
	</jstl:choose>
	<br>
	<h2>Patron:</h2>
	<acme:input-textbox code="patron.patronage.form.label.patron-company" path="patronCompany"/>
	<acme:input-textbox code="patron.patronage.form.label.patron-statement" path="patronStatement"/>
	<acme:input-textbox code="patron.patronage.form.label.patron-link" path="patronLink"/>
	
	<jstl:if test="${status == 'ACCEPTED'}">
		<acme:button code="inventor.patronage.form.button.create-report" action="/inventor/patronage-report/create?patronageId=${masterId}"/>
	</jstl:if>
</acme:form>
