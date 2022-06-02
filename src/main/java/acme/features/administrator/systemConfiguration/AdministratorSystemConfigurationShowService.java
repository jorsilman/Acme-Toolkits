/*
 * AuthenticatedAnnouncementShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.systemConfiguration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSystemConfigurationShowService implements AbstractShowService<Administrator, SystemConfiguration> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorSystemConfigurationRepository repository;

		// AbstractShowService<Administrator, Announcement> interface --------------


		@Override
		public boolean authorise(final Request<SystemConfiguration> request) {
			assert request != null;

			return true;
		}

		@Override
		public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
			assert request != null;

			return this.repository.getSystemConfiguration().get(0);
		}

		@Override
		public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "acceptedCurrencies", "systemCurrency", "strongSpamWords", "strongSpamThreshold", "weakSpamWords", "weakSpamThreshold");
			
			String acceptedCurrencies;
			acceptedCurrencies = this.repository.getSystemConfiguration().get(0).getAcceptedCurrencies();
			
			String[] currencies;
			currencies = acceptedCurrencies.split(",");
			
			final List<String> acceptedCurrenciesList = new ArrayList<String>();
			for (final String currency : currencies) {
				acceptedCurrenciesList.add(currency.trim());
			}
			
			model.setAttribute("acceptedCurrenciesList", acceptedCurrenciesList);
			final String moneyExchangeService = "Authenticated Money Exchange Perform Service";
			model.setAttribute("moneyExchangeService", moneyExchangeService);
		}
}
