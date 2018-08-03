package ru.bellintegrator.practice.user.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.user.model.User;

import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {Application.class})
//@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class UserDaoImplTestMock {

    @Mock
    Application applicationMock;

    //@Mock
    //private   UserDao userDao;

    @Test
    public void all() {
        UserDao userDao = mock(UserDao.class);
        User user = new User();
        //when( userDao.save(user)).thenReturn();
        userDao.save(user);
        verify(userDao).save(user);
        Set<User> users = userDao.all();
        verify(userDao).all();
        //Assert.assertEquals(users.size(), 0);
    }
}