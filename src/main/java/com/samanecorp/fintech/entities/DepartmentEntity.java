package com.samanecorp.fintech.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class DepartmentEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy="department")
    private List<DeptEmployeeEntity> deptEmployees;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DeptEmployeeEntity> getDeptEmployees() {
		return deptEmployees;
	}

	public void setDeptEmployees(List<DeptEmployeeEntity> deptEmployees) {
		this.deptEmployees = deptEmployees;
	}
    
}
