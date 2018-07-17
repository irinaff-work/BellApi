package ru.bellintegrator.practice.document.model;

import javax.persistence.*;

/**
 * Офис
 */
@Entity
@Table(name = "Document")
public class Document {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Ссылка на тип документа
     */
    @Column(name = "doc_type_id", nullable = false)
    private Long docTypeId;
    /**
     * Номер документа
     */
    @Column(name = "doc_number", length = 10, nullable = false)
    private String docNumber;
    /**
     * Дата документа
     */
    @Column(name = "doc_date", nullable = false)
    private String docDate;

    /**
     * Конструктор для hibernate
     */
    public Document() {

    }

    public Document(Long id, Long docTypeId, String docNumber, String docDate) {
        this.id = id;
        this.docTypeId = docTypeId;
        this.docDate = docDate;
    }

    public Long getId() {
        return id;
    }

    public Long getDocTypeId() {
        return docTypeId;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDocTypeId(Long docTypeId) {
        this.docTypeId = docTypeId;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }
}
