package com.gyj.current;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Java�̳߳ص�����: �������������̳߳���ռ��Դ�ļ��ʸ��ߣ�ִ��Ч�ʸ��� 
 * @author  gyj
 * @version  [�汾��, 2015��8��12��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ThreadPoolsTest {

	public static void main(String[] args) {
		ExecutorService pools = Executors.newFixedThreadPool(3); // �����̲߳���ִ��
		ExecutorService pools1 = Executors.newSingleThreadExecutor(); // ���߳�ִ�У��߳��������Զ���һ���߳�
		ExecutorService pools2 = Executors.newCachedThreadPool(); 	// �������������̳߳�
		
		// ����10���߳�
		for (int i = 0; i < 10; i++) {
			final int task = i; // ����Ϊ���ɱ�
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
						System.out.println("�߳�����Ϊ = " + Thread.currentThread().getName() + ", task = " + task + ", j = " + j);
					}
				}
			});
		}
	}

}
