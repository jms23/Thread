package com.gyj.current;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/**
 * 
 * 计数信号量
 * @author  gyj
 * @version  [版本号, 2015年8月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		// 定义一个线程池
		ExecutorService services = Executors.newCachedThreadPool();
		// 定义一个信号量计数器
		final Semaphore semaphore = new Semaphore(3);
		
		// 开启十个线程抢占执行的资源
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + "进入, 当前已有" + (3 - semaphore.availablePermits()) + "个并发");
					
					try {
						Thread.sleep((long)(Math.random()*10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + 
							"即将离开");				
					semaphore.release();//类似于释放lock
				}
			};
			
			services.execute(runnable);
			
			Future<String> result = services.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					return "guoyojun";
				}
			});
			
			try {
				System.out.println(result.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
