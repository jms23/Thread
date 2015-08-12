package com.gyj.current;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
		final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(4);
		for(int i=0;i<2;i++){
			new Thread(){
				public void run(){
					while(true){
						try {
							Thread.sleep((long)(Math.random()*2000));
							System.out.println(Thread.currentThread().getName() + "准备放数据");							
							queue.put(1);
							System.out.println(Thread.currentThread().getName() + "已经放了数据，" + 							
									"队列目前有" + queue.size() + "个数据");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}
				
			}.start();
		}
		
		new Thread(){
			public void run(){
				while(true){
					try {
						//将此处的睡眠时间分别改为100�?000，观察运行结�?
						Thread.sleep(2000);
						System.out.println(Thread.currentThread().getName() + "准备取数据");
						if (queue.size() < 4) {
							System.out.println("取得这么快！ 不足四个了");
						} else {
							queue.take();
							System.out.println(Thread.currentThread().getName() + "已经放了数据，" + 							
									"队列目前有" + queue.size() + "个数据");
						}
						
										
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();			
	}
}
