<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="authenticated.system-configuration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.systemCurrency" path="systemCurrency"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.strongSpamWords" path="strongSpamWords"/>
	<acme:input-double code="authenticated.system-configuration.form.label.strongSpamThreshold" path="strongSpamThreshold"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.weakSpamWords" path="weakSpamWords"/>
	<acme:input-double code="authenticated.system-configuration.form.label.weakSpamThreshold" path="weakSpamThreshold"/>

	<jstl:if test="${readonly}">
		<acme:submit test="${command == 'create'}" code="authenticated.consumer.consumer.form.button.create" action="/authenticated/consumer/create"/>
		<acme:submit test="${command == 'update'}" code="authenticated.consumer.consumer.form.button.update" action="/authenticated/consumer/update"/>
	</jstl:if>
</acme:form>