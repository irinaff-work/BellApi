package ru.bellintegrator.practice.dictionary.model;

import javax.persistence.*;

/**
 * Типы документов
 */
@Entity
@Table(name = "Document_type")
public class DocType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Код типа документа
     */
    @Column(name = "doc_type_code", length = 3, nullable = false)
    private String docCode;

    /**
     * Наименование типа документа
     */
    @Column(name = "doc_type_name", length = 100, nullable = true)
    private String docName;

    /**
     * Конструктор для hibernate
     */
    public DocType() {

    }

    public DocType(String docCode, String docName) {
        this.docCode = docCode;
        this.docName = docName;
    }

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getDocCode() {
        return docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}
