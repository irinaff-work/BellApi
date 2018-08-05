package ru.bellintegrator.practice.user.service;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dictionary.dao.CountryDao;
import ru.bellintegrator.practice.dictionary.model.Country;
import ru.bellintegrator.practice.dictionary.dao.DocTypeDao;
import ru.bellintegrator.practice.dictionary.model.DocType;
import ru.bellintegrator.practice.document.dao.DocumentDao;
import ru.bellintegrator.practice.document.model.Document;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserViewAdd;
import ru.bellintegrator.practice.user.view.UserViewUpdate;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.validate.RequestValidationException;

import javax.persistence.NoResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserDao dao;
    private final DocTypeDao docTypeDao;
    private final CountryDao countryDao;
    private final DocumentDao documentDao;
    private final OfficeDao officeDao;

    @Autowired
    public UserServiceImpl(UserDao dao, DocTypeDao docTypeDao, CountryDao countryDao, DocumentDao documentDao, OfficeDao officeDao) {
        this.dao = dao;
        this.docTypeDao = docTypeDao;
        this.countryDao = countryDao;
        this.documentDao = documentDao;
        this.officeDao = officeDao;
    }

    /**
     * Получить список всех пользователей
     *
     * @return {@Set<User>}
     */
    @Override
    @Transactional(readOnly = true)
    public Set<UserViewUpdate> all() {
        Set<User> users = dao.all();

        return users.stream()
                .map(mapUserFull())
                .collect(Collectors.toSet());
    }

    ;

    private Function<User, UserViewUpdate> mapUserFull() {
        return p -> {
            UserViewUpdate view = new UserViewUpdate();

            view.setId(p.getId().toString());
            view.setFirstName(p.getFirstName());
            view.setLastName(p.getLastName());
            view.setMiddleName(p.getMiddleName());
            view.setPosition(p.getPosition());
            view.setPhone(p.getPhone());
            view.setDocName(p.getDocName());
            view.setDocNumber(p.getDocNumber());
            view.setDocDate(p.getDocDate().toString());
            view.setCitizenshipCode(p.getCitizenshipCode());
            view.setCitizenshipName(p.getCitizenshipName());
            view.setIdentified(String.valueOf(p.isIdentified()));
            return view;
        };
    }

    /**
     * Получить список пользователей по фильтрам
     *
     * @param userView
     * @return {@Set<OfficeView>}
     */
    @Override
    @Transactional(readOnly = true)
    public Set<UserView> loadByFilter(UserView userView) {
        validationUserList(userView);
        Office office = officeDao.loadById(Long.valueOf(userView.getOfficeId()));

        DocType docType = null;
        if (!Strings.isNullOrEmpty(userView.getDocCode())) {
            docType = docTypeDao.findByDocCode(userView.getDocCode(), null);
        }

        Country country = null;
        if (!Strings.isNullOrEmpty(userView.getCitizenshipCode())) {
            log.debug("citizenshipCode=" + userView.getCitizenshipCode());
            country = countryDao.findByCode(userView.getCitizenshipCode());
        }

        Set<User> filteredUsers = dao.loadByFilter(office, country, docType, userView.getFirstName(),
                userView.getLastName(), userView.getMiddleName(), userView.getPosition());

        return filteredUsers.stream()
                .map(mapUserShort())
                .collect(Collectors.toSet());
    }

    private Function<User, UserView> mapUserShort() {
        return p -> {
            UserView view = new UserView();

            view.setId(p.getId().toString());
            view.setOfficeId(p.getOfficeId().toString());
            view.setFirstName(p.getFirstName());
            view.setLastName(p.getLastName());
            view.setMiddleName(p.getMiddleName());
            view.setPosition(p.getPosition());
            view.setDocCode(p.getDocCode());
            view.setCitizenshipCode(p.getCitizenshipCode());
            return view;
        };
    }

    /**
     * Получить пользователя по Id
     *
     * @param id
     * @return {@Set<UserViewUpdate>}
     */
    @Override
    @Transactional(readOnly = true)
    public UserViewUpdate loadById(Long id) {
        UserViewUpdate view = new UserViewUpdate();
        try {
            User user = dao.loadById(id);
            view.setId(user.getId().toString());
            view.setFirstName(user.getFirstName());
            view.setLastName(user.getLastName());
            view.setMiddleName(user.getMiddleName());
            view.setPosition(user.getPosition());
            view.setPhone(user.getPhone());
            view.setDocName(user.getDocName());
            view.setDocNumber(user.getDocNumber());
            view.setCitizenshipCode(user.getCitizenshipCode());
            view.setCitizenshipName(user.getCitizenshipName());
            view.setIdentified(String.valueOf(user.isIdentified()));
        } catch (Exception e) {
            throw new RequestValidationException("Нет пользователя с таким идентификатором");
        }
        return view;
    }

    /**
     * Изменить данные пользователя
     * @param view
     */
    @Override
    @Transactional
    public void update(UserViewUpdate view) {

        validationUserUpdate(view);
        User user = dao.loadById(Long.valueOf(view.getId()));

        user.setFirstName(view.getFirstName());
        user.setLastName(view.getLastName());
        user.setMiddleName(view.getMiddleName());
        user.setPosition(view.getPosition());
        user.setPhone(view.getPhone());

        updateUserCountry(user, view.getCitizenshipCode());
        updateUserDocument(user, view);
        user.setIdentified(true);
        dao.save(user);
    }

    private boolean updateUserDocument(User user, UserViewUpdate view) {
        if (!Strings.isNullOrEmpty(view.getDocName()) || !Strings.isNullOrEmpty(view.getDocName())) {
            DocType docType = validationDocType(null, view.getDocName());

            if (!Strings.isNullOrEmpty(view.getDocNumber()) || !Strings.isNullOrEmpty(view.getDocDate())) {
                Document document = user.getDocument();
                if (document.getDocType() != docType) {
                    document.setDocType(docType);
                }

                if (!Strings.isNullOrEmpty(view.getDocNumber())) {
                    document.setDocNumber(view.getDocNumber());
                }

                if (!Strings.isNullOrEmpty(view.getDocDate()) && document.getDocDate() != validationDocumentDate(view.getDocDate())) {
                    document.setDocDate(validationDocumentDate(view.getDocDate()));
                }

                documentDao.save(document);
                user.setDocument(document);
            }
        }
        return true;
    }

    private void updateUserCountry(User user, String citizenshipCode) {
        if (!Strings.isNullOrEmpty(citizenshipCode)) {
            try {
                Country country = countryDao.findByCode(citizenshipCode);
                user.setCountry(country);
            } catch (NoResultException e) {
                throw new RequestValidationException("Нет страны с таким кодом");
            }
        }
    }

    /**
     * Добавить нового пользователя
     *
     * @param view
     * @return OfficeSave
     */
    @Override
    @Transactional
    public void add(UserViewAdd view) {
        validationUserAdd(view);

        User user = new User(view.getFirstName(), view.getLastName(), view.getMiddleName(), view.getPhone(),
                view.getPosition(), Boolean.valueOf(view.isIdentified()));
        updateUserCountry(user, view.getCitizenshipCode());
        addUserDocument(user, view);
        dao.save(user);
    }

    private void addUserDocument(User user, UserViewAdd view) {
        if (!Strings.isNullOrEmpty(view.getDocCode()) || !Strings.isNullOrEmpty(view.getDocName())) {
            DocType docType = validationDocType(view.getDocCode(), view.getDocName());

            if (!Strings.isNullOrEmpty(view.getDocNumber()) && !Strings.isNullOrEmpty(view.getDocDate())) {
                Document document = new Document(docType, view.getDocNumber(), validationDocumentDate(view.getDocDate()));
                documentDao.save(document);
                user.setDocument(document);
            }
        }
    }

    /**
     * Удалить пользователя по ID
     * @param id
     * @return
     */
    @Override
    @Transactional
    public void delete(Long id) {
        try {
            User user = dao.loadById(id);
            Document document = user.getDocument();
            dao.deleteById(id);
            documentDao.deleteById(document.getId());
        } catch (Exception e) {
            throw new RequestValidationException("Нет пользователя с таким кодом");
        }

    }

    public void validationUserList(UserView view) {
        checkId(view.getOfficeId());
        //проверим, есть ли офис
        try {
            Office office = officeDao.loadById(Long.valueOf(view.getOfficeId()));
        } catch (NoResultException e) {
            throw new RequestValidationException("Нет офиса с таким идентификатором");
        }
        checkName(false, view.getFirstName());
        checkName(false, view.getLastName());
        checkName(false, view.getMiddleName());
        checkName(false, view.getPosition());
        checkDocCode(false, view.getDocCode());
        checkСitizenshipCode(false, view.getCitizenshipCode());
    }

    public void validationUserUpdate(UserViewUpdate view) {
        checkId(view.getId());
        //проверим, есть ли пользователь
        try {
            User user = dao.loadById(Long.valueOf(view.getId()));
        } catch (NoResultException e) {
            throw new RequestValidationException("Нет пользователя с таким идентификатором");
        }
        checkName(true, view.getFirstName());
        checkName(false, view.getLastName());
        checkName(false, view.getMiddleName());
        checkName(true, view.getPosition());
        checkPhone(false, view.getPhone());
        checkName(false, view.getDocName());
        checkDocNumber(false, view.getDocNumber());
        checkDocdate(false, view.getDocDate());
        checkСitizenshipCode(false, view.getCitizenshipCode());
        checkIsIdentified(view.isIdentified());
    }

    public void validationUserAdd(UserViewAdd view) {
        checkName(true, view.getFirstName());
        checkName(false, view.getLastName());
        checkName(false, view.getMiddleName());
        checkName(true, view.getPosition());
        checkPhone(false, view.getPhone());
        checkDocCode(false, view.getDocCode());
        checkName(false, view.getDocName());
        checkDocNumber(false, view.getDocNumber());
        checkDocdate(false, view.getDocDate());
        checkСitizenshipCode(false, view.getCitizenshipCode());
        checkIsIdentified(view.isIdentified());
    }

    public Date validationDocumentDate(String date) {
        log.debug("date=" + date);

        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new RequestValidationException("Не верный формат даты");
        }
    }

    public DocType validationDocType(String docCode, String docName) {

        DocType docType = new DocType();
        if (!Strings.isNullOrEmpty(docCode) || !Strings.isNullOrEmpty(docName)) {
            try {
                docType = docTypeDao.findByDocCode(docCode, docName);
            } catch (EmptyResultDataAccessException e) {
                throw new RequestValidationException("В справочнике нет такого типа документа");
            }
        }
        return docType;
    }

    public boolean checkId(String id) {
        Pattern pattern = Pattern.compile("[0-9]{1,10}");
        if (Strings.isNullOrEmpty(id) || !pattern.matcher(id).matches()) {
            throw new RequestValidationException("Поле <id> должно содержать от 1 до 10 цифр");
        }
        return true;
    }

    public boolean checkName(boolean isNotNull, String name) {
        if (isNotNull && Strings.isNullOrEmpty(name)) {
            throw new RequestValidationException("Текстовое поле должно быть заполнено");
        }
        if (!Strings.isNullOrEmpty(name)) {
            Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я ]{3,100}");
            if (!pattern.matcher(name).matches()) {
                throw new RequestValidationException("Текстовое должно содержать не меньше 3-х и не больше 100 буквенных символов");
            }
        }
        return true;
    }

    public boolean checkPhone(boolean isNotNull, String phone) {
        if (isNotNull && Strings.isNullOrEmpty(phone)) {
            throw new RequestValidationException("Поле <Pnone> должно быть заполнено");
        }
        Pattern pattern = Pattern.compile("[0-9]{11}");
        if (!pattern.matcher(phone).matches()) {
            throw new RequestValidationException("Поле <Pnone> должно быть введено в формате 8NNNNNNNNNN}, первый символ <8> и десять цифр без пробелов");
        }
        return true;
    }

    public boolean checkDocCode(boolean isNotNull, String docCode) {
        if (isNotNull && Strings.isNullOrEmpty(docCode)) {
            throw new RequestValidationException("Поле <docCode> должно быть заполнено");
        }
        Pattern pattern = Pattern.compile("[0-9]{2}");
        if (!pattern.matcher(docCode).matches()) {
            throw new RequestValidationException("Поле <docCode> должно содержать 2 цифровых символа");
        }
        return true;
    }

    public boolean checkDocNumber(boolean isNotNull, String docNumber) {
        if (isNotNull && Strings.isNullOrEmpty(docNumber)) {
            throw new RequestValidationException("Поле <DocNumber> должно быть заполнено");
        }
        Pattern pattern = Pattern.compile("[0-9]{10}");
        if (!pattern.matcher(docNumber).matches()) {
            throw new RequestValidationException("Поле <DocNumber> должно содержать 10 цифровых символов");
        }
        return true;
    }

    public boolean checkDocdate(boolean isNotNull, String docDate) {
        if (isNotNull && Strings.isNullOrEmpty(docDate)) {
            throw new RequestValidationException("Поле <docDate> должно быть заполнено");
        }
        if (!Strings.isNullOrEmpty(docDate)) {
            try {
                new SimpleDateFormat("yyyy-MM-dd").parse(docDate);
            } catch (ParseException e) {
                throw new RequestValidationException("Поле <docDate> должно быть введено в формате YYYY-MM-DD");
            }
        }
        return true;
    }

    public boolean checkСitizenshipCode(boolean isNotNull, String сitizenshipCode) {
        if (isNotNull && Strings.isNullOrEmpty(сitizenshipCode)) {
            throw new RequestValidationException("Поле <сitizenshipCode> должно быть заполнено");
        }
            Pattern pattern = Pattern.compile("[0-9]{3}");
            if (!Strings.isNullOrEmpty(сitizenshipCode) && !pattern.matcher(сitizenshipCode).matches()) {
                throw new RequestValidationException("Поле <сitizenshipCode> должно содержать 3 цифровых символа");
            }
        return true;
    }

    public boolean checkIsIdentified(String isIdentified) {
        Pattern pattern = Pattern.compile("true|false");
        if (Strings.isNullOrEmpty(isIdentified) || !pattern.matcher(isIdentified).matches()) {
            throw new RequestValidationException("Поле <isIdentified> должно содержать true или false");
        }
        return true;
    }
}
