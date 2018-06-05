package is.surreal.ppr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Userdata {
    private Long id;
    private String firstname;
    private String lastname;
    private Date birthday;
    private Long addressId;
    private Collection<User> usersById;
    private Address addressByAddressId;

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
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "address_id")
    @JsonIgnore
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    @JsonIgnore
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userdata userdata = (Userdata) o;

        if (id != userdata.id) return false;
        if (firstname != null ? !firstname.equals(userdata.firstname) : userdata.firstname != null) return false;
        if (lastname != null ? !lastname.equals(userdata.lastname) : userdata.lastname != null) return false;
        if (birthday != null ? !birthday.equals(userdata.birthday) : userdata.birthday != null) return false;
        if (addressId != null ? !addressId.equals(userdata.addressId) : userdata.addressId != null) return false;

        return true;
    }

    @OneToMany(mappedBy = "userdataByUserdataId")
    @JsonIgnore
    public Collection<User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<User> usersById) {
        this.usersById = usersById;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}
