package com.gyj.current;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Java线程池的运用: 不限制数量的线程池抢占资源的几率更高，执行效率更快 
 * @author  gyj
 * @version  [版本号, 2015年8月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ThreadPoolsTest {

	public static void main(String[] args) {
		ExecutorService pools = Executors.newFixedThreadPool(3); // 三个线程并排执行
		ExecutorService pools1 = Executors.newSingleThreadExecutor(); // 单线程执行，线程死亡后自动启一个线程
		ExecutorService pools2 = Executors.newCachedThreadPool(); 	// 不限制数量的线程池
		
		// 启动10个线程
		for (int i = 0; i < 10; i++) {
			final int task = i; // 设置为不可变
			pools2.execute(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 3; j++) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
						System.out.println("线程名称为 = " + Thread.currentThread().getName() + ", task = " + task + ", j = " + j);
					}
				}
			});
		}
	}

}
