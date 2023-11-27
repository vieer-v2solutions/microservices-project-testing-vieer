<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="500px">

      <v-card>
        <v-card-title>
          <span class="headline">{{ formTitle }}</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-flex xs12 sm6 md4>
                <v-text-field label="Hotel Name" v-model="item.name"></v-text-field>
              </v-flex>
              <v-flex xs12 sm6 md4>
                <v-text-field label="Location" v-model="item.location"></v-text-field>
              </v-flex>

              <v-flex xs12 sm6 md4>
                <v-btn>Reserve</v-btn>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click.native="close">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-data-table :headers="headers" :items="items" hide-actions class="elevation-1">
      <template slot="items" slot-scope="props">
          <td>{{ props.item.name }}</td>
          <td class="text-xs-right">{{ props.item.location }}</td>

          <td class="justify-center layout px-0">
            <v-btn icon class="mx-0" @click="editItem(props.item)">
              <v-icon color="teal">edit</v-icon>
            </v-btn>
          </td>
</template>

    </v-data-table>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'ListHotels',
    data: () => ({
      dialog: false,
      headers: [{
          text: 'Name',
          align: 'left',
          sortable: false,
          value: 'name'
        },
        {
          text: 'Location',
          value: 'calories'
        },


        {
          text: 'Actions',
          value: 'name',
          sortable: false
        }
      ],
      items: [{
          "id": "1",
          "name": "Le Meurice",
          "isModified": false,
          "rooms": null,
          "location": "Paris"
        },
        {
          "id": "2",
          "name": "L'Ambroisie",
          "isModified": false,
          "rooms": null,
          "location": "Helsinki"
        },
        {
          "id": "3",
          "name": "Arpège",
          "isModified": false,
          "rooms": null,
          "location": "Praha"
        },
        {
          "id": "4",
          "name": "Alain Ducasse au Plaza Athénée",
          "isModified": false,
          "rooms": null,
          "location": "Zurich"
        },
        {
          "id": "5",
          "name": "Pavillon LeDoyen",
          "isModified": false,
          "rooms": null,
          "location": "Berlin"
        },
        {
          "id": "6",
          "name": "Pierre Gagnaire",
          "isModified": false,
          "rooms": null,
          "location": "Frankfurt"
        },
        {
          "id": "7",
          "name": "Guy Savoy",
          "isModified": false,
          "rooms": null,
          "location": "NewYork"
        },
        {
          "id": "8",
          "name": "Pré Catelan",
          "isModified": false,
          "rooms": null,
          "location": "Chicago"
        },
        {
          "id": "9",
          "name": "L'Astrance",
          "isModified": false,
          "rooms": null,
          "location": "HoChiMinh"
        },
        {
          "id": "10",
          "name": "Le Bristol",
          "isModified": false,
          "rooms": null,
          "location": "California"
        }
      ],
      item: {
        edit: false,
        name: '',
        calories: 0,
        fat: 0,
        carbs: 0,
        protein: 0
      }
    }),

    computed: {
      formTitle() {
        return "List of Hotels in our system "
      }
    },

    created() {
      // axios.get(`http://localhost:8801/v1/hotels/all`)
      //   .then(response => {

      //     this.items = response.data
      //     console.log(this.items)
      //   })
      //   .catch(e => {
      //     console.log(e)
      //   })
    },

    methods: {


      editItem(item) {
        this.item = item
        this.item.edit = true
        this.dialog = true
      },

      deleteItem(index) {
        // alert user for delete
        this.items.splice(index, 1)
      },

      close() {
        this.item.edit = false
        this.dialog = false
      },


    },


    mounted() {

    },
  }
</script>

<style scoped>

</style>
