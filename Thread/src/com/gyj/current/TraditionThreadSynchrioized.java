package com.gyj.current;

/**
 * 同步关键字 Synchrioized的使用
 * @author  gyj
 * @version  [版本号, 2015年8月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TraditionThreadSynchrioized {
	private final String name = "guoyoujun";
	
	public static void main(String[] args) {
		System.out.println(new TraditionThreadSynchrioized().name);
		new TraditionThreadSynchrioized().init();
	}
	
	/**
	 * 初始化，启动两个线程在运行
	 * @see [类、类#方法、类#成员]
	 */
	private void init(){
		final OutPut o = new OutPut();
		// 启动一个线程
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					o.outName("guoyoujun");
				}
			}
		}).start();
		
		// 再启动一个线程
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					o.outName1("zhangxiaoxiang");
				}
				
			}
		}).start();
	}
	
	
	static class OutPut {
		
		
		public synchronized void outName(String name){
			int length = name.length();
			for (int i = 0; i < length; i++) {
				System.out.println(name.charAt(i));
			}
		}
		
		public synchronized void outName1(String name){
			int length = name.length();
			for (int i = 0; i < length; i++) {
				System.out.println(name.charAt(i));
			}
		}
	}
	
	

	

}
