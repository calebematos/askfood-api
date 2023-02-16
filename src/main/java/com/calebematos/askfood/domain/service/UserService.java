package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.BusinessException;
import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.UserNotFoundException;
import com.calebematos.askfood.domain.model.User;
import com.calebematos.askfood.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

	public static final String MSG_ROLE_IN_USE = "User code %d cannot be removed because it is in use";

	private final UserRepository userRepository;

	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public void delete(Long userId) {

		try {
			userRepository.deleteById(userId);
			userRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw UserNotFoundException.of(userId);
		} catch (DataIntegrityViolationException e) {
			throw EntityInUseException.of(format(MSG_ROLE_IN_USE, userId));
		}
	}
	
	public User findById(Long userId){
		return userRepository.findById(userId).orElseThrow(() ->
				UserNotFoundException.of(userId));
	}

	@Transactional
	public void updatePassword(Long userId, String currentPassword, String newPassword) {

		User user = findById(userId);
		if(!currentPassword.equals(user.getPassword())){
			throw BusinessException.of("The current password entered does not match the user's password");
		}

		user.setPassword(newPassword);

	}
}
