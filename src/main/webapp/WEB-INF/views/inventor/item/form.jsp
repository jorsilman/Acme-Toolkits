<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form> 
	<acme:input-textbox code="inventor.item.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.item.form.label.description" path="description"/>
	<acme:input-money code="inventor.item.form.label.retailprice" path="retailPrice"/>
	<acme:input-textbox code="inventor.item.form.label.link" path="link"/>
	<acme:input-select code="inventor.item.form.label.type" path="itemType">
		<acme:input-option value="TOOL" code="inventor.item.form.label.tool" selected ="${itemType == 'TOOL' }"/>
		<acme:input-option value ="COMPONENT" code="inventor.item.form.label.component" selected="${ itemType == 'COMPONENT' }" />
	</acme:input-select>
	
	<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create"/>
	
	
		
	
</acme:form>