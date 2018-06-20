package crossRiver;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;
import crossRiver.addLog;

public class Monkey extends Thread {
	/**
	 * id	一个静态变量，由于给每次生成的猴子ID赋值
	 * ID	猴子的ID值
	 * direction	猴子的通行方向
	 * Speed	猴子的速度
	 * position		猴子当前的位置
	 * ladderIndex		猴子所在的梯子编号
	 * birthTime	猴子出生的时间
	 * reachTime	猴子到达对岸的时间
	 * strategy		猴子采取的过河策略
	 * ladders		所有梯子的集合
	 * monkeys		所有猴子的集合
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
	 * 构造函数1
	 * @param maxSpeed		猴子过河的最大速度
	 * @param sameStr		猴子是否采取相同的选择梯子的策略
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
		logger.info(this.toString()+ "\t要过桥");
	}
	/**
	 * 构造函数2
	 * @param maxSpeed		猴子过河的最大速度
	 * @param ladders		所有梯子的集合
	 * @param sameStr		猴子是否采取相同的选择梯子的策略
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
		logger.info(this.toString()+ "\t要过桥");
	}
	/**
	 * 获得出生时间
	 * @return		BirthTime
	 */
	public long getBirthTime() {
		return birthTime;
	}
	/**
	 * 获得到达时间
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
		return "猴子"+ID+"("+direction+"、速度:"+Speed+"、采用策略"+strategy.toString()+")";
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
	 * 计算过河总时间、过河平均时间和吞吐率并在日志输出
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
		logger.info(size+"只猴子过河花费"+(latestReach-earliyBirth)/1000f+"秒,平均耗时为"+
				(latestReach-earliyBirth)/(size*1000f)+"吞吐率为"+(size*1000f)/(latestReach-earliyBirth));
		
	}
	/**
	 * 计算公平性并在日志输出
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
		logger.info("公平性为"+sum*1.0f/count);
		
	}


}
