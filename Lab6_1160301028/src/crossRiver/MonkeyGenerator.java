package crossRiver;

import java.util.ArrayList;

public class MonkeyGenerator extends Thread{
	/**
	 * interval 	休眠时间
	 * allNum	需要创建的猴子总数
	 * proNum	已经创建的猴子总数
	 * maxSpeed		猴子的最大速度
	 * sameStr		是否采取相同的选择梯子的策略
	 */
	private int interval;
	private int allNum;
	private int proNum;
	private int maxSpeed;
	static boolean flag = true;
	private boolean sameStr = false;
	static ArrayList<Ladder> ladders = new ArrayList<Ladder>();
	/**
	 * 设置内部属性
	 * @param interval	休眠间隔时间
	 */
	public void setInterval(int interval) {
		this.interval = interval*1000;
	}
	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}
	public void setProNum(int proNum) {
		this.proNum = proNum;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	/**
	 * 猴子构造器的构造函数
	 * @param interval		休眠间隔时间
	 * @param allNum	需要创建的猴子总数
	 * @param proNum	已经创建的猴子总数
	 * @param Maxspeed		猴子过河的最大速率
	 * @param ladders		所有梯子的集合
	 * @param sameStr		是否采用相同的选择梯子的策略
	 */
	public MonkeyGenerator(int interval,int allNum,int proNum,int Maxspeed,ArrayList<Ladder> ladders,boolean sameStr) {
		// TODO Auto-generated constructor stub
		this.interval = interval*1000;
		this.allNum = allNum;
		this.proNum = proNum;
		this.maxSpeed = Maxspeed;
		this.ladders = ladders;
		this.sameStr = sameStr;
	}
	/**
	 * 重写MonkeyGenerator线程的run()方法，在run()方法中依次创建Monkey类的实例
	 */
	@Override
	public void run() {
		while(allNum>0){
			if(allNum>=proNum){
				for (int i = 0; i < proNum; i++) {
					
					if(flag){
						Monkey m = new Monkey(maxSpeed,ladders,sameStr);
						m.start();
						flag = false;
					}else{
						Monkey m = new Monkey(maxSpeed,sameStr);
						m.start();
					}
					
				}
				allNum -= proNum;
			}else{
				for (int i = 0; i < allNum; i++) {
					if(flag){
						Monkey m = new Monkey(maxSpeed,ladders,sameStr);
						m.start();
						flag = false;
					}else{
						Monkey m = new Monkey(maxSpeed,sameStr);
						m.start();
					}
				}
				allNum = 0;
			}
			try {
				sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
