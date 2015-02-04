package com.sample.auth;


import java.util.Map;

import com.worklight.server.auth.api.MissingConfigurationOptionException;
import com.worklight.server.auth.api.UserIdentity;
import com.worklight.server.auth.api.UserNamePasswordLoginModule;
import com.worklight.server.bundle.api.WorklightConfiguration;

public class ConfiguredIdentityLoginModule extends UserNamePasswordLoginModule {

	private static final long serialVersionUID = 1L;

	private static final String USERNAME_PROPERTY_CONF = "username-property";
	private static final String PASSWORD_PROPERTY_CONF = "password-property";

	private String configuredUsername;
	private String configuredPassword;

	@Override
	public void init(Map<String, String> options) throws MissingConfigurationOptionException {
		String usernameProperty = options.remove(USERNAME_PROPERTY_CONF);
		if (usernameProperty == null) throw new MissingConfigurationOptionException(USERNAME_PROPERTY_CONF);
		String passwordProperty = options.remove(PASSWORD_PROPERTY_CONF);
		if (passwordProperty == null) throw new MissingConfigurationOptionException(PASSWORD_PROPERTY_CONF);
		super.init(options);

		WorklightConfiguration conf = WorklightConfiguration.getInstance();
		configuredUsername = conf.getStringProperty(usernameProperty);
		configuredPassword = conf.getStringProperty(passwordProperty);

		if (configuredUsername == null || configuredUsername.length() == 0) {
			throw new IllegalStateException("ConfiguredIdentityLoginModule cannot resolve property " + usernameProperty + ". Please check project configuration properties.");
		}

		if (configuredPassword == null || configuredPassword.length() == 0) {
			throw new IllegalStateException("ConfiguredIdentityLoginModule cannot resolve property " + usernameProperty + ". Please check project configuration properties.");
		}

	}

	@Override
	public boolean login(Map<String, Object> authenticationData) {
		populateCache(authenticationData);
		return configuredUsername.equals(username) && configuredPassword.equals(password);
	}

	@Override
	public void logout() {
		cleanup();
	}

	@Override
	public void abort() {
		cleanup();
	}

	@Override
	public UserIdentity createIdentity(String loginModule) {
		return createUserIdentity(loginModule, username, password, username, null, null);
	}
}
