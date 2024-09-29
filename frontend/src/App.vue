<script setup>
import Header from './components/Header.vue'
import GetGamers from "@/components/GetGamers.vue";

</script>

<template>
  <main>
  <img alt="bestseller logo" class="logo" src="./assets/bestseller-logo-vector.svg" width="250" height="250" />
    <Header msg="Customers Overview"/>
    <GetGamers/>
  </main>
</template>

<style scoped>
header {
  line-height: 10;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 0 0 0;
  }
}
</style>
