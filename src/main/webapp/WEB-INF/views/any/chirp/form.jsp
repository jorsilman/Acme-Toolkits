<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.chirp.form.label.title" path="title"/>
	<acme:input-textbox code="any.chirp.form.label.author" path="author"/>
	<acme:input-textbox code="any.chirp.form.label.body" path="body"/>
	<acme:input-email code="any.chirp.form.label.email" path="email"/>
	<jstl:if test="${command == 'create'}">
		<acme:input-checkbox code="any.chirp.form.label.confirm" path="confirm"/>
		<acme:submit code="any.chirp.form.button.create" action="/any/chirp/create"/>
	</jstl:if>
	

</acme:form>
