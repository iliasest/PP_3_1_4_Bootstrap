package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

@Component
public class TableService implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TableService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {
        User admin = new User("admin",
                20, "admin");
        User user = new User("user",
                15, "user");
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        roleService.saveRole(userRole);
        roleService.saveRole(adminRole);

        admin.addRole(adminRole);
        user.addRole(userRole);

        userService.add(admin);
        userService.add(user);
    }
}
