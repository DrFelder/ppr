package is.surreal.ppr.model;

import javax.persistence.*;

@Entity
public class Operationparticipation {
    private Long id;
    private Long userId;
    private Byte accepted;
    private Byte declined;
    private Long equipmentId;
    private Long helperId;
    private String question;
    private String answer;
    private User userByUserId;
    private Equipment equipmentByEquipmentId;
    private Helper helperByHelperId;

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
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Basic
    @Column(name = "equipment_id")
    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Basic
    @Column(name = "helper_id")
    public Long getHelperId() {
        return helperId;
    }

    public void setHelperId(Long helperId) {
        this.helperId = helperId;
    }

    @Basic
    @Column(name = "question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operationparticipation that = (Operationparticipation) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (accepted != null ? !accepted.equals(that.accepted) : that.accepted != null) return false;
        if (declined != null ? !declined.equals(that.declined) : that.declined != null) return false;
        if (equipmentId != null ? !equipmentId.equals(that.equipmentId) : that.equipmentId != null) return false;
        if (helperId != null ? !helperId.equals(that.helperId) : that.helperId != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;

        return true;
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
    @JoinColumn(name = "equipment_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Equipment getEquipmentByEquipmentId() {
        return equipmentByEquipmentId;
    }

    public void setEquipmentByEquipmentId(Equipment equipmentByEquipmentId) {
        this.equipmentByEquipmentId = equipmentByEquipmentId;
    }

    @ManyToOne
    @JoinColumn(name = "helper_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Helper getHelperByHelperId() {
        return helperByHelperId;
    }

    public void setHelperByHelperId(Helper helperByHelperId) {
        this.helperByHelperId = helperByHelperId;
    }
}
