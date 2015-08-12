package com.gyj.current;

/**
 * ͬ���ؼ��� Synchrioized��ʹ��
 * @author  gyj
 * @version  [�汾��, 2015��8��11��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class TraditionThreadSynchrioized {
	private final String name = "guoyoujun";
	
	public static void main(String[] args) {
		System.out.println(new TraditionThreadSynchrioized().name);
		new TraditionThreadSynchrioized().init();
	}
	
	/**
	 * ��ʼ�������������߳�������
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private void init(){
		final OutPut o = new OutPut();
		// ����һ���߳�
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					o.outName("guoyoujun");
				}
			}
		}).start();
		
		// ������һ���߳�
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
