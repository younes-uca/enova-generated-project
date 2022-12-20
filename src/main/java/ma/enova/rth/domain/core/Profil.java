package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;


/**
 * profil
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "profil_")
@GenericGenerator(name = "profilSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "profil_seq"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profil extends AuditBusinessObject {

    /**
     * Fields.
     */

    /**
     * Code
     */
    private String code;

    /**
     * Libellé
     */
    private String libelle;

    /**
     * Description
     */
    private String description;

    /**
     * roles
     */
    private List<Role> rolesList;


    /**
     * Constructeur par défaut.
     */
    public Profil() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public Profil(Long id) {
        super(id);
    }

    /**
     * Methods.
     */

    @Column(length = 255)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(length = 255)
    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Type(type = "text")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(name = "profil_roles_",
            joinColumns = {@JoinColumn(name = "id_profil")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")})
    public List<Role> getRolesList() {
        return this.rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }


    @Id
    @Column(name = "id_profil")
    @GeneratedValue(generator = "profilSequenceGenerator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }


}