package com.allegro.api.service;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.allegro.api.annotation.LogProcess;
import com.allegro.api.dao.AbstractDAO;
import com.allegro.api.dao.UserDAO;
import com.allegro.api.exception.AllegroException;
import com.allegro.api.exception.ErrorCode;
import com.allegro.api.model.User;

@Component
public class UserService extends Service<User> {
	private final String PROFILE_URL = "http://www.kaskus.co.id/profile/";

	@Autowired
	private UserDAO userDAO;

	@Override
	protected AbstractDAO<User> dao() {
		return userDAO;
	}

	@Override
	@LogProcess(className = User.class)
	public ResponseEntity<User> save(User object) {

		if (null != userDAO.findByKaskusId(object)) {
			throw new AllegroException(StringUtils.join(
				"Kaskus ID ",
				object.getKaskusId(),
					" already exit"), ErrorCode.RESOURCE_EXIST);
		}

		String token = UUID.randomUUID().toString();
		object.setToken(token);
		object.setVerified(false);
		object.setVerifiedDate(null);
		return super.save(object);
	}

	@LogProcess(className = User.class)
	public ResponseEntity<User> verifyUser(User user) {
		try {
			Document profileDoc = Jsoup.connect(PROFILE_URL + user.getKaskusId()).get();
			Elements bioElements = profileDoc
					.select("div#main >  div#kk-container > div#main > div.row > div.col.grid-12 > div#details-header > div.row > div.col.grid-5 > div.group-desc > div.description");
			String bioContent = bioElements.text().replace("Bio", "").trim();

			User userFound = userDAO.findByKaskusId(user);

			if (userFound != null) {
				String token = userFound.getToken();
				if (StringUtils.equals(token, bioContent)) {
					logger.debug("Kaskus ID {} is verified!", user.getKaskusId());
					userFound.setVerified(true);
					userFound.setVerifiedDate(DateTime.now());
					super.update(userFound);
				} else {
					logger.debug(
						"Token doesn't match! Kaskus ID {} is NOT verified",
						user.getKaskusId());
				}
			} else {
				logger.debug("Kaskus ID {} is NOT found!", user.getKaskusId());
			}

			return new ResponseEntity<User>(HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}

	}
}
