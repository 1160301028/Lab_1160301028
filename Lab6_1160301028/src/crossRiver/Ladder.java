package crossRiver;

import java.util.ArrayList;

public class Ladder {
	/**
	 * len	���ӵĳ���
	 * steps[]	����ÿһ�׹��ɵ�int�����飬���ڴ洢ÿһ���Ϻ��ӵ�id
	 * to_left��to_right		���ڱ�ʾ����Ŀǰ��ͨ�з���
	 */
	private int len;
	private int[] steps;//�洢ÿһ�׺��ӵ�id
	private boolean to_left = false;
	private boolean to_right = false;
	
	/**
	 * ���ӹ��캯��
	 * @param len	���ӵĳ���
	 */
	public Ladder(int len) {
		// TODO Auto-generated constructor stub
		this.len = len;
		this.steps = new int[len];
	}
	/**
	 * �����ӵ�ͨ�з�������Ϊ����
	 */
	private void setleft() {
		// TODO Auto-generated method stub
		this.to_left = true;
		this.to_right = false;
	}
	/**
	 * �����ӵ�ͨ�з�������Ϊ����
	 */
	private void setRight(){
		this.to_left = false;
		this.to_right = true;
	}
	/**
	 * �����ӵ�ͨ�з�������Ϊ��ͨ
	 */
	private void setEmpty() {
		// TODO Auto-generated method stub
		this.to_left = false;
		this.to_right = false;
	}
	/**
	 * ���������ַ����������ӵķ���
	 * @param s		ͨ�з����ַ���
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
	 * ����to_right��״ֵ̬
	 * @return	to_right
	 */
	public boolean getTo_right(){
		return to_right;
	}
	/**
	 * ����to_left��״ֵ̬
	 * @return		to_left
	 */
	public boolean getTo_left(){
		return to_left;
	}
	/**
	 * ����steps[]����
	 * @return		steps[]
	 */
	public int[] getSteps() {
		return steps;
	}
	
	/**
	 * һֻ���ӵ���԰�֮�������ŵ�ͨ�з���
	 * @param monkeys
	 * @return ���������Ƿ��к���  �еĻ�Ϊfalse  û��Ϊtrue
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
