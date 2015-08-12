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
 * �����ź���
 * @author  gyj
 * @version  [�汾��, 2015��8��12��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		// ����һ���̳߳�
		ExecutorService services = Executors.newCachedThreadPool();
		// ����һ���ź���������
		final Semaphore semaphore = new Semaphore(3);
		
		// ����ʮ���߳���ռִ�е���Դ
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
					System.out.println("�߳�" + Thread.currentThread().getName() + "����, ��ǰ����" + (3 - semaphore.availablePermits()) + "������");
					
					try {
						Thread.sleep((long)(Math.random()*10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�߳�" + Thread.currentThread().getName() + 
							"�����뿪");				
					semaphore.release();//�������ͷ�lock
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
