package com.example.springdemo.service;

import com.example.springdemo.model.UserModel;
import com.example.springdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserModel createData(UserModel request) {
        UserModel userResponse = new UserModel();
        userResponse.setName(request.getName());
        userResponse.setEmail(request.getEmail());
        userResponse.setPassword(request.getPassword());
        userRepository.save(userResponse);
        return userResponse;
    }


    public UserModel getLogin(UserModel request)  throws BadRequestException  {
        Optional<UserModel> optionalUserModel = userRepository.findByEmail(request.getEmail());

        if (optionalUserModel.isPresent()) {
            if (optionalUserModel.get().getPassword().equals(request.getPassword()) &&
                    optionalUserModel.get().getEmail().equals(request.getEmail())) {
                UserModel userResponse = new UserModel();
                userResponse.setId(optionalUserModel.get().getId());
                userResponse.setName(optionalUserModel.get().getName());
                userResponse.setEmail(optionalUserModel.get().getEmail());
                userResponse.setPassword(optionalUserModel.get().getPassword());
                return userResponse;
            } else  {
                throw new BadRequestException();
            }
        } else {
            throw new BadRequestException();
        }
    }


    public UserModel updateUsername(Long id, UserModel request) throws BadRequestException {
        Optional<UserModel> optionalUserModel = userRepository.findById(id);
        if (optionalUserModel.isPresent()) {
            UserModel userResponse = new UserModel();
            userResponse.setId(optionalUserModel.get().getId());
            userResponse.setName(request.getName()); // set new name
            userResponse.setEmail(optionalUserModel.get().getEmail());
            userResponse.setPassword(optionalUserModel.get().getPassword());
            userRepository.save(userResponse);
            return userResponse;
        } else {
            throw new BadRequestException();
        }
    }

}
