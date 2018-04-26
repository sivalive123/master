package com.fifthgen.unittest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Portfolio {

	public static ArrayList<String> readFile() throws FileNotFoundException {
		File file = new File("C:\\Users\\542294\\Desktop\\PortfolioTracker.txt");
		Scanner readFile = new Scanner(file);
		ArrayList<String> portFolio = new ArrayList<String>();
		while (readFile.hasNextLine()) {
			portFolio.add(readFile.nextLine());
		}
		return portFolio;

	}

	public static String[] removePortfolioCommas(ArrayList<String> portFolio) {
		String[] portFolioInArray = new String[portFolio.size()];
		for (int i = 0; i < portFolio.size(); i++) {
			portFolioInArray[i] = portFolio.get(i).replace(",", " ");
		}
		return portFolioInArray;

	}

	public static int[] totalStockValueWithoutSort(String[] portFolioInArray) throws FileNotFoundException

	{
		int[] totalStockValue = new int[portFolioInArray.length];

		for (int j = 0; j < portFolioInArray.length; j++) {

			ArrayList<String> al1 = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(portFolioInArray[j], "-");
			while (st.hasMoreElements()) {
				StringTokenizer st1 = new StringTokenizer(st.nextToken().trim(), " ");
				while (st1.hasMoreElements()) {
					al1.add(st1.nextToken());
				}
			}

			String[] s1 = new String[al1.size()];
			for (int i = 0; i < al1.size(); i++) {
				s1[i] = al1.get(i);
			}

			int[] num = new int[s1.length / 2];
			int count1 = 0;
			for (int i = 0; i < s1.length; i++) {
				if (i % 2 != 0) {
					num[count1] = Integer.parseInt(s1[i]);
					count1++;
				}
			}

			totalStockValue[j] = 0;
			for (int i = 0; i < num.length; i++) {
				totalStockValue[j] = totalStockValue[j] + num[i];

			}

			al1.clear();

		}
		return totalStockValue;
	}

	public static int[] totalStockInDescendingOrder(int[] totalStockValue) throws FileNotFoundException {

		ArrayList<Integer> al3 = new ArrayList<Integer>();
		for (int i = 0; i < totalStockValue.length; i++) {
			al3.add(totalStockValue[i]);
		}

		for (int i = 0; i < totalStockValue.length; i++) {
			Collections.sort(al3, Collections.reverseOrder());
		}

		int[] totalStockValueDesc = new int[al3.size()];
		for (int i = 0; i < al3.size(); i++) {
			totalStockValueDesc[i] = al3.get(i);
		}
		return totalStockValueDesc;
	}

	public String[] FinalSortedOrder(int[] totalStockValue, int[] totalStockValueDesc, ArrayList<String> portFolio)
			throws FileNotFoundException {

		int j = 0;
		int i = 0;
		String[] finalOrder = new String[totalStockValueDesc.length];
		ArrayList<Integer> al4 = new ArrayList<Integer>();

		do {
			boolean emptySpaceFound = false;
			if (totalStockValue[i] != totalStockValueDesc[j]) {
				j++;
			} else {
				do {
					if (al4.contains(j)) {
						j++;

					} else {
						emptySpaceFound = true;

					}
				} while (j < totalStockValue.length && emptySpaceFound == false);

				finalOrder[j] = portFolio.get(i);
				al4.add(j);
				i++;
				j = 0;
			}

		} while (i < totalStockValue.length);

		return finalOrder;

	}

	public static void main(String[] args) throws FileNotFoundException {
		Portfolio p = new Portfolio();
		String[] FinalPortFolioOrder = p.FinalSortedOrder(totalStockValueWithoutSort(removePortfolioCommas(readFile())),
				totalStockInDescendingOrder(totalStockValueWithoutSort(removePortfolioCommas(readFile()))), readFile());
		for (int i = 0; i < FinalPortFolioOrder.length; i++) {
			System.out.println(FinalPortFolioOrder[i]);
		}
	}

}
