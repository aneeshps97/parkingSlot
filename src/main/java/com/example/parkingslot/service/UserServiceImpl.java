package com.example.parkingslot.service;

import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.entity.Login;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import com.example.parkingslot.repository.UserRepository;
import com.example.parkingslot.entity.SignUp;
import com.example.parkingslot.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    @Override
    public User add(SignUp request) throws ParkingSlotException {
        User user = null;
        try {
            user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setUserToken(UUID.randomUUID().toString());
            user = userRepository.save(user);
        }catch (DataIntegrityViolationException e) {
            // Check if it’s a Hibernate constraint violation
            if (e.getCause() instanceof ConstraintViolationException cve) {
                // Check SQL error code or constraint name
                if (cve.getSQLException().getErrorCode() == 1062) { // MySQL duplicate entry
                    throw new ParkingSlotException(StatusCodes.EMAIL_ALREADY_EXISTS);
                }
            }
            // Any other DB issue
            throw new ParkingSlotException(StatusCodes.SIGNUP_FAILED);
        }catch (Exception e) {
            throw new ParkingSlotException(StatusCodes.SIGNUP_FAILED);
        }
        return user;
    }

    @Override
    public User getUserToken(User user) throws Exception {
        return null;
    }

    @Override
    public User findByEmail(String email) throws ParkingSlotException {
        return (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new ParkingSlotException(StatusCodes.USER_NOT_FOUND));
    }

    @Override
    public User authenticateUser(Login request) throws ParkingSlotException {
        User user = (User) userRepository.findByEmail(request.getEmail().trim())
                .orElseThrow(() -> new ParkingSlotException(StatusCodes.USER_NOT_FOUND));
       if (user.getPassword().equalsIgnoreCase(request.getPassword()))
            return user;
        else throw new ParkingSlotException(StatusCodes.WRONG_PASSWORD);
    }

    @Override
    public User findById(int userId) throws ParkingSlotException {
        return userRepository.findById(userId).orElseThrow(()->new ParkingSlotException(StatusCodes.USER_NOT_FOUND));
    }
}
