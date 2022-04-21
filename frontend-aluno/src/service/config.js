import axios from 'axios'

window.axios = require('axios');

new Vue({
    el: '#app',
    
    components: {

    },

    mounted: function () {

        axios.get('http://localhost:8080/api/aluno')
        .then(response => console.log(response));

    },

    data: {

    }

  })