package ru.bellintegrator.practice.user.service;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.country.dao.CountryDao;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docType.dao.DocTypeDao;
import ru.bellintegrator.practice.docType.model.DocType;
import ru.bellintegrator.practice.document.dao.DocumentDao;
import ru.bellintegrator.practice.document.model.Document;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserViewFull;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.validate.RequestValidationException;

import javax.persistence.NoResultException;
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
     * Получить список пользователей по Id офиса
     *
     * @param userView
     * @return {@Set<OfficeView>}
     */
    @Override
    public Set<UserView> loadByOfficeId(UserView userView) {

        Set<User> filteredUsers = dao.loadByOfficeId(userView.officeId, userView.firstName,
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
            view.position = p.getPosition();
            view.isIdentified = p.isIdentified();
            return view;
        };
    }

    /**
     * Получить список пользователей по Id пользователя
     *
     * @param id
     * @return {@Set<OfficeViewAll>}
     */
    @Override
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
        view.docDate = user.getDocDate();
        view.citizenshipCode = user.getCitizenshipCode();
        view.isIdentified = user.isIdentified();

        return view;
    }

    /**
     * Изменить данные пользователя
     *
     * @param userView
     */
    @Override
    public void update(UserViewFull userView) {

        validationUserUdate(userView);

        //обновляем информацию о пользователе
        dao.update(userCreate(userView));
    }

    private User userCreate (UserViewFull userView) {
        DocType docType;
        Document document = new Document();
        Country country = new Country();

        //получить ссылку на страну citizenshipCode
        if (!Strings.isNullOrEmpty(userView.citizenshipCode)) {
            try {
                country = countryDao.findByCode(userView.citizenshipCode);
            } catch (NoResultException e) {

                throw new RequestValidationException("Нет страны с таким кодом");
            }
        }

        //проверим, есть ли тип документа
        if (!Strings.isNullOrEmpty(userView.docName)) {
            try {
                docType = docTypeDao.findByDocName(userView.docName);

                //получить ссылку на документ или добавить его по docNumber и docDate
                if (!Strings.isNullOrEmpty(userView.docNumber)) {
                    try {
                        document = documentDao.findDocument(docType, userView.docNumber, userView.docDate);
                    } catch (NoResultException e) {
                        //добавление
                        document = new Document(docType, userView.docNumber, userView.docDate);
                        documentDao.add(document);
                    }
                }
            } catch (NoResultException e) {

                throw new RequestValidationException("Нет типа документа с таким наименованием");
            }
        }

        User user = new User(new Office(), country, document, userView.firstName, userView.lastName, userView.middleName,
                userView.position, userView.phone, true);

        return user;
    }

    /**
     * Добавить нового пользователя
     *
     * @param userView
     * @return OfficeSave
     */
    @Override
    public void add(UserViewFull userView) {
        validationUserAdd(userView);
        dao.save(userCreate(userView));
    }


    public void validationUserUdate(UserViewFull view) {
        validationUserAdd(view);
        //проверим, есть ли пользователь
        try {
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
}
