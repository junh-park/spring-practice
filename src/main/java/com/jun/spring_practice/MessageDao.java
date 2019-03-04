package com.jun.spring_practice;

public class MessageDao {

	private ConnectionMaker connectionMaker;

	public MessageDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

}
