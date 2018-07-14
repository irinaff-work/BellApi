package ru.bellintegrator.practice.user.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeViewAll;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.user.view.UserViewShort;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private List<User> listUsers = new ArrayList(Arrays.asList(
            new User(1L, 1L,"Игорь", "Ермолкин", "Игоревич","333-33-33", "менеджер",
                    "10","1","11.2", "12.05.2018",
                    "1234", true),
            new User(2L, 2L,"Дмитрий", "Кулич", "Николаевич","333-33-33", "менеджер",
                    "10","1","11.2", "12.05.2018",
                    "1234", true)
    ));

    /**
     * Получить список пользователей по Id офиса
     *
     * @param userView
     * @return {@List<OfficeView>}
     */
    @Override
    public List<UserViewShort> filteredOfficeId(UserViewShort userView) {
        List<User> filteredUsers = new ArrayList<User>();

        for (User item: listUsers) {
            if ((item.getOfficeId().equals(userView.officeId))) {
                item.setIdentified(true);
                filteredUsers.add(item);
            }
        }
        return filteredUsers.stream()
                .map(mapUserShort())
                .collect(Collectors.toList());
    }

    private Function<User, UserViewShort> mapUserShort() {
        return p -> {
            UserViewShort view = new UserViewShort();
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
     * @return {@List<OfficeViewAll>}
     */
    @Override
    public List<UserView> filteredId(Long id) {
        List<User> filteredUsers = new ArrayList<User>();

        for (User item: listUsers) {
            if ((item.getId()).equals(id)) {
                item.setIdentified(true);
                filteredUsers.add(item);
            }
        }
        return filteredUsers.stream()
                .map(mapUser())
                .collect(Collectors.toList());
    };

    private Function<User, UserView> mapUser() {
        return p -> {
            UserView view = new UserView();
            view.id = p.getId();
            view.officeId = p.getOfficeId();
            view.firstName = p.getFirstName();
            view.lastName = p.getLastName();
            view.middleName = p.getMiddleName();
            view.phone = p.getPhone();
            view.position = p.getPosition();
            view.docCode = p.getDocCode();
            view.docName = p.getDocName();
            view.docNumber = p.getDocNumber();
            view.docDate = p.getDocDate();
            view.citizenshipCode = p.getCitizenshipCode();
            view.isIdentified = p.isIdentified();
            return view;
        };
    }

    /**
     * Изменить данные пользователя
     *
     * @param userView
     */
    @Override
    public void update(UserView userView) {
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
    public void createUser(UserView userView) {

        User userSave = new User(userView.id, userView.officeId, userView.firstName, userView.lastName,
                userView.middleName, userView.phone, userView.position, userView.docCode, userView.docName,
                userView.docNumber, userView.docDate, userView.citizenshipCode, userView.isIdentified);

        listUsers.add(userSave);
    }
}
