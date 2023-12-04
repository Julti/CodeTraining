package aoc2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
	private static final Pattern p = Pattern.compile("\\d+");
	/*
	 * Maybe there are more efficient ways to solve this, will revisit
	 */
	public static void main(String[] args) {
		Scanner s;
		try {
			ArrayList<String> tiles = new ArrayList<>();
			s = new Scanner(new File("D:\\input.txt"));
			while (s.hasNext()) {
				tiles.add(s.nextLine());
			}
			String sc = "*#+$@//'-%=&";
			long count = 0;
			ArrayList<CharOb> sco = new ArrayList<>();
			HashMap<CharOb,ArrayList<Long>> controlSt = new HashMap<Day3.CharOb, ArrayList<Long>>();
			for (int i = 0; i < tiles.size(); i++) {
				for (int j = 0; j < tiles.get(i).length(); j++) {
					if (sc.contains(tiles.get(i).charAt(j) + "")) {
						int[] pos = { i, j };
						sco.add(new CharOb(pos,tiles.get(i).charAt(j) + ""));
					}
				}
			}
			for (int i = 0; i < tiles.size(); i++) {
				Matcher m = p.matcher(tiles.get(i));
				String word = tiles.get(i);
				int control = 0;
				String subword=tiles.get(i);
				while (m.find()) {

					String number = m.group();

					long numberVal = Long.parseLong(number);
					int index = word.indexOf(subword);
					int index2 = subword.indexOf(number);
					final int v = i;
					int intindex = index;
					int limita = intindex+index2 - 1;
					int limitb = intindex +(index2)+ number.length();
					int limitc = v - 1;
					int limitd = v + 1;
					Stream<CharOb> c = sco.stream().filter(
							app -> (app.coordinates[1] >= limita && app.coordinates[1] <= limitb && app.coordinates[0] >= limitc && app.coordinates[0] <= limitd));
					List<CharOb> related = c.collect(Collectors.toList());
					
					for (int j = 0; j < related.size(); j++) {
						if(related.get(j).symbol.equals("*")) {
							if(controlSt.containsKey(related.get(j))==false) {
								controlSt.put(related.get(j), new ArrayList<Long>());
							}
							controlSt.get(related.get(j)).add(numberVal);
						}
						
					}
					control = index2+number.length();
					subword = subword.substring(Integer.min(control,subword.length()), subword.length());
					m = p.matcher(subword);
				}
			}
			Set<CharOb> keys =controlSt.keySet();
			for (CharOb key:keys) {
				ArrayList<Long> nums = controlSt.get(key);
				long prod =1;
				if(nums.size()>1) {
					for (int j = 0; j < nums.size(); j++) {
						prod*=nums.get(j);
					}
					count+=prod;
				}
			}
			System.out.println(count);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void partA() {
		Scanner s;
		try {
			ArrayList<String> tiles = new ArrayList<>();
			s = new Scanner(new File("D:\\input.txt"));
			while (s.hasNext()) {
				tiles.add(s.nextLine());
			}
			String sc = "*#+$@//'-%=&";
			long count = 0;
			ArrayList<int[]> sco = new ArrayList<>();
			for (int i = 0; i < tiles.size(); i++) {
				for (int j = 0; j < tiles.get(i).length(); j++) {
					if (sc.contains(tiles.get(i).charAt(j) + "")) {
						int[] pos = { i, j };
						sco.add(pos);
					}
				}
			}
			for (int i = 0; i < tiles.size(); i++) {
				Matcher m = p.matcher(tiles.get(i));
				String word = tiles.get(i);
				int control = 0;
				String subword=tiles.get(i);
				while (m.find()) {

					String number = m.group();

					long numberVal = Long.parseLong(number);
					int index = word.indexOf(subword);
					int index2 = subword.indexOf(number);
					final int v = i;
					int intindex = index;
					int limita = intindex+index2 - 1;
					int limitb = intindex +(index2)+ number.length();
					int limitc = v - 1;
					int limitd = v + 1;
					Stream<int[]> c = sco.stream().filter(
							app -> (app[1] >= limita && app[1] <= limitb && app[0] >= limitc && app[0] <= limitd));
					if (c.toArray().length > 0) {
						count += numberVal;
						System.out.println(numberVal);
					}

					control = index2+number.length();
					subword = subword.substring(Integer.min(control,subword.length()), subword.length());
					m = p.matcher(subword);
				}
			}
			System.out.println(count);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static class CharOb {
		public int[] coordinates;
		public String symbol;
		public CharOb(int[] coordinates,String symbol) {
			this.coordinates = coordinates;
			this.symbol=symbol;
		}
	}
}
