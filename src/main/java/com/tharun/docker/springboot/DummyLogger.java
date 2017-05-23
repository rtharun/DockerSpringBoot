package com.tharun.docker.springboot;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DummyLogger {
	private static final Logger log = LoggerFactory.getLogger(DummyLogger.class);

	private static int counter;

	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy hh:mm:ss");

	public void logStuff() {
		new Reminder("logwatch-reminder", new Callback<String>() {
			@Override
			public void notify(String t) {
				String hostName = "";
				try {
					hostName = InetAddress.getLocalHost().getHostName();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}

				Calendar cal = Calendar.getInstance();

				log.info("----------- Writing sample log [" + (counter) + "] ----------");
				log.info("Time is ---" + sdf.format(cal.getTime()));
				log.info("UUID is ---" + UUID.randomUUID());
				log.info("Host is ---" + hostName);
				log.info("---------------------------------------------------------");
				counter += 1;
			}
		}, 5L, true);
	}
}
