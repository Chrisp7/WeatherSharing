package corefuction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getDegree {
	public void writeMaxDegreeToFile(ArrayList<String> degreeData)
			throws IOException {

		String filePath = "/Users/CP/Documents/Study/code/jee_eclipse/WeatherSharing/WebContent/js/temperatureData.js";
		Pattern pattern = Pattern.compile("\\ (\\d+)\\℃");
		Matcher m = null;
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		} else {
			file.delete();
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file, false);
		StringBuffer sb = new StringBuffer();
		int[] data = new int[5];
		for (int i = 0; i < degreeData.size(); i++) {
			m = pattern.matcher(degreeData.get(i));
			while (m.find()) {
				// 想要不包含 10℃的前后空格和℃就要在group()里面写1；
				data[i] = Integer.parseInt(m.group(1));
			}
		}
		String weatherStr = "var data=[" + data[0] + "," + data[1] + ","
				+ data[2] + "];";
		sb.append(weatherStr);
		out.write(sb.toString().getBytes());
		out.close();
	}

	public void writeAverageDegreeToFile(ArrayList<String> degreeData)
			throws IOException {
		String[] item = new String[10];
		String[] eachItem = new String[]{" "," "};
		int specialCase = 0;
		String filePath = "/Users/CP/Documents/Study/code/jee_eclipse/WeatherSharing/WebContent/js/temperatureData.js";
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		} else {
			file.delete();
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file, false);
		StringBuffer sb = new StringBuffer();
		int[] data = new int[3];
		for (int i = 0; i < degreeData.size(); i++) {
			item = degreeData.get(i).split(" ");
			eachItem = item[3].split("~");
			
			if (eachItem[0].equals(item[3])) {
				specialCase = Integer.parseInt(item[3].substring(0,
						item[3].length() - 1));
				data[i] = specialCase;
				
			} else {
				int a = Integer.parseInt(eachItem[0].substring(0,
						eachItem[0].length() - 1));
				int b = Integer.parseInt(eachItem[1].substring(0,
						eachItem[1].length() - 1));
				int avg = (a + b) / 2;
				data[i] = avg;
			}

		}

		String weatherStr = "var data=[" + data[0] + "," + data[1] + ","
				+ data[2] + "];";
		sb.append(weatherStr);
		out.write(sb.toString().getBytes());
		out.close();

	}
	/*
	 * public static void main(String[] args) throws IOException {
	 * 
	 * ArrayList<String> testArrayList = new ArrayList<String>(); String str =
	 * "05月16日 周六 多云 25℃~15℃ 东风 3-4级"; String str2 =
	 * "05月17日 周六 多云 25℃~15℃ 东风 3-4级"; String str3 =
	 * "05月18日 周六 多云 25℃~15℃ 东风 3-4级"; testArrayList.add(str);
	 * testArrayList.add(str2); testArrayList.add(str3);
	 * 
	 * writeAverageDegreeToFile(testArrayList); }
	 */
}
