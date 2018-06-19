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
  <div class="popup">
    <p v-if="errors.length">
      <b>Please correct the following error(s):</b>
    <ul class="error-list">
      <!-- eslint-disable-next-line -->
      <li class="error-list-item" v-for="error in errors">{{ error }}</li>
    </ul>
    </p>
    <p>
    <h3>Start Operation: {{operation.title}}</h3>
    <!-- eslint-disable-next-line -->
    </p>

    <p>
      <label for="date">Date:</label>
      <input :disabled="true" id="date" type="date" v-model="operation.date">
    </p>
    <p>
      <label for="publicdescription">Public description:</label>
      <textarea :disabled="true"  id="publicdescription" v-model="operation.publicdescription"
                placeholder="Public description">
        </textarea>
    </p>
    <p>
      <label for="privatedescription">Private description:</label>
      <textarea :disabled="true"
        id="privatedescription" v-model="operation.privatedescription"
        placeholder="Private description"
      >
      </textarea>
    </p>
    <p>
      <label for="location">Location:</label>
      <input :disabled="true" id="location" v-model="operation.location" placeholder="Location">
    </p>
    <p>
      <button id="submit" v-on:click="checkForm">Start Operation</button>
      <button v-on:click="$router.go(-1)">back</button>
    </p>

  </div>
</template>

<script>
import AXIOS from '../../../config/http-commons';

export default {
  data() {
    return {
      errors: [],
      operation: {
        title: null,
        date: null,
        publicdescription: null,
        privatedescription: null,
        location: null,
      },
    };
  },
  created() {
    this.operation.id = this.$parent.$route.query.operation_id;
    this.operation.title = this.$parent.$route.query.operation_title;
    this.operation.date = this.$parent.$route.query.operation_date;
    this.operation.publicdescription = this.$parent.$route.query.operation_publicdescription;
    this.operation.privatedescription = this.$parent.$route.query.operation_privatedescription;
    this.operation.location = this.$parent.$route.query.operation_location;
    this.operation.startDate = this.$parent.$route.query.operation_startdate;
  },
  methods: {
    checkForm(e) {
      if (this.operation.id && this.operation.startDate == null) {
        this.operation.startDate = new Date();
        AXIOS.post('http://localhost:8080/rest/operation/', this.operation, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
          .then(() => {
            this.loading = false;
            this.$router.push({
              name: 'SingleOperation',
              params: { id: this.$parent.$route.params.id },
              headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` },
            });
          })
          .catch(() => {
            this.loading = false;
          });
        return true;
      }
      this.errors = [];
      if (!this.operation.startDate) this.errors.push('Operation already started');
      e.preventDefault();
      return false;
    },
  },
};
</script>
