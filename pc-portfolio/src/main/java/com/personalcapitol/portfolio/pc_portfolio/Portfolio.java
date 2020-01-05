package com.personalcapitol.portfolio.pc_portfolio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.personalcapitol.result.Result;

public class Portfolio {

	public static void main(String[] args) {
		Portfolio portfolio = new Portfolio();
		int numOfSimulations = 10000;
		int numOfYears = 20;
		double initialPrinciple = 100000.0;
		double inflation = 3.5;
		// risk = standardDeviation, return = mean
		portfolio.runSimulation(numOfSimulations, initialPrinciple, 6.189, 6.3438, inflation, numOfYears);
		portfolio.runSimulation(numOfSimulations, initialPrinciple, 9.4324, 15.675, inflation, numOfYears);
		
	}

	public Result runSimulation(final int numOfSimulations, double initialPrinciple, double mean,
			double standardDeviation, double inflation, int numOfYears) {
		
		final DecimalFormat df = new DecimalFormat("#.##");
		Double[] initialPrinciples = new Double[numOfSimulations];
		Arrays.fill(initialPrinciples, initialPrinciple);
		List<List<Double>> allReturns = new ArrayList();
		List<List<Double>> allRates = new ArrayList();
		List<Double> previousPrinciples = Arrays.asList(initialPrinciples);

		for (int i = 1; i <= numOfYears; i++) {
			List<Double> rateOfReturns = getGaussianSimulation(numOfSimulations, standardDeviation, mean);
			List<Double> simulatedReturns = getReturns(rateOfReturns, previousPrinciples, inflation);
			allReturns.add(simulatedReturns);
			allRates.add(rateOfReturns);
			previousPrinciples = simulatedReturns;
		}
		Result result = calculateMedianBestWorst(previousPrinciples);
		System.out.println("At the end of year "+numOfYears+" with return %"+mean+" and risk of "+standardDeviation+",\nMedian is:" + df.format(result.getMedian()) + "\nBest case :"
				+ df.format(result.getBestCase()) + "\nWorst case:" + df.format(result.getWorstCase())+"\n\n");
		return result;
	}

	public List<Double> getGaussianSimulation(int numberOfSimulations, double standardDeviation, double mean) {
		List<Double> simulations = new ArrayList<Double>();
		Random r = new Random();
		for (int i = 0; i < numberOfSimulations; i++) {
			double d = r.nextGaussian() * standardDeviation + (mean);
			simulations.add(d);
		}
		return simulations;
	}

	public List<Double> getReturns(List<Double> rateOfReturn, List<Double> initialPrinciples, double inflation) {
		List<Double> returns = new ArrayList<Double>();
		double ret;
		for (int i = 0; i < rateOfReturn.size(); i++) {
			ret = initialPrinciples.get(i) * Math.pow((1 + ((rateOfReturn.get(i) - inflation) / 100)), 1);
			returns.add(ret);
		}
		return returns;
	}

	public Result calculateMedianBestWorst(List<Double> simulatedReturns) {
		Result rs = new Result();
		List<Double> resultSet = new ArrayList<Double>(simulatedReturns);
		Collections.sort(resultSet);
		rs.setMedian(resultSet.get((resultSet.size() / 2) - 1));
		rs.setBestCase(resultSet.get(((int) resultSet.size() * 9 / 10) - 1));
		rs.setWorstCase(resultSet.get((resultSet.size() / 10) - 1));
		return rs;
	}

}
