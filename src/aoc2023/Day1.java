package aoc2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
	private static final Pattern p = Pattern.compile("\\d{1}");
	public static void main(String[] args) {
		Scanner s;
		try {
			s = new Scanner(new File("C:\\aoc\\input.txt"));
			long v =0;
			while(s.hasNext()) {
				
				String line = s.nextLine();
				String[] valuesOf = {"one","two","three","four","five","six","seven","eight","nine","1","2","3","4","5","6","7","8","9"};
				String[] joinsmap = {"1","2","3","4","5","6","7","8","9","1","2","3","4","5","6","7","8","9"};
				HashMap<String,int[]> counters = new HashMap<String, int[]>();
				for(int i =0;i<valuesOf.length;i++) {
					counters.put(valuesOf[i], new int[]{line.indexOf(valuesOf[i]),line.lastIndexOf(valuesOf[i])});
				}
				int minPos = Integer.MAX_VALUE;
				String minVal = "";
				int maxPos = -1;
				String maxVal = "";
				for (int i = 0; i < valuesOf.length; i++) {
					if(counters.get(valuesOf[i])[0]<minPos&&counters.get(valuesOf[i])[0]!=-1) {
						minPos = counters.get(valuesOf[i])[0];
						minVal = valuesOf[i];
					}
					if(counters.get(valuesOf[i])[1]>maxPos) {
						maxPos = counters.get(valuesOf[i])[1];
						maxVal = valuesOf[i];
					}
				}
				String join = "";
				String mapMin="";
				String mapMax="";
				for (int i = 0; i < valuesOf.length; i++) {
					if(valuesOf[i]==minVal) {
						mapMin=joinsmap[i];
					}
					if(valuesOf[i]==maxVal) {
						mapMax=joinsmap[i];
					}
				}
				join = mapMin+mapMax;
				System.out.println(line+" "+minVal+" "+maxVal+" "+join);
				long l = Long.parseLong(join);
				
				v+=l;
			}
			System.out.println(v);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
}
