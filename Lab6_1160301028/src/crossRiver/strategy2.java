package crossRiver;

import java.util.ArrayList;
import java.util.logging.Logger;

public class strategy2 implements Strategy {
	/**
	 * 没有空的梯子的时候选择方向相同的梯子
	 */

	Logger logger = Logger.getLogger("strategy2");

	@Override
	public synchronized void chooseLadder(Monkey monkey) {
		// TODO Auto-generated method stub
		addLog.addlog(logger);
		for (int i = 0; i < monkey.ladders.size(); i++) {
			if (monkey.ladders.get(i).checkL_R(monkey.monkeys)) {
				monkey.setLadderIndex(i);
				monkey.setPosition(1);
				monkey.ladders.get(i).getSteps()[monkey.getPosition()] = monkey.getID();
				monkey.ladders.get(i).setDirect(monkey.getDirection());
				// System.out.println(monkey.toString()+"登上设置桥"+i+"toright:"+monkey.ladders.get(i).getTo_right()+"\ttoLeft:"+monkey.ladders.get(i).getTo_left());
				// System.out.println(monkey.toString()+"查看桥0"+"toright:"+monkey.ladders.get(0).getTo_right()+"\ttoLeft:"+monkey.ladders.get(0).getTo_left());
				// System.out.println(monkey.toString()+"查看桥1"+"toright:"+monkey.ladders.get(1).getTo_right()+"\ttoLeft:"+monkey.ladders.get(1).getTo_left());
				// System.out.println(monkey.toString()+"查看桥2"+"toright:"+monkey.ladders.get(2).getTo_right()+"\ttoLeft:"+monkey.ladders.get(2).getTo_left());
				// System.out.println(monkey.toString() + "登上桥"+i);
				logger.info(monkey.toString() + "登上桥" + i);
				break;
			}
		}
		if (monkey.getPosition() == 0) {
			for (int i = 0; i < monkey.ladders.size(); i++) {
				// 第一阶没猴子并且 梯子和猴子同方向的时候 猴子上梯子
				if (monkey.ladders.get(i).getSteps()[1] == 0
						&& ((monkey.getDirection().equals("L-->R") && monkey.ladders.get(i).getTo_right())
								|| (monkey.getDirection().equals("R-->L") && monkey.ladders.get(i).getTo_left()))) {
					monkey.setLadderIndex(i);
					monkey.setPosition(1);
					monkey.ladders.get(i).getSteps()[monkey.getPosition()] = monkey.getID();
					monkey.ladders.get(i).setDirect(monkey.getDirection());
					// System.out.println(("桥"+i+"toRight:"+monkey.ladders.get(i).getTo_right()
					// ));
					// System.out.println(("桥"+i+"toLeft:"+
					// monkey.ladders.get(i).getTo_left() ));
					// System.out.println(monkey.toString()+"\ttoRight:"+monkey.ladders.get(i).getTo_right()+"\ttoLeft:"
					// +monkey.ladders.get(i).getTo_left() );
					// System.out.println(monkey.toString() + "登上同方向的桥"+i);
					logger.info(monkey.toString() + "登上同方向的桥" + i);
					break;
				}
			}
		}

		if (monkey.getPosition() == 0) {
//			System.out.println(monkey.toString() + "在等待");
			logger.info(monkey.toString() + "在等待");
		}

	}

	@Override
	public synchronized void forword(Monkey monkey) {
		int position = monkey.getPosition();
		int speed = monkey.getSpeed();
		int ladderIndex = monkey.getLadderIndex();
		int preIndex = position;
		int ladderLen = monkey.ladders.get(ladderIndex).getSteps().length;
		for (int i = position; i < ladderLen; i++) {
			if (monkey.ladders.get(ladderIndex).getSteps()[i] != 0) {
				preIndex = i;
				break;
			}
		}
		if (preIndex == position) {// 前面没有猴子
			monkey.ladders.get(ladderIndex).getSteps()[position] = 0;
			position = position + speed;
			monkey.setPosition(position);
			if (position >= monkey.ladders.get(ladderIndex).getSteps().length) {
				monkey.ladders.get(ladderIndex).checkL_R(monkey.monkeys);
				monkey.setReachTime(System.currentTimeMillis());
				logger.info(monkey.toString() + "已经到达了");
			} else {
				monkey.ladders.get(ladderIndex).getSteps()[position] = monkey.getID();
				logger.info(monkey.toString() + "已经到达梯子" + ladderIndex + "的" + position + "处");
			}
		} else if (position + speed >= preIndex) {// 追赶上前一只猴子
			monkey.ladders.get(ladderIndex).getSteps()[position] = 0;
			position = preIndex - 1;
			monkey.setPosition(position);
			logger.info(monkey.toString() + "已经到达梯子" + ladderIndex + "的" + position + "处");
		} else {// 追不上猴子
			monkey.ladders.get(ladderIndex).getSteps()[position] = 0;
			position = position + speed;
			monkey.setPosition(position);
			monkey.ladders.get(ladderIndex).getSteps()[position] = monkey.getID();
			logger.info(monkey.toString() + "已经到达梯子" + ladderIndex + "的" + position + "处");
		}
	}

	@Override
	public String toString() {
		return "strategy2";
	}

}
