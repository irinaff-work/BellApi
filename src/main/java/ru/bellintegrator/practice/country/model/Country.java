package ru.bellintegrator.practice.country.model;


import javax.persistence.*;

/**
 * Справочник стран
 */
@Entity
@Table(name = "Country")
public class Country {

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
//            mappedBy="country"
//            //fetch = FetchType.LAZY,
//            //cascade = CascadeType.ALL,
//            //orphanRemoval = true
//    )

    /**
     * Код страны
     */
    @Column(name = "code", length = 3, nullable = false)
    private String code;
    /**
     * Название страны
     */
    @Column(name = "name", length = 250, nullable = true)
    private String name;


    /**
     * Конструктор для hibernate
     */
    public Country() {

    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
