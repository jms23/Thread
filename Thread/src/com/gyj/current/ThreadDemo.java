package com.gyj.current;

/**
 * 
 * �����̵߳ķ�ʽ
 * @author  gyj
 * @version  [�汾��, 2015��8��11��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ThreadDemo {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("����һ���߳�");
			}
		}).start();
		
		Thread threads = new Thread(){
			public void run() {
				System.out.println("�ڶ����̴߳�����ʽ");
				
			};
		};
		
		threads.start();
	}
	
	
	
}

