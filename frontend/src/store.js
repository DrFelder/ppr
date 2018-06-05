import * as Vuex from 'vuex';
import Vue from 'vue';

const LOGIN = 'LOGIN';
const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
const LOGOUT = 'LOGOUT';

Vue.use(Vuex);

// eslint-disable-next-line
export default new Vuex.Store({
  state: {
    isLoggedIn: !!localStorage.getItem('userdata'),
    userdata: JSON.parse(localStorage.getItem('userdata')),
    credentials: JSON.parse(localStorage.getItem('credentials')),
    pending: false,
    client: 'testjwtclientid',
    secret: 'XY7kmzoNzl100',
  },
  mutations: {
    [LOGIN](state, data) {
      state.pending = true;
      state.userdata = data.userdata;
      state.credentials = data.credentials;
    },
    [LOGIN_SUCCESS](state) {
      state.isLoggedIn = true;
      state.pending = false;
    },
    [LOGOUT](state) {
      state.isLoggedIn = false;
      state.userdata = null;
    },
  },
  actions: {
    login({ commit }, data) {
      commit(LOGIN, data); // show spinner
      return new Promise((resolve) => {
        setTimeout(() => {
          localStorage.setItem('userdata', JSON.stringify(data.userdata));
          localStorage.setItem('credentials', JSON.stringify(data.credentials));
          commit(LOGIN_SUCCESS);
          resolve();
        }, 1000);
      });
    },
    logout({ commit }) {
      localStorage.removeItem('userdata');
      localStorage.removeItem('credentials');
      commit(LOGOUT);
    },

  },
  getters: {
    isLoggedIn: state => state.isLoggedIn,

    credentials: state => state.credentials,
    userdata: state => state.userdata,

    client: state => state.client,
    secret: state => state.secret,

    accessToken: state => state.credentials.access_token,
    tokentype: state => state.credentials.token_type,
    expiresIn: state => state.credentials.expires_in,
    scope: state => state.credentials.scope,
    jti: state => state.credentials.jti,
  },
});
