package com.fifthgen.unittest;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class PortfolioTest extends Portfolio {

	@Test(priority = 1)
	public void validateRemovalOfCommasInEachPortfolio() throws FileNotFoundException {

		ArrayList<String> portFolioExamples = new ArrayList<String>();
		portFolioExamples.add("a-,b-,c,d,");
		portFolioExamples.add("14,20,30-");
		String[] portfolio = removePortfolioCommas(portFolioExamples);
		for (int i = 0; i < portfolio.length; i++) {
			Assert.assertFalse(portfolio[i].contains(","));
		}

	}

	@Test(priority = 2)
	public void validateTotalStockValueInEachPortforlio() throws FileNotFoundException {

		String[] portFolioExamples = { "a - 100 b - 90 c - 80", "a -100 c - 90", "c - 10 d-50" };
		int[] totalStockInEachPortfolio = totalStockValueWithoutSort(portFolioExamples);
		String[] totalStockInPortfolio = new String[totalStockInEachPortfolio.length];
		for (int i = 0; i < totalStockInEachPortfolio.length; i++) {
			totalStockInPortfolio[i] = String.valueOf(totalStockInEachPortfolio[i]);
			Assert.assertFalse(totalStockInPortfolio[i].contains("-"));
		}
		Assert.assertEquals(totalStockInPortfolio[0], "270");
		Assert.assertEquals(totalStockInPortfolio[1], "190");
		Assert.assertEquals(totalStockInPortfolio[2], "60");

	}

	@Test(priority = 3)
	public void validateTotalStockInDescendingOrder() throws FileNotFoundException {

		int[] totalStockInPortfolio = { 100, 200, 300, 150, 250, 50 };
		int[] totalStockInDescending = totalStockInDescendingOrder(totalStockInPortfolio);
		Assert.assertEquals(totalStockInDescending[0], 300);
		Assert.assertEquals(totalStockInDescending[1], 250);
		Assert.assertEquals(totalStockInDescending[2], 200);
		Assert.assertEquals(totalStockInDescending[3], 150);
		Assert.assertEquals(totalStockInDescending[4], 100);
		Assert.assertEquals(totalStockInDescending[5], 50);

	}

	@Test(priority = 4)
	public void validateFinalOrder() throws FileNotFoundException {

		int[] totalStockInEachPortfolio = { 100, 50, 300, 150 };
		int[] totalStockInEachPortfolioDesc = { 300, 150, 100, 50 };
		ArrayList<String> portFolioExamples = new ArrayList<String>();
		portFolioExamples.add("a-50, b-20, c-30");
		portFolioExamples.add("a-50");
		portFolioExamples.add("a-100,b-100,c-0,d-100");
		portFolioExamples.add("a-50,b-100");

		String[] finalPortfolioOrder = FinalSortedOrder(totalStockInEachPortfolio, totalStockInEachPortfolioDesc,
				portFolioExamples);

		Assert.assertEquals(finalPortfolioOrder[0], "a-100,b-100,c-0,d-100");
		Assert.assertEquals(finalPortfolioOrder[1], "a-50,b-100");
		Assert.assertEquals(finalPortfolioOrder[2], "a-50, b-20, c-30");
		Assert.assertEquals(finalPortfolioOrder[3], "a-50");

	}
}
