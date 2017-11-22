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

package is.surreal.ppr.model;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class Requirement implements Serializable {
    int id = 0;
    Operation operation = null;
    User user = null;
    Timestamp created = null;
    Application[] applications = null;

    public abstract Operation getOperation();

    public abstract void setOperation(Operation operation);

    public abstract int getId();

    public abstract void setId(int id);

    public abstract User getUser();

    public abstract void setUser(User user);

    public abstract Timestamp getCreated();

    public abstract void setCreated(Timestamp created);

    public abstract Application[] getApplications();

    public abstract void setApplications(Application[] applications);

}
