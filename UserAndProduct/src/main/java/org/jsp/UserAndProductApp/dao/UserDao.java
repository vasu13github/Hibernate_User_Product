package org.jsp.UserAndProductApp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.UserAndProductApp.dto.User;

public class UserDao{
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	
//	we use the below method for to save the object in database
	public User saveUser(User user) {
		EntityTransaction t=manager.getTransaction();
		manager.persist(user);
		t.begin();
		t.commit();
		return user;
	}
	
//	we use the below method for to update the object in database
	public User updateUser(User user) {
		EntityTransaction t=manager.getTransaction();
		manager.merge(user);
		t.begin();
		t.commit();
		return user;
	}
	
//	we use the below method for to find the object in database
	public User findByUser(int id) {
	  return  manager.find(User.class, id);
	}
	
	
//	we use the below method for to delete the object in database using jpa
	public boolean deleteUser(int id) {
		User u=findByUser(id);
		if(u!=null) {
			manager.remove(u);
			EntityTransaction t=manager.getTransaction();
			t.begin();
			t.commit();
			return true;
		}
		else {
			return false;
		}
		
	}
	
//	we use the below method for to verify the given data is present in database or not
		public User VerifyUser(long phone ,String password) {
			Query q=manager.createQuery("select u from User u where u.phone=?1 and u.Password=?2");
			q.setParameter(1, phone);
			q.setParameter(2, password);
			try {
			return (User)q.getSingleResult();
			}
			catch (NoResultException e) {
				return null;
			}
		}
		
		
//		we use the below method for to verify the given data is present in database or not	
		public User VerifyUser(String email ,String password) {
			Query q=manager.createQuery("select u from User u where u.email=?1 and u.Password=?2");
			q.setParameter(1, email);
			q.setParameter(2, password);
			try {
			return (User)q.getSingleResult();
			}
			catch (NoResultException e) {
				return null;
			}
		}
		
	}
	
	
	
