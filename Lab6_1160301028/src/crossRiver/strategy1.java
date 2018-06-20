package crossRiver;

import java.util.logging.Logger;

public class strategy1 implements Strategy{
	/**
	 * ֻѡ��յ�����
	 */
	Logger logger = Logger.getLogger("strategy1");
	@Override
	public synchronized  void chooseLadder(Monkey monkey) {
		// TODO Auto-generated method stub
		addLog.addlog(logger);
		for (int i = 0; i < monkey.ladders.size(); i++) {
			if(monkey.ladders.get(i).checkL_R(monkey.monkeys)){
				monkey.setLadderIndex(i);
				monkey.setPosition(1);
				monkey.ladders.get(i).getSteps()[monkey.getPosition()] = monkey.getID();
				monkey.ladders.get(i).setDirect(monkey.getDirection());
//				System.out.println(monkey.toString()+"����������"+i+"toright:"+monkey.ladders.get(i).getTo_right()+"\ttoLeft:"+monkey.ladders.get(i).getTo_left());
//				System.out.println(monkey.toString()+"�鿴��0"+"toright:"+monkey.ladders.get(0).getTo_right()+"\ttoLeft:"+monkey.ladders.get(0).getTo_left());
//				System.out.println(monkey.toString()+"�鿴��1"+"toright:"+monkey.ladders.get(1).getTo_right()+"\ttoLeft:"+monkey.ladders.get(1).getTo_left());
//				System.out.println(monkey.toString()+"�鿴��2"+"toright:"+monkey.ladders.get(2).getTo_right()+"\ttoLeft:"+monkey.ladders.get(2).getTo_left());
//				System.out.println(monkey.toString() + "������"+i);
				logger.info(monkey.toString() + "������"+i);
				break;
			}
		}
		if(monkey.getPosition()==0){
//			System.out.println(monkey.toString()+ "�ڵȴ�");
			logger.info(monkey.toString()+ "�ڵȴ�");
		}
		
	}


	@Override
	public synchronized  void forword(Monkey monkey) {
		int position = monkey.getPosition();
		int speed = monkey.getSpeed();
		int ladderIndex = monkey.getLadderIndex();
		int preIndex = position;
		int ladderLen = monkey.ladders.get(ladderIndex).getSteps().length;
		for (int i =position; i < ladderLen; i++) {
			if(monkey.ladders.get(ladderIndex).getSteps()[i]!=0){
				preIndex = i;//ͬһ��������ǰ��һ�����ӵ�λ��
				break;
			}
		}
		if(preIndex == position){//ǰ��û�к���
			monkey.ladders.get(ladderIndex).getSteps()[position] = 0;
			position = position +speed;
			monkey.setPosition(position);
			if(position>=monkey.ladders.get(ladderIndex).getSteps().length){
				monkey.ladders.get(ladderIndex).checkL_R(monkey.monkeys);
				monkey.setReachTime(System.currentTimeMillis());
				logger.info(monkey.toString()+ "�Ѿ�������");
			}else{
				monkey.ladders.get(ladderIndex).getSteps()[position] = monkey.getID();
				logger.info(monkey.toString() + "�Ѿ���������"+ladderIndex+"��"+
				position+"��");
			}
		}else if(position+speed>=preIndex){//׷����ǰһֻ����
			monkey.ladders.get(ladderIndex).getSteps()[position] = 0;
			position = preIndex -1;
			monkey.setPosition(position);
			logger.info(monkey.toString() + "�Ѿ���������"+ladderIndex+"��"+position+"��");
		}else{//׷���Ϻ���
			monkey.ladders.get(ladderIndex).getSteps()[position] = 0;
			position = position +speed;
			monkey.setPosition(position);
			monkey.ladders.get(ladderIndex).getSteps()[position] = monkey.getID();
			logger.info(monkey.toString() + "�Ѿ���������"+ladderIndex+"��"+
			position+"��");
		}
	}
	@Override
	public String toString() {
		return "strategy1";
	}
	
	

}
