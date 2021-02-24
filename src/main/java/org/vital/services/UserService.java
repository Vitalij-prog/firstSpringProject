package org.vital.services;


import org.springframework.stereotype.Service;
import org.vital.hibdao.UserDao;
import org.vital.models.User;

import java.util.List;

@Service
public class UserService {

    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public boolean findUserForAuthorization(User user) {
        return usersDao.findByNameAndPassword(user);
    }

   /* public Auto findAutoById(int id) {
        return usersDao.findAutoById(id);
    }*/

}
