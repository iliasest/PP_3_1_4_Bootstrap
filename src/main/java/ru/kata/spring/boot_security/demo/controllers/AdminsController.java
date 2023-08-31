package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {
//    @GetMapping
//    public String admin() {
//        return "admin";
//    }
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminsController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public ModelAndView getUsers(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("usersList", userService.getUsers());

        return modelAndView;
    }

//    @GetMapping("/{id}")
//    public ModelAndView showUser(@PathVariable("id") int id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", userService.getUserById(id));
//        modelAndView.setViewName("/show");
//        return modelAndView;
//    }

    @GetMapping("/new")
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new");

        ////
        List<Role> roles = roleRepository.findAll();
        modelAndView.addObject("allRoles", roles);
        ////

        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");

        userService.add(user);

        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", userService.getUserById(id));


        ////
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);

        List<Role> roles = roleRepository.findAll();
        modelAndView.addObject("allRoles", roles);
        ////


        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView update(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");

        userService.edit(user);

        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");

        userService.delete(id);

        return modelAndView;
    }
}
