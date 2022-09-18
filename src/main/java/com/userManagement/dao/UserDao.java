package com.userManagement.dao;

import java.io.PrintWriter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.userManagement.model.User;
import com.userManagement.utl.HibernateUtil;

public class UserDao {

	
	public void saveUser(User user) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(user);
			System.out.print("Hello");		
			transaction.commit();
		}
		catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int id) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			User user = session.get(User.class, id);
			if (user != null) {
                session.delete(user);
                System.out.println("user is deleted");
            }
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public User getUser(int id) {
		Transaction transaction = null;
		User user = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			user = session.get(User.class, id); 	 	
			transaction.commit();
		}
		catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List <User> getAllUser(){

		System.out.print("getAllUser");
		
		Transaction transaction = null;
		List <User> listOfUser = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			listOfUser = session.createQuery("from User").getResultList();
			transaction.commit();
			
		}catch(Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
			e.printStackTrace();
		}
		return listOfUser;
	}
}
