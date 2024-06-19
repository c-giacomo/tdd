package it.polish.exception;

public class PNException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PNException() {
        super();
    }
	
	public PNException(String message) {
        super(message);
    }

}
