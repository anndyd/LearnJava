package com.lfcc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * ������ѵ Java �γ���ϰ
 * @author I063098
 *
 */
public class WeekOf1 {
	public static void main(String[] args) {
		String usage = "Usage: \n"
				+ "       Input 1 to run SplitString,\n"
				+ "       Input 2 to run AlignString,\n"
				+ "       Input exit to Exit program.";
		System.out.println(usage);
		while (true) {
			System.out.print("Please input your selection:");
			String input = getConsoleInput();
			if (input.trim().equalsIgnoreCase("Exit")) {
				break;
			} else if (input.trim().equals("1") || input.trim().equals("2")) {
				System.out.print("Please input a string:");
				if (input.trim().equals("1")) {
					splitString(getConsoleInput());
				} else {
					System.out.println("The string should be Key,Value pair, likes: {N:US,C:NYC}");
					alignString(getConsoleInput());
				}
			}
		}
	}

	/**
	 * Exercise 1
	 * ���ݿ���ĳ������һ�ֶ�( summary_txt ) ����Ϊ Char(30),
	 * ���ڼ�¼��������Ϣ�������ȳ���30�������������������д��� �ֶ�����¼���롣ʵ�ָ÷��д����߼�����ӡ���������̨
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
	 * �ڴ�����ҡ����м��ʱ����Ҫ���ݶ����ݽ��ж��봦�����Ҽ���Ҷ��룬
	 * ���м������룬����ͳһΪ5λ����������༶���ݣ��ṹΪ��ֵ�ԣ� ��{N:US, C:NYC}, ��ֵ�п���Ϊ�գ����ܰ����ո�
	 * ʵ�ָ����ݴ����߼�����ӡ���������̨��
	 */
	public static void alignString(String input){
		// Nation is key, City is value
		int iStart = 0;
		StringBuilder city;
		StringBuilder nation;
		while (true) {
			try {
				int leftBracket = input.indexOf("{", iStart);
				int rightBracket = input.indexOf("}", iStart);
				if (leftBracket >= 0 && rightBracket > 0) {
					String tmpStr = input.substring(leftBracket + 1,
							rightBracket);

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
						
						if (iStart == 0) System.out.println("[City : Nation]");
						System.out.println(String.format("[%s : %s]", city, nation));
					} else {
						break;
					}
					iStart = rightBracket + 1;
				} else {
					System.out.println("Your input data is invalid!");
					break;
				}
			} catch (Exception e) {
				System.out.println("Your input data is invalid!");
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static String getConsoleInput() {
		String input = "";
		try {
			input = new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			System.out.println("Your input is invalid!");
			e.printStackTrace();
		}
		return input;
	}
}
