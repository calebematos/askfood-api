package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.BusinessException;
import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.UserNotFoundException;
import com.calebematos.askfood.domain.model.Role;
import com.calebematos.askfood.domain.model.User;
import com.calebematos.askfood.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

	public static final String MSG_ROLE_IN_USE = "User code %d cannot be removed because it is in use";

	private final UserRepository userRepository;
	private final RoleService roleService;

	@Transactional
	public User save(User user) {

		userRepository.detach(user);
		Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

		if(optionalUser.isPresent() && !optionalUser.get().equals(user)){
			throw BusinessException.of(format("There is already a registered user with email %s", user.getEmail()));
		}

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

	@Transactional
    public void disassociateRole(Long userId, Long roleId) {
		User user = findById(userId);
		Role role = roleService.findById(roleId);

		user.removeRole(role);
	}

	@Transactional
	public void associateRole(Long userId, Long roleId) {
		User user = findById(userId);
		Role role = roleService.findById(roleId);

		user.addRole(role);
	}
}
