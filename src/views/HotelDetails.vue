<template>
  <div class="hotel-details">
    <h4>Hotel Details</h4>
    <hr />

    <!-- Display hotel info if available -->
    <div v-if="hotel">
      <div class="hotel-card">
        <div class="user-columns">
          <div class="hotel-info">
            <div class="card">
              <img
                src="https://png.pngtree.com/png-clipart/20190705/original/pngtree-hotel-icon-for-personal-and-commercial-use-png-image_4341927.jpg"
                class="card-img-top dp"
                alt="Hotel Image"
              />
              <hr />
              <div class="card-body">
                <!-- Check if 'name' property is defined -->
                <h5>Hotel Name: {{ hotel.name || "N/A" }}</h5>
                <!-- Check if 'location' property is defined -->
                <p>Location: {{ hotel.location || "N/A" }}</p>
                <!-- Check if 'star' property is defined -->
                <p>Star: {{ hotel.star || "N/A" }}</p>
                
                <!-- Display the user's name if available -->
                <p v-if="user">{{ user.name }}</p>
              </div>
            </div>
          </div>

          <!-- Display hotel ratings if available -->
          <div class="ratings">
            <h6>Ratings given by users</h6>
            <div v-if="hotelRatings.length > 0">
              <div
                class="rating-item"
                v-for="rating in hotelRatings"
                :key="rating.ratingId"
              >
                <div class="rating-content">
                  <!-- Check if 'userId' property is defined in rating -->
                  <h6>
                    Username: {{ getUserById(rating.userId) || "N/A" }}
                  </h6>
                  <!-- Check if 'rating' property is defined -->
                  <p>Rating: {{ rating.rating || "N/A" }}</p>
                  <!-- Check if 'feedback' property is defined -->
                  <p>Feedback: {{ rating.feedback || "N/A" }}</p>
                </div>
              </div>
            </div>
            <div v-else>
              <p>No ratings available.</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Display an error message if data cannot be fetched -->
    <div v-else>
      <p>Failed to fetch hotel data.</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const hotelId = route.params.hotelId;
const hotel = ref(null);
const hotelRatings = ref([]);
const users = ref([]);

const fetchHotelData = async () => {
  try {
    const response = await fetch(
      `http://microservice-alb-1225467990.ap-south-1.elb.amazonaws.com/hotels/${hotelId}`
    );
    if (!response.ok) {
      throw new Error("Failed to fetch hotel data.");
    }
    const data = await response.json();
    hotel.value = data;
    console.log("Fetched hotel data:", data);
  } catch (error) {
    console.error("Error fetching hotel data:", error);
  }
};

const fetchHotelRatings = async () => {
  try {
    const response = await fetch(
      `http://microservice-alb-1225467990.ap-south-1.elb.amazonaws.com/ratings/hotels/${hotelId}`
    );
    if (!response.ok) {
      throw new Error("Failed to fetch hotel ratings.");
    }
    const data = await response.json();
    hotelRatings.value = data;
    console.log("Fetched hotel ratings:", data);
  } catch (error) {
    console.error("Error fetching hotel ratings:", error);
  }
};

const fetchUserData = async () => {
  try {
    const response = await fetch(
      `http://microservice-alb-1225467990.ap-south-1.elb.amazonaws.com/users`
    );
    if (!response.ok) {
      throw new Error("Failed to fetch user data.");
    }
    const data = await response.json();
    users.value = data;
    console.log("Fetched user data:", data);
  } catch (error) {
    console.error("Error fetching user data:", error);
  }
};

const getUserById = (userId) => {
  const user = users.value.find((u) => u.userId === userId);
  return user ? user.name : "N/A";
};

onMounted(() => {
  fetchHotelData();
  fetchHotelRatings();
  fetchUserData();
});
</script>

<style scoped>
.user-details {
  text-align: center;
  padding: 20px;
}

.hotel-card {
  background-color: #f5f5f5;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 10px;
  margin: 0 auto;
  text-align: left;
  margin: 20px;
}
/*  */
.user-columns {
  display: flex; /* Use flexbox for column layout */
}

.hotel-info {
  flex: 15%; /* Left column takes up 40% width */
  margin-right: 20px; /* Add some spacing between columns */
}

.ratings {
  flex: 60%; /* Right column takes up 60% width */
  border: 1px solid #9e9b9b;
  border-radius: 5px;
  padding: 20px;
}

.rating-item {
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 10px;
}

.rating-content {
  text-align: left;
  padding: 4px; /* Adjust padding as needed */
}

.dp {
  height: 200px;
  width: 200px;
  margin: auto;
}
</style>
