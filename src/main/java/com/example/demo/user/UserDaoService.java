package com.example.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
		
	private static List<User> users = new ArrayList<User>();
	private static int userCount;
	
	static {
		users.add(new User(1, "Korn", new Date()));
		users.add(new User(2, "Joy", new Date()));
		users.add(new User(3, "Adam", new Date()));
		users.add(new User(4, "Nithin", new Date()));
		userCount = users.size();
	}
	
	public List<User> findAllUsers() {
		return users;
	}
	
	public User findOneById(int id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User saveUser(User user) {
		if(user.getId() == 0) {
			user.setId(++userCount);
		}
		user.setTimeStamp(new Date());
		users.add(user);
		return user;
	}
	
	public User deleteUserById(int id) {
		
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		
		return null;
		
	}
	
}