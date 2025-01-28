package webservice.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webservice.api.model.User;
import webservice.api.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user-service")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users")
    public String readUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/users/users";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam(name = "id", required = false) Long id) {
        userService.deleteUser(id);
        return "redirect:/user-service/users";
    }

    @GetMapping(value = "/new")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "/users/new-user";
    }

    @PostMapping(value = "/new")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/new-user";
        }
        userService.addUser(user);
        return "redirect:/user-service/users";
    }

    @GetMapping(value = "/update")
    public String editUser(@RequestParam(name = "id", required = false) Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/users/update-user";
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/update-user";
        }
        userService.updateUser(user);
        return "redirect:/user-service/users";
    }
}
