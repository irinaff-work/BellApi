package ru.bellintegrator.practice.country.dao;

public interface CountryDao {

    /**
     * получить ссылку на страну  по citizenshipCode
     *
     * @param citizenshipCode
     * @return {@Long}
     */
    public Long findByСitizenshipCode(String citizenshipCode);

}
