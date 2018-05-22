<template>
  <div>
    <h2>User details for user {{ $route.params.id }}</h2>
      <tbody>
      <tr>
        <th>First Name</th>
        <td>{{ response.userdataByUserdataId.firstname }}</td>
      </tr>
      <tr>
        <th>Last Name</th>
        <td>{{ response.userdataByUserdataId.lastname }}</td>
      </tr>
      <tr>
        <th>Username</th>
        <td>{{ response.username }}</td>
      </tr>
      <tr>
        <th>Email address</th>
        <td>{{ response.email }}</td>
      </tr>
      <tr>
        <th>Birthday</th>
        <td>{{ response.userdataByUserdataId.birthday }}</td>
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
      loading: false,
    };
  },

  created() {
    this.loading = true;
    AXIOS.get(`http://localhost:8080/rest/user/${this.$route.params.username}`, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
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
