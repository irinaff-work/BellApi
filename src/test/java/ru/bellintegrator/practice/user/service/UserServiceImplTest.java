package ru.bellintegrator.practice.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bellintegrator.practice.user.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void validationDocumentDate() {
        //User user = new User();
        String DocDate1 = "2018-01-23";
        String DocDate2 = "23/11/2018";
        String DocDate3 = "23.11.2018";
        String DocDate4 = "2018.18.23";
        String DocDate5 = "2018/18/23";
        String DocDate6 = "2018/18/23";
        String DocDate7 = "2018-01-23-12";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date vDate1 = new Date();
        try {
            vDate1 = format.parse(DocDate1);
            log.info("DocDate1="+DocDate1.toString());

        } catch (ParseException e) {
            Assert.fail("Неверный шаблон даты");
        }
        Assert.assertNull(userService.validationDocumentDate(DocDate1));
        //log.info("DocDate1 validate="+userService.validationDocumentDate(DocDate1).toString());
        //Assert.assertEquals(vDate1, vDate1);//userService.validationDocumentDate(DocDate1)
    }
}