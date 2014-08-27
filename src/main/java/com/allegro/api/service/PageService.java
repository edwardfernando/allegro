package com.allegro.api.service;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.allegro.api.dao.AbstractDAO;
import com.allegro.api.dao.PageDAO;
import com.allegro.api.model.Page;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Component
public class PageService extends Service<Page> {

	@Autowired
	private PageDAO dao;

	@Override
	protected AbstractDAO<Page> dao() {
		return dao;
	}

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat
	    .forPattern("yyyy-MM-dd'T'HH:mm:ssZ");

	@Override
	public ResponseEntity<Page> save(Page object) {

		/**
		 * Make sure no duplicated thread stored to DB
		 */
		if (isThreadExist(object.getThreadId())) {
			logger.debug("threadExist");
			return new ResponseEntity<Page>(HttpStatus.FORBIDDEN);
		}

		Config config = ConfigFactory.load();

		try {
			String threadURL = StringUtils.replace(
			    config.getString("kaskus_thread_url"),
			    "[thread_id]",
			    object.getThreadId());

			Document doc = Jsoup.connect(threadURL).get();
			Element firstSection = doc.select("section.hfeed ").first();

			object.setTitle(firstSection.select("h2.entry-title").text());
			object.setUrl(threadURL);
			object.setThreadId(object.getThreadId());
			object.setContent(firstSection.select("div.entry").first().html());
			object.setThreadCreateAt(DATE_FORMATTER.parseDateTime(firstSection.select(
			    "time.entry-date").attr("datetime")));

			return super.save(object);
		} catch (IOException e) {
			logger.error("Exception Caught", e);
			return new ResponseEntity<Page>(HttpStatus.SERVICE_UNAVAILABLE);
		}

	}

	private boolean isThreadExist(String threadID) {
		Page page = dao.execUnique("threadId", threadID);
		return page != null;
	}
}
