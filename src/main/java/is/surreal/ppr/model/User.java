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
import java.util.Date;

public class User implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private Date dateOfBirth;
    private Address address;
    private String telephoneNumber;
    private String emailAddress;
    private Operation[] operationsOwned;
    private Operation[] operationsParticipate;

    public User(Integer id, String firstName, String lastName, String username, Date dateOfBirth, Address address, String telephoneNumber, String emailAddress, Operation[] operationsOwned, Operation[] operationsParticipate) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public Operation[] getOperationsOwned() {
        return operationsOwned;
    }

    public void setOperationsOwned(Operation[] operationsOwned) {
        this.operationsOwned = operationsOwned;
    }

    public Operation[] getOperationsParticipate() {
        return operationsParticipate;
    }

    public void setOperationsParticipate(Operation[] operationsParticipate) {
        this.operationsParticipate = operationsParticipate;
    }
}
