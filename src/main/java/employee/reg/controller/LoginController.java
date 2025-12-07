package employee.reg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import employee.reg.model.User;
import employee.reg.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping({"/login","/"})
    public String showLoginForm(HttpServletRequest request, Model model) {
        HttpSession s = request.getSession(false);
        if (s != null) {
            s.removeAttribute("error");
            s.removeAttribute("success");
        }
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(HttpServletRequest request, Model model) {
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        if (loginId == null || loginId.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Enter credentials.");
            return "login";
        }

        User u = userService.findByLoginId(loginId);
        if (u == null || !u.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid login ID or password.");
            return "login";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("userId", u.getId());
        session.setAttribute("userName", u.getName());

        return "redirect:/welcome";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession s = request.getSession(false);
        if (s != null) s.invalidate();
        return "redirect:/login";
    }
}
