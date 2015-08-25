package com.automic.dataShare.taskServers.htkg.imulate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDGenerator {
	private static String curr;
	private static int add = 0;
	private static String dtStr = "yyMMddHHmmss";

	static {
		curr = new SimpleDateFormat(dtStr).format(new Date(System
				.currentTimeMillis()));
		new ResetTimerThread();
	}

	public static String generate() {
		return curr + getAdd();
	}

	private static synchronized String getAdd() {
		add += 1;
		if (add < 10) {
			return "000" + add;
		}
		if (add < 100) {
			return "00" + add;
		}
		if (add < 1000) {
			return "0" + add;
		}
		if (add < 9999) {
			return "" + add;
		}
		String s = "9999";
		try {
			Thread.sleep(1L);
		} catch (Exception localException) {
		} finally {
			add = 0;
		}
		return s;
	}

	private static void update() {
		curr = new SimpleDateFormat(dtStr).format(new Date(System
				.currentTimeMillis()));
	}

	private static class ResetTimerThread extends Thread {
		public ResetTimerThread() {
			setDaemon(true);
			start();
		}

		public void run() {
			while (true) {
				IDGenerator.update();
				try {
					Thread.sleep(1000L);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
