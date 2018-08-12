package ru.bellintegrator.practice.document.dao;

import ru.bellintegrator.practice.dictionary.model.DocType;
import ru.bellintegrator.practice.document.model.Document;

/**
 * DAO для работы с документами
 */
public interface DocumentDao {

    /**
     * Получить ссылку на документ по docType, docNumber и docDate
     *
     * @param docType
     * @param docNumber
     * @param docDate
     * @return
     */
    public Document findDocument(DocType docType, String docNumber, String docDate);

    /**
     * Добавить документ по docNumber и docDate
     *
     * @param document
     */
    public void save(Document document);

    /*
     * Удалить документ по Id
     * * @param id
     */
    public void deleteById(Long id);
}
