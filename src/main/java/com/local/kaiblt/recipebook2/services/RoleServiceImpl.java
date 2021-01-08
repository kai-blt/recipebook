package com.local.kaiblt.recipebook2.services;

import com.local.kaiblt.recipebook2.models.Role;
import com.local.kaiblt.recipebook2.repository.RoleRepository;
import com.local.kaiblt.recipebook2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository rolerepos;

    @Autowired
    UserRepository userrepos;

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        rolerepos.findAll()
            .iterator()
            .forEachRemaining(roles::add);
        return roles;
    }

    @Override
    public Role findRoleById(long id) {
        return rolerepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not Found"));
    }

    @Override
    public Role save(Role role) {
        if (role.getUsers().size() > 0) {
            throw new EntityNotFoundException("User Roles are not updated here");
        }
        return rolerepos.save(role);
    }

    @Override
    public Role findByName(String name) {
        Role role = rolerepos.findByNameIgnoreCase(name);

        if (role != null) {
            return role;
        } else {
            throw new EntityNotFoundException(name);
        }
    }

    @Override
    public Role update(long id, Role role) {

//        if (role.getName() == null) {
//            throw new EntityNotFoundException("No role found");
//        }
//
//        if (role.getUsers().size() > 0) {
//            throw new EntityNotFoundException("User Roles not updated here");
//        }
//
//        Role newRole = findRoleById(id);
//
//        rolerepos.updateRoleName(,id,role.getName());
//
        return null;
    }

    @Override
    public void deleteAll() {
        rolerepos.deleteAll();
    }
}
