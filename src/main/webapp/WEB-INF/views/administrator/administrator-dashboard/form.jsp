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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<acme:form readonly="${readonly}">
	<acme:input-integer
		code="administrator.administrator-dashboard.form.label.totalNumberOfComponents"
		path="totalNumberOfComponents" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.avgRPriceOfComponentsByTechnologyAndCurrency"
		path="avC" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.devRPriceOfComponentsByTechnologyAndCurrency"
		path="devC" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.minRPriceOfComponentsByTechnologyAndCurrency"
		path="minC" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.maxRPriceOfComponentsByTechnologyAndCurrency"
		path="maxC" />


	<acme:input-integer
		code="administrator.administrator-dashboard.form.label.totalNumberOfTools"
		path="totalNumberOfTools" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.avgItems"
		path="avI" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.devItems"
		path="devI" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.minItems"
		path="minI" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.maxItems"
		path="maxI" />
		
		
				
		
	<acme:input-integer
		code="administrator.administrator-dashboard.form.label.totalNumberOfProposedPatronages"
		path="totalNumberOfProposedPatronages" />
	<acme:input-integer
		code="administrator.administrator-dashboard.form.label.totalNumberOfAcceptedPatronages"
		path="totalNumberOfAcceptedPatronages" />
	<acme:input-integer
		code="administrator.administrator-dashboard.form.label.totalNumberOfDeniedPatronages"
		path="totalNumberOfDeniedPatronages" />
		<acme:input-textarea
		code="administrator.admin-dashboard.form.label.avgPatronages"
		path="avP" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.devPatronages"
		path="devP" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.minPatronages"
		path="minP" />
	<acme:input-textarea
		code="administrator.admin-dashboard.form.label.maxPatronages"
		path="maxP" />
		

</acme:form>
