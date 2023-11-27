import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HotelsList from '../views/HotelsList.vue';
import RegisteredUsers from '../views/RegisteredUsers.vue';
import HomePage from '../views/HomePage.vue';
import ContactUs from '../views/ContactUs.vue';
import AboutUs from '../views/AboutUs.vue';
import UserDetails from '../views/UserDetails.vue'; // Import the UserDetails component
import HotelDetails from '@/views/HotelDetails.vue';

const routes: Array<RouteRecordRaw> = [

  {
    path: '/', 
    name: 'home',
    component: HomePage
  },
  {
    path: '/contact', 
    name: 'contact',
    component: ContactUs
  },
  {
    path: '/about', 
    name: 'about',
    component: AboutUs
  },
  {
    path: '/users', 
    name: 'users',
    component: RegisteredUsers
  },
  {
    path: '/hotels', 
    name: 'hotels',
    component: HotelsList
  },
  {
    path: '/users/:userId', // Dynamic route parameter
    name: 'user-details', // Name for the route
    component: UserDetails // Use the UserDetails component for user details
  },
  {
    path: '/hotels/:hotelId', // Dynamic route parameter
    name: 'hotel-details', // Name for the route
    component: HotelDetails // Use the UserDetails component for user details
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
