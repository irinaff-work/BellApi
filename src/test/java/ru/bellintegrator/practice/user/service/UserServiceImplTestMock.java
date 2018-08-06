package ru.bellintegrator.practice.user.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.mockito.Mockito;
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
import ru.bellintegrator.practice.user.view.UserViewUpdate;
import ru.bellintegrator.practice.validate.RequestValidationException;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class UserServiceImplTestMock {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private UserServiceImpl userService = null;
    private UserDao userDao = null;
    private OfficeDao officeDao = null;
    private User user = null;
    private Office office = null;

    public UserServiceImplTestMock(UserServiceImpl userService, UserDao userDao, OfficeDao officeDao, User user, Office office) {
        this.userService = userService;
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.user = user;
        this.office = office;
    }

    @Parameterized.Parameters
    public static List<Object[]> balanceRates() {
        UserDao userDao = Mockito.mock(UserDao.class);
        DocTypeDao docTypeDao = Mockito.mock(DocTypeDao.class);
        CountryDao countryDao= Mockito.mock(CountryDao.class);
        DocumentDao documentDao = Mockito.mock(DocumentDao.class);
        OfficeDao officeDao = Mockito.mock(OfficeDao.class);

        UserServiceImpl userService = new UserServiceImpl(userDao, docTypeDao, countryDao, documentDao, officeDao);
        User user = new User();
        Office office = new Office();

        return Arrays.asList(new Object[][] {
                {userService, userDao, officeDao, user, office}});
    }

    @Before
    public void before() {
        //log.info("Before");
        initMocks(this);
    }

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

        when(officeDao.loadById(anyLong())).thenReturn(office);

        view.setOfficeId("1");
        view.setFirstName("Иванов");
        view.setLastName("Иван");
        view.setMiddleName("Иванович");
        view.setPosition("должность");
        view.setDocCode("21");
        view.setCitizenshipCode("643");
        userService.validationUserList(view);
    }

    @Test(expected = RequestValidationException.class)
    public void validationUserListBad() {
        UserView view = new UserView();

        when(officeDao.loadById(anyLong())).thenReturn(office);

        view.setOfficeId("");
        view.setFirstName("Иванов");
        view.setLastName("Иван");
        view.setMiddleName("Иванович");
        view.setPosition("должность");
        view.setDocCode("21");
        view.setCitizenshipCode("643");
        userService.validationUserList(view);
    }

    @Test
    public void validationUserAddOk1() {
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
    }

    @Test
    public void validationUserAddOk2() {
        UserViewAdd view = new UserViewAdd();

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

    @Test(expected = RequestValidationException.class)
    public void validationUserAddBad1() {
        UserViewAdd view = new UserViewAdd();

        view.setFirstName("");
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
    }

    @Test(expected = RequestValidationException.class)
    public void validationUserAddBad2() {
        UserViewAdd view = new UserViewAdd();

        view.setFirstName("Иванов");
        view.setLastName("123");
        view.setMiddleName("");
        view.setPosition("должность");
        view.setPhone("");
        view.setDocCode("");
        view.setDocName("11");
        view.setDocNumber("");
        view.setDocDate("");
        view.setCitizenshipCode("");
        view.setIdentified("true");
        userService.validationUserAdd(view);
    }

    @Test
    public void validationUserUpdateOk() {
        UserViewUpdate view = new UserViewUpdate();

        when(userDao.loadById(anyLong())).thenReturn(user);

        view.setId("1");
        view.setFirstName("Иванов");
        view.setLastName("Иван");
        view.setMiddleName("Иванович");
        view.setPosition("должность");
        view.setCitizenshipCode("021");
        view.setPhone("");
        view.setDocName("паспорт гражданина РФ");
        view.setDocNumber("");
        view.setDocDate("");
        view.setCitizenshipCode("");
        view.setIdentified("true");
        userService.validationUserUpdate(view);
    }

    @Test(expected = RequestValidationException.class)
    public void validationUserUpdateBad() {
        UserViewUpdate view = new UserViewUpdate();

        when(userDao.loadById(anyLong())).thenReturn(user);

        view.setId("1");
        view.setFirstName("");
        view.setLastName("Иван");
        view.setMiddleName("Иванович");
        view.setPosition("должность");
        view.setCitizenshipCode("021");
        view.setPhone("");
        view.setDocName("паспорт гражданина РФ");
        view.setDocNumber("");
        view.setDocDate("");
        view.setCitizenshipCode("");
        view.setIdentified("true");
        userService.validationUserUpdate(view);
    }

}