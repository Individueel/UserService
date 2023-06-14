package com.hoef.mike.userservice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.hoef.mike.userservice.Controllers.UserController;
import com.hoef.mike.userservice.Entities.User;
import com.hoef.mike.userservice.Services.UserService;

@SpringBootTest
class UserServiceApplicationTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetUserById_ExistingUser_ReturnsUser() throws Exception {
        // Arrange
        String userId = "108992502999793757522";
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setName("Mike");
        when(userService.getUserById(userId)).thenReturn(mockUser);

        // Act
        User result = userController.getUserById(userId);
        System.out.println(result.getId() + " " + result.getName());

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Mike", result.getName());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testGetUserById_NonExistingUser_ThrowsException() throws Exception {
        // Arrange
        String userId = "123";
        when(userService.getUserById(userId)).thenReturn(null);

        // Act and Assert
        assertThrows(ResponseStatusException.class, () -> userController.getUserById(userId));
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testCreateUser_ValidUser_ReturnsCreatedUser() {
        // Arrange
        User user = new User();
        user.setId("123");
        user.setName("Mike");
        when(userService.createUser(user)).thenReturn(user);

        // Act
        User createdUser = userController.createUser(user);

        // Assert
        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(user.getName(), createdUser.getName());
        verify(userService, times(1)).createUser(user);
    }
}
