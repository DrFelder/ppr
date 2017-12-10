package is.surreal.ppr.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Address {
    private Long id;
    private String street;
    private Integer streetnumber;
    private String zipcode;
    private String country;
    private String city;
    private String state;
    private Collection<Userdata> userdataById;

    public Address() {
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
    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "streetnumber")
    public Integer getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(Integer streetnumber) {
        this.streetnumber = streetnumber;
    }

    @Basic
    @Column(name = "zipcode")
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != address.id) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (streetnumber != null ? !streetnumber.equals(address.streetnumber) : address.streetnumber != null)
            return false;
        if (zipcode != null ? !zipcode.equals(address.zipcode) : address.zipcode != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (state != null ? !state.equals(address.state) : address.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (streetnumber != null ? streetnumber.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<Userdata> getUserdataById() {
        return userdataById;
    }

    public void setUserdataById(Collection<Userdata> userdataById) {
        this.userdataById = userdataById;
    }
}
