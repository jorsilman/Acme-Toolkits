<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.user-account.list.label.username"
		path="username" />
	<acme:list-column code="any.user-account.list.label.name"
		path="identity.name" />
	<acme:list-column code="any.user-account.list.label.surname"
		path="identity.surname" />
	<acme:list-column code="any.user-account.list.label.email"
		path="identity.email" />
	<acme:list-column code="any.user-account.list.label.roles"
		path="roles" />
	
	
</acme:list>