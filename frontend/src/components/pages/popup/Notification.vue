<!--
  - Project Puerto Rico
  - Copyright (C) 2018  Thomas Pötzsch, Stephan Stroh
  -
  - This program is free software: you can redistribute it and/or modify
  - it under the terms of the GNU General Public License as published by
  - the Free Software Foundation, either version 3 of the License, or
  - (at your option) any later version.
  -
  - This program is distributed in the hope that it will be useful,
  - but WITHOUT ANY WARRANTY; without even the implied warranty of
  - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  - GNU General Public License for more details.
  -
  - You should have received a copy of the GNU General Public License
  - along with this program.  If not, see <http://www.gnu.org/licenses/>.
  -->

<template>
  <transition name="modal-fade">
    <div class="modal-backdrop">
      <div class="modal"
           role="dialog">
        <header class="modal-header" id="modalTitle">
          <slot name="header">
            <span v-if="vuexNotifications.length !== 0">
              You received new notifications:
            </span>
            <span v-else>There are no new notifications.</span>
          </slot>
        </header>
        <section class="modal-body" id="modalDescription">
          <slot name="body">
            <ul v-if="vuexNotifications.length !== 0" class="modal-notification-list">
              <!-- eslint-disable-next-line -->
              <li class="modal-notification-item" v-for="notification in vuexNotifications">
                <div @click="close(); markAsRead(notification.id)">
                  <router-link :to="{ name: 'SingleOperation',
                  params: { id: notification.operationId }} ">
                    An operation you are participating in has started:<br/>
                    {{ notification.message }}
                  </router-link>
                </div>
              </li>
            </ul>
            <a v-if="vuexNotifications.length !== 0" class="modal-button-close"
               @click="markAllAsRead(vuexNotifications)">Mark all notifications as read</a>
            <a class="modal-button-close" @click="close">Close</a>
          </slot>
        </section>
      </div>
    </div>
  </transition>
</template>
<script>
import AXIOS from '../../../config/http-commons';

export default {
  name: 'modal',
  methods: {
    close() {
      this.$emit('close');
    },
    markAllAsRead(notifications) {
      notifications.map((notification) => {
        const notificationread = {};
        notificationread.messageId = notification.id;
        AXIOS.post('http://localhost:8080/rest/read/', notificationread, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } });
        return null;
      });
      this.$store.dispatch('removeNotifications');
    },
    markAsRead(messageId) {
      const notificationread = {};
      notificationread.messageId = messageId;
      AXIOS.post('http://localhost:8080/rest/read/', notificationread, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } }).then(() => {
        this.$store.dispatch('removeSingleNotification', messageId);
      });
    },
    removeNotifications() {
      this.$store.dispatch('removeNotifications');
    },
  },
  computed: {
    vuexNotifications() {
      return this.$store.getters.notifications;
    },
  },
};
</script>
