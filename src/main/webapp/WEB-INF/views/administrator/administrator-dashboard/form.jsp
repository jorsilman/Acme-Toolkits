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

<acme:form readonly="${readonly}">
	<acme:input-integer code="administrator.administrator-dashboard.form.label.totalNumberOfComponents" path="totalNumberOfComponents"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.averageRetailPriceOfComponentsByTechnologyAndCurrency" path="averageRetailPriceOfComponentsByTechnologyAndCurrency"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.deviationRetailPriceOfComponentsByTechnologyAndCurrency" path="deviationRetailPriceOfComponentsByTechnologyAndCurrency"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.minimumRetailPriceOfComponentsByTechnologyAndCurrency" path="minimumRetailPriceOfComponentsByTechnologyAndCurrency"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.maximumRetailPriceOfComponentsByTechnologyAndCurrency" path="maximumRetailPriceOfComponentsByTechnologyAndCurrency"/>
	
	
	<acme:input-integer code="administrator.administrator-dashboard.form.label.totalNumberOfTools" path="totalNumberOfTools"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.averageRetailPriceOfToolsByCurrency" path="averageRetailPriceOfToolsByCurrency"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.deviationRetailPriceOfToolsByCurrency" path="deviationRetailPriceOfToolsByCurrency"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.minimumRetailPriceOfToolsByCurrency" path="minimumRetailPriceOfToolsByCurrency"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.maximumRetailPriceOfToolsByCurrency" path="maximumRetailPriceOfToolsByCurrency"/>
	
	
	<acme:input-integer code="administrator.administrator-dashboard.form.label.totalNumberOfProposedPatronages" path="totalNumberOfProposedPatronages"/>
	<acme:input-integer code="administrator.administrator-dashboard.form.label.totalNumberOfAcceptedPatronages" path="totalNumberOfAcceptedPatronages"/>
	<acme:input-integer code="administrator.administrator-dashboard.form.label.totalNumberOfDeniedPatronages" path="totalNumberOfDeniedPatronages"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.averageBudgetOfPatronagesByStatus" path="averageBudgetOfPatronagesByStatus"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.deviationBudgetOfPatronagesByStatus" path="deviationBudgetOfPatronagesByStatus"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.minimumBudgetOfPatronagesByStatus" path="minimumBudgetOfPatronagesByStatus"/>
	<acme:input-textarea code="administrator.administrator-dashboard.form.label.maximumBudgetOfPatronagesByStatus" path="maximumBudgetOfPatronagesByStatus"/>
	
</acme:form>
