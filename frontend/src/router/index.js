/*
 * Project Puerto Rico
 * Copyright (C) 2018  Thomas Pötzsch, Stephan Stroh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import Vue from 'vue';
import Router from 'vue-router';
import UserList from '@/components/pages/user/UserList';
import SingleUser from '@/components/pages/user/SingleUser';
import OperationList from '@/components/pages/operation/OperationList';
import SingleOperation from '@/components/pages/operation/SingleOperation';
import Login from '@/components/pages/login/Login';
import Registration from '@/components/pages/login/Registration';
import AddEquipment from '@/components/pages/requirements/AddEquipment';
import AddHelper from '@/components/pages/requirements/AddHelper';
import EditEquipment from '@/components/pages/requirements/EditEquipment';
import EditHelper from '@/components/pages/requirements/EditHelper';
import HelperApplication from '@/components/pages/requirements/HelperApplication';
import EquipmentApplication from '@/components/pages/requirements/EquipmentApplication';
import SingleOperationDeletion from '@/components/pages/operation/SingleOperationDeletion';
import OperationCreation from '@/components/pages/operation/OperationCreation';
import Home from '@/components/pages/Home';
import Layout from '@/components/layouts/MainLayout';
import EditOperation from '@/components/pages/operation/EditOperation';
import StartOperation from '@/components/pages/operation/StartOperation';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'layout',
      component: Layout,
      children: [
        {
          path: '/login',
          name: 'Login',
          component: Login,
          meta: { noAuth: true, title: 'PPR - Login' },
        },
        {
          path: '/register',
          name: 'Registration',
          component: Registration,
          meta: { noAuth: true, title: 'PPR - Registration' },
        },
        {
          path: '/home',
          name: 'Home',
          component: Home,
          meta: { noAuth: true, title: 'PPR - Home' },
        },
        {
          path: '/user',
          name: 'UserList',
          component: UserList,
          meta: { title: 'PPR - User List' },
        },
        {
          path: '/user/:username',
          name: 'SingleUser',
          component: SingleUser,
          meta: { title: 'PPR - User Details' },
        },
        {
          path: '/operation',
          name: 'OperationList',
          component: OperationList,
          meta: { title: 'PPR - Operation List' },
          children: [
            {
              path: 'create',
              name: 'OperationCreation',
              component: OperationCreation,
              meta: { title: 'PPR - Operation Creation' },
            },
          ],
        },
        {
          path: '/operation/:id',
          name: 'SingleOperation',
          component: SingleOperation,
          meta: { title: 'PPR - Operation Details' },
          children: [
            {
              path: 'delete',
              name: 'SingleOperationDeletion',
              component: SingleOperationDeletion,
              meta: { title: 'PPR - Delete Operation' },
            },
            {
              path: 'helperapplication',
              name: 'HelperApplication',
              component: HelperApplication,
              meta: { title: 'PPR - Apply for Helper role' },
            },
            {
              path: 'equipmentapplication',
              name: 'EquipmentApplication',
              component: EquipmentApplication,
              meta: { title: 'PPR - Apply for Equipment' },
            },
            {
              path: 'addequipment',
              name: 'AddEquipment',
              component: AddEquipment,
              meta: { title: 'PPR - Add Equipment' },
            },
            {
              path: 'addhelper',
              name: 'AddHelper',
              component: AddHelper,
              meta: { title: 'PPR - Add Helper' },
            },
            {
              path: 'editequipment',
              name: 'EditEquipment',
              component: EditEquipment,
              meta: { title: 'PPR - Edit Equipment' },
            },
            {
              path: 'edithelper',
              name: 'EditHelper',
              component: EditHelper,
              meta: { title: 'PPR - Edit Helper' },
            },
            {
              path: 'editoperation',
              name: 'EditOperation',
              component: EditOperation,
              meta: { title: 'PPR - Edit Operation' },
            },
            {
              path: 'startoperation',
              name: 'StartOperation',
              component: StartOperation,
              meta: { title: 'PPR - Start Operation' },
            },
          ],
        },
      ],
    },
  ],
});
