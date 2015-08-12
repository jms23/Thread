package com.gyj.current;

import java.util.Random;

/**
 * 
 * �̱߳��������
 * @author  gyj
 * @version  [�汾��, 2015��8��12��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ThreadLocalTest {
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		// ���������߳�
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// ����һ�����ֵ
					int data = new Random().nextInt();
					System.out.println("�߳�" + Thread.currentThread().getName() + "����ֵ��" + data);
					threadLocal.set(data);
					
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get(){
			System.out.println("A���߳�" + Thread.currentThread().getName() + "��ֵΪ " + threadLocal.get());
		}
	}
	
	static class B{
		public void get(){
			System.out.println("B���߳�" + Thread.currentThread().getName() + "��ֵΪ " + threadLocal.get());
		}
	}

}
