package com.tenor.tsf.gs.exceptions;

import org.apache.log4j.Logger;

public class DateExpection extends RuntimeException {
	private static final Logger LOGGER = Logger.getLogger(EmptyFieldException.class);
	private String msg;
	public DateExpection(String Msg) {
		super();
		// TODO Auto-generated constructor stub
		this.msg=Msg;
		LOGGER.error(this.msg,this);
	}
	
}
