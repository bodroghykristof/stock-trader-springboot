package com.codecool.stocktraderspringboot.controller;

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

	@GetMapping("/buy/{stock}/{price}")
	public String buyStock(@PathVariable String stock, @PathVariable int price) {
		System.out.println("Got request");
		try {
			trader.buy("aapl", 100);
			return "Done";
		} catch (IOException | JSONException e) {
			e.printStackTrace();
			return "Error";
		}
	}

}
