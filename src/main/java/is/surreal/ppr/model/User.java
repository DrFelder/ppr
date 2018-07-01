/*
 * Project Puerto Rico
 * Copyright (C) 2018  Thomas Pötzsch, Stephan Stroh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package is.surreal.ppr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

//TODO: Zur Datenbank hinzufügen

@Entity
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Long userdataId;
    private Collection<Operation> operationsById;
    private Collection<Operationparticipation> operationparticipationsById;
    private Userdata userdataByUserdataId;
    private List<Role> roles;
    private Collection<Notificationread> readsById;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    @JsonIgnore
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
    @Column(name = "userdata_id")
    @JsonIgnore
    public Long getUserdataId() {
        return userdataId;
    }

    public void setUserdataId(Long userdataId) {
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
    @JsonIgnore
    public Collection<Operation> getOperationsById() {
        return operationsById;
    }

    public void setOperationsById(Collection<Operation> operationsById) {
        this.operationsById = operationsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
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

    @OneToMany(mappedBy = "userByUserId")
    @JsonIgnore
    public Collection<Notificationread> getReadsById() {
        return readsById;
    }

    public void setReadsById(Collection<Notificationread> readsById) {
        this.readsById = readsById;
    }
}
