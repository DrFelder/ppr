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
    </p>
  </div>
</template>

<script>
import AXIOS from '../../../config/http-commons';

export default {
  data() {
    return {
      errors: [],
      equipment: {
        title: null,
        description: null,
        operationId: null,
      },
    };
  },
  methods: {
    checkForm(e) {
      if (this.equipment.title && this.equipment.description) {
        this.equipment.operationId = this.$parent.$route.params.id;
        AXIOS.post('http://localhost:8080/rest/equipment/', this.equipment, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
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
      if (!this.equipment.title) this.errors.push('Title required.');
      if (!this.equipment.description) this.errors.push('Description required.');
      e.preventDefault();
      return false;
    },
  },
};
</script>
