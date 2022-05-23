<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.quantity.list.label.item.code" path="item.code" width="20%"/>
	<acme:list-column code="inventor.quantity.list.label.item.name" path="item.name" width="20%"/>
	<acme:list-column code="inventor.quantity.list.label.number" path="number" width="20%"/>
	<acme:list-column code="inventor.quantity.list.label.item.retailPrice" path="item.retailPrice" width="20%"/>	
</acme:list>


<jstl:choose>
<jstl:when test="${published == false}">

<acme:button code="inventor.quantity.list.button.createComponent" action="/inventor/quantity/create-component?masterId=${masterId}"/>
<acme:button code="inventor.quantity.list.button.createTool" action="/inventor/quantity/create-tool?masterId=${masterId}"/>

</jstl:when>
</jstl:choose>	