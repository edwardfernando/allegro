package com.allegro.api.service;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.allegro.api.dao.AbstractDAO;
import com.allegro.api.dao.UserDAO;
import com.allegro.api.model.User;

@Component
public class UserService extends Service<User> {
	private final String PROFILE_URL = "http://www.kaskus.co.id/profile/";

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MongoTemplate template;


	@Override
	protected AbstractDAO<User> dao() {
		return userDAO;
	}

	@Override
	public ResponseEntity<User> save(User object) {
		String token = UUID.randomUUID().toString();
		object.setToken(token);
		object.setVerified(false);
		object.setVerifiedDate(null);
		return super.save(object);
	}

	public void verifyUser (User user) throws IOException {
		Document profileDoc = Jsoup.connect(PROFILE_URL+user.getKaskusId()).get();
		Elements bioElements = profileDoc.select("div#main >  div#kk-container > div#main > div.row > div.col.grid-12 > div#details-header > div.row > div.col.grid-5 > div.group-desc > div.description");
		String bioContent = bioElements.text().replace("Bio", "").trim();

		boolean isUserFound = template.exists(new Query(Criteria.where("kaskusId").is(user.getKaskusId())), User.class);
		User userFound = template.findOne(new Query(Criteria.where("kaskusId").is(user.getKaskusId())), User.class);

		if (isUserFound) {
			String token = userFound.getToken();
			if (StringUtils.equals(token, bioContent)) {
				logger.debug("Kaskus ID {} is verified!",user.getKaskusId());
				userFound.setVerified(true);
				userFound.setVerifiedDate(DateTime.now());
				super.update(userFound);
			} else {
				logger.debug("Token doesn't match! Kaskus ID {} is NOT verified",user.getKaskusId());
			}
		} else {
			logger.debug("Kaskus ID {} is NOT found!", user.getKaskusId());
		}


	}
}
