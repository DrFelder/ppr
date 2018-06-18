<template>
  <div>
    <h2>Detail page for {{ response.title }}</h2>
    <router-view></router-view>
    <tbody>
    <tr v-if="currentprivilege === 'ORGANIZER' ">
      <th>Action</th>
      <td>
        <router-link :to="{ name: 'EditOperation',
        query: { operation_id: response.id, operation_title: response.title,
        operation_date: response.date, operation_publicdescription: response.publicdescription,
        operation_privatedescription: response.privatedescription,
        operation_location: response.location}}" tag="button">
          Edit
        </router-link>

        <router-link v-if="response.startDate == null" :to="{ name: 'StartOperation',
        query: { operation_id: response.id, operation_title: response.title,
        operation_date: response.date, operation_publicdescription: response.publicdescription,
        operation_privatedescription: response.privatedescription,
        operation_location: response.location,
        operation_startdate: response.startDate}}" tag="button">
          Start
        </router-link>
        <span v-else>
          Operation started: {{response.startDate}}
        </span>
      </td>
    </tr>
    <tr>
      <th>Title</th>
      <td>{{ response.title }}</td>
    </tr>
    <tr>
      <th>Date</th>
      <td>{{ response.date }}</td>
    </tr>
    <tr>
      <th>Location</th>
      <td>{{ response.location }}</td>
    </tr>
    <tr>
      <th>Organizer</th>
      <td>
        <router-link :to="{ name: 'SingleUser',
        params: { username: response.userByOrganizerId.username }}">
          {{ response.userByOrganizerId.username }}
        </router-link>
      </td>
    </tr>
    <tr>
      <th>Public description</th>
      <td>{{ response.publicdescription }}</td>
    </tr>
    <tr  v-if="currentprivilege === 'ORGANIZER' || currentprivilege === 'PARTICIPATOR'">
      <th>Private Description</th>
      <td>{{ response.privatedescription }}</td>
    </tr>
    </tbody>
    <h2>Equipment</h2>
    <router-link :to="{ name: 'AddEquipment' }" tag="button">Add  new equipment</router-link>
    <tbody>
    <tr>
      <th>
        Title
      </th>
      <th>
        Description
      </th>
      <th>
        Apply
      </th>
      <th v-if="currentprivilege === 'ORGANIZER'">
        Edit
      </th>
    </tr>
    <!-- eslint-disable-next-line -->
    <tr v-for="equipment in response.equipmentById">
      <td>
        {{ equipment.title }}
      </td>
      <td>
        {{ equipment.description }}
      </td>
      <td>
        <span v-if="!!equipment.participation && equipment.participation.accepted">
          Accepted
        </span>
        <span v-else-if="!!equipment.participation && equipment.participation.declined">
          Declined
        </span>
        <span v-else-if="!!equipment.participation">
          Applied
        </span>
        <router-link v-else :to="{ name: 'EquipmentApplication',
        query: { equipment_id: equipment.id }}">
          Apply
        </router-link>
      </td>
      <td v-if="currentprivilege==='ORGANIZER'">
        <router-link :to="{ name: 'EditEquipment',
        query: { equipment_id: equipment.id, equipment_title: equipment.title,
        equipment_description: equipment.description }}">
          Edit
        </router-link>
      </td>
    </tr>
    </tbody>
    <h2>Helpers</h2>
    <router-link :to="{ name: 'AddHelper' }" tag="button">Add new helper</router-link>
    <tbody>
    <tr>
      <th>
        Title
      </th>
      <th>
        Description
      </th>
      <th>
        Apply
      </th>
      <th v-if="currentprivilege === 'ORGANIZER'">
        Edit
      </th>
    </tr>
    <!-- eslint-disable-next-line -->
    <tr v-for="helper in response.helpersById">
      <td>
        {{ helper.title }}
      </td>
      <td>
        {{ helper.description }}
      </td>
      <td>
        <span v-if="!!helper.participation && helper.participation.accepted">
          Accepted
        </span>
        <span v-else-if="!!helper.participation && helper.participation.declined">
          Declined
        </span>
        <span v-else-if="!!helper.participation">
          Applied
        </span>
        <router-link v-else :to="{ name: 'HelperApplication', query: { helper_id: helper.id }}">
          Apply
        </router-link>
      </td>
      <td v-if="currentprivilege==='ORGANIZER'">
        <router-link :to="{ name: 'EditHelper',
        query: { helper_id: helper.id, helper_title: helper.title,
        helper_description: helper.description }}">
          Edit
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
      currentprivilege: '',
      response: [],
      operationparticipation: {},
      errors: [],
      loading: false,
    };
  },

  created() {
    this.loading = true;
    AXIOS.get(`http://localhost:8080/rest/currentprivileges/${this.$route.params.id}`, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
      .then((response) => {
        // eslint-disable-next-line
        console.log(response.data)
        this.$store.dispatch('setPrivilege', response.data).then(() => {
          this.currentprivilege = this.$store.getters.privilege;
        });
      })
      .catch((err) => {
        this.currentprivilege = JSON.stringify(err);
      });
    AXIOS.get(`http://localhost:8080/rest/operation/${this.$route.params.id}`, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
      .then((operationResponse) => {
        AXIOS.get(`http://localhost:8080/rest/operationparticipation/equipment/${this.$route.params.id}`, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
          .then((operationparticipationResponse) => {
            operationparticipationResponse.data.forEach((operationparticipation) => {
              operationResponse.data.equipmentById.forEach((equipment) => {
                if (equipment.id === operationparticipation[1]) {
                  // eslint-disable-next-line no-param-reassign
                  equipment.participation = {
                    user_id: operationparticipation[0],
                    equipment_id: operationparticipation[1],
                    accepted: operationparticipation[2],
                    declined: operationparticipation[3],
                    question: operationparticipation[4],
                    answer: operationparticipation[5],
                  };
                }
              });
            });
          }).then(() => {
            AXIOS.get(`http://localhost:8080/rest/operationparticipation/helper/${this.$route.params.id}`, { headers: { Authorization: `Bearer ${this.$store.getters.accessToken}` } })
              .then((operationparticipationResponse) => {
                operationparticipationResponse.data.forEach((operationparticipation) => {
                  operationResponse.data.helpersById.forEach((helper) => {
                    if (helper.id === operationparticipation[1]) {
                      // eslint-disable-next-line no-param-reassign
                      helper.participation = {
                        user_id: operationparticipation[0],
                        equipment_id: operationparticipation[1],
                        accepted: operationparticipation[2],
                        declined: operationparticipation[3],
                        question: operationparticipation[4],
                        answer: operationparticipation[5],
                      };
                    }
                  });
                });
                this.response = operationResponse.data;
                this.loading = false;
              })
              .catch(() => {
                this.loading = false;
              });
          })
          .catch(() => {
            this.loading = false;
          });
      })
      .catch(() => {
        this.loading = false;
      });
  },
};
</script>
