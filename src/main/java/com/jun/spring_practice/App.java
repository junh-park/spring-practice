package com.jun.spring_practice;

import java.sql.SQLException;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
    	UserDao dao = new UserDao();
    	
    	User user = new User();
    	user.setId("whiteship");
    	user.setName("jun");
    	user.setPassword("married");
    	
    	dao.add(user);
    	
    	System.out.println(user.getId() + " registered successfully");
    	
    	User user2 = dao.get(user.getId());
    	System.out.println(user2.getName());
    	System.out.println(user2.getPassword());
    	
    	System.out.println(user2.getId()+" seached successfully");
    	
    }
}
