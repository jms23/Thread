package com.gyj.current;

/**
 * 通过继承创建线程
 * 
 * @author  gyj
 * @version  [版本号, 2015年8月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ThreadExtends {
	
	
	public static void main(String[] args) {
		class MyThread extends Thread{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 100;
				while(i!=0) {
					System.out.println("循环次数"  + i + ", " + Thread.currentThread().getName());
					i--;
				}
			}
		}
		
		MyThread myThread = new MyThread();
		myThread.start();
		MyThread myThread1 = new MyThread();
		myThread1.start();
		
		System.out.println("主进程" + Thread.currentThread().getName());
	}
}
