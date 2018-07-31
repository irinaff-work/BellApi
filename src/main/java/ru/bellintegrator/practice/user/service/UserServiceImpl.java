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
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserViewFull;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.validate.RequestValidationException;

import javax.persistence.NoResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserDao dao;
    private final DocTypeDao docTypeDao;
    private final CountryDao countryDao;
    private final DocumentDao documentDao;

    @Autowired
    public UserServiceImpl(UserDao dao, DocTypeDao docTypeDao, CountryDao countryDao, DocumentDao documentDao) {
        this.dao = dao;
        this.docTypeDao = docTypeDao;
        this.countryDao = countryDao;
        this.documentDao = documentDao;
    }

    /**
     * Получить список всех пользователей
     *
     * @return {@Set<User>}
     */
    @Override
    @Transactional(readOnly = true)
    public Set<UserViewFull> all() {
        Set<User> users = dao.all();

        return users.stream()
                .map(mapUserFull())
                .collect(Collectors.toSet());
    }

    ;

    private Function<User, UserViewFull> mapUserFull() {
        return p -> {
            UserViewFull view = new UserViewFull();
            view.id = p.getId();
            view.officeId = p.getOfficeId();
            view.firstName = p.getFirstName();
            view.lastName = p.getLastName();
            view.middleName = p.getMiddleName();
            view.position = p.getPosition();
            view.phone = p.getPhone();
            view.docName = p.getDocName();
            view.docCode = p.getDocCode();
            view.docNumber = p.getDocNumber();
            view.docDate = p.getDocDate().toString();
            view.citizenshipCode = p.getCitizenshipCode();
            view.isIdentified = p.isIdentified();
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

        Set<User> filteredUsers = dao.loadByFilter(userView.officeId, userView.firstName,
                userView.lastName, userView.middleName, userView.position,
                userView.docNumber, userView.citizenshipCode);
        return filteredUsers.stream()
                .map(mapUserShort())
                .collect(Collectors.toSet());
    }

    private Function<User, UserView> mapUserShort() {
        return p -> {
            UserView view = new UserView();
            view.id = p.getId();
            view.officeId = p.getOfficeId();
            view.firstName = p.getFirstName();
            view.lastName = p.getLastName();
            view.middleName = p.getMiddleName();
            view.docNumber = p.getDocNumber();
            view.citizenshipCode = p.getCitizenshipCode();
            view.position = p.getPosition();
            view.isIdentified = p.isIdentified();
            return view;
        };
    }

    /**
     * Получить пользователя по Id
     *
     * @param id
     * @return {@Set<OfficeViewAll>}
     */
    @Override
    @Transactional(readOnly = true)
    public UserViewFull loadById(Long id) {

        User user = dao.loadById(id);

        UserViewFull view = new UserViewFull();
        view.id = user.getId();
        view.officeId = user.getOfficeId();
        view.firstName = user.getFirstName();
        view.lastName = user.getLastName();
        view.middleName = user.getMiddleName();
        view.phone = user.getPhone();
        view.position = user.getPosition();
        view.docName = user.getDocName();
        view.docNumber = user.getDocNumber();
        if (Strings.isNullOrEmpty(user.getDocNumber())) {
            view.docDate = "";
        } else {
            view.docDate = user.getDocDate().toString();
        }
        view.citizenshipCode = user.getCitizenshipCode();
        view.isIdentified = user.isIdentified();

        return view;
    }

    /**
     * Изменить данные пользователя
     *
     * @param view
     */
    @Override
    @Transactional
    public void update(UserViewFull view) {

        validationUserUpdate(view);
        User user = dao.loadById(view.id);

        //обновляем информацию о пользователе
        user.setFirstName(view.firstName);
        user.setLastName(view.lastName);
        user.setMiddleName(view.middleName);
        user.setPosition(view.position);
        user.setPhone(view.phone);

        updateUserCountry(user, view);
        updateUserDocument(user,view);
        user.setIdentified(true);
        dao.save(user);
    }

    private boolean updateUserDocument(User user, UserViewFull view) {
        if (!Strings.isNullOrEmpty(view.docCode) || !Strings.isNullOrEmpty(view.docName)) {
            DocType docType = validationDocType(view.docCode, view.docName);

            if (!Strings.isNullOrEmpty(view.docNumber) || !Strings.isNullOrEmpty(view.docDate)) {
                Document document = user.getDocument();
                if (document.getDocType() != docType) {
                    document.setDocType(docType);
                }

                if (!Strings.isNullOrEmpty(view.docNumber) && document.getDocNumber() != view.docNumber) {
                    document.setDocNumber(view.docNumber);
                }

                if (!Strings.isNullOrEmpty(view.docDate) && document.getDocDate() != validationDocumentDate(view.docDate)) {
                    document.setDocDate(validationDocumentDate(view.docDate));
                }

                documentDao.save(document);
                user.setDocument(document);
            }
        }
        return true;
    }

    private void updateUserCountry(User user, UserViewFull view) {
        if (!Strings.isNullOrEmpty(view.citizenshipCode)) {
            try {
                Country country = countryDao.findByCode(view.citizenshipCode);
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
    public void add(UserViewFull view) {
        //validationUserAdd(view);
        User user = new User(view.firstName, view.lastName, view.middleName, view.phone, view.position, view.isIdentified);
        updateUserCountry(user,view);
        addUserDocument(user,view);
        dao.save(user);
    }

    private void addUserDocument(User user, UserViewFull view) {
        if (!Strings.isNullOrEmpty(view.docCode) || !Strings.isNullOrEmpty(view.docName)) {
            DocType docType = validationDocType(view.docCode, view.docName);

            if (!Strings.isNullOrEmpty(view.docNumber) && !Strings.isNullOrEmpty(view.docDate)) {
                Document document = new Document(docType, view.docNumber, validationDocumentDate(view.docDate));
                documentDao.save(document);
                user.setDocument(document);
            }
        }
    }

    public void validationUserUpdate(UserViewFull view) {
        //validationUserAdd(view);
        //проверим, есть ли пользователь
        try {
            log.debug("view.id=" + view.id);
            User user = dao.loadById(view.id);
        } catch (NoResultException e) {
            throw new RequestValidationException("Нет пользователя с таким идентификатором");
        }
    }

    public void validationUserAdd(UserViewFull view) {
        if (view.id == 0 || view.firstName.isEmpty() ||
                view.position.isEmpty() || !view.isIdentified) {
            throw new RequestValidationException("Не заполнены обязательные для заполнения поля");
        }
    }

    public Date validationDocumentDate(String date) {
        log.debug("date=" + date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date docDate = new Date();
        try {
            docDate = format.parse(date);
        } catch (ParseException e) {
            throw new RequestValidationException("Не верный формат даты");
        }
        return docDate;
    }

    public DocType validationDocType(String docCode, String docName) {

        DocType docType = new DocType();
        if (!Strings.isNullOrEmpty(docCode) || !Strings.isNullOrEmpty(docName)) {
            try {
                docType = docTypeDao.find(docCode, docName);
            } catch (EmptyResultDataAccessException e) {
                throw new RequestValidationException("В справочнике нет такого типа документа");
            }
        }
        return docType;
    }
}
