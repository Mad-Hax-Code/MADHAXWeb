package com.madhax.website.service;

import com.madhax.website.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by James Cathcart on 2/18/2019
 */
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    UserService userService;

    @Test
    public void getAllTest() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        users.add(user1);
        users.add(user2);

        when(userService.getAll()).thenReturn(users);
        List<User> returnedUsers = userService.getAll();

        assertEquals(2, returnedUsers.size());
        verify(userService, times(1)).getAll();
    }

    @Test
    public void getById() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");

        when(userService.getById(anyLong())).thenReturn(user);

        User returnedUser = userService.getById(1L);

        assertEquals("John", returnedUser.getFirstName());
        verify(userService, times(1)).getById(anyLong());
    }

    @Test
    public void save() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");

        when(userService.save(any())).thenReturn(user);

        User savedUser = userService.save(user);

        assertEquals("John", savedUser.getFirstName());
        verify(userService, times(1)).save(any());
    }

    @Test
    public void delete() {
        User user = new User();
        user.setId(1L);

        userService.delete(user);
        verify(userService, times(1)).delete(any());
    }

    @Test
    public void deleteById() {
        User user = new User();
        user.setId(1L);

        userService.deleteById(1L);
        verify(userService, times(1)).deleteById(anyLong());
    }
}