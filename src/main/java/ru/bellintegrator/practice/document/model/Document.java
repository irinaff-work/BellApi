package ru.bellintegrator.practice.document.model;

import ru.bellintegrator.practice.docType.model.DocType;

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

    @OneToMany(
            mappedBy="document",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    /**
     * Ссылка на тип документа
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id")
    private DocType docType;

    /**
     * Номер документа
     */
    @Column(name = "doc_number", length = 10, nullable = false)
    private String docNumber;
    /**
     * Дата документа
     */
    @Column(name = "doc_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private String docDate;

    /**
     * Конструктор для hibernate
     */
    public Document() {

    }

    public Document(DocType docType, String docNumber, String docDate) {
        this.docType = docType;
        this.docDate = docNumber;
        this.docDate = docDate;
    }

    public Long getId() {
        return id;
    }

    public DocType getDocType() {
        return this.docType;
    }

    public Long getDocTypeId() {
        return this.docType.getId();
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

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }
}
