package com.automic.timers;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimersManager{

	/**
	 * @param timerNm
	 * @param startMinu
	 * @param intv
	 * @param tk
	 */
	public static void startTimer(String timerNm,int startMinu,long intv,TimerTask tk){		
		Timer t = new Timer(timerNm,false);
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.HOUR_OF_DAY),
				startMinu,0);
		t.schedule(tk, cal.getTime(), intv * 1000);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		startTimer("timer1",0,10,new TimersTask());
		
	}
	
}
