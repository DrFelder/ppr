package is.surreal.ppr.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Operation {
    private Long id;
    private String title;
    private Date date;
    private String publicdescription;
    private String privatedescription;
    private String location;
    private Integer organizerId;
    private Collection<Equipment> equipmentById;
    private Collection<Helper> helpersById;
    private User userByOrganizerId;
    private Collection<Operationparticipation> operationparticipationsById;

    public Operation() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "publicdescription")
    public String getPublicdescription() {
        return publicdescription;
    }

    public void setPublicdescription(String publicdescription) {
        this.publicdescription = publicdescription;
    }

    @Basic
    @Column(name = "privatedescription")
    public String getPrivatedescription() {
        return privatedescription;
    }

    public void setPrivatedescription(String privatedescription) {
        this.privatedescription = privatedescription;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "organizer_id")
    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (id != operation.id) return false;
        if (title != null ? !title.equals(operation.title) : operation.title != null) return false;
        if (date != null ? !date.equals(operation.date) : operation.date != null) return false;
        if (publicdescription != null ? !publicdescription.equals(operation.publicdescription) : operation.publicdescription != null)
            return false;
        if (privatedescription != null ? !privatedescription.equals(operation.privatedescription) : operation.privatedescription != null)
            return false;
        if (location != null ? !location.equals(operation.location) : operation.location != null) return false;
        if (organizerId != null ? !organizerId.equals(operation.organizerId) : operation.organizerId != null)
            return false;

        return true;
    }

    @OneToMany(mappedBy = "operationByOperationId")
    public Collection<Equipment> getEquipmentById() {
        return equipmentById;
    }

    public void setEquipmentById(Collection<Equipment> equipmentById) {
        this.equipmentById = equipmentById;
    }

    @OneToMany(mappedBy = "operationByOperationId")
    public Collection<Helper> getHelpersById() {
        return helpersById;
    }

    public void setHelpersById(Collection<Helper> helpersById) {
        this.helpersById = helpersById;
    }

    @ManyToOne
    @JoinColumn(name = "organizer_id", referencedColumnName = "id", insertable = false, updatable = false)
    public User getUserByOrganizerId() {
        return userByOrganizerId;
    }

    public void setUserByOrganizerId(User userByOrganizerId) {
        this.userByOrganizerId = userByOrganizerId;
    }

    @OneToMany(mappedBy = "operationByOperationId")
    public Collection<Operationparticipation> getOperationparticipationsById() {
        return operationparticipationsById;
    }

    public void setOperationparticipationsById(Collection<Operationparticipation> operationparticipationsById) {
        this.operationparticipationsById = operationparticipationsById;
    }
}
