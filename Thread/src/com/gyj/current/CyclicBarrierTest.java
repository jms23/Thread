package com.gyj.current;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �߳��໥�ȴ�
 * @author  ���� ����
 * @version  [�汾��, 2015��8��12��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final  CyclicBarrier cb = new CyclicBarrier(3);//ָ����Ҫ�����߳�ͬʱ������ܼ�������ִ��
		for(int i=0;i<3;i++){
		//for(int i=0;i<4;i++){ ����������߳���4������await�ﵽ3���ͻ������ߣ����������ص�4���̣߳�����4���̵߳����Ҳ���������ִ�С�����
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�1����ǰ����" + (cb.getNumberWaiting()+1) + "���Ѿ����" + (cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));		
						if (cb.getNumberWaiting() == 2) {
							System.out.println("�˶��������ǰɣ���, ����˭�ȷ��ʵ������������");
							new A().getData();
						} else {
							cb.await();//������Ҫ�ڴ˴��ȣ�ֱ�������ﵽ3
						}
						
						
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
	
	static class A{
		public void getData(){
			System.out.println(Thread.currentThread().getName());
		}
	}
}
