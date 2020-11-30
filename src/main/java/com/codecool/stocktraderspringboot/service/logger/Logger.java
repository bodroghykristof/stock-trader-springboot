package com.codecool.stocktraderspringboot.service.logger;

import org.springframework.stereotype.Component;

@Component
public interface Logger {

	void log(String message);

}
