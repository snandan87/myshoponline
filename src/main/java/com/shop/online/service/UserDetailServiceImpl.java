package com.shop.online.service;

import java.util.List;

import javax.annotation.Resource;import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.online.exception.UserDetailExist;
import com.shop.online.exception.UserDetailNotFound;
import com.shop.online.model.UserDetail;
import com.shop.online.repository.UserDetailRepository;


@Service
public class UserDetailServiceImpl implements UserDetailService {
	
	@Resource
	private UserDetailRepository userDetailRepository;

	@Override
	@Transactional
	public UserDetail create(UserDetail UserDetail) throws UserDetailExist {
		UserDetail createdUserDetail;
		createdUserDetail = userDetailRepository.save(UserDetail);
		return createdUserDetail;
	}
	
	@Override
	@Transactional
	public UserDetail findById(int id) {
		return userDetailRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=UserDetailNotFound.class)
	public UserDetail delete(int id) throws UserDetailNotFound {
		UserDetail deletedUserDetail = userDetailRepository.findOne(id);
		
		if (deletedUserDetail == null)
			throw new UserDetailNotFound();
		
		userDetailRepository.delete(deletedUserDetail);
		return deletedUserDetail;
	}

	@Override
	@Transactional
	public List<UserDetail> findAll() {
		return userDetailRepository.findAll();
	}
	

	@Override
	@Transactional
	public List<String> findAllUnicqueFname(){
		return userDetailRepository.findAllUnicqueFname();
	}

	@Override
	@Transactional(rollbackFor=UserDetailNotFound.class)
	public UserDetail update(UserDetail UserDetail) throws UserDetailNotFound {
		UserDetail updatedUserDetail = userDetailRepository.findOne(UserDetail.getId());
		
		if (updatedUserDetail == null)
			throw new UserDetailNotFound();
		
		updatedUserDetail.setLName(UserDetail.getLName());
		updatedUserDetail.setFName(UserDetail.getFName());
		updatedUserDetail.setLocation(UserDetail.getLocation());
		updatedUserDetail.setPhone(UserDetail.getPhone());
		return updatedUserDetail;
	}

	@Override
	public boolean isUserExist(UserDetail UserDetail) {
		if(userDetailRepository.findUncqueByFnameLnameLocation(UserDetail.getFName(), UserDetail.getLName(), UserDetail.getLocation()).isEmpty())
		return false;
		else
		return true;	
	}

}
