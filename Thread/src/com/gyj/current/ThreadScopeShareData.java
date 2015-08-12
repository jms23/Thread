package com.gyj.current;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 * ���߳����ݹ���
 * @author  guoyoujun
 * @version  [�汾��, 2015��8��11��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ThreadScopeShareData {
	private static Map<Thread, Integer> threadMap = new HashMap<Thread, Integer>();
	
	public static void main(String[] args) {
		// ���������߳�
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int data = new Random().nextInt();
					// ��������
					threadMap.put(Thread.currentThread(), data);
					// ȡ������
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get(){
			System.out.println(Thread.currentThread().getName() + "�̵߳�ֵ��" + threadMap.get(Thread.currentThread()));
		}
	}
	
	static class B{
		public void get(){
			System.out.println(Thread.currentThread().getName() + "�̵߳�ֵ��" + threadMap.get(Thread.currentThread()));
		}
	}
	
}
