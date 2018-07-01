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
      <label for="title">Title:</label>
      <input id="title" v-model="equipment.title" placeholder="Title">
    </p>
    <p>
      <label for="description">Description:</label>
      <textarea id="description" v-model="equipment.description"
                placeholder="Description">
      </textarea>
    </p>
    <p>
      <button v-on:click="checkForm">Submit</button>
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
      helper: {
        title: null,
        description: null,
        operationId: null,
        id: null,
      },
    };
  },
  created() {
    this.helper.id = this.$parent.$route.query.equipment_id;
    this.helper.title = this.$parent.$route.query.equipment_title;
    this.helper.description = this.$parent.$route.query.equipment_description;
  },
  methods: {
    checkForm(e) {
      if (this.helper.title && this.helper.description && this.helper.id) {
        this.helper.operationId = this.$parent.$route.params.id;
        AXIOS.post('http://localhost:8080/rest/helper/', this.helper, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
          .then(() => {
            this.loading = false;
            this.$router.push({
              name: 'SingleOperation',
              params: { id: this.$parent.$route.params.id },
              headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` },
            });
            location.reload();
          })
          .catch(() => {
            this.loading = false;
          });
        return true;
      }
      this.errors = [];
      if (!this.helper.title) this.errors.push('Title required.');
      if (!this.helper.description) this.errors.push('Description required.');
      e.preventDefault();
      return false;
    },
  },
};
</script>
