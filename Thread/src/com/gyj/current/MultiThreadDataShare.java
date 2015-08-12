package com.gyj.current;

/**
 * 
 * ���߳����ݹ���
 * @author  gyj
 * @version  [�汾��, 2015��8��12��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
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
					System.out.println("���������ֵj="+data1.getJ());
				}
				
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 3; i++) {
					data1.take();
					System.out.println("���ٹ����ֵj="+data1.getJ());
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
	 * ��ȡ j
	 * @return ���� j
	 */
	public int getJ() {
		return j;
	}

	
}
