package com.example.springbootwebservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private int count=3;

	private static List<User> users=new ArrayList<>();

	static {
		users.add(new User("ABC", 123, new Date()));
		users.add(new User("XYZ", 895, new Date()));
		users.add(new User("ABC", 123, new Date()));
	}

	public List<User>findAll(){
		return users;
	}
	public User save(User data) {
		if(null==data.getId()) 

			data.setId(count++);
		users.add(data);


		return data;

	}

	public User getUser(int id) {
		for(User data :users) {
			if(id==data.getId())
				return  data;
		}

		return null;
	}
}
