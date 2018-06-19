// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import store from './store';
import './assets/css/fontawesome.min.css';

require('./assets/css/main.css');

Vue.config.productionTip = false;

// eslint-disable-next-line func-names
const filter = function (text, length, clamp) {
  const compClamp = clamp || '...';
  const node = document.createElement('div');
  node.innerHTML = text;
  const content = node.textContent;
  return content.length > length ? content.slice(0, length) + compClamp : content;
};

Vue.filter('truncate', filter);

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
