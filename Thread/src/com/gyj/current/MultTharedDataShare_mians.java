package com.gyj.current;

/**
 * 
 * ���̹߳������
 * @author  gyj
 * @version  [�汾��, 2015��8��12��]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MultTharedDataShare_mians {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShareDatas data2 = new ShareDatas();
		new Thread(new Myrunnable11(data2)).start();
		new Thread(new Myrunnanle22(data2)).start();
		new Thread(new Myrunnable11(data2)).start();
		new Thread(new Myrunnanle22(data2)).start();
		new Thread(new Myrunnable11(data2)).start();
		new Thread(new Myrunnanle22(data2)).start();
		new Thread(new Myrunnable11(data2)).start();
		new Thread(new Myrunnanle22(data2)).start();
		new Thread(new Myrunnable11(data2)).start();
		new Thread(new Myrunnanle22(data2)).start();
	}

}

/*
 * ��Ʊ���߳�
 */
class Myrunnable11 implements Runnable{
	private ShareDatas object;
	public  Myrunnable11(ShareDatas object) {
		this.object = object;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		object.put();
	}
	
}

/*
 *��Ʊ���߳� 
 */
class Myrunnanle22 implements Runnable{
	private ShareDatas object;
	public Myrunnanle22(ShareDatas object) {
		this.object = object;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		object.take();
	}
	
}

/*
 * ҵ�������ݹ�����
 */
class ShareDatas{
	private int count = 100; //�ܹ�100��Ʊ
	
	public synchronized void put(){
		// 
		try {
			Thread.sleep(200);
			count++;
			System.out.println("����һ��Ʊ");
			
			Thread.sleep(200);
			System.out.println("Ʊ��������" + count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void take(){
		// 
				try {
					Thread.sleep(200);
					count--;
					System.out.println("����һ��Ʊ");
					
					Thread.sleep(200);
					System.out.println("Ʊ��������" + count);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	}
}