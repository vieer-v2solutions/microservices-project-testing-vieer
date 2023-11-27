import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import ListHotels from '@/components/hotels/ListHotels'
import Hotel from '@/components/hotels/Hotel'
import SearchHotel from '@/components/search/SearchHotel'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/listhotels',
      name: 'ListHotels',
      component: ListHotels
    },
    {
      path: '/hotels/:id',
      name: 'Hotels',
      component: Hotel
    },
    {
      path: '/search',
      name: 'SearchHotel',
      component: SearchHotel
    },
  ]

})
