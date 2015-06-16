package com.lfcc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * 浦软培训 Java 课程练习
 * @author I063098
 *
 */
public class WeekOf1 {
	public static void main(String[] args) {
		try {
			String usage = "Usage: \n"
					+ "       Input 1 to run SplitString,\n"
					+ "       Input 2 to run AlignString,\n"
					+ "       Input exit to Exit program.";
			System.out.println(usage);
			while (true) {
				System.out.print("Please input your selection:");
				String input = new BufferedReader(new InputStreamReader(System.in))
						.readLine();
				if (input.trim().equalsIgnoreCase("Exit")) {
					break;
				} else {
					System.out.print("Please input a string:");
					String input2 = new BufferedReader(new InputStreamReader(System.in))
							.readLine();
					if (input.trim().equals("1")) {
						splitString(input2);
					} else {
						alignString(input2);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Your input is invalid!");
			e.printStackTrace();
		}
	}

	/**
	 * Exercise 1
	 * 数据库中某表，包含一字段( summary_txt ) 长度为 Char(30),
	 * 用于记录短文字信息。若长度超过30，则对输入的文字作分行处理， 分多条记录插入。实现该分行处理逻辑并打印输出到控制台
	 */
	public static void splitString(String input) {
		List<String> output = new ArrayList<String>();
		int iStart = 0;
		boolean hasMore = true;
		while (hasMore) {
			String tmpStr;
			try {
				tmpStr = input.substring(iStart, iStart + 30);
			} catch (IndexOutOfBoundsException e) {
				tmpStr = input.substring(iStart);
				hasMore = false;
			}
			if (tmpStr != null && tmpStr.length() > 0)
				output.add(tmpStr);
			iStart += 30;
		}
		int iLine = 0;
		for (String itm : output) {
			System.out.println(String.format("Line %d : %s", ++iLine, itm));
		}
	}

	/**
	 * Exercise 2 
	 * 在处理国家、城市简称时，需要根据对数据进行对齐处理，国家简称右对齐，
	 * 城市简称左对齐，长度统一为5位。输入包含多级数据，结构为键值对， 如{N:US, C:NYC}, 键值有可能为空，可能包含空格。
	 * 实现该数据处理逻辑，打印输出到控制台。
	 */
	public static void alignString(String input){
		// Nation is key, City is value
		int iStart = 0;
		System.out.println("[City : Nation]");
		StringBuilder city;
		StringBuilder nation;
		while (true) {
			try {
				int leftBracket = input.indexOf("{", iStart);
				int rightBracket = input.indexOf("}", iStart);
				if (leftBracket >= 0 && rightBracket > 0) {
					String tmpStr = input.substring(leftBracket + 1,
							rightBracket);
					iStart = rightBracket + 1;

					if (tmpStr != null && tmpStr.length() > 0) {
						String[] tmpArray = tmpStr.split(",");

						city = new StringBuilder();
						nation = new StringBuilder();
						for (String itm : tmpArray) {
							String[] kv = itm.trim().split(":");
							if (kv[0].trim().equals("N")) {
								nation.append(kv.length > 1 ? kv[1].trim() : "");
							} else {
								city.append(kv.length > 1 ? kv[1].trim() : "");
							}
						}
						while (city.length()<5) {
							city.append(" ");
						}
						while (nation.length()<5) {
							nation.insert(0, " ");
						}
						
						System.out.println(String.format("[%s : %s]", city, nation));
					} else {
						break;
					}
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("Your input data is invalid!");
				e.printStackTrace();
				break;
			}
		}
	}
}
