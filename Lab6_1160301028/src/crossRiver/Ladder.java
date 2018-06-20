package crossRiver;

import java.util.ArrayList;

public class Ladder {
	/**
	 * len	梯子的长度
	 * steps[]	梯子每一阶构成的int型数组，用于存储每一阶上猴子的id
	 * to_left、to_right		用于表示梯子目前的通行方向
	 */
	private int len;
	private int[] steps;//存储每一阶猴子的id
	private boolean to_left = false;
	private boolean to_right = false;
	
	/**
	 * 梯子构造函数
	 * @param len	梯子的长度
	 */
	public Ladder(int len) {
		// TODO Auto-generated constructor stub
		this.len = len;
		this.steps = new int[len];
	}
	/**
	 * 将梯子的通行方向设置为向左
	 */
	private void setleft() {
		// TODO Auto-generated method stub
		this.to_left = true;
		this.to_right = false;
	}
	/**
	 * 将梯子的通行方向设置为向右
	 */
	private void setRight(){
		this.to_left = false;
		this.to_right = true;
	}
	/**
	 * 将梯子的通行方向设置为不通
	 */
	private void setEmpty() {
		// TODO Auto-generated method stub
		this.to_left = false;
		this.to_right = false;
	}
	/**
	 * 根据输入字符串设置梯子的方向
	 * @param s		通行方向字符串
	 */
	public void setDirect(String s) {
		// TODO Auto-generated method stub
		if(s.equals("L-->R")){
			setRight();
		}else{
			setleft();
		}

	}
	/**
	 * 返回to_right的状态值
	 * @return	to_right
	 */
	public boolean getTo_right(){
		return to_right;
	}
	/**
	 * 返回to_left的状态值
	 * @return		to_left
	 */
	public boolean getTo_left(){
		return to_left;
	}
	/**
	 * 返回steps[]数组
	 * @return		steps[]
	 */
	public int[] getSteps() {
		return steps;
	}
	
	/**
	 * 一只猴子到达对岸之后设置桥的通行方向
	 * @param monkeys
	 * @return 返回桥上是否有猴子  有的话为false  没有为true
	 */
	public boolean checkL_R(ArrayList<Monkey> monkeys){
		for (int i = 0; i < this.steps.length; i++) {
			if(this.steps[i]!=0){
				int id = this.steps[i];
				this.setDirect(monkeys.get(id).getDirection());
				return false;
			}
		}
		setEmpty();
		return true;
	}

}
