package com.jun.spring_practice;

public class AccountDao{

	private ConnectionMaker connectionMaker;

	public AccountDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

}
