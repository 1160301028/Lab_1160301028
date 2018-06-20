package crossRiver;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;
import crossRiver.addLog;

public class Monkey extends Thread {
	/**
	 * id	һ����̬���������ڸ�ÿ�����ɵĺ���ID��ֵ
	 * ID	���ӵ�IDֵ
	 * direction	���ӵ�ͨ�з���
	 * Speed	���ӵ��ٶ�
	 * position		���ӵ�ǰ��λ��
	 * ladderIndex		�������ڵ����ӱ��
	 * birthTime	���ӳ�����ʱ��
	 * reachTime	���ӵ���԰���ʱ��
	 * strategy		���Ӳ�ȡ�Ĺ��Ӳ���
	 * ladders		�������ӵļ���
	 * monkeys		���к��ӵļ���
	 */
	static int id = 0;
	private int ID;
	private String direction;
	private int Speed;
	private int position;
	private int ladderIndex =-1;
	private long birthTime=-1;
	private long reachTime=-1;
	private Strategy strategy;
	static ArrayList<Ladder> ladders =null;
	static ArrayList<Monkey> monkeys = new ArrayList<>();
	Logger logger = Logger.getLogger("Monkey");
	strategy1 s1 = new strategy1();
	strategy2 s2 = new strategy2();
	/**
	 * ���캯��1
	 * @param maxSpeed		���ӹ��ӵ�����ٶ�
	 * @param sameStr		�����Ƿ��ȡ��ͬ��ѡ�����ӵĲ���
	 */
	public Monkey(int maxSpeed,boolean sameStr) {
		addLog.addlog(logger);
		this.birthTime = System.currentTimeMillis();
		this.ID = id;
		id++;
		this.position = 0;
		Random random = new Random();
		this.direction = Math.random() > 0.5?"L-->R":"R-->L";
		this.Speed = random.nextInt(maxSpeed) % (maxSpeed) + 1;
		if(sameStr){
			this.strategy = s2;
		}else{
			this.strategy = Math.random() > 0.5? s1 : s2;
		}
	
//		this.strategy = s2;
		this.monkeys.add(this);
		logger.info(this.toString()+ "\tҪ����");
	}
	/**
	 * ���캯��2
	 * @param maxSpeed		���ӹ��ӵ�����ٶ�
	 * @param ladders		�������ӵļ���
	 * @param sameStr		�����Ƿ��ȡ��ͬ��ѡ�����ӵĲ���
	 */
	public Monkey(int maxSpeed,ArrayList<Ladder> ladders,boolean sameStr) {
		addLog.addlog(logger);
		this.birthTime = System.currentTimeMillis();
		this.ID = id;
		id++;
		this.position = 0;
		Random random = new Random();
		this.direction = Math.random() > 0.5?"L-->R":"R-->L";
		this.Speed = random.nextInt(maxSpeed) % (maxSpeed) + 1;
		this.ladders = ladders;
		if(sameStr){
			this.strategy = s2;
		}else{
			this.strategy = Math.random() > 0.5? s1 : s2;
		}
		this.monkeys.add(this);
		logger.info(this.toString()+ "\tҪ����");
	}
	/**
	 * ��ó���ʱ��
	 * @return		BirthTime
	 */
	public long getBirthTime() {
		return birthTime;
	}
	/**
	 * ��õ���ʱ��
	 * @return		ReachTime
	 */
	public long getReachTime() {
		return reachTime;
	}
	public void setBirthTime(long birthTime) {
		this.birthTime = birthTime;
	}
	public void setReachTime(long reachTime) {
		this.reachTime = reachTime;
	}
	public static void setLadders(ArrayList<Ladder> ladders) {
		Monkey.ladders = ladders;
	}
	public void setLadderIndex(int ladderIndex) {
		this.ladderIndex = ladderIndex;
	}
	public int getLadderIndex() {
		return ladderIndex;
	}
	public int getID() {
		return ID;
	}
	public int getSpeed() {
		return Speed;
	}
	public String getDirection(){
		return direction;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "����"+ID+"("+direction+"���ٶ�:"+Speed+"�����ò���"+strategy.toString()+")";
	}
	
	
	@Override
	public void run() {
		while(this.position<this.ladders.get(0).getSteps().length){
			if(this.position==0){
				this.strategy.chooseLadder(this);
			}else{
				this.strategy.forword(this);
			}
			try{
				sleep(1000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		boolean flag = true;
		for (int i = 0; i < monkeys.size(); i++) {
			if(monkeys.get(i).getReachTime()==-1){
				flag = false;
			}
		}
		if(flag){
			calFairLog();
			calH_Puff();
		}
	}
	/**
	 * ���������ʱ�䡢����ƽ��ʱ��������ʲ�����־���
	 */
	private void calH_Puff() {
		// TODO Auto-generated method stub
		addLog.addlog(logger);
		long earliyBirth = birthTime;
		long latestReach = reachTime;
		int size = monkeys.size();
		for (int i = 0; i < size; i++) {
			if(monkeys.get(i).getBirthTime()<earliyBirth){
				earliyBirth = monkeys.get(i).getBirthTime();
			}
			if(monkeys.get(i).getReachTime()>latestReach){
				latestReach = monkeys.get(i).getReachTime();
			}
		}
		logger.info(size+"ֻ���ӹ��ӻ���"+(latestReach-earliyBirth)/1000f+"��,ƽ����ʱΪ"+
				(latestReach-earliyBirth)/(size*1000f)+"������Ϊ"+(size*1000f)/(latestReach-earliyBirth));
		
	}
	/**
	 * ���㹫ƽ�Բ�����־���
	 */
	private void calFairLog() {
		// TODO Auto-generated method stub
		addLog.addlog(logger);
		int sum = 0;
		int size = monkeys.size();
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(i!=j){
					if((monkeys.get(i).getReachTime()-monkeys.get(j).getReachTime())*
							(monkeys.get(i).getBirthTime()-monkeys.get(j).getBirthTime())>=0 ){
						sum ++;
					}else{
						sum--;
					}
					count++;
				}
				
			}
		}
		logger.info("��ƽ��Ϊ"+sum*1.0f/count);
		
	}


}
