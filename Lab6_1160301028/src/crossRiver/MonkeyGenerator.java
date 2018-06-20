package crossRiver;

import java.util.ArrayList;

public class MonkeyGenerator extends Thread{
	/**
	 * interval 	����ʱ��
	 * allNum	��Ҫ�����ĺ�������
	 * proNum	�Ѿ������ĺ�������
	 * maxSpeed		���ӵ�����ٶ�
	 * sameStr		�Ƿ��ȡ��ͬ��ѡ�����ӵĲ���
	 */
	private int interval;
	private int allNum;
	private int proNum;
	private int maxSpeed;
	static boolean flag = true;
	private boolean sameStr = false;
	static ArrayList<Ladder> ladders = new ArrayList<Ladder>();
	/**
	 * �����ڲ�����
	 * @param interval	���߼��ʱ��
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
	 * ���ӹ������Ĺ��캯��
	 * @param interval		���߼��ʱ��
	 * @param allNum	��Ҫ�����ĺ�������
	 * @param proNum	�Ѿ������ĺ�������
	 * @param Maxspeed		���ӹ��ӵ��������
	 * @param ladders		�������ӵļ���
	 * @param sameStr		�Ƿ������ͬ��ѡ�����ӵĲ���
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
	 * ��дMonkeyGenerator�̵߳�run()��������run()���������δ���Monkey���ʵ��
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
