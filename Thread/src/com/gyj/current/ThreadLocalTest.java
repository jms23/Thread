package com.gyj.current;

import java.util.Random;

/**
 * 
 * 线程本地类测试
 * @author  gyj
 * @version  [版本号, 2015年8月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ThreadLocalTest {
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		// 启动两个线程
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// 产生一个随机值
					int data = new Random().nextInt();
					System.out.println("线程" + Thread.currentThread().getName() + "产生值：" + data);
					threadLocal.set(data);
					
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get(){
			System.out.println("A：线程" + Thread.currentThread().getName() + "的值为 " + threadLocal.get());
		}
	}
	
	static class B{
		public void get(){
			System.out.println("B：线程" + Thread.currentThread().getName() + "的值为 " + threadLocal.get());
		}
	}

}
