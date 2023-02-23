package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.RoleNotFoundException;
import com.calebematos.askfood.domain.model.Permission;
import com.calebematos.askfood.domain.model.Role;
import com.calebematos.askfood.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RoleService {

	public static final String MSG_ROLE_IN_USE = "Role code %d cannot be removed because it is in use";

	private final RoleRepository roleRepository;
	private final PermissionService permissionService;

	@Transactional
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Transactional
	public void delete(Long roleId) {

		try {
			roleRepository.deleteById(roleId);
			roleRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw RoleNotFoundException.of(roleId);
		} catch (DataIntegrityViolationException e) {
			throw EntityInUseException.of(format(MSG_ROLE_IN_USE, roleId));
		}
	}
	
	public Role findById(Long roleId){
		return roleRepository.findById(roleId).orElseThrow(() ->
				RoleNotFoundException.of(roleId));
	}

	@Transactional
    public void disassociate(Long roleId, Long permissionId) {
		Role role = findById(roleId);
		Permission permission = permissionService.findById(permissionId);

		role.removePermission(permission);
	}

	@Transactional
	public void associate(Long roleId, Long permissionId) {
		Role role = findById(roleId);
		Permission permission = permissionService.findById(permissionId);

		role.addPermission(permission);
	}
}
