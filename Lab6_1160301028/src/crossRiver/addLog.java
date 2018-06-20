package crossRiver;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class addLog {

	/**
	 *  be used to add log in the program
	 */
	private static FileHandler filehandler;
	static {
		try {
			filehandler = new FileHandler("txt/log.txt");
			filehandler.setFormatter(new SimpleFormatter());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addlog(Logger log) {
		log.addHandler(filehandler);
	}
}