package com.gyj.current;

/**
 * 
 * 多线程数据共享
 * @author  gyj
 * @version  [版本号, 2015年8月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MultiThreadDataShare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 final ShareData1 data1 = new ShareData1();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 3; i++) {
					data1.put();
					System.out.println("新增共享的值j="+data1.getJ());
				}
				
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 3; i++) {
					data1.take();
					System.out.println("减少共享的值j="+data1.getJ());
				}
				
				
			}
		}).start();
	}
	
	

}

class ShareData1{
	private int j = 1;
	
	public synchronized void put(){
		j++;
	}
	
	public synchronized void take(){
		j--;
	}

	/**
	 * 获取 j
	 * @return 返回 j
	 */
	public int getJ() {
		return j;
	}

	
}
