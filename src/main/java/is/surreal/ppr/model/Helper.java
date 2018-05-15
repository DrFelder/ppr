package is.surreal.ppr.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Helper {
    private Long id;
    private String title;
    private String description;
    private Integer operationId;
    private Operation operationByOperationId;
    private Collection<Operationparticipation> operationparticipationsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "operation_id")
    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Helper helper = (Helper) o;

        if (id != helper.id) return false;
        if (title != null ? !title.equals(helper.title) : helper.title != null) return false;
        if (description != null ? !description.equals(helper.description) : helper.description != null) return false;
        if (operationId != null ? !operationId.equals(helper.operationId) : helper.operationId != null) return false;

        return true;
    }

    @ManyToOne
    @JoinColumn(name = "operation_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Operation getOperationByOperationId() {
        return operationByOperationId;
    }

    public void setOperationByOperationId(Operation operationByOperationId) {
        this.operationByOperationId = operationByOperationId;
    }

    @OneToMany(mappedBy = "helperByHelperId")
    public Collection<Operationparticipation> getOperationparticipationsById() {
        return operationparticipationsById;
    }

    public void setOperationparticipationsById(Collection<Operationparticipation> operationparticipationsById) {
        this.operationparticipationsById = operationparticipationsById;
    }

    public Operationparticipation operationparticipationForUsername(String username) {
        for (Operationparticipation operationparticipation : getOperationparticipationsById()) {
            if (operationparticipation.getUserByUserId().getUsername().equals(username)) {
                return operationparticipation;
            }
        }
        return null;
    }
}
