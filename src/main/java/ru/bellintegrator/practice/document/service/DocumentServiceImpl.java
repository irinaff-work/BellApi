package ru.bellintegrator.practice.document.service;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.document.dao.DocumentDao;
import ru.bellintegrator.practice.document.model.Document;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.OfficeViewFull;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService{

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DocumentDao dao;

    @Autowired
    public DocumentServiceImpl(DocumentDao dao) {
        this.dao = dao;
    }

        /**
     * Получить документ по Id
     *
     * @param id
     * @return {@Document}
     */
//    @Override
//    @Transactional(readOnly = true)
//    public Document loadById(Long id) {
//
//        Document document = dao.loadById(id);
//        return document;
//    };

    /**
     * Добавить новый офис
     *
     * @param document
     * @return
     */
    @Override
    @Transactional
    public void save (Document document) {
        dao.save(document);
    };
}
