package employee.reg.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import employee.reg.model.User;
import employee.reg.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public String doRegister(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String dobStr = request.getParameter("dob"); // yyyy-MM-dd
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        if (name == null || name.trim().isEmpty()
                || dobStr == null || dobStr.trim().isEmpty()
                || loginId == null || loginId.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Please fill required fields (Name, DOB, Login ID, Password).");
            return "registration";
        }

        if (userService.findByLoginId(loginId) != null) {
            model.addAttribute("error", "Login ID already exists. Choose another.");
            return "registration";
        }

        Date dob = null;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            dob = fmt.parse(dobStr);
        } catch (ParseException e) {
            model.addAttribute("error", "Invalid date format for DOB.");
            return "registration";
        }

        User u = new User();
        u.setName(name);
        u.setDob(dob);
        u.setGender(gender);
        u.setAddress(address);
        u.setCity(city);
        u.setState(state);
        u.setLoginId(loginId);
        u.setPassword(password); 

        userService.save(u);

        model.addAttribute("success", "Registration successful. You can now login.");
        return "registration";
    }
}
