package com.allegro.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.allegro.api.dao.AccessTokenDAO;
import com.allegro.api.exception.AllegroException;
import com.allegro.api.model.AccessToken;

public class AccessTokenInterceptor extends HandlerInterceptorAdapter {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccessTokenDAO dao;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
	        Object handler) throws Exception {

		logger.debug("AccessTokenInterceptor Executed : {}", request);

		String tokenString = request.getHeader("access_token");
		AccessToken tokenObject = dao.execUnique("token", tokenString);

		boolean returnValue = true;
		if (tokenString == null) {
			returnValue = false;
			throw new AllegroException("No access_token found in your HTTP header request");
		}

		if (tokenObject == null) {
			returnValue = false;
			throw new AllegroException("Your access_token is not valid");
		}

		if (!tokenObject.isActive()) {
			returnValue = false;
			throw new AllegroException("Your access_token is not activated yet.");
		}

		if (tokenObject.isExpired()) {
			returnValue = false;
			throw new AllegroException("Your access token is expired");
		}

		return returnValue;
	}
}
