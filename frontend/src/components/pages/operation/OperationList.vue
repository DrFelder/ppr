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
  <div>
    <h2>Operation list</h2>
    <router-view></router-view>
    <tbody>
    <tr>
      <th>Title</th>
      <th>Date</th>
      <th>Public description</th>
      <th>Location</th>
      <th>More</th>
    </tr>
    <!-- eslint-disable-next-line -->
    <tr v-for="operation in response">
      <td>{{ operation.title }}</td>
      <td>{{ operation.date }}</td>
      <td>{{ operation.publicdescription | truncate(300, '...') }}</td>
      <td>{{ operation.location }}</td>
      <td>
        <router-link :to="{ name: 'SingleOperation', params: { id: operation.id }} " tag="button">
          details
        </router-link>
      </td>
    </tr>
    </tbody>
  </div>
</template>

<script>
import AXIOS from '../../../config/http-commons';

export default {
  data() {
    return {
      response: [],
      errors: [],
    };
  },
  created() {
    this.loading = true;
    AXIOS.get('http://localhost:8080/rest/operation/', { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
      .then((response) => {
        this.response = response.data;
        this.loading = false;
      })
      .catch(() => {
        this.loading = false;
      });
  },
};
</script>
