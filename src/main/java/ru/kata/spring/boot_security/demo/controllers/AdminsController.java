package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {
    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ModelAndView getUsers(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("usersList", userService.getUsers());
        modelAndView.addObject("user", user);
        modelAndView.addObject("allRoles", roleService.getAllRoles());

        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView addUser(@ModelAttribute("user") User user, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new_user");

        List<Role> roles = roleService.getAllRoles();
        modelAndView.addObject("allRoles", roles);
        User userItem = userService.findByUsername(principal.getName());
        modelAndView.addObject("usersList", userService.getUsers());
        modelAndView.addObject("user", userItem);


        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");

        userService.add(user);

        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView update(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");

        userService.add(user);

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
