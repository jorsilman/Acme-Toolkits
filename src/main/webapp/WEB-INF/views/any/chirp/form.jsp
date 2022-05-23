<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.chirp.form.label.title" path="title" placeholder="any.chirp.form.label.title"/>
	<acme:input-textbox code="any.chirp.form.label.author" path="author" placeholder="any.chirp.form.label.author"/>
	<acme:input-textbox code="any.chirp.form.label.body" path="body" placeholder="any.chirp.form.label.body"/>
	<acme:input-email code="any.chirp.form.label.email" path="email" placeholder="any.chirp.form.label.email"/>
	<jstl:if test="${command == 'create'}">
		<acme:input-checkbox code="any.chirp.form.label.confirm" path="confirm"/>
		<acme:submit code="any.chirp.form.button.create" action="/any/chirp/create"/>
	</jstl:if>
	

</acme:form>
