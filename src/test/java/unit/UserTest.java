/*
 * Project Puerto Rico
 * Copyright (C) 2018  Thomas Pötzsch, Stephan Stroh
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

package unit;

import is.surreal.ppr.Application;
import is.surreal.ppr.model.User;
import is.surreal.ppr.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void generalUserTest() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("username-test");
        user.setPassword("Wow!");

        this.userRepository.save(user);

        User savedUser = this.userRepository.findByUsername("username-test");
        assertEquals(savedUser.getEmail(), user.getEmail());
        assertEquals(savedUser.getUsername(), user.getUsername());
        assertEquals(savedUser.getPassword(), user.getPassword());
        assertNotNull(savedUser.getId());
    }

}
