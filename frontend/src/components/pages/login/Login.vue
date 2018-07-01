<!--
  - Project Puerto Rico
  - Copyright (C) 2018  Thomas Pötzsch, Stephan Stroh
  -
  - This program is free software: you can redistribute it and/or modify
  - it under the terms of the GNU General Public License as published by
  - the Free Software Foundation, either version 3 of the License, or
  - (at your option) any later version.
  -
  - This program is distributed in the hope that it will be useful,
  - but WITHOUT ANY WARRANTY; without even the implied warranty of
  - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  - GNU General Public License for more details.
  -
  - You should have received a copy of the GNU General Public License
  - along with this program.  If not, see <http://www.gnu.org/licenses/>.
  -->

<template>
  <div>
    <h2>Login</h2>
    <p v-if="errors.length">
      <b>Please correct the following error(s):</b>
    <ul class="error-list">
      <!-- eslint-disable-next-line -->
      <li class="error-list-item" v-for="error in errors">{{ error }}</li>
    </ul>
    </p>
    <p>
      <label for="username_login">User:</label>
      <input id="username_login" v-model="login.username" placeholder="Username">
    </p>
    <p>
      <label for="password_login">Password:</label>
      <input id="password_login" type="password" v-model="login.password" @keyup.enter="checkForm">
    </p>
    <p>
      <button id="submit_login" v-on:click="checkForm">Submit</button>
    </p>
  </div>
</template>

<script>
import AXIOS from '../../../config/http-commons';

export default {
  data() {
    return {
      errors: [],
      login: {
        username: null,
        password: null,
      },
    };
  },
  computed: {
    clientAndSecret() {
      return `${this.$store.getters.client}:${this.$store.getters.secret}`;
    },
  },
  // TODO: line 52: computed value should be used
  methods: {
    checkForm(e) {
      if (this.login.username && this.login.password) {
        const bodyFormData = new FormData();
        bodyFormData.set('grant_type', 'password');
        bodyFormData.set('username', this.login.username);
        bodyFormData.set('password', this.login.password);
        AXIOS({
          method: 'post',
          url: 'http://localhost:8080/oauth/token',
          auth: {
            username: this.$store.getters.client,
            password: this.$store.getters.secret,
          },
          data: bodyFormData,
          config: { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } },
        })
          .then((token) => {
            AXIOS.get('http://localhost:8080/rest/user/current', { headers: { Authorization: `Bearer ${token.data.access_token}` } })
              .then((response) => {
                this.$store.dispatch('login', { userdata: response.data, credentials: token.data })
                  .then(() => {
                    this.$router.push({ name: 'Home' });
                  });
              });
          });
        return true;
      }
      this.errors = [];
      if (!this.login.username) this.errors.push('Username required.');
      if (!this.login.password) this.errors.push('Password required.');
      e.preventDefault();
      return false;
    },
  },
};
</script>
