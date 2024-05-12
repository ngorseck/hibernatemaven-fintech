package com.samanecorp.fintech.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.fintech.entities.UserEntity;
import com.samanecorp.fintech.hibernate.HibernateUtil;

public class LoginDao {
	Logger logger = LoggerFactory.getLogger(LoginDao.class);
	
	public Optional<UserEntity> log (String email, String pwd) {
		UserEntity result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cr.from(UserEntity.class);
								cr.select(user);
								cr.where(cb.equal(user.get("email"), email));
								cr.where(cb.equal(user.get("password"), pwd));
		try {
			result = session.createQuery(cr).getSingleResult();
			logger.info("Connexion ok");
		} catch (Exception e) {
			logger.error("Error :", e);
		} finally {
			session.close();
		}
		return Optional.ofNullable(result);
	}
	public Optional<UserEntity> logException (String email, String pwd) {
		UserEntity result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cr.from(UserEntity.class);
								cr.select(user);
								cr.where(cb.equal(user.get("email"), email));
								cr.where(cb.equal(user.get("password"), pwd));
		
		return Optional.ofNullable(session.createQuery(cr).getSingleResult());
	}
	
	public Optional<UserEntity> loginUser(String email, String pwd) {
		UserEntity userEntity = null;
		Transaction transaction = null;
 		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
 			transaction = session.beginTransaction();
 			userEntity = (UserEntity) session.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email")
 												.setParameter("email", email)
 												.uniqueResult();
	            if (userEntity != null && userEntity.getPassword().equals(pwd)) {
	                return Optional.ofNullable(userEntity);
	            }		 
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
			//	transaction.rollback();
			}
			logger.error("Error ", e);
		}
		return Optional.ofNullable(userEntity);
 	}
	public Optional<UserEntity> login (String email, String password) {
		
		if (email.equals("contact@samanecorporation.com") && password.equals("passer123@")) {
			UserEntity user = new UserEntity();
			user.setEmail(email);
			user.setPassword(password);
			
			return Optional.of(user) ;
		} else {
			return Optional.ofNullable(null);
		}
		
	}
}
