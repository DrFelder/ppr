/*
 * Project Puerto Rico
 * Copyright (C) 2017  Thomas Pötzsch, Stephan Stroh
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

package is.surreal.ppr.repository;

import java.util.Date;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcOperationDao extends SimpleJdbcDaoSupport {
    private String name;
    private String location;
    private Date dateOfExecution;
    private String publicDescription;
    private String privateDescription;
    private JdbcRequirementDao[] requirements;

    public JdbcOperationDao(String name, String location, Date dateOfExecution, String publicDescription, String privateDescription, JdbcRequirementDao[] requirements) {
        this.name = name;
        this.location = location;
        this.dateOfExecution = dateOfExecution;
        this.publicDescription = publicDescription;
        this.privateDescription = privateDescription;
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateOfExecution() {
        return dateOfExecution;
    }

    public void setDateOfExecution(Date dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
    }

    public String getPublicDescription() {
        return publicDescription;
    }

    public void setPublicDescription(String publicDescription) {
        this.publicDescription = publicDescription;
    }

    public String getPrivateDescription() {
        return privateDescription;
    }

    public void setPrivateDescription(String privateDescription) {
        this.privateDescription = privateDescription;
    }

    public JdbcRequirementDao[] getRequirements() {
        return requirements;
    }

    public void setRequirements(JdbcRequirementDao[] requirements) {
        this.requirements = requirements;
    }
}
