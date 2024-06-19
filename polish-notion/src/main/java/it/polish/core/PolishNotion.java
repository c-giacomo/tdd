package it.polish.core;

import it.polish.exception.PNException;

public class PolishNotion {

	public Integer calcola(String exp) {
		try {
			if (exp == null || exp.isEmpty()) throw new PNException();
			
			String[] s = exp.split(" ");
			
			Integer i1 = Integer.valueOf(s[0]);
			if (s.length == 1) return i1;
			
			Integer i2 = Integer.valueOf(s[1]);
			String ope = s[2];
			
			if (s.length > 3 || ope == null) throw new PNException();
			
			switch(ope) {
				case "+": return sum(i1, i2);
				case "-": return minus(i1, i2);
				case "/": return divide(i1, i2);
				case "*": return multiply(i1, i2);
				default: return 0;
			}
		
		} catch (Exception e) {
			throw new PNException();
		}
		
	}
	
	private Integer sum(Integer i1, Integer i2) {
		return i1 + i2;
	}
	
	private Integer minus(Integer i1, Integer i2) {
		return i1 - i2;
	}

	private Integer divide(Integer i1, Integer i2) {
		return i1 / i2;
	}

	private Integer multiply(Integer i1, Integer i2) {
		return i1 * i2;
	}

}
