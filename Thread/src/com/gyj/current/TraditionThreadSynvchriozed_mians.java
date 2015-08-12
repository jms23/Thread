package com.gyj.current;

public class TraditionThreadSynvchriozed_mians {
	Output out = new Output();
	public static void main(String[] args) {
		new TraditionThreadSynvchriozed_mians().execute();
	}
	
	private void execute(){
		// 启动一个线程
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					out.out("guoyoujun");
				}
			}
		}).start();
		
		// 启动第二个线程
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				out.out("zhoujilun");
			}
		}).start();
	}
	
	/**
	 * 
	 * count_out 一直在++
	 * count_in 每次切换了线程就从0 开始
	 * @author gyj
	 * @version  [版本号, 2015年8月11日]
	 * @see  [相关类/方法]
	 * @since  [产品/模块版本]
	 */
	private class Output{
		private int count_out = 0;
		
		public void out(String name){
			int count_in = 0;
			int length = name.length();
			synchronized (this) {
				for (int i = 0; i < length; i++) {
					try {
						Thread.sleep(100);
						System.out.println(name.charAt(i));
						count_out++;
						count_in++;
						
						System.out.println("count_out = " + count_out);
						System.out.println("count_in = " + count_in);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				System.out.println();
			}
		}
	}
}
