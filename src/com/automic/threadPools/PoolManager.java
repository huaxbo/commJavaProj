package com.automic.threadPools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolManager {

	private static int pool_min = 1;
	private static int pool_max = 5;
	
	/**
	 * 
	 */
	private PoolManager(){}
	
	private static ThreadPoolExecutor pool = new ThreadPoolExecutor(pool_min, pool_max, 
			500L, TimeUnit.MILLISECONDS,
			new ArrayBlockingQueue<Runnable>(pool_min));
	
	/**
	 * @param task
	 */
	public synchronized static void execute(Runnable task) throws Exception{		
		try{
			if(pool.getPoolSize() < pool_max){
				pool.execute(task);					
			}else{
				Thread.sleep(1500);				
			}
		}catch(Exception e){
			throw e;
		}finally{}
		
	}
	
	public static void main(String[] args){
		for(int i=0;i<100;i++){
			try{
				execute(new ThreadTask(i+1));
			}catch(Exception e){
				i--;
				e.printStackTrace();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}		
	}
	
}
