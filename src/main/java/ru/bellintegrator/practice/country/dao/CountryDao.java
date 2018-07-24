package ru.bellintegrator.practice.country.dao;

import ru.bellintegrator.practice.country.model.Country;

//справочник, только получаем данные
public interface CountryDao {

    /**
     * получить ссылку на страну  по citizenshipCode
     *
     * @param citizenshipCode
     * @return {@Country}
     */
    public Country findByCode(String citizenshipCode);

}