package com.gyj.current;

/**
 * 用途：子线程循环10次， 主线程循环100次， 来来回回循环50次
 * @author  gyj
 * @version  [版本号, 2015年8月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
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
		
		// 主线程循环50次
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
			System.out.println("子线程" + Thread.currentThread().getName());
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
			System.out.println("主线程" + Thread.currentThread().getName());
		}
		flag = true;
		this.notify();
		
	}
}
