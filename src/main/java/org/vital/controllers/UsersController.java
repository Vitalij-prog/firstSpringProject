package org.vital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.vital.dao.UserDAO;
import org.vital.models.User;
import org.vital.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    //private final UserDAO userDAO;

    private UserService userService;

    /*public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;

    }*/
    public UsersController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/login")
    public String login(Model model) {

        User user = new User();

        model.addAttribute("user", user);
        return "users/login";
    }

    @PostMapping("/findUser")
    public String findUser(@ModelAttribute("user")  @Valid User user, BindingResult bindingResult) {

       /* if(bindingResult.hasErrors()) {
            return "users/login";
        }*/

        if(userService.findUserForAuthorization(user)) {
            return "home";
        }
        return "users/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {

        model.addAttribute("user", new User());
        return "users/signup";
    }

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("users", userService.findAllUsers() /*userDAO.index()*/);
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("user", userService.findUser(id)/*userDAO.show(id)*/);
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {                     //@ModelAttribute("user") User user
        model.addAttribute("user", new User());             // nothing
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user")  @Valid User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "users/new";
        }

        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {

        model.addAttribute("user", userService.findUser(id) /*userDAO.show(id)*/);

        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "users/edit";
        }
        user.setRole("user");
        //userDAO.update(id, user);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("user") User user /*@PathVariable("id") int id*/ ) {

        //userDAO.delete(id);
        userService.deleteUser(user);
        return "redirect:/users";
    }

}
