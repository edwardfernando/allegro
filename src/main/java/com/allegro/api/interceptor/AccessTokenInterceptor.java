package com.allegro.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.allegro.api.dao.AccessTokenDAO;
import com.allegro.api.exception.AllegroException;
import com.allegro.api.exception.ErrorCode;
import com.allegro.api.model.AccessToken;

public class AccessTokenInterceptor extends HandlerInterceptorAdapter {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccessTokenDAO dao;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
	        Object handler) throws Exception {

		String tokenString = request.getHeader("access_token");
		AccessToken tokenObject = dao.execUnique("token", tokenString);

		if (tokenString == null) {
			throw new AllegroException(
			                           "No access_token found in your HTTP header request",
			                           ErrorCode.AUTHENTICATION_ERROR);
		}

		if (tokenObject == null) {
			throw new AllegroException(
			                           "Your access_token is not valid",
			                           ErrorCode.AUTHENTICATION_ERROR);
		}

		if (!tokenObject.isActive()) {
			throw new AllegroException(
			                           "Your access_token is not activated yet",
			                           ErrorCode.AUTHENTICATION_ERROR);
		}

		if (tokenObject.isExpired()) {
			throw new AllegroException(
			                           "Your access token is expired",
			                           ErrorCode.AUTHENTICATION_ERROR);
		}

		return true;
	}
}
