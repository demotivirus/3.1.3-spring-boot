package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("admin")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "user", new User());
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("roles", roleService.getAll());
        modelAndView.setViewName("admin_panel");
        return modelAndView;
    }

    @GetMapping("user")
    public ModelAndView userPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "user", new User());
        modelAndView.setViewName("user_panel");
        return modelAndView;
    }

    @GetMapping("admin/allUsers")
    public String getAll(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("admin/getUser")
    @ResponseBody
    public Optional<User> getUser(Long id)    {
        return userService.getOne(id);
    }

    @PostMapping(value="admin/addNew")
    public String addNew(User user, @RequestParam Long roleId) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add( roleService.getOne(roleId).get());
        user.setRoles(roleSet);
        userService.addNew(user);
        return "redirect:admin/allUsers";
    }

    @GetMapping("admin/editUser")
    @PostMapping("admin/editUser")
    @PutMapping("admin/editUser")
    public ModelAndView editUser(ModelAndView mv, User user, @RequestParam Long roleId) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.getOne(roleId).get());
        user.setRoles(roleSet);
        userService.update(user);
        mv.addObject("user", user);
        return mv;
    }

    @GetMapping("admin/delete")
    @DeleteMapping("admin/delete")
    public String delete(User user, @RequestParam Long id) {
        userService.delete(id);
        return "redirect:admin";
    }

}
