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
    <h2>Registration</h2>
    <p v-if="errors.length">
      <b>Please correct the following error(s):</b>
    <ul class="error-list">
      <!-- eslint-disable-next-line -->
      <li class="error-list-item" v-for="error in errors">{{ error }}</li>
    </ul>
    </p>
    <p>
      <label for="username">User:</label>
      <input id="username" v-model="registration.username" placeholder="Username">
    </p>
    <p>
      <label for="password">Password:</label>
      <input id="password" type="password" v-model="registration.password">
    </p>
    <p>
      <button v-on:click="checkForm">Submit</button>
    </p>
  </div>
</template>

<script>
import AXIOS from '../../../config/http-commons';

export default {
  data() {
    return {
      errors: [],
      registration: {
        username: null,
        password: null,
      },
    };
  },
  methods: {
    checkForm(e) {
      if (this.registration.username && this.registration.password) {
        AXIOS.post('http://localhost:8080/public/users/register/',
          this.registration)
          .then(() => {
            this.loading = false;
            this.$router.push({ name: 'OperationList' });
          })
          .catch(() => {
            this.loading = false;
          });
        return true;
      }
      this.errors = [];
      if (!this.registration.username) this.errors.push('Username required.');
      if (!this.registration.password) this.errors.push('Password required.');
      e.preventDefault();
      return false;
    },
  },
};
</script>
