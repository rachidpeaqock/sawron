package com.tenor.tsf.gs.exceptions;

import org.apache.log4j.Logger;

public class NotFoundException extends RuntimeException {
	private static final Logger LOGGER = Logger.getLogger(EmptyFieldException.class);
	private String msg;
	public NotFoundException(String Msg) {
		super();
		this.msg=Msg;
		LOGGER.debug(this.msg, this);
	}

}
