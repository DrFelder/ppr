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

import java.sql.Timestamp;

public class JdbcApplicationDao extends JdbcTemplate {
    private JdbcUserDao user;
    private JdbcOperationDao operation;
    private String comment;
    private Timestamp commentTime;
    private String answer;
    private Timestamp answerTime;
    private Timestamp created;
    private Timestamp accepted;

    public JdbcApplicationDao(JdbcUserDao user, JdbcOperationDao operation, String comment, Timestamp commentTime, String answer, Timestamp answerTime, Timestamp created, Timestamp accepted) {
        this.user = user;
        this.operation = operation;
        this.comment = comment;
        this.commentTime = commentTime;
        this.answer = answer;
        this.answerTime = answerTime;
        this.created = created;
        this.accepted = accepted;
    }

    public JdbcApplicationDao(JdbcUserDao user, JdbcOperationDao operation) {
    }

    public void applyForOperation() {
    }

    public void answerApplication() {
    }

    public JdbcUserDao getUser() {
        return user;
    }

    public void setUser(JdbcUserDao user) {
        this.user = user;
    }

    public JdbcOperationDao getOperation() {
        return operation;
    }

    public void setOperation(JdbcOperationDao operation) {
        this.operation = operation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Timestamp getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Timestamp answerTime) {
        this.answerTime = answerTime;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getAccepted() {
        return accepted;
    }

    public void setAccepted(Timestamp accepted) {
        this.accepted = accepted;
    }
}
