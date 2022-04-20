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

<acme:list>
	<acme:list-column code="inventor.item.list.label.name" path="name" width="10%"/>
	<acme:list-column code="inventor.item.list.label.code" path="code"/>
	<acme:list-column code="inventor.item.list.label.technology" path="technology"/>
	<acme:list-column code="inventor.item.list.label.description" path="description"/>
	<acme:list-column code="inventor.item.list.label.retailPrice" path="retailPrice"/>
	<acme:list-column code="inventor.item.list.label.link" path="link"/>
	<acme:list-column code="inventor.item.list.label.itemType" path="itemType"/>
</acme:list>
