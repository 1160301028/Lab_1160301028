package crossRiver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
	public static void main(String[] args) {
		int ladderNum=1;
		int len=1;
		int interval=1;
		int allNum=1;
		int proNum=1;
		int Maxspeed=1;
		boolean sameStr=false;
		try {
			String encoding = "UTF-8";
			File file = new File("txt/config.txt");
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String s0= "ladderNum";
					String s1= "len";
					String s2= "interval";
					String s3= "allNum";
					String s4= "proNum";
					String s5= "Maxspeed";
					String s6= "sameStr";
					if(parseStr(s0, lineTxt)!=""){
						ladderNum = Integer.parseInt(parseStr(s0, lineTxt));
					}
					if(parseStr(s1, lineTxt)!=""){
						len = Integer.parseInt(parseStr(s1, lineTxt));
					}
					if(parseStr(s2, lineTxt)!=""){
						interval = Integer.parseInt(parseStr(s2, lineTxt));
					}
					if(parseStr(s3, lineTxt)!=""){
						allNum = Integer.parseInt(parseStr(s3, lineTxt));
					}
					if(parseStr(s4, lineTxt)!=""){
						proNum = Integer.parseInt(parseStr(s4, lineTxt));
					}
					if(parseStr(s5, lineTxt)!=""){
						Maxspeed = Integer.parseInt(parseStr(s5, lineTxt));
					}
					if(parseStr(s6, lineTxt)!=""){
						sameStr = Boolean.parseBoolean(parseStr(s6, lineTxt));
						System.out.println(parseStr(s6,lineTxt));
					}	
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		LadderGenerator ladderGenerator = new LadderGenerator();
		ArrayList<Ladder> ladders = ladderGenerator.getLadders(ladderNum, len);
		MonkeyGenerator monGen = new MonkeyGenerator(interval, allNum, proNum, Maxspeed, ladders, sameStr);
		monGen.start();
	}

	private static String parseStr(String str,String line){
		String s0 = str + "\\s*=\\s*(\\d+)";
		Pattern p0 = Pattern.compile(s0);
		Matcher m = p0.matcher(line);
		if (m.find( )) {
			return m.group(1);
	      }
		return "";
	}
}
