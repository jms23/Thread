package com.gyj.current;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ��д����Ӧ��
 * @author  gyj
 * @version  [�汾��, 2015��8��12��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		// ���������̶߳� �����߳�д
		final Queue queue = new Queue();
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						queue.get();
					}
					
				}
			}).start();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						queue.write(new Random().nextInt(1000));
					}
				}
			}).start();
		}
		
	}

}

class Queue{
	private Object oject = null;
	
	ReadWriteLock lock = new ReentrantReadWriteLock();
	public void get(){
		lock.readLock().lock();
		try {
			Thread.sleep((long)(Math.random()*1000));
			if (oject == null) {
				System.out.println(Thread.currentThread().getName() + " objec == null");
			} else {
				System.out.println(Thread.currentThread().getName() + "object data is" + oject);
			}
		} catch (Exception e) {
		} finally {
			lock.readLock().unlock();
		}
	}
	
	public void write(Object data){
		
		lock.writeLock().lock();
		try {
			Thread.sleep((long)(Math.random()*1000));
			this.oject = data;
			System.out.println(Thread.currentThread().getName() + "д���ֵΪ" + data);
		} catch (Exception e) {
		} finally {
			lock.writeLock().unlock();
		}
	}
}
