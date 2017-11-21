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

public class Application {
    private User user;
    private Operation operation;
    private String comment;
    private java.sql.Timestamp commentTime;
    private String answer;
    private java.sql.Timestamp answerTime;
    private java.sql.Timestamp created;
    private java.sql.Timestamp accepted;

    public Application(User user, Operation operation) {
    }

    public void applyForOperation() {
    }

    public void answerApplication() {
    }
}
