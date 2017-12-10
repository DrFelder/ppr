package is.surreal.ppr.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String roles;
    private Integer userdataId;
    private Collection<Operation> operationsById;
    private Collection<Operationparticipation> operationparticipationsById;
    private Userdata userdataByUserdataId;

    public User() {
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "roles")
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Basic
    @Column(name = "userdata_id")
    public Integer getUserdataId() {
        return userdataId;
    }

    public void setUserdataId(Integer userdataId) {
        this.userdataId = userdataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (roles != null ? !roles.equals(user.roles) : user.roles != null) return false;
        if (userdataId != null ? !userdataId.equals(user.userdataId) : user.userdataId != null) return false;

        return true;
    }

    @OneToMany(mappedBy = "userByOrganizerId")
    public Collection<Operation> getOperationsById() {
        return operationsById;
    }

    public void setOperationsById(Collection<Operation> operationsById) {
        this.operationsById = operationsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Operationparticipation> getOperationparticipationsById() {
        return operationparticipationsById;
    }

    public void setOperationparticipationsById(Collection<Operationparticipation> operationparticipationsById) {
        this.operationparticipationsById = operationparticipationsById;
    }

    @ManyToOne
    @JoinColumn(name = "userdata_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Userdata getUserdataByUserdataId() {
        return userdataByUserdataId;
    }

    public void setUserdataByUserdataId(Userdata userdataByUserdataId) {
        this.userdataByUserdataId = userdataByUserdataId;
    }
}
