package ru.bellintegrator.practice.document.service;

import ru.bellintegrator.practice.document.model.Document;

public interface DocumentService {

        /**
     * Получить документ по Id
     *
     * @param id
     * @return {@Document}
     */
    //public Document loadById(Long id);

    /**
     * Добавить новый документ
     *
     * @param document
     * @return Document
     */
    public void save(Document document);
}
