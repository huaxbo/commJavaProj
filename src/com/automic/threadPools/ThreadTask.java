package com.automic.threadPools;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTask implements Runnable {

	private int idx = 0;
	private static AtomicInteger ai = new AtomicInteger();
	/**
	 * @param idx
	 */
	public ThreadTask(int idx){
		
		this.idx = idx;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(ai.incrementAndGet());
		System.out.println("ThreadTask-" + idx + "::" + System.currentTimeMillis());
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{}
		
	}

}
