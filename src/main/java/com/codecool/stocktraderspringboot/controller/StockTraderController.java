package com.codecool.stocktraderspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockTraderController {

	@GetMapping("/buy/{stock}/{price}")
	public String buyStock(@PathVariable String stock, @PathVariable String price) {
		System.out.println("Got request");
		return "Response";
	}

}
