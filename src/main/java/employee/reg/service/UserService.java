package employee.reg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import employee.reg.dao.UserDAO;
import employee.reg.model.User;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void save(User u) {
        userDAO.save(u);
    }

    @Transactional(readOnly = true)
    public User findByLoginId(String loginId) {
        return userDAO.findByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDAO.findAll();
    }
}
