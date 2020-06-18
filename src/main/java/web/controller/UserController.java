package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class UserController {
    private UserService userService;
    private RoleService roleService;
    private Role role;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("users", userService.listUsers());
        modelAndView.addObject("roles", roleService.getRoles());
        modelAndView.setViewName("admin_info");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView getUserPage(ModelAndView mv, Principal principal) {
        User user = userService.findUserByName(principal.getName());
        mv.addObject("user", user);
        mv.setViewName("user_info");
        return mv;
    }

    @PostMapping("/admin/addUser")
    public ModelAndView addUserPost(@ModelAttribute("user") User user, @RequestParam Long roleId) {
        Set<Role> role = Collections.singleton(roleService.getRoleById(roleId));
        user.setRoles(role);
        userService.add(user);
        return new ModelAndView("redirect:/admin");
    }

	@GetMapping("/admin/editUser")
	public ModelAndView editGet(@RequestParam Long id, ModelAndView modelAndView) {
		modelAndView.setViewName("editUser");
		User user = userService.getUserFromId(id);
		modelAndView.addObject("user", user);
		List<Role> roles = roleService.getRoles();
		modelAndView.addObject("roles", roles);
		return modelAndView;
	}

    @PostMapping(value = "/admin/editUser")
    public ModelAndView editPost(@ModelAttribute("admin/user") User user, @RequestParam Long roleId) {
        Set<Role> roleSet = Collections.singleton(roleService.getRoleById(roleId));
        System.err.println(roleSet);
        user.setRoles(roleSet);
        userService.edit(user);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/admin/delete")
        public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}