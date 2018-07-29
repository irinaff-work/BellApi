package ru.bellintegrator.practice.dictionary.dao;

import ru.bellintegrator.practice.dictionary.model.Country;

//справочник, только получаем данные
public interface CountryDao {

    /**
     * получить ссылку на страну  по citizenshipCode
     *
     * @param code
     * @return {@Country}
     */
    public Country findByCode(String code);

}
