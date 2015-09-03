package com.sci.user.dao;

import java.util.List;

import com.sci.user.model.User;


public interface UserDAO {
	public List<User> list();
	
	public User get(int id);
	
	public void saveOrUpdate(User user);
	
	public void delete(int id);
	
	public Boolean authenticate(User user);
	
	public Boolean isUserExists(String userName);
}