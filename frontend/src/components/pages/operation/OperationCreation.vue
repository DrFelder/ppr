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
      <input id="title" v-model="operation.title" placeholder="Title">
    </p>
    <p>
      <label for="date">Date:</label>
      <input id="date" type="date" v-model="operation.date">
    </p>
    <p>
      <label for="publicdescription">Public description:</label>
      <textarea id="publicdescription" v-model="operation.publicdescription"
                placeholder="Public description">
        </textarea>
    </p>
    <p>
      <label for="privatedescription">Private description:</label>
      <textarea
        id="privatedescription" v-model="operation.privatedescription"
        placeholder="Private description"
      >
      </textarea>
    </p>
    <p>
      <label for="location">Location:</label>
      <input id="location" v-model="operation.location" placeholder="Location">
    </p>
    <p>
      <button id="submit" v-on:click="checkForm">Submit</button>
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
  methods: {
    checkForm(e) {
      if (this.operation.title && this.operation.date && this.operation.publicdescription
          && this.operation.privatedescription && this.operation.location) {
        AXIOS.post('http://localhost:8080/rest/operation/', this.operation, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
          .then((response) => {
            this.loading = false;
            this.$router.push({
              name: 'SingleOperation',
              params: { id: response.headers.location.slice(response.headers.location.lastIndexOf('/') + 1) },
              headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` },
            });
          })
          .catch(() => {
            this.loading = false;
          });
        return true;
      }
      this.errors = [];
      if (!this.operation.title) this.errors.push('Title required.');
      if (!this.operation.date) this.errors.push('Date required.');
      if (!this.operation.publicdescription) this.errors.push('Public description required.');
      if (!this.operation.privatedescription) this.errors.push('Private description required.');
      if (!this.operation.location) this.errors.push('Location required.');
      e.preventDefault();
      return false;
    },
  },
};
</script>
