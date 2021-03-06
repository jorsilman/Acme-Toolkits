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
	<acme:hidden-data path="patronageId"/>
	
	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="patron.patronage-report.form.label.sequenceNumber" path="sequenceNumber" readonly="true"/>
		<acme:input-moment code="patron.patronage-report.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	
	<acme:input-textarea code="patron.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="patron.patronage-report.form.label.link" path="link"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:input-checkbox code="inventor.patronage-report.form.label.confirmation" path="confirmation"/>
		<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create?patronageId=${patronageId}"/>
	</jstl:if>
</acme:form>
