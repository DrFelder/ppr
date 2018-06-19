<template>
  <div>
    <h2>Operation list</h2>
    <router-view></router-view>
    <tbody>
    <tr>
      <th>Title</th>
      <th>Date</th>
      <th>Public description</th>
      <th>Location</th>
      <th>More</th>
    </tr>
    <!-- eslint-disable-next-line -->
    <tr v-for="operation in response">
      <td>{{ operation.title }}</td>
      <td>{{ operation.date }}</td>
      <td>{{ operation.publicdescription | truncate(300, '...') }}</td>
      <td>{{ operation.location }}</td>
      <td>
        <router-link :to="{ name: 'SingleOperation', params: { id: operation.id }} " tag="button">
          details
        </router-link>
      </td>
    </tr>
    </tbody>
  </div>
</template>

<script>
import AXIOS from '../../../config/http-commons';

export default {
  data() {
    return {
      response: [],
      errors: [],
    };
  },
  created() {
    this.loading = true;
    AXIOS.get('http://localhost:8080/rest/operation/', { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
      .then((response) => {
        this.response = response.data;
        this.loading = false;
      })
      .catch(() => {
        this.loading = false;
      });
  },
};
</script>
