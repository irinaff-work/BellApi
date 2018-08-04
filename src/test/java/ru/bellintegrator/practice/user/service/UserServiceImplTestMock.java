package ru.bellintegrator.practice.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.dictionary.dao.CountryDao;
import ru.bellintegrator.practice.dictionary.dao.DocTypeDao;
import ru.bellintegrator.practice.document.dao.DocumentDao;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.validate.RequestValidationException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTestMock {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Mock
    UserDao userDao;
    @Mock
    DocTypeDao docTypeDao;
    @Mock
    CountryDao countryDao;
    @Mock
    DocumentDao documentDao;
    @Mock
    OfficeDao officeDao;

    @Test
    public void validationDocumentDate() {

        UserServiceImpl userService = new UserServiceImpl(userDao, docTypeDao, countryDao, documentDao, officeDao);
        String DocDate1 = "2018-01-23";
        String DocDate2 = "2018.01.23";
        String DocDate3 = "2018/01/23";
        String DocDate4 = "20180123";
        String DocDate5 = "23/01/2018";
        String DocDate6 = "23.01.2018";
        String DocDate7 = "23-01-2018";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date vDate1 = new Date();
        Date vDate2 = new Date();
        try {
            vDate1 = format.parse(DocDate1);
        } catch (ParseException e) {
            Assert.fail("Неверный шаблон даты");
        }

        Assert.assertEquals(vDate1, userService.validationDocumentDate(DocDate1));

        try {
            vDate2 = userService.validationDocumentDate(DocDate2);
        } catch (RequestValidationException e) {
            vDate2 = vDate1;
        }
        Assert.assertEquals(vDate1, vDate2);
    }
}