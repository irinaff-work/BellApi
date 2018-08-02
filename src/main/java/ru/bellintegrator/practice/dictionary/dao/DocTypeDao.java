package ru.bellintegrator.practice.dictionary.dao;

import ru.bellintegrator.practice.dictionary.model.DocType;

//справочник, только получаем данные
public interface DocTypeDao {

    /**
     * получить тип документа по docName
     *
     * @param docName
     * @return {@Set<User>}
     */
    public DocType findByDocName(String docName);

    /**
     * получить тип документа по docCode и docName
     *
     * @param docName
     * @return {@Set<User>}
     */
    public DocType findByDocCode (String docCode, String docName);
}
