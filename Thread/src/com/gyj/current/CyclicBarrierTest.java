package com.gyj.current;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程相互等待
 * @author  姓名 工号
 * @version  [版本号, 2015年8月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final  CyclicBarrier cb = new CyclicBarrier(3);//指定需要几个线程同时到达才能继续往下执行
		for(int i=0;i<3;i++){
		//for(int i=0;i<4;i++){ 如果开启的线程是4个，则await达到3个就会往后走，不会再拦截第4个线程，即第4个线程到达后也会继续往下执行。。。
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点1，当前已有" + (cb.getNumberWaiting()+1) + "个已经到达，" + (cb.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));		
						if (cb.getNumberWaiting() == 2) {
							System.out.println("人都到齐了是吧？！, 看看谁先访问到下面这个方法");
							new A().getData();
						} else {
							cb.await();//程序需要在此处等，直到数量达到3
						}
						
						
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
	
	static class A{
		public void getData(){
			System.out.println(Thread.currentThread().getName());
		}
	}
}
