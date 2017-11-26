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

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class JdbcUserDao extends JdbcTemplate {
    private String firstName;
    private String lastName;
    private String username;
    private Date dateOfBirth;
    private JdbcAddressDao address;
    private String telephoneNumber;
    private String emailAddress;
    private OperationDaoImpl[] operationsOwned;
    private OperationDaoImpl[] operationsParticipate;

    public JdbcUserDao(String firstName, String lastName, String username, Date dateOfBirth, JdbcAddressDao address, String telephoneNumber, String emailAddress, OperationDaoImpl[] operationsOwned, OperationDaoImpl[] operationsParticipate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.operationsOwned = operationsOwned;
        this.operationsParticipate = operationsParticipate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public JdbcAddressDao getAddress() {
        return address;
    }

    public void setAddress(JdbcAddressDao address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public OperationDaoImpl[] getOperationsOwned() {
        return operationsOwned;
    }

    public void setOperationsOwned(OperationDaoImpl[] operationsOwned) {
        this.operationsOwned = operationsOwned;
    }

    public OperationDaoImpl[] getOperationsParticipate() {
        return operationsParticipate;
    }

    public void setOperationsParticipate(OperationDaoImpl[] operationsParticipate) {
        this.operationsParticipate = operationsParticipate;
    }
}
