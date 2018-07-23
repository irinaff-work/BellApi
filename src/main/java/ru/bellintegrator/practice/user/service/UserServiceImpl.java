package ru.bellintegrator.practice.user.service;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.country.dao.CountryDao;
import ru.bellintegrator.practice.docType.dao.DocTypeDao;
import ru.bellintegrator.practice.docType.model.DocType;
import ru.bellintegrator.practice.document.dao.DocumentDao;
import ru.bellintegrator.practice.document.model.Document;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserViewFull;
import ru.bellintegrator.practice.user.view.UserView;

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

        Long officeId = userView.officeId;
        String firstName = userView.firstName;
        String lastName = userView.lastName;
        String middleName = userView.middleName;
        String position = userView.position;
        String docNumber = userView.docNumber;
        String citizenshipCode = userView.citizenshipCode;

        Set<User> filteredUsers = dao.loadByOfficeId(officeId, firstName, lastName, middleName,
                position, docNumber, citizenshipCode);
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

        //получить ссылку на тип документа по docName
        DocType docType;
        Long document;
        if (!Strings.isNullOrEmpty(userView.docName)) {
            try {
                docType = docTypeDao.findByDocName(userView.docName);
            } catch ( NoResultException e) {
                //запись в лог
            }
        }
        //получить ссылку на документ или добавить его по docNumber и docDate
        if (!Strings.isNullOrEmpty(userView.docNumber)) {
            try {
                document = documentDao.findDocument(docType, userView.docNumber, userView.docDate);
            } catch (NoResultException e) {
                //добавление
                Document document = new Document()
            }
        }

        //получить ссылку на страну citizenshipCode
        if (!Strings.isNullOrEmpty(userView.citizenshipCode)) {
            try {
                Long countryId = countryDao.findByСitizenshipCode(userView.citizenshipCode);
            } catch ( NoResultException e) {
                //запись в лог
            }
        }
        //проапдейтить пользователя

        for (User item: listUsers) {
            if (item.getId().equals(userView.id)) {

                if (!userView.firstName.isEmpty()) {
                    item.setFirstName(userView.firstName);
                }

                if (!userView.lastName.isEmpty()) {
                    item.setFirstName(userView.lastName);
                }
                if (!userView.middleName.isEmpty()) {
                    item.setFirstName(userView.middleName);
                }
                if (!userView.position.isEmpty()) {
                    item.setFirstName(userView.position);
                }
                if (!userView.phone.isEmpty()) {
                    item.setFirstName(userView.phone);
                }
                if (!userView.docName.isEmpty()) {
                    item.setFirstName(userView.docName);
                }
                if (!userView.docDate.isEmpty()) {
                    item.setFirstName(userView.docDate);
                }
                if (!userView.citizenshipCode.isEmpty()) {
                    item.setFirstName(userView.citizenshipCode);
                }

                item.setIdentified(userView.isIdentified);
            }
        }
    };

    /**
     * Добавить нового пользователя
     *
     * @param userView
     * @return OfficeSave
     */
    @Override
    public void save(UserViewFull userView) {
        //получить ссылку на страну citizenshipCode
        //получить ссылку на тип документа по docCode и docName
        //получить ссылку на документ или добавить его по docNumber и docDate
        //добавить пользователя со всеми ссылками

        User userSave = new User(userView.id, userView.officeId, userView.firstName, userView.lastName,
                userView.middleName, userView.phone, userView.position, userView.docCode, userView.docName,
                userView.docNumber, userView.docDate, userView.citizenshipCode, userView.isIdentified);

        listUsers.add(userSave);
    }
}
