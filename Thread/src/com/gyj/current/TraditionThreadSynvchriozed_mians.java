package com.gyj.current;

public class TraditionThreadSynvchriozed_mians {
	Output out = new Output();
	public static void main(String[] args) {
		new TraditionThreadSynvchriozed_mians().execute();
	}
	
	private void execute(){
		// ����һ���߳�
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					out.out("guoyoujun");
				}
			}
		}).start();
		
		// �����ڶ����߳�
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
	 * count_out һֱ��++
	 * count_in ÿ���л����߳̾ʹ�0 ��ʼ
	 * @author gyj
	 * @version  [�汾��, 2015��8��11��]
	 * @see  [�����/����]
	 * @since  [��Ʒ/ģ��汾]
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
