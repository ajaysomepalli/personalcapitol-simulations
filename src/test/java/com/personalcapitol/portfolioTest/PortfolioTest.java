package com.personalcapitol.portfolioTest;


import java.text.DecimalFormat;

import org.junit.Test;

import com.personalcapitol.portfolio.Portfolio;
import com.personalcapitol.result.Result;
import junit.framework.TestCase;


public class PortfolioTest extends TestCase {
	final DecimalFormat df = new DecimalFormat("#.##");

	@Test
	public void testWithZeroInflationZeroRiskZeroReturns() {
		Portfolio portfolioTester = new Portfolio();
		Result resultTester = new Result();
		resultTester = portfolioTester.runSimulation(10000, 100000, 0, 0, 0, 20);
		assertEquals(100000.0, resultTester.getMedian());
	}
	
	@Test
	public void testWithHighInflationThanReturns() {
		Portfolio portfolioTester = new Portfolio();
		Result resultTester = new Result();
		resultTester = portfolioTester.runSimulation(10000, 100000, 0, 0, 3, 20);
		assertFalse(resultTester.getBestCase() > 100000.0);
	}
	
	@Test
	public void testWithStandardReturnsNoRiskNoInflation() {
		Portfolio portfolioTester = new Portfolio();
		Result resultTester = new Result();
		resultTester = portfolioTester.runSimulation(10000, 100000, 5, 0, 0, 20);
		System.out.println((100000 * Math.pow(1.05, 20)));
		
		assertEquals((100000 * Math.pow(1.05, 20)), resultTester.getMedian(), .1);
	}
}
