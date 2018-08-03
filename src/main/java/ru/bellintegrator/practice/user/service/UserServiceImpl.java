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

            view.setId(p.getId());
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
            view.setIdentified(p.isIdentified());
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

        Office office = officeDao.loadById(userView.getOfficeId());

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

            view.setId(p.getId());
            view.setOfficeId(p.getOfficeId());
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
     * @return {@Set<OfficeViewAll>}
     */
    @Override
    @Transactional(readOnly = true)
    public UserViewUpdate loadById(Long id) {

        User user = dao.loadById(id);

        UserViewUpdate view = new UserViewUpdate();

        view.setId(user.getId());
        view.setFirstName(user.getFirstName());
        view.setLastName(user.getLastName());
        view.setMiddleName(user.getMiddleName());
        view.setPosition(user.getPosition());
        view.setPhone(user.getPhone());
        view.setDocName(user.getDocName());
        view.setDocNumber(user.getDocNumber());
//
//        if (Strings.isNullOrEmpty(user.getDocNumber())) {
//            view.setDocCode("");
//        } else {
//            view.setDocCode(user.getDocDate().toString());
//        }
        view.setCitizenshipCode(user.getCitizenshipCode());
        view.setCitizenshipName(user.getCitizenshipName());
        view.setIdentified(user.isIdentified());
        return view;
    }

    /**
     * Изменить данные пользователя
     *
     * @param view
     */
    @Override
    @Transactional
    public void update(UserViewUpdate view) {

        validationUserUpdate(view);
        User user = dao.loadById(view.getId());

        //обновляем информацию о пользователе
        user.setFirstName(view.getFirstName());
        user.setLastName(view.getLastName());
        user.setMiddleName(view.getMiddleName());
        user.setPosition(view.getPosition());
        user.setPhone(view.getPhone());

        updateUserCountry(user, view);
        updateUserDocument(user,view);
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

    private void updateUserCountry(User user, UserViewUpdate view) {
        if (!Strings.isNullOrEmpty(view.getCitizenshipCode())) {
            try {
                Country country = countryDao.findByCode(view.getCitizenshipCode());
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
        //validationUserAdd(view);
        User user = new User(view.getFirstName(), view.getLastName(), view.getMiddleName(), view.getPhone(),
                view.getPosition(), view.isIdentified());
        //updateUserCountry(user,view);
        //addUserDocument(user,view);
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

    public void validationUserUpdate(UserViewUpdate view) {
        //validationUserAdd(view);
        //проверим, есть ли пользователь
        try {
            log.debug("view.id=" + view.getId());
            User user = dao.loadById(view.getId());
        } catch (NoResultException e) {
            throw new RequestValidationException("Нет пользователя с таким идентификатором");
        }
    }

    public void validationUserAdd(UserViewUpdate view) {
        if (view.getId() == 0 || view.getFirstName().isEmpty() ||
                view.getPosition().isEmpty() || !view.isIdentified()) {
            throw new RequestValidationException("Не заполнены обязательные для заполнения поля");
        }
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

    /**
     * Удалить офис по ID
     * @param id
     * @return
     */
    @Override
    @Transactional
    public void delete (Long id) {
        User user = dao.loadById(id);
        Document document = user.getDocument();
        dao.deleteById(id);
        documentDao.deleteById(document.getId());
    }
}
