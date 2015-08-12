package com.gyj.current;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * 缓存系统
 * @author  gyj
 * @version  [版本号, 2015年8月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CacheDemo {

	private Map<String, Object> cacheData = new HashMap<String, Object>();
	
	public static void main(String[] args) {
		
	}
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	public Object getData(String key){
		Object object = null;
		lock.readLock().lock();
		try {
			object = cacheData.get(key);
			if (object == null) {
				lock.readLock().unlock();
				
				lock.writeLock().lock();
				
				try {
					if (object == null) {
						object = "values";
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					lock.writeLock().unlock();
				}
				
				lock.readLock().lock();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.readLock().unlock();
		}
		return object;
	}

}
