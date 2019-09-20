package com.tenor.tsf.gs.exceptions;

import org.apache.log4j.Logger;


public class EmptyFieldException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(EmptyFieldException.class);
	
	private String msg;
	
	public EmptyFieldException( String Msg) {
		this.msg=Msg;
		LOGGER.error(this.msg,this);
	}
}
