package com.gyj.current;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的应用
 * @author  gyj
 * @version  [版本号, 2015年8月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		// 启动三个线程读 三个线程写
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
			System.out.println(Thread.currentThread().getName() + "写入的值为" + data);
		} catch (Exception e) {
		} finally {
			lock.writeLock().unlock();
		}
	}
}
