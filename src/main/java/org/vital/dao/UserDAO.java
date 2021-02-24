package org.vital.dao;

import org.springframework.stereotype.Component;
import org.vital.models.User;

import java.util.ArrayList;

@Component
public class UserDAO {

    public static int USER_COUNT;

    private ArrayList<User> users;

    {
        users = new ArrayList<User>();
        /*users.add(new User(++USER_COUNT, "user", "user"));
        users.add(new User(++USER_COUNT, "admin", "admin"));
        users.add(new User(++USER_COUNT, "vital", "vital"));*/
    }

    public ArrayList<User> index() {
        return users;
    }

    public User show(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {

        user.setId(++USER_COUNT);
        users.add(user);
    }

    public void update(int id, User user) {

        User updateUser = show(id);

        updateUser.setName(user.getName());
        updateUser.setPassword(user.getPassword());
    }

    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
