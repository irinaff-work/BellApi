package ru.bellintegrator.practice.dictionary.dao;

import ru.bellintegrator.practice.dictionary.model.DocType;

/**
 * DAO для работы со справочником типов документов
 */
public interface DocTypeDao {

    /**
     * Получить тип документа по docName
     *
     * @param docName
     * @return
     */
    public DocType findByDocName(String docName);

    /**
     * Получить тип документа по docCode и docName
     *
     * @param docCode
     * @param docName
     * @return
     */
    public DocType findByDocCode (String docCode, String docName);
}
