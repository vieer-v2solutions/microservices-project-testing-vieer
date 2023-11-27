<template>
  <div>
    <h4 class="users">Registered Users</h4>
    <div class="user-cards-container">
      <div class="user-cards">
        <div class="user-card" v-for="user in users" :key="user.userId">
          <div class="user-card-inner">
            <h3>{{ user.name }}</h3>
            <p>Email: {{ user.email }}</p>
            <button class="view-details-button" @click="navigateToUserDetails(user.userId)">View Details</button>
            <!-- Add more user details here as needed -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const users = ref([]);
const router = useRouter();

onMounted(() => {
  // Fetch user data from JSON Server
  fetch("http://microservice-alb-1225467990.ap-south-1.elb.amazonaws.com/users")
    .then((response) => response.json())
    .then((data) => {
      users.value = data;
    });
});

// Function to navigate to user details page
function navigateToUserDetails(userId) {
  router.push(`/users/${userId}`);
}
</script>



<style scoped>
.user-cards-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.user-cards {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.user-card {
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 300px;
}

.user-card h3 {
  font-size: 20px;
  margin: 0;
}

.user-card p {
  margin: 10px 0;
}

.users {
  text-align: center;
  margin: 20px;
  margin-bottom: 40px;
}

/* Button Style */
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
</style>
