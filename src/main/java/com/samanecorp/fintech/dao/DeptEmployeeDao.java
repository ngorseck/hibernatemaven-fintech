package com.samanecorp.fintech.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.fintech.entities.DepartmentEntity;
import com.samanecorp.fintech.entities.DeptEmployeeEntity;
import com.samanecorp.fintech.hibernate.HibernateUtil;

public class DeptEmployeeDao extends RepositoryImpl<DepartmentEntity> {

	Logger logger = LoggerFactory.getLogger(LoginDao.class);

	public Optional<List<DeptEmployeeEntity>> findDeptInClause(String searchKey) {
		
		List<DeptEmployeeEntity> results = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<DeptEmployeeEntity> cq = cb.createQuery(DeptEmployeeEntity.class);
		Root<DeptEmployeeEntity> emp = cq.from(DeptEmployeeEntity.class);
		
		Subquery<DepartmentEntity> subquery = cq.subquery(DepartmentEntity.class);
		Root<DepartmentEntity> dept = subquery.from(DepartmentEntity.class);
		Join<DepartmentEntity, DeptEmployeeEntity> sqEmp = dept.join("deptEmployees");
		
		subquery.select(sqEmp.get("name"))
		  		.distinct(true)
		  		.where(cb.like(
		  				dept.get("name"), "%" + searchKey + "%")
		  			);
		
		cq.select(emp)
		   .where(cb.in(emp.get("department.name")).value(subquery));
								 
		try {
			results = session.createQuery(cq).getResultList();
			logger.info("Connexion ok");
		} catch (Exception e) {
			logger.error("Error :", e);
		} finally {
			session.close();
		}
		return Optional.ofNullable(results);
	}
	
	public Optional<List<DeptEmployeeEntity>> findWithPredicate() {
		
		List<DeptEmployeeEntity> results = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<DeptEmployeeEntity> cq = cb.createQuery(DeptEmployeeEntity.class);
		Root<DeptEmployeeEntity> depEmp = cq.from(DeptEmployeeEntity.class);

		Predicate conditionItDept = cb.equal(depEmp.get("department.name"), "IT");
		Predicate conditionFinDept = cb.equal(depEmp.get("department.name"), "FINANCE");
		Predicate finalPredicate = cb.or(conditionItDept, conditionFinDept);
		
		cq.where(finalPredicate);

		try {
			results = session.createQuery(cq).getResultList();
			logger.info("Connexion ok");
		} catch (Exception e) {
			logger.error("Error :", e);
		} finally {
			session.close();
		}
		return Optional.ofNullable(results);
	}
}
