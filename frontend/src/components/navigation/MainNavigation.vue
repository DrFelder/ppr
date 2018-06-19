<template>
  <ul class="top-menu">
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
      <a v-if="vuexNotifications.length === 0" class="notification-button-inactive"
         @click="showModal">
        bell
      </a>
      <a v-else class="notification-button-active" @click="showModal">bell</a>
      <notificationPopup v-show="isModalVisible" @close="closeModal"/>
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
import notificationPopup from '@/components/pages/popup/Notification';
import AXIOS from '../../config/http-commons';

export default {
  components: {
    notificationPopup,
  },
  data() {
    return {
      isModalVisible: false,
    };
  },
  methods: {
    vuexLogout() {
      AXIOS.get().then(() => {
        this.$store.dispatch('logout')
          .then(() => {
            this.$router.push({ name: 'Home' });
          });
      });
    },
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
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
    vuexNotifications() {
      return this.$store.getters.notifications;
    },
  },
};
</script>
