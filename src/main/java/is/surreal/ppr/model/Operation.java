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

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Operation {
    private Long id;
    private String title;
    private Date date;
    private Date startDate;
    private String publicdescription;
    private String privatedescription;
    private String location;
    private Long organizerId;
    private Collection<Equipment> equipmentById;
    private Collection<Helper> helpersById;
    private Collection<Notification> notificationsById;
    private User userByOrganizerId;

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
    @Size(min = 1, max = 40, message = "Title should have between 1 and 60 characters.")
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Size(min = 1, max = 2000, message = "Public description should have between 1 and 2000 characters.")
    @Column(name = "publicdescription")
    public String getPublicdescription() {
        return publicdescription;
    }

    public void setPublicdescription(String publicdescription) {
        this.publicdescription = publicdescription;
    }

    @Basic
    @Size(min = 1, max = 2000, message = "Private description should have between 1 and 2000 characters.")
    @Column(name = "privatedescription")
    public String getPrivatedescription() {
        return privatedescription;
    }

    public void setPrivatedescription(String privatedescription) {
        this.privatedescription = privatedescription;
    }

    @Basic
    @Size(min = 1, max = 60, message = "Location should have between 1 and 60 characters.")
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "organizer_id")
    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (id != operation.id) return false;
        if (title != null ? !title.equals(operation.title) : operation.title != null) return false;
        if (date != null ? !date.equals(operation.date) : operation.date != null) return false;
        if (publicdescription != null ? !publicdescription.equals(operation.publicdescription) : operation.publicdescription != null)
            return false;
        if (privatedescription != null ? !privatedescription.equals(operation.privatedescription) : operation.privatedescription != null)
            return false;
        if (location != null ? !location.equals(operation.location) : operation.location != null) return false;
        if (organizerId != null ? !organizerId.equals(operation.organizerId) : operation.organizerId != null)
            return false;

        return true;
    }

    @OneToMany(mappedBy = "operationByOperationId")
    public Collection<Equipment> getEquipmentById() {
        return equipmentById;
    }

    public void setEquipmentById(Collection<Equipment> equipmentById) {
        this.equipmentById = equipmentById;
    }

    @OneToMany(mappedBy = "operationByOperationId")
    public Collection<Helper> getHelpersById() {
        return helpersById;
    }

    public void setHelpersById(Collection<Helper> helpersById) {
        this.helpersById = helpersById;
    }

    @OneToMany(mappedBy = "operationByOperationId")
    public Collection<Notification> getNotificationsById() {
        return notificationsById;
    }

    public void setNotificationsById(Collection<Notification> notificationsById) {
        this.notificationsById = notificationsById;
    }

    @ManyToOne
    @JoinColumn(name = "organizer_id", referencedColumnName = "id", insertable = false, updatable = false)
    public User getUserByOrganizerId() {
        return userByOrganizerId;
    }

    public void setUserByOrganizerId(User userByOrganizerId) {
        this.userByOrganizerId = userByOrganizerId;
    }
}
