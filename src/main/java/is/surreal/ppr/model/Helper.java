package is.surreal.ppr.model;

import javax.persistence.*;

@Entity
public class Helper {
    private Long id;
    private String title;
    private String description;
    private Integer operationId;
    private Operation operationByOperationId;

    public Helper() {
    }

    @Id
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

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (operationId != null ? operationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "operation_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Operation getOperationByOperationId() {
        return operationByOperationId;
    }

    public void setOperationByOperationId(Operation operationByOperationId) {
        this.operationByOperationId = operationByOperationId;
    }
}
