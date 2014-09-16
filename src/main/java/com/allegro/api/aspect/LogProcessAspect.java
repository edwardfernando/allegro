package com.allegro.api.aspect;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.allegro.api.annotation.LogProcess;
import com.allegro.api.model.Model;
//import org.slf4j.Logger;

@Aspect
@Component
public class LogProcessAspect {
	//protected final Logger logger = LoggerFactory.getLogger(getClass());
	private final Logger log = Logger.getLogger("ProcessLog");
	private FileHandler fh;

	@Before("@annotation(logProcess) && args(object,..)")
	public void testLog(LogProcess logProcess, Model object) {
		//logger.info(object.toString());
		//logger.debug("Action Type : {}",logProcess.actionType());
		//logger.debug("Message : {}",logProcess.detailProcess());

		try {
			fh = new FileHandler("/var/apps/allegro/logs/process-logging.log");
			log.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			log.info("Test logggg :P");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
