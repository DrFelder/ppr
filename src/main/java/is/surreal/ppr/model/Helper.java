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

@Entity
public class Helper {
    private Long id;
    private String title;
    private String description;
    private Long operationId;
    private Operation operationByOperationId;
    private Collection<Operationparticipation> operationparticipationsById;

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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        Helper helper = (Helper) o;

        if (id != helper.id) return false;
        if (title != null ? !title.equals(helper.title) : helper.title != null) return false;
        if (description != null ? !description.equals(helper.description) : helper.description != null) return false;
        if (operationId != null ? !operationId.equals(helper.operationId) : helper.operationId != null) return false;

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

    @OneToMany(mappedBy = "helperByHelperId")
    @JsonIgnore
    public Collection<Operationparticipation> getOperationparticipationsById() {
        return operationparticipationsById;
    }

    public void setOperationparticipationsById(Collection<Operationparticipation> operationparticipationsById) {
        this.operationparticipationsById = operationparticipationsById;
    }

    public Operationparticipation operationparticipationForUsername(String username) {
        for (Operationparticipation operationparticipation : getOperationparticipationsById()) {
            if (operationparticipation.getUserByUserId().getUsername().equals(username)) {
                return operationparticipation;
            }
        }
        return null;
    }
}
