<template>
  <div>
    <div>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
          <!-- Navbar Brand -->
          <router-link to="/" class="navbar-brand">
            <img
              src="https://www.pngfind.com/pngs/m/258-2583262_free-png-hotel-png-png-image-with-transparent.png"
              alt="Logo"
              width="80"
            />
          </router-link>

          <!-- Navbar Toggler (for mobile) -->
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>

          <!-- Navbar Links (with ml-auto to align to the right) -->
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item">
                <router-link to="/users" class="nav-link"
                  >View Users</router-link
                >
              </li>
              <li class="nav-item">
                <router-link to="/hotels" class="nav-link"
                  >View Hotels</router-link
                >
              </li>
              <li class="nav-item">
                <router-link to="/about" class="nav-link">About Us</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/contact" class="nav-link"
                  >Contact Us</router-link
                >
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
    <div>
      <router-view />
    </div>
  </div>
</template>

<script setup lang="ts">
</script>

<style scoped>
.navbar-nav {
  margin-left: auto;
}
</style>
