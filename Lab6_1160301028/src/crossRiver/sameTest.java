package crossRiver;

import java.util.ArrayList;

public class sameTest {
	public static void main(String[] args) {
		LadderGenerator ladderGenerator = new LadderGenerator();
		ArrayList<Ladder> ladders = ladderGenerator.getLadders(3, 10);
		MonkeyGenerator monGen = new MonkeyGenerator(2, 6, 4, 5, ladders,true);
		monGen.start();
	}
}
