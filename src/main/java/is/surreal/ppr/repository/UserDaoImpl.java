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

import is.surreal.ppr.model.Operation;
import is.surreal.ppr.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveOrUpdate(User user) {

    }

    @Override
    public void delete(int userId) {

    }

    @Override
    public User get(int userId) {
        String query = "SELECT * FROM user where id=" + userId;

        return jdbcTemplate.query(query, new ResultSetExtractor<User>() {

            @Override
            public User extractData(ResultSet resultSet) throws SQLException,
                    DataAccessException {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setFirstName(resultSet.getString("firstName"));
                    //user.setAddress(resultSet.getString("address"));
                    user.setBirthDay(resultSet.getDate("birthDay"));
                    user.setEmail(resultSet.getString("email"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setTelephoneNumber(resultSet.getString("telephoneNumber"));
                    user.setUsername(resultSet.getString("userName"));
                    return user;
                }
                return null;
            }
        });
    }

    @Override
    public List<User> list() {
        return null;
    }
}
