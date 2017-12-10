package is.surreal.ppr.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Userdata {
    private Long id;
    private String firstname;
    private String lastname;
    private Date birthday;
    private Integer addressId;
    private Collection<User> usersById;
    private Address addressByAddressId;

    public Userdata() {
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
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
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

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userdataByUserdataId")
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
