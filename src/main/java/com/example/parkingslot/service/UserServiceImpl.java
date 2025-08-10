package com.example.parkingslot.service;

import com.example.parkingslot.dao.UserDAO;
import com.example.parkingslot.entity.SignUpRequest;
import com.example.parkingslot.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    UserDAO userDAO;
    @Override
    public User add(SignUpRequest request) throws Exception {
        User user = null;
        try {
            user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setUserToken(UUID.randomUUID().toString());
            user = userDAO.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User getUserToken(User user) throws Exception {
        return null;
    }

    @Override
    public User findByEmail(String email) throws Exception {
        return (User) userDAO.findByEmail(email)
                .orElseThrow(() -> new Exception("User not found"));
    }
}
