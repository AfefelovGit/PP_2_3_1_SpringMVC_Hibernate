package webservice.api.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webservice.api.data.models.User;
import webservice.api.data.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user-service")
public class UserController {

    final UserService userService;

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

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam(name = "id", required = false) Long id) {
        userService.deleteUser(id);
        return "redirect:/user-service/users";
    }

    // переход на форму добавления нового пользователя
    @GetMapping(value = "/new")
    public String newUserForm(@ModelAttribute("user") User user) {
        return "/users/new-user";
    }

    // обработка формы добавления нового пользователя
    @PostMapping(value = "/new")
    public String newUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/new-user";
        }
        userService.addUser(user);
        return "redirect:/user-service/users";
    }

    // переход на форму изменения существующего пользователя
    @GetMapping(value = "/update")
    public String updateUserForm(@RequestParam(name = "id", required = false) Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/users/update-user";
    }

    // обработка формы изменения существующего пользователя
    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "/users/update-user";
        }
        user.setId(Long.parseLong(request.getParameter("id")));
        userService.updateUser(user);
        return "redirect:/user-service/users";
    }
}
