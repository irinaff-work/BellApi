package ru.bellintegrator.practice.docType.dao;

import ru.bellintegrator.practice.docType.model.DocType;

public interface DocTypeDao {

    /**
     * получить ссылку на тип документа по docName
     *
     * @param docName
     * @return {@Set<User>}
     */
    public DocType findByDocName(String docName);
}
