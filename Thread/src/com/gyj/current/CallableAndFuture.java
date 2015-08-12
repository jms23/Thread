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
 * �߳��������ؽ��ֵ
 * @author  ���� ����
 * @version  [�汾��, 2015��8��12��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class CallableAndFuture {

	public static void main(String[] args) {
		// ����һ�����̳߳�
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> result = executorService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				Thread.sleep(2000);
				return "hello";
			}
		});
		
		System.out.println("�ȴ��������");
		
		try {
			System.out.println("�õ����" + result.get(1, TimeUnit.SECONDS));
			System.out.println("�������--------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("-----------");
		
		// �ڶ��ַ�ʽ
		ExecutorService executorService2 = Executors.newFixedThreadPool(10);
		
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService2);
		// �ύʮ���߳�
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
				System.out.println("���ȷ��ص���" + completionService.take().get());
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
