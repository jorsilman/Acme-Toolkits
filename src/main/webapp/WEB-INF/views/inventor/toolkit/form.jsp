<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
		<acme:input-textbox code="inventor.toolkit.form.label.code" path="code" placeholder="inventor.toolkit.form.placeholder.code"/>
		</jstl:when>
		
		<jstl:when test="${command != 'create'}">
			<acme:input-textbox code="inventor.toolkit.form.label.code" path="code" placeholder="inventor.toolkit.form.placeholder.code" readonly="true"/>
		</jstl:when>
		
	</jstl:choose>

		
			<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
			<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
			<acme:input-textarea code="inventor.toolkit.form.label.assembly-notes" path="assemblyNotes"/>
			<acme:input-url code="inventor.toolkit.form.label.link" path="link"/>

	
	<jstl:choose>	
		<jstl:when test="${published == false && acme:anyOf(command, 'show,update,delete,publish')}">
			<acme:button code="inventor.toolkit.form.button.quantities" action="/inventor/quantity/list?masterId=${id}"/>
			<acme:submit code="inventor.toolkit.form.button.update" action="update"/>
			<acme:submit code="inventor.toolkit.form.button.delete" action="delete"/>
			<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish?id=${id}"/>
		</jstl:when>
	
		<jstl:when test="${published == true}">
			
			<acme:input-money code="inventor.toolkit.form.label.retail-price" path="retailPriceTookit"/>
			
			<acme:button code="inventor.toolkit.form.button.quantities" action="/inventor/quantity/list?masterId=${id}"/>
		</jstl:when>
			
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
		</jstl:when>
	
	
	</jstl:choose>	
	
</acme:form>