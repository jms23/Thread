package com.gyj.current;

/**
 * ͨ���̳д����߳�
 * 
 * @author  gyj
 * @version  [�汾��, 2015��8��11��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ThreadExtends {
	
	
	public static void main(String[] args) {
		class MyThread extends Thread{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 100;
				while(i!=0) {
					System.out.println("ѭ������"  + i + ", " + Thread.currentThread().getName());
					i--;
				}
			}
		}
		
		MyThread myThread = new MyThread();
		myThread.start();
		MyThread myThread1 = new MyThread();
		myThread1.start();
		
		System.out.println("������" + Thread.currentThread().getName());
	}
}
