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
import is.surreal.ppr.model.Operation;
import is.surreal.ppr.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OperationDaoImpl implements OperationDao {

    private JdbcTemplate jdbcTemplate;

    public OperationDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOrUpdate(Operation operation) {
        if (operation.getId() > 0) {
            String query = "UPDATE operation SET title=?,  date=?,  publicDescription=?,  privateDescription=?,  location=? WHERE id=?";
            jdbcTemplate.update(query, operation.getTitle(), operation.getDate(), operation.getPublicDescription(), operation.getPrivateDescription(), operation.getLocation(), operation.getId());
        } else {
            String query = "INSERT INTO operation (title, date, publicDescription, privateDescription, location) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(query, operation.getTitle(), operation.getDate(), operation.getPublicDescription(), operation.getPrivateDescription(), operation.getLocation());
        }
    }

    @Override
    public void delete(int operationId) {
        String query = "DELETE FROM operation WHERE id = ?";
        jdbcTemplate.update(query, operationId);
    }

    @Override
    public Operation get(int operationId) {
        String query =
                "SELECT " +
                        "o.id as operation_id, " +
                        "o.created as operation_created, " +
                        "o.updated as operation_updated, " +
                        "title, " +
                        "date, " +
                        "publicDescription, "+
                        "privateDescription, "+
                        "location, "+
                        "organizer_id, " +
                        "u.id as user_id, " +
                        "username, " +
                        "email, " +
                        "lastname, " +
                        "firstname, " +
                        "birthday, " +
                        "telephonenumber " +
                        "FROM operation o "+
                        "JOIN user u on o.organizer_id=u.id " +
                        "WHERE o.id=?";

        return jdbcTemplate.query(query, new ResultSetExtractor<Operation>() {

            @Override
            public Operation extractData(ResultSet resultSet) throws SQLException,
                    DataAccessException {
                if (resultSet.next()) {
                    Operation operation = new Operation();
                    operation.setId(resultSet.getInt("operation_id"));
                    operation.setTitle(resultSet.getString("title"));
                    operation.setDate(resultSet.getDate("date"));
                    operation.setPublicDescription(resultSet.getString("publicDescription"));
                    operation.setPrivateDescription(resultSet.getString("privateDescription"));
                    operation.setLocation(resultSet.getString("location"));

                    User user = new User();
                    user.setUsername(resultSet.getString("username"));
                    user.setTelephoneNumber(resultSet.getString("telephoneNumber"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setEmail(resultSet.getString("email"));
                    user.setBirthDay(resultSet.getDate("birthday"));
                    user.setId(resultSet.getInt("user_id"));

                    operation.setOrganizer(user);

                    return operation;
                }
                return null;
            }
        }, operationId);

    }

    @Override
    public List<Operation> list() {
        String query = "SELECT * FROM operation";
        List<Operation> listOperation = jdbcTemplate.query(query, new RowMapper<Operation>() {

            @Override
            public Operation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Operation operation = new Operation();
                operation.setId(resultSet.getInt("id"));
                operation.setTitle(resultSet.getString("title"));
                operation.setDate(resultSet.getDate("date"));
                operation.setPublicDescription(resultSet.getString("publicDescription"));
                operation.setPrivateDescription(resultSet.getString("privateDescription"));
                operation.setLocation(resultSet.getString("location"));
                return operation;
            }
        });

        return listOperation;
    }
}
