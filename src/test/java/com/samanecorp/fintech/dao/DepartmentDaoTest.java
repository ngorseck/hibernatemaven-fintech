package com.samanecorp.fintech.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samanecorp.fintech.entities.DepartmentEntity;

public class DepartmentDaoTest {

	private DepartmentDao departmentDao;
	private boolean result;
	
	@BeforeEach
	void init () {
		departmentDao = new DepartmentDao();
	}
	
	@Test
	void crudOK () {
		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setName("DEV");
		
		//result = departmentDao.add(departmentEntity);
		
		//Assertions.assertTrue(result);
		
		Assertions.assertEquals(4, departmentDao.list(departmentEntity).size());
		
		departmentEntity.setId(2L);
		departmentEntity.setName("HR");
		result = departmentDao.update(departmentEntity);
		Assertions.assertTrue(result);
		  
		
		departmentEntity = departmentDao.get(3L, departmentEntity);
		Assertions.assertEquals("IT", departmentEntity.getName());
		  
		//result = departmentDao.delete(6L, departmentEntity);
		//Assertions.assertTrue(result);
		  
		Assertions.assertEquals(4, departmentDao.list(departmentEntity).size());
	}
	
}
