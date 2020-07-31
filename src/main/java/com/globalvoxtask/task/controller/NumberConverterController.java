package com.globalvoxtask.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberConverterController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(NumberConverterController.class);

	@GetMapping("/int-to-binary/{intValue}")
	public ResponseEntity<String> IntToBinary(@PathVariable int intValue)
	{

		logger.info("Converting int =" + intValue);
		String binaryNumber = "";

		while (intValue > 0)
		{
			int y = intValue % 2;
			binaryNumber = y + binaryNumber;
			intValue = intValue / 2;
		}
		logger.info("Result Binary number =" + binaryNumber);

		return ResponseEntity.ok()
				.body("binary value " + binaryNumber);
	}
}
