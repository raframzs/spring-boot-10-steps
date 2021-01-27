package com.RRS.springbootin10steps.service;

import com.RRS.springbootin10steps.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserService {


    private long usersCount = 4L;
    private static List<User> users = new ArrayList<>();

    static
    {
        users.add(new User(1L, "Rafael", new Date()));
        users.add(new User(2L, "Lina", new Date()));
        users.add(new User(3L, "Natalia", new Date()));
        users.add(new User(4L, "Mayra", new Date()));
    }

    /**
     * GET all users
     * @return a List of users
     */
    public List<User> findAll() {
        return users;
    }

    /**
     * POST a desired user
     * @param user The user to persist
     * @return the saved user
     */
    public User save(User user){
        if (user.getId() == null){
            user.setId(++usersCount);
            users.add(user);
        }
        return user;
    }

    /**
     * GET a user existing in the list thought the id
     * @param id The id of the user to find
     * @return The user matching with the id or null if isn't in the list
     */
    public User findById(Long id){
        for (User user: users) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }


}
