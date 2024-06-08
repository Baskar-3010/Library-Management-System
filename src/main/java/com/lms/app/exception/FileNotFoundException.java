package com.lms.app.exception;

public class FileNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -838673363484903963L;
	
	public FileNotFoundException(String msg){
		super(msg);
	}

}
