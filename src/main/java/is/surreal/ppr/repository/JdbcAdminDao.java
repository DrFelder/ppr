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

public class JdbcAdminDao extends JdbcUserDao {
    public JdbcAdminDao(String firstName, String lastName, String username, Date dateOfBirth, JdbcAddressDao address, String telephoneNumber, String emailAddress, JdbcOperationDao[] operationsOwned, JdbcOperationDao[] operationsParticipate) {
        super(firstName, lastName, username, dateOfBirth, address, telephoneNumber, emailAddress, operationsOwned, operationsParticipate);
    }
}
