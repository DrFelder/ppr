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
import is.surreal.ppr.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user.getId() > 0) {
            String query = "UPDATE user SET email=?, lastname=?, firstname=?, birthday=?, telephonenumber=? WHERE id=?";
            jdbcTemplate.update(query,
                    user.getEmail(), user.getLastName(), user.getFirstName(),
                    user.getBirthDay(), user.getTelephoneNumber(), user.getId()
            );
        } else {
            String query = "INSERT INTO user (email,lastname, firstname, birthday, telephonenumber) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(query,
                    user.getEmail(), user.getLastName(), user.getFirstName(),
                    user.getBirthDay(), user.getTelephoneNumber()
            );
        }
    }

    @Override
    public void delete(int userId) {
        String query = "DELETE FROM user WHERE id=?";
        jdbcTemplate.update(query, userId);
    }

    @Override
    public User get(int userId) {
        String query = "SELECT user.*, " +
                "a.id as address_id, " +
                "street, " +
                "streetnumber, " +
                "zipcode, " +
                "country, " +
                "city, " +
                "state " +
                "FROM user " +
                "JOIN address a ON user.address_id = a.id " +
                "WHERE user.id=?";

        return jdbcTemplate.query(query, new ResultSetExtractor<User>() {

            @Override
            public User extractData(ResultSet resultSet) throws SQLException,
                    DataAccessException {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setBirthDay(resultSet.getDate("birthDay"));
                    user.setEmail(resultSet.getString("email"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setTelephoneNumber(resultSet.getString("telephoneNumber"));
                    user.setUsername(resultSet.getString("userName"));

                    Address address = new Address();
                    address.setCity(resultSet.getString("city"));
                    address.setCountry(resultSet.getString("country"));
                    address.setId(resultSet.getInt("address_id"));
                    address.setState(resultSet.getString("state"));
                    address.setStreet(resultSet.getString("street"));
                    address.setStreetNumber(resultSet.getInt("streetNumber"));
                    address.setZipCode(resultSet.getString("zipCode"));
                    user.setAddress(address);

                    return user;
                }
                return null;
            }
        }, userId);
    }

    @Override
    public List<User> list() {
        String query = "SELECT user.*, " +
                "address.id as address_id, " +
                "street, " +
                "streetnumber, " +
                "zipcode, " +
                "country, " +
                "city, " +
                "state " +
                "FROM user " +
                "JOIN address on user.address_id = address.id";

        List<User> listUser = jdbcTemplate.query(query, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setBirthDay(resultSet.getDate("birthday"));
                user.setEmail(resultSet.getString("email"));
                user.setLastName(resultSet.getString("lastname"));
                user.setTelephoneNumber(resultSet.getString("telephoneNumber"));
                user.setUsername(resultSet.getString("username"));

                Address address = new Address();
                address.setCity(resultSet.getString("city"));
                address.setCountry(resultSet.getString("country"));
                address.setId(resultSet.getInt("address_id"));
                address.setState(resultSet.getString("state"));
                address.setStreet(resultSet.getString("street"));
                address.setStreetNumber(resultSet.getInt("streetNumber"));
                address.setZipCode(resultSet.getString("zipCode"));
                user.setAddress(address);

                return user;
            }
        });
        return listUser;
    }
}
