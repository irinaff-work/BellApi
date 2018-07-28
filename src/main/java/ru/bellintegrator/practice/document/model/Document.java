package ru.bellintegrator.practice.document.model;

import ru.bellintegrator.practice.docType.model.DocType;

import javax.persistence.*;
import java.util.Date;

/**
 * Офис
 */
@Entity
@Table(name = "Document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

//    @OneToMany(
//            mappedBy="document",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )

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
    private Date docDate;

    /**
     * Конструктор для hibernate
     */
    public Document() {

    }

    public Document(DocType docType, String docNumber, Date docDate) {
        this.docType = docType;
        this.docNumber = docNumber;
        this.docDate = docDate;
    }

    public Long getId() {
        return id;
    }

    public DocType getDocType() {
        return this.docType;
    }

    public Long getDocTypeId() {
        if (this.docType == null) {
            return Long.valueOf(0);
        }
        return this.docType.getId();
    }

    public String getDocNumber() {
        return docNumber;
    }

    public Date getDocDate() {
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

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }
}
