package com.samanecorp.fintech.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.samanecorp.fintech.hibernate.HibernateUtil;

public class RepositoryImpl<T> implements Repository<T> {
	
	private Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction transaction = null;
	
	
	@Override
	public boolean add(T t) {
		try {
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
			return true;
		} catch (Exception e2) {
			return false;
		}
	}

	@Override
	public boolean delete(long id,T t) {
				
		try {
			transaction = session.beginTransaction();
			session.delete(session.get(t.getClass(), id));
			transaction.commit();
			return true;
		} catch (Exception e2) {
			return false;
		}
	}

	@Override
	public boolean update(T t) {
		try {
			transaction = session.beginTransaction();
			session.merge(t);
			transaction.commit();
			return true;
		} catch (Exception e2) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(T t) {
		
       CriteriaBuilder cb = session.getCriteriaBuilder();
      
       CriteriaQuery<T> cr = (CriteriaQuery<T>) cb.createQuery(t.getClass());
       Root<T> root = (Root<T>) cr.from(t.getClass());
       cr.select(root);
              
       return session.createQuery(cr).getResultList();
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get (long id,T t) {
		
        return (T) session.get(t.getClass(), id);
	}

	
}