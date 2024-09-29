<template>
  <div style="overflow-x:auto;" >
   <button @click="getGamersData">Get Customers</button>
    <table >
      <tr><th>ID</th><th>Name</th><th>Email</th><th>Address Street</th><th>Game Names</th><th>Operation</th></tr>
      <tr v-for="x in data" ><td>{{x.id}}</td><td>{{x.name}}</td><td>{{x.email}}</td><td>{{x.address.street}}</td><td ><ul v-for="g in x.games"><li>{{g.name}}({{g.level}})</li></ul></td><td><button @click="deleteGamersData(x.id)">Delete Customers Data</button></td></tr>
  </table>
  </div>
</template>

<script>

export default {
  data() {
    return {
      data: null,
    };
  },
  deletedata() {
    return {
      deletedata: null,
    };
  },
  methods: {
    async getGamersData() {
      const response = await fetch("http://localhost:8080/Customers");
      this.data = await response.json();
    },
    async deleteGamersData(i) {
      // Simple DELETE request with fetch
      fetch('http://localhost:8080/Customers/'+i, { method: 'DELETE' })
          .then(response => response.json())
          .then(x => deletedata.value = x);

    }
  }
}
</script>
