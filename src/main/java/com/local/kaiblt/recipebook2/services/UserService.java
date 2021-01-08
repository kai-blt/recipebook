package com.local.kaiblt.recipebook2.services;

import com.local.kaiblt.recipebook2.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    User save(User user);

    User update(User user, long id);

    void delete(long id);

    public void deleteAll();
}
