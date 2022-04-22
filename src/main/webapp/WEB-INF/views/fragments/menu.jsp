<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Provider,acme.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.announcement.list" action="/authenticated/announcement/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-jorge" action="http://www.azuaga.es/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-fran" action="https://www.realbetisbalompie.es/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-francisco" action="http://bandalasnievesolivares.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-jose" action="https://keepa.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-alberto" action="https://sevillafc.es/"/>
			<acme:menu-separator/>
					<acme:menu-suboption code="master.menu.any.chirp.list-recent" action="/any/chirp/list-recent"/>
        <acme:menu-suboption code="master.menu.any.item.list" action="/any/item/list"/>
		</acme:menu-option>
		
	<acme:menu-option code="master.menu.any.useraccount">
			<acme:menu-suboption code="master.menu.any.useraccount.consumer" action="/any/user-account/list?role=consumer"/>
			<acme:menu-suboption code="master.menu.any.useraccount.inventor" action="/any/user-account/list?role=inventor"/>
			<acme:menu-suboption code="master.menu.any.useraccount.patron" action="/any/user-account/list?role=patron"/>
			<acme:menu-suboption code="master.menu.any.useraccount.provider" action="/any/user-account/list?role=provider"/>
    </acme:menu-option>	

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list-recent"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration.form" action="/administrator/system-configuration/show"/>
		</acme:menu-option>
		


		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.patronage.list" action="/inventor/patronage/list-own"/>
      <acme:menu-suboption code="master.menu.inventor.patronageReport.list" action="/inventor/patronage-report/list-own"/>
      <acme:menu-suboption code="master.menu.inventor.item.tool" action="/inventor/item/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		

		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.patronage.list" action="/patron/patronage/list-own"/>
			<acme:menu-suboption code="master.menu.patron.patronageReport.list" action="/patron/patronage-report/list-own"/>
		</acme:menu-option>
	</acme:menu-left>
	
	<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.patronage.list" action="/patron/patronage/list-own"/>
			<acme:menu-suboption code="master.menu.patron.patronage-report.list" action="/patron/patronage-report/list-own"/>
			<acme:menu-suboption code="master.menu.patron.patron-dashboard.show" action="/patron/patron-dashboard/show"/>
		</acme:menu-option>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.announcement.list" action="/authenticated/announcement/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

