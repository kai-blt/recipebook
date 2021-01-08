package com.local.kaiblt.recipebook2.services;

import com.local.kaiblt.recipebook2.models.*;
import com.local.kaiblt.recipebook2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleService roleService;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userrepos.findAll()
            .iterator()
            .forEachRemaining(users::add);
        return users;
    }

    @Override
    public User findByUsername(String username) {
        return userrepos.findByUsername(username.toLowerCase());
    }

    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();

        if (user.getUserid() != 0) {
            userrepos.findById(user.getUserid())
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername().toLowerCase());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        newUser.getRoles().clear();
        for (UserRoles ur : user.getRoles()) {
            Role role = roleService.findRoleById(ur.getRole().getRoleid());

            newUser.getRoles().add(new UserRoles(newUser, role));
        }

        newUser.getRecipes().clear();
        for (Recipe r : user.getRecipes()) {
            Recipe r1 = new Recipe(r.getName(), r.getType(), newUser);

            //Add all ingredients to the recipe
            for (Ingredient i : r.getIngredients()) {
                r1.getIngredients().add(new Ingredient(i.getName(), i.getQuantity(), i.getMeasurement(), r1));
            }

            //Add all steps to the recipe
            for (Step s : r.getSteps()) {
                r1.getSteps().add(new Step(s.getStepnumber(), s.getInstructions(), r1));
            }

            //Add recipe to user
            newUser.getRecipes().add(r1);
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user, long id) {
        return null;
    }

    @Transactional
    @Override
    public void delete(long id) {

    }

    @Transactional
    @Override
    public void deleteAll() {

    }
}
