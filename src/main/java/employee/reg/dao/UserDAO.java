package employee.reg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import employee.reg.model.User;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User u) {
        String sql = "INSERT INTO users(name, dob, gender, address, city, state, loginId, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.Date sqlDob = null;
        if (u.getDob() != null) {
            sqlDob = new java.sql.Date(u.getDob().getTime());
        }
        jdbcTemplate.update(sql,
                u.getName(),
                sqlDob,
                u.getGender(),
                u.getAddress(),
                u.getCity(),
                u.getState(),
                u.getLoginId(),
                u.getPassword()
        );
    }

    public User findByLoginId(String loginId) {
        String sql = "SELECT * FROM users WHERE loginId = ?";
        List<User> list = jdbcTemplate.query(sql, new UserRowMapper(), loginId);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));

            java.sql.Date sqlDob = rs.getDate("dob");
            if (sqlDob != null) {
                u.setDob(new java.util.Date(sqlDob.getTime()));
            } else {
                u.setDob(null);
            }

            u.setGender(rs.getString("gender"));
            u.setAddress(rs.getString("address"));
            u.setCity(rs.getString("city"));
            u.setState(rs.getString("state"));
            u.setLoginId(rs.getString("loginId"));
            u.setPassword(rs.getString("password"));
            return u;
        }
    }
}
