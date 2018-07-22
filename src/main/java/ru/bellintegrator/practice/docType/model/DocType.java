package ru.bellintegrator.practice.docType.model;

import ru.bellintegrator.practice.document.model.Document;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Типы документов
 */
@Entity
@Table(name = "Document_Type")
public class DocType {

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
            mappedBy="docType",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Document> documents;

    public Set<Document> getDocuments() {
        if (documents == null) {
            documents = new HashSet<>();
        }
        return documents;
    }
    public void addDocument(Document document) {
        getDocuments().add(document);
        document.setDocType(this);
    }

    public void removeDocument(Document document) {
        getDocuments().remove(document);
        document.setDocType(null);
    }
    /**
     * Код типа документа
     */
    @Column(name = "doc_code", length = 3, nullable = false)
    private String docCode;
    /**
     * Наименование типа документа
     */
    @Column(name = "doc_name", length = 100, nullable = true)
    private String docName;

    /**
     * Конструктор для hibernate
     */
    public DocType() {

    }

    public DocType(Long id, String docCode, String docName) {
        this.id = id;
        this.docCode = docCode;
        this.docName = docName;
    }

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
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
