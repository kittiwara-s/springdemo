package com.example.springdemo.service;

import com.example.springdemo.model.UserModel;
import com.example.springdemo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;


    @Test
    void createData() {
        UserModel mockRequest = new UserModel();
        UserModel mockResponse = new UserModel();
        mockResponse.setId(1L);

        when(userRepository.save(any(UserModel.class))).thenReturn(mockResponse);
        UserModel result = userService.createData(mockRequest);
        Assertions.assertThat(result).isEqualTo(mockResponse);
        verify(userRepository,times(1)).save(any(UserModel.class));
    }

    @Test
    void getLogin() throws Exception {
        UserModel mockRequest = new UserModel();
        mockRequest.setEmail("test@example.com");
        mockRequest.setPassword("password123");

        UserModel mockResponse = new UserModel();
        mockResponse.setId(1L);
        mockResponse.setName("Test User");
        mockResponse.setEmail("test@example.com");
        mockResponse.setPassword("password123");

        when(userRepository.findByEmail(mockRequest.getEmail())).thenReturn(Optional.of(mockResponse));
        UserModel result = userService.getLogin(mockRequest);
        Assertions.assertThat(result).isNotNull().isEqualTo(mockResponse);
        verify(userRepository,times(1)).findByEmail(mockRequest.getEmail());
    }

    @Test
    void updateUsername() throws Exception  {

        UserModel mockRequest = new UserModel();
        mockRequest.setName("Test User");
        mockRequest.setEmail("test@example.com");

        UserModel mockResponse = new UserModel();
        mockResponse.setId(1L);
        mockResponse.setName("Test User");
        mockResponse.setEmail("test@example.com");
        mockResponse.setPassword("password123");

        when(userRepository.findById(mockRequest.getId())).thenReturn(Optional.of(mockResponse));
        UserModel result = userService.updateUsername(mockRequest.getId(), mockRequest);
        Assertions.assertThat(result).isNotNull().isEqualTo(mockResponse);
        verify(userRepository,times(1)).findById(mockRequest.getId());
    }
}