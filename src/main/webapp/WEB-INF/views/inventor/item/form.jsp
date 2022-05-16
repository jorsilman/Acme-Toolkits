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
		
	</acme:input-select>
	
	<jstl:choose>
		<jstl:when test="${command == 'create-tool' }">
			<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create-tool"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command,'show, update, delete')}"> 
            <acme:submit code="inventor.item.form.button.update" action="/inventor/item/update-tool"/>
            <acme:submit code="inventor.item.form.button.delete" action="delete-tool"/>
        </jstl:when>
	</jstl:choose>
	
	
	
	
		
	
</acme:form>