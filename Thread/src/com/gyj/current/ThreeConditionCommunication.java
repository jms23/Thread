package com.gyj.current;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * ���߳�����ʹ��:�������Ҫ�ͷ���
 * @author  guoyoujun
 * @version  [�汾��, 2015��8��11��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ThreeConditionCommunication {

	public static void main(String[] args) {
		
		// ʵ����һ��ͬ����
		final Bussiness bussiness = new Bussiness();
		// ����һ���߳�
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				bussiness.sub1();
			}
		}).start();
		
		// ������һ���߳�
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				bussiness.sub2();
			}
		}).start();
		
		// main�߳�ѭ��
		bussiness.main();
	}
	
	/**
	 * ������װ�����Ĺ�����
	 * @author  gyj
	 * @version  [�汾��, 2015��8��11��]
	 * @see  [�����/����]
	 * @since  [��Ʒ/ģ��汾]
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
				// ���==1 ������
				for (int i = 0; i <2; i++) {
					System.out.println("--------sub1, " + i);
				}
				// ��������Ҫ�ͷ�������2ִ��
				shouldSub = 2;
				condition2.signal();
			} finally {
				// ����
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
				// ���==2 ������
				for (int i = 0; i <2; i++) {
					System.out.println("--------sub2, " + i);
				}
				// ��������Ҫ�ͷ�������2ִ��
				shouldSub = 3;
				condition3.signal();
			} finally {
				// ����
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
				// ���==2 ������
				for (int i = 0; i <2; i++) {
					System.out.println("--------main, " + i);
				}
				// ��������Ҫ�ͷ�������2ִ��
				shouldSub = 1;
				condition1.signal();
			} finally {
				// ����
				lock.unlock();
			}
		}
	}
}
