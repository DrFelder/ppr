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

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import java.sql.Timestamp;

public abstract class JdbcRequirementDao extends SimpleJdbcDaoSupport {
    String title;
    String description;
    JdbcOperationDao operation = null;
    JdbcUserDao user = null;
    Timestamp created = null;
    JdbcApplicationDao[] applications = null;

    public abstract String getTitle();

    public abstract void setTitle(String title);

    public abstract String getDescription();

    public abstract void setDescription(String description);

    public abstract JdbcOperationDao getOperation();

    public abstract void setOperation(JdbcOperationDao operation);

    public abstract JdbcUserDao getUser();

    public abstract void setUser(JdbcUserDao user);

    public abstract Timestamp getCreated();

    public abstract void setCreated(Timestamp created);

    public abstract JdbcApplicationDao[] getApplications();

    public abstract void setApplications(JdbcApplicationDao[] applications);

}
