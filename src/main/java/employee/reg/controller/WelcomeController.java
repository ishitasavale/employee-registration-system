package employee.reg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import employee.reg.model.User;
import employee.reg.service.UserService;

@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest req, Model model) {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        Object uname = s.getAttribute("userName");
        if (uname != null) model.addAttribute("userName", uname.toString());

        List<User> all = userService.findAll();
        model.addAttribute("users", all);

        return "welcome";
    }
}
