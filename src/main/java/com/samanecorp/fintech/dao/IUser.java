package com.samanecorp.fintech.dao;
import java.util.List;

import com.samanecorp.fintech.entities.UserEntity;


public interface IUser {

	public List<UserEntity> liste();
	public boolean delete(long mat);
	public boolean update(UserEntity e);
	public UserEntity get(long mat);
	public boolean add(UserEntity u);
}