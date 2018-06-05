<template>
  <div id="app-layout">
    <Navigation></Navigation>
    <router-view></router-view>
  </div>
</template>
<script>
import Navigation from '../navigation/MainNavigation';

export default {
  components: {
    Navigation,
  },
};
</script>
