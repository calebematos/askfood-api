package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.PermissionNotFoundException;
import com.calebematos.askfood.domain.model.Permission;
import com.calebematos.askfood.domain.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public Permission findById(Long permissionId){
        return permissionRepository.findById(permissionId).orElseThrow(() ->
                PermissionNotFoundException.of(permissionId));
    }

}
