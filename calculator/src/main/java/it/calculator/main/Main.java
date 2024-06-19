package it.calculator.main;

import it.calculator.Calcolatrice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main(String[] args) {
		int result = new Calcolatrice().sub(4, 4);
		log.info(String.valueOf(result));

	}

}
