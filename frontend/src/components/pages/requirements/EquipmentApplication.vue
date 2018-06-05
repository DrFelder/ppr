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
      <label for="question">Do you have a question on your mind for the operation organizer?</label>
      <textarea id="question" v-model="operationparticipation.question"
                placeholder="I wanted to ask if...">
      </textarea>
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
      operationparticipation: {
        question: null,
        equipmentId: this.$route.query.equipment_id,
      },
    };
  },
  methods: {
    checkForm() {
      AXIOS.post('http://localhost:8080/rest/operationparticipation/', this.operationparticipation, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
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
    },
  },
};
</script>
