package com.klimenko.academer.service;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.klimenko.academer.domain.User;

@Service("userService")
@Transactional
public class UserService {
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	//Retrieves all users in List<>
	public List<User> getAll() {
		logger.debug("Retrieving all users");
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("FROM Person");
		
		return query.list();
	}
	
	
	//Retrieves a single user	
	public User get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		User user = (User) session.get(User.class, id);
		
		return user;
	}
	
	
	//Adds a new user
	public void add(User user) {
		logger.debug("Adding new user");
		
		Session session = sessionFactory.getCurrentSession();
		
		session.save(user);
	}
	
	
	//Deletes a user
	public void delete(Integer id) {
		logger.debug("Deleting a user");
		
		Session session = sessionFactory.getCurrentSession();
		
		User user = (User) session.get(User.class, id);
		
		session.delete(user);
	}
	
	public void edit(User user) {
		logger.debug("Editing a user");
		
		Session session = sessionFactory.getCurrentSession();
		
		User existingUser = (User) session.get(User.class, user.getId());
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		
		session.save(existingUser);
	}
}
