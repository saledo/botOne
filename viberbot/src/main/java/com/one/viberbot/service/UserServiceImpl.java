package com.one.viberbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.viberbot.database.entity.AppUser;
import com.one.viberbot.database.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Override
	public Iterable<AppUser> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void add(AppUser user) {
		AppUser appuser = userRepository.findOne(user.getId());
		
		if(appuser != null && !appuser.getSubscribe())
			userRepository.save(user);
	}

	@Override
	public AppUser getByViberId(String viberId) {
		return userRepository.getByViberId(viberId);
	}

	@Override
	public void subscribe(String viberId) {
		AppUser user = userRepository.getByViberId(viberId);
		user.setSubscribe(true);
		userRepository.save(user);
	}

	@Override
	public void unsubscribe(String viberId) {
		AppUser user = userRepository.getByViberId(viberId);
		user.setSubscribe(false);
		userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

}
