package is.surreal.ppr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Notificationread {
    private Long id;
    private Long messageId;
    private Long userId;
    private Timestamp date;
    private User userByUserId;
    private Notification messageByMessageId;

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
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "message_id")
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notificationread notificationread = (Notificationread) o;

        if (messageId != notificationread.messageId) return false;
        if (userId != notificationread.userId) return false;
        if (date != null ? !date.equals(notificationread.date) : notificationread.date != null) return false;

        return true;
    }

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    public Notification getMessageByMessageId() {
        return messageByMessageId;
    }

    public void setMessageByMessageId(Notification messageByMessageId) {
        this.messageByMessageId = messageByMessageId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
