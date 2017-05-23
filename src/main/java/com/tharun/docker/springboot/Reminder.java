package com.tharun.docker.springboot;

/*
 * This is a convenience class for creating one time or repeating reminders
 */
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class Reminder {
	private static final Logger log = Logger.getLogger(Reminder.class);

	Timer timer;
	private String reminderName;

	public Reminder(String reminderName, Callback<?> callback, Long freqInSeconds, boolean repeatForever) {
		this(reminderName, callback, freqInSeconds, repeatForever, freqInSeconds);
	}

	public Reminder(String reminderName, Callback<?> callback, Long freqInSeconds, boolean repeatForever, Long delayInSeconds) {
		this.reminderName = reminderName;
		timer = new Timer(reminderName + "-- sleeping seconds:" + delayInSeconds);
		RemindTask remindTask = new RemindTask();
		remindTask.setPM(callback);
		remindTask.setRepetition(repeatForever);

		if (repeatForever) {
			timer.schedule(remindTask, delayInSeconds * 1000, freqInSeconds * 1000);
		} else {
			timer.schedule(remindTask, delayInSeconds * 1000);
		}
	}
	class RemindTask extends TimerTask {
		boolean repeatForever = false;
		Callback<?> pm = null;

		public void setPM(Callback<?> pm) {
			this.pm = pm;
		}

		public void setRepetition(boolean repeatForever) {
			this.repeatForever = repeatForever;
		}

		@Override
		public void run() {
			try{
				if (!repeatForever) {
					// Not necessary after this call
					timer.cancel();
				}
				pm.notify(null);
			}catch(Throwable t){
				log.error(t);
			}
		}
	}

	public void cancel() {
		timer.cancel();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		log.fatal("[Reminder.finalize()] - Reminder is getting Garbage Collected, This will cause termination for: " + reminderName);
	}
}

