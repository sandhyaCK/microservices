package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.dto.PasswordDto;
import com.bridgelabz.fundoonotes.model.User;

@Repository
public class UserRepository {
	@PersistenceContext
	private EntityManager entityManger;

	/* Query for save the data into userDatabase */
	
	public User save(User information) {
		Session session = entityManger.unwrap(Session.class);
		session.saveOrUpdate(information);
		return information;
	}

	/* Query to find the user by id */
	
	public User findUserById(Long id) {
		Session session = entityManger.unwrap(Session.class);
		Query<User> q = session.createQuery("FROM User where id=:id");
		q.setParameter("id", id);
		return (User) q.uniqueResult();
	}

	/* Query to get the usr information bby email */
	
	public User getUser(String email) {
		System.out.println("$$$$$$$");
		Session session = entityManger.unwrap(Session.class);
		Query<User> q = session.createQuery("FROM User where email=:email");
		q.setParameter("email", email);

		return (User) q.uniqueResult();
	}

	/* Query to update the PasswordInformation */
	
	public boolean update(PasswordDto information, Long id) {
		Session session = entityManger.unwrap(Session.class);
		Query<User> q = session.createQuery("update User set password=:p" + " " + " where id=:id");
		q.setParameter(" p", information.getConfirmPassword());
		q.setParameter("id", id);
		int status = q.executeUpdate();
		if (status > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Query to update the verify to TRUE once the token taken from the
	 * RegisteredUser
	 */
	
	public boolean verify(Long id) {
		Session session = entityManger.unwrap(Session.class);
		Query<User> q = session
				.createQuery("update User set is_verified =:p" + " " + " " + " where user_id=:i");
		q.setParameter("p", true);
		q.setParameter("i", id);
		int status = q.executeUpdate();
		if (status > 0) {
			return true;

		} else {
			return false;
		}

	}

	/* Query to list out all the User Information */
	
	public List<User> getUsers() {
		Session session = entityManger.unwrap(Session.class);
		List<User> userList = session.createQuery("FROM User").getResultList();
		return userList;
	}

}
