package com.gyj.current;

/**
 * 
 * 多线程共享变量
 * @author  gyj
 * @version  [版本号, 2015年8月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
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
 * 退票类线程
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
 *买票类线程 
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
 * 业务处理（数据共享处理）
 */
class ShareDatas{
	private int count = 100; //总共100张票
	
	public synchronized void put(){
		// 
		try {
			Thread.sleep(200);
			count++;
			System.out.println("退了一张票");
			
			Thread.sleep(200);
			System.out.println("票的总数是" + count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void take(){
		// 
				try {
					Thread.sleep(200);
					count--;
					System.out.println("卖了一张票");
					
					Thread.sleep(200);
					System.out.println("票的总数是" + count);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	}
}