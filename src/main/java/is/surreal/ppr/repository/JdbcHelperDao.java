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

import java.sql.Timestamp;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcHelperDao extends JdbcRequirementDao {
    private JdbcOperationDao operation;
    private JdbcUserDao user;
    private Timestamp created;
    private JdbcApplicationDao[] applications;

    public JdbcHelperDao(JdbcOperationDao operation, JdbcUserDao user, Timestamp created, JdbcApplicationDao[] applications) {
        this.operation = operation;
        this.user = user;
        this.created = created;
        this.applications = applications;
    }

    @Override
    public JdbcOperationDao getOperation() {
        return operation;
    }

    @Override
    public void setOperation(JdbcOperationDao operation) {
        this.operation = operation;
    }

    @Override
    public JdbcUserDao getUser() {
        return user;
    }

    @Override
    public void setUser(JdbcUserDao user) {
        this.user = user;
    }

    @Override
    public Timestamp getCreated() {
        return created;
    }

    @Override
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public JdbcApplicationDao[] getApplications() {
        return applications;
    }

    @Override
    public void setApplications(JdbcApplicationDao[] applications) {
        this.applications = applications;
    }
}
