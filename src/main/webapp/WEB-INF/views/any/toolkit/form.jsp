<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.toolkit.form.label.code" path="code"/>
	<acme:input-textbox code="any.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="any.toolkit.form.label.description" path="description"/>
	<acme:input-textbox code="any.toolkit.form.label.assembly-notes" path="assemblyNotes"/>
	
	
	<acme:button code="any.toolkit.form.button.components" action="/any/item/list-component?masterId=${id}"/>
	<acme:button code="any.toolkit.form.button.tools" action="/any/item/list-tool?masterId=${id}"/>
</acme:form>