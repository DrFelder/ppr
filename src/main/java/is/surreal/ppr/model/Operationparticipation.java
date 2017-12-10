package is.surreal.ppr.model;

import javax.persistence.*;

@Entity
public class Operationparticipation {
    private Long id;
    private Integer userId;
    private Integer operationId;
    private User userByUserId;
    private Operation operationByOperationId;
    private Byte accepted;
    private Byte declined;

    public Operationparticipation() {
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
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "operation_id")
    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    @Basic
    @Column(name = "accepted")
    public Byte getAccepted() {
        return accepted;
    }

    public void setAccepted(Byte accepted) {
        this.accepted = accepted;
    }

    @Basic
    @Column(name = "declined")
    public Byte getDeclined() {
        return declined;
    }

    public void setDeclined(Byte declined) {
        this.declined = declined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operationparticipation that = (Operationparticipation) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (operationId != null ? !operationId.equals(that.operationId) : that.operationId != null) return false;
        if (accepted != null ? !accepted.equals(that.accepted) : that.accepted != null) return false;
        if (declined != null ? !declined.equals(that.declined) : that.declined != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (operationId != null ? operationId.hashCode() : 0);
        result = 31 * result + (accepted != null ? accepted.hashCode() : 0);
        result = 31 * result + (declined != null ? declined.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
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
