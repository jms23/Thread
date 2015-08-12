package com.gyj.current;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 线程生产返回结果值
 * @author  姓名 工号
 * @version  [版本号, 2015年8月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CallableAndFuture {

	public static void main(String[] args) {
		// 定义一个单线程池
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> result = executorService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				Thread.sleep(2000);
				return "hello";
			}
		});
		
		System.out.println("等待结果返回");
		
		try {
			System.out.println("拿到结果" + result.get(1, TimeUnit.SECONDS));
			System.out.println("测试输出--------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("-----------");
		
		// 第二种方式
		ExecutorService executorService2 = Executors.newFixedThreadPool(10);
		
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService2);
		// 提交十个线程
		for (int i = 0; i < 10; i++) {
			final int task = i;
			completionService.submit(new Callable<Integer>() {
				
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return task;
				}
			});
		}
		
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println("最先返回的是" + completionService.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
