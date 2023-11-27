<template>
  <div>
    <h4 class="hotels">Hotels List</h4>
    <button class="add-hotel-button" @click="showAddHotelForm">Add Hotel</button>

    <div v-if="isAddHotelFormVisible" class="add-hotel-form-container">
      <div class="add-hotel-form">
        <h3>Add a New Hotel</h3>
        <form @submit="addHotel">
          <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" v-model="newHotel.name" required>
          </div>

          <div class="form-group">
            <label for="location">Location:</label>
            <input type="text" id="location" v-model="newHotel.location" required>
          </div>

          <div class="form-group">
            <label for="star">Star:</label>
            <input type="number" id="star" v-model="newHotel.star" required>
          </div>

          <div class="button-group">
            <button type="submit" class="submit-button">Submit</button>
            <button type="button" @click="hideAddHotelForm" class="cancel-button">Cancel</button>
          </div>
        </form>
      </div>
    </div>

  

    <div class="hotel-cards-container">
      <div class="hotel-cards">
        <div class="hotel-card" v-for="hotel in hotels" :key="hotel.hotelId">
          <div class="hotel-card-inner">
            <h3>{{ hotel.name }}</h3>
            <p>Location: {{ hotel.location }}</p>
            <p>Star: {{ hotel.star }}</p>
          </div>
          <button class="view-details-button" @click="navigateToHotelDetails(hotel.hotelId)">View Hotel</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const hotels = ref([]);
const isAddHotelFormVisible = ref(false);

const newHotel = ref({
  name: '',
  location: '',
  star: 0
});

onMounted(() => {
  // Fetch hotel data from JSON Server
  fetch("http://microservice-alb-1225467990.ap-south-1.elb.amazonaws.com/hotels")
    .then((response) => response.json())
    .then((data) => {
      hotels.value = data;
    });
});

function navigateToHotelDetails(hotelId) {
  router.push(`/hotels/${hotelId}`);
}

function showAddHotelForm() {
  isAddHotelFormVisible.value = true;
}

function hideAddHotelForm() {
  isAddHotelFormVisible.value = false;
}

function addHotel() {
  // Perform any necessary validation here
  if (newHotel.value.name && newHotel.value.location && newHotel.value.star) {
    // Send a POST request to your API to add the new hotel
    fetch("http://microservice-alb-1225467990.ap-south-1.elb.amazonaws.com/hotels", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(newHotel.value)
    })
      .then((response) => response.json())
      .then((data) => {
        // Add the newly created hotel to the list
        hotels.value.push(data);
        newHotel.value = {
          name: '',
          location: '',
          star: 0
        };
        hideAddHotelForm();
      });
  }
}
</script>

<style scoped>
.hotel-cards-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.hotel-cards {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.hotel-card {
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 300px;
}

.hotel-card h3 {
  font-size: 20px;
  margin: 0;
}

.hotel-card p {
  margin: 10px 0;
}

.hotels {
  text-align: center;
  margin: 20px;
  margin-bottom: 40px;
}

.view-details-button {
  background-color: #007bff; /* Blue color for the button */
  color: #fff; /* White text color */
  padding: 5px; /* Adjust padding for the button */
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease; /* Add hover effect */
  margin: auto;
}

.view-details-button:hover {
  background-color: #0056b3; /* Darker blue color on hover */
}
.add-hotel-form-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent background overlay */
  z-index: 999; /* Ensure it's above other content */
}

.add-hotel-form {
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  width: 300px;
}

.add-hotel-form h3 {
  font-size: 20px;
  margin: 0 0 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.button-group {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.submit-button {
  background-color: #007bff;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.cancel-button {
  background-color: #ccc;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.submit-button:hover {
  background-color: #0056b3;
}

.cancel-button:hover {
  background-color: #999;
}
</style>
