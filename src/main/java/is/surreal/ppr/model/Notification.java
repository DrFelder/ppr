package is.surreal.ppr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Notification {
    private Long id;
    private String message;
    private Long operationId;
    private Operation operationByOperationId;

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
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "operation_id")
    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification notification = (Notification) o;

        if (id != notification.id) return false;
        if (message != null ? !message.equals(notification.message) : notification.message != null) return false;
        if (operationId != null ? !operationId.equals(notification.operationId) : notification.operationId != null) return false;

        return true;
    }

    @ManyToOne
    @JoinColumn(name = "operation_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    public Operation getOperationByOperationId() {
        return operationByOperationId;
    }

    public void setOperationByOperationId(Operation operationByOperationId) {
        this.operationByOperationId = operationByOperationId;
    }
}
