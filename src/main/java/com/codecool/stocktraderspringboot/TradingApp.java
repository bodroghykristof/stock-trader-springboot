package com.codecool.stocktraderspringboot;

import com.codecool.stocktraderspringboot.logger.FileLogger;
import com.codecool.stocktraderspringboot.logger.Logger;
import com.codecool.stocktraderspringboot.service.RemoteURLReader;
import com.codecool.stocktraderspringboot.service.StockAPIService;
import com.codecool.stocktraderspringboot.service.Trader;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides a command line interface for stock trading.
 **/
public class TradingApp {

	private static final Logger logger = new FileLogger();
	private static final RemoteURLReader urlReader = new RemoteURLReader();
	private static final StockAPIService stockService = new StockAPIService(urlReader);
	private static final Trader trader = new Trader(logger, stockService);

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String symbol = readSymbol(keyboard);
		Double bid = readBid(keyboard);
		attemptPurchase(symbol, bid);
	}

	private static String readSymbol(Scanner scanner) {
		System.out.println("Enter a stock symbol (for example aapl):");
		return scanner.nextLine();
	}

	private static Double readBid(Scanner keyboard) {
		System.out.println("Enter the maximum price you are willing to pay: ");
		double price;
		try {
			price = keyboard.nextDouble();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Enter a number");
			return null;
		}
		return price;
	}

	private static void attemptPurchase(String symbol, Double bid) {
		if (bid == null) return;

		try {
			boolean purchased = trader.buy(symbol, bid);
			if (purchased) {
				logger.log("Purchased stock!");
			}
			else {
				logger.log("Couldn't buy the stock at that price.");
			}
		} catch (Exception e) {
			logger.log("There was an error while attempting to buy the stock: " + e.getMessage());
		}
	}
}
