<template>
  <div>
    <h2>User list</h2>
    <tbody>
    <tr>
      <th>User ID</th>
      <th>Username</th>
      <th>Email address</th>
      <th>Roles</th>
    </tr>
    <!-- eslint-disable-next-line -->
    <tr v-for="user in response">
      <td>{{ user.username }}</td>
      <td>{{ user.email }}</td>
      <td>{{ user.roles }}</td>
      <td>
        <router-link :to="{ name: 'SingleUser', params: { id: user.id }}">
          Show profile
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
    AXIOS.get('http://localhost:8080/rest/user/')
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
