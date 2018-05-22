<template>
  <ul>
    <li style="float: left">
      <router-link :to="{ name: 'Home'}">
        home
      </router-link>
    </li>
    <li style="float: left">
      <router-link :to="{ name: 'OperationList'}">
        browse operations
      </router-link>
    </li>
    <li style="float: left">
      <router-link :to="{ name: 'OperationCreation'}">
        create operation
      </router-link>
    </li>
    <li style="float: right">
      <div>
        <span v-if="!vuexIsLoggedIn">
          <router-link router-link to="/login">Login</router-link>
        </span>
        <div class="menu-form" v-if="vuexIsLoggedIn">
          <span class="menu-text">You are logged in as <i>{{ vuexUser }}</i></span>
          <a id="logout_button" class="menu-button" href="#"  @click="vuexLogout">Logout</a>
        </div>
      </div>
    </li>

  </ul>
</template>
<script>
import AXIOS from '../../config/http-commons';

export default {
  methods: {
    vuexLogout() {
      AXIOS.get().then(() => {
        this.$store.dispatch('logout')
          .then(() => {
            this.$router.push({ name: 'Home' });
          });
      });
    },
  },
  computed: {
    vuexIsLoggedIn() {
      return this.$store.getters.isLoggedIn;
    },
    vuexUser() {
      const userdata = this.$store.getters.userdata;
      return userdata.username ? userdata.username : null;
    },
  },
};
</script>
