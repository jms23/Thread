package com.gyj.current;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 * 多线程数据共享
 * @author  guoyoujun
 * @version  [版本号, 2015年8月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ThreadScopeShareData {
	private static Map<Thread, Integer> threadMap = new HashMap<Thread, Integer>();
	
	public static void main(String[] args) {
		// 启动两个线程
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int data = new Random().nextInt();
					// 存入数据
					threadMap.put(Thread.currentThread(), data);
					// 取出数据
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get(){
			System.out.println(Thread.currentThread().getName() + "线程的值是" + threadMap.get(Thread.currentThread()));
		}
	}
	
	static class B{
		public void get(){
			System.out.println(Thread.currentThread().getName() + "线程的值是" + threadMap.get(Thread.currentThread()));
		}
	}
	
}
