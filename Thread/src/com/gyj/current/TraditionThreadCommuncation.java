package com.gyj.current;

/**
 * ��;�����߳�ѭ��10�Σ� ���߳�ѭ��100�Σ� �����ػ�ѭ��50��
 * @author  gyj
 * @version  [�汾��, 2015��8��11��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class TraditionThreadCommuncation {
	public static void main(String[] args) {
		final A a = new A();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 50; i++) {
						
							a.bus();
						
				}
			}
		}).start();
		
		// ���߳�ѭ��50��
		for (int i = 0; i < 50; i++) {
			a.main();
		}
	}
	
	
}

class A{
	
	private boolean flag = true;
	public synchronized void bus()
	{
		while (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println("���߳�" + Thread.currentThread().getName());
		}
		flag = false;
		this.notify();
		
	}
	
	public synchronized void main() 
	{
		while (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < 100; i++) {
			System.out.println("���߳�" + Thread.currentThread().getName());
		}
		flag = true;
		this.notify();
		
	}
}
