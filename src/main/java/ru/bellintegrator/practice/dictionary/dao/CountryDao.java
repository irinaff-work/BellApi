package ru.bellintegrator.practice.dictionary.dao;

import ru.bellintegrator.practice.dictionary.model.Country;

/**
 * DAO для работы со справочником стран
 */
public interface CountryDao {

    /**
     * Получить страну по коду
     *
     * @param code
     * @return
     */
    public Country findByCode(String code);

}
