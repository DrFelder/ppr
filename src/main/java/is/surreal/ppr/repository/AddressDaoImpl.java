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

import is.surreal.ppr.model.Address;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    private JdbcTemplate jdbcTemplate;

    public AddressDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOrUpdate(Address address) {
        if (address.getId() > 0) {
            String query = "UPDATE address SET street=?, streetnumber=?, zipcode=?, country=?, city=?, state=? " +
                    "WHERE id=?";
            jdbcTemplate.update(query,
                    address.getStreet(), address.getStreetNumber(), address.getZipCode(), address.getCountry(),
                    address.getCity(), address.getState(), address.getId()
            );
        } else {
            String query = "INSERT INTO address (street, streetnumber, zipcode, country, city, state) " +
                    "VALUES (?,?,?,?,?,?)";
            jdbcTemplate.update(query,
                    address.getStreet(), address.getStreetNumber(), address.getZipCode(), address.getCountry(),
                    address.getCity(), address.getState()
            );
        }
    }

    @Override
    public void delete(int addressId) {
        String query = "DELETE FROM address WHERE id=?";
        jdbcTemplate.update(query, addressId);
    }

    @Override
    public Address get(int addressId) {
        String query = "SELECT * FROM address WHERE id=?";

        return jdbcTemplate.query(query, new ResultSetExtractor<Address>() {

            @Override
            public Address extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {

                    Address address = new Address();
                    address.setCity(resultSet.getString("city"));
                    address.setCountry(resultSet.getString("country"));
                    address.setId(resultSet.getInt("address_id"));
                    address.setState(resultSet.getString("state"));
                    address.setStreet(resultSet.getString("street"));
                    address.setStreetNumber(resultSet.getInt("streetNumber"));
                    address.setZipCode(resultSet.getString("zipCode"));

                    return address;
                }
                return null;
            }
        }, addressId);
    }

    @Override
    public List<Address> list() {
        String query = "SELECT * FROM user";

        List<Address> listAddress = jdbcTemplate.query(query, new RowMapper<Address>() {

            @Override
            public Address mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setZipCode(resultSet.getString("zipcode"));
                address.setStreetNumber(resultSet.getInt("streetnumber"));
                address.setStreet(resultSet.getString("street"));
                address.setState(resultSet.getString("state"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));

                return address;
            }
        });
        return listAddress;
    }
}
