package com.codecool.stocktraderspringboot.controller;

import com.codecool.stocktraderspringboot.exception.UnknownStockException;
import com.codecool.stocktraderspringboot.service.Trader;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StockTraderController {

	private Trader trader;

	public StockTraderController(Trader trader) {
		this.trader = trader;
	}

	@GetMapping("/buy/{stock}/{bid}")
	public String buyStock(@PathVariable String stock, @PathVariable int bid) {
		try {
			if (trader.buy(stock, bid)) {
				return "Purchased " + stock + " stock at $" + bid + ", since its higher that the current price ($" + trader.getPrice() + ")";
			} else return "Bid for " + stock + " was $" + bid + " but the stock price is $" + trader.getPrice() + ", no purchase was made.";
		} catch (IOException | JSONException e) {
			e.printStackTrace();
			return "An error occurred no purchase happened";
		} catch (UnknownStockException e) {
			return "Unfortunately the given stock is not present in our system";
		}
	}

}
