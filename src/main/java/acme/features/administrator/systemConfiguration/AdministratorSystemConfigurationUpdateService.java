/*
 * AuthenticatedEmployerUpdateService.java
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
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSystemConfigurationRepository repository;

	// AbstractUpdateService<Authenticated, Employer> interface ---------------


	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "acceptedCurrencies", "systemCurrency", "strongSpamWords", "strongSpamThreshold", "weakSpamWords", "weakSpamThreshold");
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "acceptedCurrencies", "systemCurrency", "strongSpamWords", "strongSpamThreshold", "weakSpamWords", "weakSpamThreshold");
		
		String acceptedCurrencies;
		acceptedCurrencies = request.getModel().getAttribute("acceptedCurrencies").toString();
		
		String[] currencies;
		currencies = acceptedCurrencies.split(",");
		
		final List<String> acceptedCurrenciesList = new ArrayList<String>();
		for (final String currency : currencies) {
			acceptedCurrenciesList.add(currency.trim());
		}
		
		model.setAttribute("acceptedCurrenciesList", acceptedCurrenciesList);
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;

		SystemConfiguration result;
		result = this.repository.getSystemConfiguration().get(0);

		return result;
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		String systemCurrency;
		String acceptedCurrencies;
		systemCurrency = request.getModel().getAttribute("systemCurrency").toString();
		acceptedCurrencies = request.getModel().getAttribute("acceptedCurrencies").toString();
		
		String[] currencies;
		currencies = acceptedCurrencies.split(",");
		
		final List<String> acceptedCurrenciesList = new ArrayList<String>();
		for (final String currency : currencies) {
			acceptedCurrenciesList.add(currency.trim());
		}
		
		boolean isSystemCurrencyAccepted;
		isSystemCurrencyAccepted = (acceptedCurrenciesList.contains(systemCurrency));
		errors.state(request, isSystemCurrencyAccepted, "systemCurrency", "administrator.system-configuration.form.error.bad-system-currency");
	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<SystemConfiguration> request, final Response<SystemConfiguration> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
