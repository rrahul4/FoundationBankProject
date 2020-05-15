package com.batch12.rvirb.foundation.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFound extends RuntimeException {

	public AccountNotFound(String message) {

		super(message);
		// TODO Auto-generated constructor stub

	}
}
