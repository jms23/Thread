package com.gyj.current;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 多线程锁的使用:无论如何要释放锁
 * @author  guoyoujun
 * @version  [版本号, 2015年8月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ThreeConditionCommunication {

	public static void main(String[] args) {
		
		// 实例化一个同步类
		final Bussiness bussiness = new Bussiness();
		// 启动一个线程
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				bussiness.sub1();
			}
		}).start();
		
		// 再启动一个线程
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				bussiness.sub2();
			}
		}).start();
		
		// main线程循环
		bussiness.main();
	}
	
	/**
	 * 用锁封装独立的功能类
	 * @author  gyj
	 * @version  [版本号, 2015年8月11日]
	 * @see  [相关类/方法]
	 * @since  [产品/模块版本]
	 */
	static class Bussiness{
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		
		private int shouldSub = 1;
		
		public void sub1() {
			lock.lock();
			try{
				while (shouldSub!=1) {
					try {
						condition1.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 如果==1 则运行
				for (int i = 0; i <2; i++) {
					System.out.println("--------sub1, " + i);
				}
				// 运行完了要释放锁，让2执行
				shouldSub = 2;
				condition2.signal();
			} finally {
				// 解锁
				lock.unlock();
			}
		}
		
		public void sub2() {
			lock.lock();
			try{
				while (shouldSub!=2) {
					try {
						condition2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 如果==2 则运行
				for (int i = 0; i <2; i++) {
					System.out.println("--------sub2, " + i);
				}
				// 运行完了要释放锁，让2执行
				shouldSub = 3;
				condition3.signal();
			} finally {
				// 解锁
				lock.unlock();
			}
		}
		
		public void main() {
			lock.lock();
			try{
				while (shouldSub!=3) {
					try {
						condition3.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 如果==2 则运行
				for (int i = 0; i <2; i++) {
					System.out.println("--------main, " + i);
				}
				// 运行完了要释放锁，让2执行
				shouldSub = 1;
				condition1.signal();
			} finally {
				// 解锁
				lock.unlock();
			}
		}
	}
}
