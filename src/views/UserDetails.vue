<template>
  <div class="user-details">
    <h4>User Details</h4>
    <hr />
    <div class="user-card">
      <div class="user-columns">
        <!-- Left Column (40% width) -->
        <div class="user-info">
          <div class="card">
            <img
              src="https://www.transparentpng.com/thumb/user/gray-user-profile-icon-png-fP8Q1P.png"
              class="card-img-top dp"
              alt="User Avatar"
            />
            <hr />
            <div class="card-body">
              <p>Name: {{ user.name }}</p>
              <p>Email: {{ user.email }}</p>
            </div>
          </div>
          <div class="card rating-card">
            <h6>Leave a Rating for a Hotel</h6>
            <div class="rating-form">
              <button @click="showRatingForm" class="btn btn-primary">
                Add Rating
              </button>
              <!-- Modal -->
              <div
                class="modal"
                tabindex="-1"
                role="dialog"
                :class="{ show: showForm }"
              >
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title">Add Rating</h5>
                      <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-label="Close"
                        @click="closeForm"
                      >
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body">
                      <div class="form-group">
                        <label for="hotelDropdown">Select Hotel:</label>
                        <select
                          v-model="selectedHotel"
                          id="hotelDropdown"
                          class="form-control"
                        >
                          <option
                            v-for="hotel in hotels"
                            :value="hotel.hotelId"
                            :key="hotel.hotelId"
                          >
                            {{ hotel.name }}
                          </option>
                        </select>
                      </div>
                      <div class="form-group">
                        <label for="ratingDropdown">Rating:</label>
                        <select
                          v-model="selectedRating"
                          id="ratingDropdown"
                          class="form-control"
                        >
                          <option value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                          <option value="5">5</option>
                        </select>
                      </div>
                      <div class="form-group">
                        <label for="feedback">Feedback:</label>
                        <textarea
                          v-model="feedback"
                          id="feedback"
                          class="form-control"
                        ></textarea>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <button @click="submitRating" class="btn btn-primary">
                        Submit
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- End Modal -->
            </div>
          </div>
        </div>

        <!-- Right Column (60% width) -->
        <div class="ratings">
          <h6>Ratings Given by User</h6>
          <div class="rating-list">
            <div
              class="rating-item"
              v-for="rating in userRatings"
              :key="rating.ratingId"
            >
              <div class="rating-content">
                <p>Hotel: {{ getHotelName(rating.hotelId) }}</p>
                <p>Location: {{ getHotelLocation(rating.hotelId) }}</p>
                <p>Star: {{ getHotelStar(rating.hotelId) }}</p>
                <p>Rating: {{ rating.rating }}</p>
                <p>Feedback: {{ rating.feedback }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const userId = route.params.userId;
const user = ref({});
const userRatings = ref([]);
const hotels = ref([]);
const showForm = ref(false);
const selectedHotel = ref(null);
const selectedRating = ref(null);
const feedback = ref("");
let nextRatingId = null;

onMounted(() => {
  // Fetch user data
  fetch(`http://microservice-alb-1225467990.ap-south-1.elb.amazonaws.com/users/${userId}`)
    .then((response) => response.json())
    .then((data) => {
      user.value = data;
    })
    .catch((error) => {
      console.error("Error fetching user data:", error);
    });

  // Fetch hotels data
  fetch("http://microservice-alb-1225467990.ap-south-1.elb.amazonaws.com/hotels")
    .then((response) => response.json())
    .then((data) => {
      hotels.value = data;
    });

  // Fetch user ratings data
  fetch(`http://microservice-alb-1225467990.ap-south-1.elb.amazonaws.com/ratings/users/${userId}`)
    .then((response) => response.json())
    .then((data) => {
      userRatings.value = data;

      // Calculate the next available ratingId based on the length of userRatings
      const maxRatingId = Math.max(...userRatings.value.map((rating) => Number(rating.ratingId)));
      nextRatingId = maxRatingId + 1;
    })
    .catch((error) => {
      console.error("Error fetching user ratings:", error);
    });
});

function getHotelName(hotelId) {
  const hotel = hotels.value.find((h) => h.hotelId === hotelId);
  return hotel ? hotel.name : "";
}

function getHotelLocation(hotelId) {
  const hotel = hotels.value.find((h) => h.hotelId === hotelId);
  return hotel ? hotel.location : "";
}

function getHotelStar(hotelId) {
  const hotel = hotels.value.find((h) => h.hotelId === hotelId);
  return hotel ? hotel.star : "";
}

const showRatingForm = () => {
  showForm.value = true;
};
const closeForm = () => {
  showForm.value = false;
};

const submitRating = () => {
  if (!selectedHotel.value || !selectedRating.value || !feedback.value) {
    alert("Please fill in all fields.");
    return;
  }

  const newRating = {
    ratingId: nextRatingId, // Use the calculated nextRatingId
    userId: userId,
    hotelId: selectedHotel.value,
    rating: selectedRating.value,
    feedback: feedback.value,
  };

  // Make the API request to add the new rating
  fetch("http://microservice-alb-1225467990.ap-south-1.elb.amazonaws.com/ratings/addrating", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newRating),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log("Rating added successfully:", data);
      userRatings.value.push(data);
      selectedHotel.value = null;
      selectedRating.value = null;
      feedback.value = "";
      showForm.value = false;

      // Increment the nextRatingId for the next submission
      nextRatingId++;
    })
    .catch((error) => {
      console.error("Error adding rating:", error);
    });
};
</script>

<style scoped>
  /* Your scoped styles here */
</style>

<style scoped>
.user-details {
  text-align: center;
  padding: 20px;
}

.user-card {
  background-color: #f5f5f5;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
  text-align: left;
}

.user-columns {
  display: flex;
}

.user-info {
  flex: 40%;
  margin-right: 20px;
}

.ratings {
  flex: 60%;
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
  border: 1px solid #ddd;
  padding: 10px;
}

.dp {
  height: 200px;
  width: 200px;
  margin: auto;
}

.rating-form {
  margin-top: 20px;
}

.form-group {
  margin-bottom: 20px;
}

.form-control {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.btn-primary {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}
.modal.show {
  display: block;
  background: rgba(0, 0, 0, 0.5);
}
.btn-primary:hover {
  background-color: #0056b3;
}
.rating-card {
  padding: 20px;
  margin-top: 10px;
}
</style>