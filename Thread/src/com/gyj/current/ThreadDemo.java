package com.gyj.current;

/**
 * 
 * 创建线程的方式
 * @author  gyj
 * @version  [版本号, 2015年8月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ThreadDemo {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("创建一个线程");
			}
		}).start();
		
		Thread threads = new Thread(){
			public void run() {
				System.out.println("第二种线程创建方式");
				
			};
		};
		
		threads.start();
	}
	
	
	
}

