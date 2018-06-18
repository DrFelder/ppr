import * as Vuex from 'vuex';
import Vue from 'vue';

const LOGIN = 'LOGIN';
const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
const LOGOUT = 'LOGOUT';
const SET_PRIVILEGE = 'SET_PRIVILEGE';
const REMOVE_PRIVILEGE = 'REMOVE_PRIVILEGE';
const SET_NOTIFICATIONS = 'SET_NOTIFICATIONS';
const CLEAR_NOTIFICATIONS = 'CLEAR_NOTIFICATIONS';
const CLEAR_SINGLE_NOTIFICATION = 'CLEAR_SINGLE_NOTIFICATION';

Vue.use(Vuex);

// eslint-disable-next-line
export default new Vuex.Store({
  state: {
    currentPrivileges: 'asdf',
    isLoggedIn: !!localStorage.getItem('userdata'),
    userdata: JSON.parse(localStorage.getItem('userdata')),
    credentials: JSON.parse(localStorage.getItem('credentials')),
    pending: false,
    client: 'testjwtclientid',
    secret: 'XY7kmzoNzl100',
    notifications: [],
  },
  mutations: {
    [SET_PRIVILEGE](state, privilege) {
      state.currentPrivileges = privilege;
    },
    [REMOVE_PRIVILEGE](state) {
      state.currentPrivileges = '';
    },
    [SET_NOTIFICATIONS](state, notifications) {
      state.notifications = notifications;
    },
    [CLEAR_NOTIFICATIONS](state) {
      state.notifications = [];
    },
    [CLEAR_SINGLE_NOTIFICATION](state, notificationId) {
      const index = state.notifications
        .map(notification => notification.id).indexOf(notificationId);
      state.notifications.splice(index, 1);
    },
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
    setPrivilege({ commit }, privilege) {
      commit(SET_PRIVILEGE, privilege);
    },
    removePrivilege({ commit }) {
      commit(REMOVE_PRIVILEGE);
    },
    setNotifications({ commit }, notifications) {
      commit(SET_NOTIFICATIONS, notifications);
    },
    removeNotifications({ commit }) {
      commit(CLEAR_NOTIFICATIONS);
    },
    removeSingleNotification({ commit }, notificationId) {
      commit(CLEAR_SINGLE_NOTIFICATION, notificationId);
    },
  },
  getters: {
    privilege: state => state.currentPrivileges,

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

    notifications: state => state.notifications,
  },
});
