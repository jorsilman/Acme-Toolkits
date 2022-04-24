<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.toolkit.form.label.code" path="code"/>
	<acme:input-textbox code="any.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="any.toolkit.form.label.description" path="description"/>
	<acme:input-textbox code="any.toolkit.form.label.assembly-notes" path="assemblyNotes"/>
	<acme:input-textbox code="any.toolkit.form.label.retail-price-euro" path="retailPriceEuro"/>
	<acme:input-textbox code="any.toolkit.form.label.retail-price-dollar" path="retailPriceDollar"/>
	<acme:input-textbox code="any.toolkit.form.label.retail-price-gbd" path="retailPriceGBD"/>
	
	
	<acme:button code="any.toolkit.form.button.items" action="/any/item/list-by-toolkit?masterId=${id}"/>

</acme:form>