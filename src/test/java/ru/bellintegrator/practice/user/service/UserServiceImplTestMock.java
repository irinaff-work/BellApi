package ru.bellintegrator.practice.user.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.bellintegrator.practice.dictionary.dao.CountryDao;
import ru.bellintegrator.practice.dictionary.dao.DocTypeDao;
import ru.bellintegrator.practice.document.dao.DocumentDao;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.dao.OfficeDaoImpl;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.user.view.UserViewAdd;
import ru.bellintegrator.practice.validate.RequestValidationException;

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
    UserServiceImpl userService = new UserServiceImpl(userDao, docTypeDao, countryDao, documentDao, officeDao);


//    @Before
//    public void before() {
//        log.info("Before");
//    }

    @Test
    public void validationDocumentDateOk() {
        String DocDate1 = "2018-01-23";
        Assert.assertNotNull(userService.validationDocumentDate(DocDate1));
    }

    @Test(expected = RequestValidationException.class)
    public void validationDocumentDateBad() {
        String DocDate1 = "2018.01.23";
        userService.validationDocumentDate(DocDate1);
    }

    @Test
    public void checkNameОк() {
        String value = "Иванов";
        Assert.assertTrue(userService.checkName(true, value));
    }

    @Test(expected = RequestValidationException.class)
    public void checkNameBad() {
        String value = "Иванов22";
        userService.checkName(true, value);
    }

    @Test
    public void checkPhoneОк() {
        String value = "81111111111";
        Assert.assertTrue(userService.checkPhone(true, value));
    }

    @Test(expected = RequestValidationException.class)
    public void checkPhoneBad() {
        String value = "8-917-453-23-21";
        userService.checkPhone(true, value);
    }

    @Test
    public void checkDocCodeОк() {
        String value = "21";
        Assert.assertTrue(userService.checkDocCode(true, value));
    }

    @Test(expected = RequestValidationException.class)
    public void checkDocCodeBad() {
        String value = "паспорт";
        userService.checkDocNumber(true, value);
    }

    @Test
    public void checkDocNumberОк() {
        String value = "6453812345";
        Assert.assertTrue(userService.checkDocNumber(true, value));
    }

    @Test(expected = RequestValidationException.class)
    public void checkDocNumberBad() {
        String value = "паспорт";
        userService.checkDocCode(true, value);
    }

    @Test
    public void checkСitizenshipCodeОк() {
        String value = "112";
        Assert.assertTrue(userService.checkСitizenshipCode(true, value));
    }

    @Test(expected = RequestValidationException.class)
    public void checkСitizenshipCodeBad() {
        String value = "02";
        userService.checkСitizenshipCode(true, value);
    }

    @Test
    public void checkIsIdentifiedОк() {
        String value = "true";
        Assert.assertTrue(userService.checkIsIdentified(value));
    }

    @Test(expected = RequestValidationException.class)
    public void checkIsIdentifiedeBad() {
        String value = "falsess";
        userService.checkIsIdentified(value);
    }

    @Test
    public void checkIdОк() {
        String value = "1";
        Assert.assertTrue(userService.checkId(value));
    }

    @Test(expected = RequestValidationException.class)
    public void checkIdBad() {
        String value = "-1";
        userService.checkId(value);
    }

    @Test
    public void validationUserListOk() {
        UserView view = new UserView();
        //OfficeDao officeDao = Mockito.mock(OfficeDaoImpl.class);
        Office office = new Office();

        when(officeDao.loadById(anyLong())).thenReturn(office);

        view.setOfficeId("1");
        view.setFirstName("Иванов");
        view.setLastName("Иван");
        view.setMiddleName("Иванович");
        view.setPosition("должность");
        view.setDocCode("1234567890");
        view.setCitizenshipCode("021");
        //userService.validationUserList(view);
    }

    @Test
    public void validationUserAddOk() {
        UserViewAdd view = new UserViewAdd();

        view.setFirstName("Иванов");
        view.setLastName("Иван");
        view.setMiddleName("Иванович");
        view.setPosition("должность");
        view.setPhone("89177588888");
        view.setDocCode("21");
        view.setDocName("паспорт гражданина РФ");
        view.setDocNumber("1234567890");
        view.setDocDate("2013-03-21");
        view.setCitizenshipCode("643");
        view.setIdentified("true");
        userService.validationUserAdd(view);

        view.setFirstName("Иванов");
        view.setLastName("");
        view.setMiddleName("");
        view.setPosition("должность");
        view.setPhone("");
        view.setDocCode("");
        view.setDocName("");
        view.setDocNumber("");
        view.setDocDate("");
        view.setCitizenshipCode("");
        view.setIdentified("true");
        userService.validationUserAdd(view);
    }
}