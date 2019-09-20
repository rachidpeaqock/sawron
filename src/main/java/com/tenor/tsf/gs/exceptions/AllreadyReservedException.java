package com.tenor.tsf.gs.exceptions;

import org.apache.log4j.Logger;

public class AllreadyReservedException extends RuntimeException{
	private static final Logger LOGGER = Logger.getLogger(EmptyFieldException.class);
	private String msg;
	public AllreadyReservedException(String Msg) {
		this.msg=Msg;
		LOGGER.error(this.msg,this);
	}
}
