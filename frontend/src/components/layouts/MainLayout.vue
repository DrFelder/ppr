<template>
  <div id="app-layout">
    <Navigation></Navigation>
    <router-view></router-view>
  </div>
</template>
<script>
import Navigation from '../navigation/MainNavigation';
import AXIOS from '../../config/http-commons';

export default {
  data() {
    return {
      notifications: [],
    };
  },
  components: {
    Navigation,
  },
  methods: {
    loadData() {
      AXIOS.get('http://localhost:8080/rest/notification/', { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
        .then((notifications) => {
          this.$store.dispatch('setNotifications', notifications.data);
        });
    },
  },
  mounted() {
    if (this.$store.getters.isLoggedIn) {
      this.loadData();
    }

    setInterval(() => {
      if (this.$store.getters.isLoggedIn) {
        this.loadData();
      }
    }, 10000);
  },
};
</script>
