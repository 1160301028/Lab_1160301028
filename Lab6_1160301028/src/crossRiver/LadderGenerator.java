package crossRiver;

import java.util.ArrayList;
import java.util.logging.Logger;

public class LadderGenerator {
	private ArrayList<Ladder> ladders = new ArrayList<>();
	Logger logger = Logger.getLogger("LadderGenerator");
	public ArrayList<Ladder> getLadders(int ladderNum,int len) {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < ladderNum; i++) {
			Ladder ladder = new Ladder(len);
			ladders.add(ladder);
		}
		addLog.addlog(logger);
		logger.info("����"+ladderNum+"������Ϊ"+len+"������");
		return ladders;
	}
}
