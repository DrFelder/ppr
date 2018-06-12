<template>
  <section class="hero is-success is-fullheight">
    <div class="hero-body">
      <div class="container has-text-centered">
        <div class="column is-4 is-offset-1">
          <h3 class="title has-text-grey">Login</h3>
            <div class="box">
              <p v-if="errors.length">
                <b>Please correct the following error(s):</b>
                <ul class="error-list">
                  <!-- eslint-disable-next-line -->
                  <li class="error-list-item" v-for="error in errors">{{ error }}</li>
                </ul>
              </p>
            <form>
              <div class="field">
                <div class="control">
                  <input class="input is-large" id="username_login" v-model="login.username" placeholder="Your Username">
                </div>
              </div>
              <div class="field">
                <div class="control">
                  <input class="input is-large" id="password_login" type="password" placeholder="Your Password" v-model="login.password" @keyup.enter="checkForm">
                </div>
              </div>
              <div>
                <button class="button is-block is-info is-large is-fullwidth" id="submit_login" v-on:click="checkForm">Login</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import AXIOS from '../../../config/http-commons';

export default {
  data() {
    return {
      errors: [],
      login: {
        username: null,
        password: null,
      },
    };
  },
  computed: {
    clientAndSecret() {
      return `${this.$store.getters.client}:${this.$store.getters.secret}`;
    },
  },
  // TODO: line 52: computed value should be used
  methods: {
    checkForm(e) {
      if (this.login.username && this.login.password) {
        const bodyFormData = new FormData();
        bodyFormData.set('grant_type', 'password');
        bodyFormData.set('username', this.login.username);
        bodyFormData.set('password', this.login.password);
        AXIOS({
          method: 'post',
          url: 'http://localhost:8080/oauth/token',
          auth: {
            username: this.$store.getters.client,
            password: this.$store.getters.secret,
          },
          data: bodyFormData,
          config: { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } },
        })
          .then((token) => {
            AXIOS.get('http://localhost:8080/rest/user/current', { headers: { Authorization: `Bearer ${token.data.access_token}` } })
              .then((response) => {
                this.$store.dispatch('login', { userdata: response.data, credentials: token.data })
                  .then(() => {
                    this.$router.push({ name: 'Home' });
                  });
              });
          });
        return true;
      }
      this.errors = [];
      if (!this.login.username) this.errors.push('Username required.');
      if (!this.login.password) this.errors.push('Password required.');
      e.preventDefault();
      return false;
    },
  },
};
</script>
