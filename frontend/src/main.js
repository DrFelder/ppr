// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import store from './store';

require('./assets/css/main.css');

Vue.config.productionTip = false;

router.beforeEach((to, from, next) => {
// eslint-disable-next-line no-console
  const noAuthRequired = to.matched.some(route => route.meta.noAuth);
  const authed = !!store.state.userdata;
  if (!noAuthRequired && !authed) {
    next('/login');
  } else {
    next();
  }
});

router.beforeEach((to, from, next) => {
  document.title = to.meta.title;
  next();
});

/* eslint-disable no-new */
new Vue({
  store,
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
});