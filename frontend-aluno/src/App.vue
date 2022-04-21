<template>


<div class="conteudo padding">
  
<div id="AppAluno" class="card w-50">
  <div   class="card-body horizontal" v-for="aluno in alunos" :key='aluno.id' >
    <div class="content horizontal">
      <li class="title-nome"> {{aluno.nome}} </li>
      <li class="title-curso"> {{aluno.curso}} </li>
    </div>
    <div class="botao">
      <button v-on:click.prevent="getInfo(aluno)" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Editar
      </button>
      <a href="#" class="margin btn btn-danger" v-on:click.prevent="deleteAluno(aluno)">Deletar</a>
    </div>
  
  </div>
</div>
<form v-on:submit.prevent="postAluno(alunoNome, alunoCurso)" class="formulario">
  <h2> Adicione um novo aluno </h2>
  
  <div class="input-group mb-3">
    <span class="input-group-text" id="inputGroup-sizing-default">Nome</span>
    <input  :placeholder="[ ]" v-model="alunoNome" id="novoAlunoNome" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
  </div>
  <div class="input-group mb-3">
  <label class="input-group-text" for="inputGroupSelect01"> Curso </label>
  <select  :placeholder="[  ]" v-model="alunoCurso" class="form-select" id="inputGroupSelect01">
    <option value=" " selected>Deixar em branco</option>
    <option value="DD"> Design Digital </option>
    <option value="CC"> Ciência da Computação </option>
    <option value="ES"> Engenharia de Software </option>
    <option value="SI"> Sistemas de Informação </option>
    <option value="RC"> Redes de Computadores </option>
  </select>
</div>

  <button type="submit" class="btn btn-outline-primary"> Adicionar </button>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"> Editar aluno(a): {{alunoNome}} de {{alunoCurso}} </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="input-group mb-3">
          <span class="input-group-text" id="inputGroup-sizing-default">Nome</span>
          <input :placeholder="[  ]" v-model="novoNome" id="novoAlunoNome" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
        </div>
        <div class="input-group mb-3">
          <label class="input-group-text" for="inputGroupSelect01"> Curso </label>
          <select :placeholder="[  ]" v-model="novoCurso" class="form-select" id="inputGroupSelect01">
            <option value=" " selected>Deixar em branco</option>
            <option value="DD"> Design Digital </option>
            <option value="CC"> Ciência da Computação </option>
            <option value="ES"> Engenharia de Software </option>
            <option value="SI"> Sistemas de Informação </option>
            <option value="RC"> Redes de Computadores </option>
          </select>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button placeholder="" v-on:click.prevent="updateAluno(alunoNome, novoNome, novoCurso)" data-bs-dismiss="modal" type="button" class="btn btn-primary">Salvar alterações</button>
      </div>
    </div>
  </div>
</div>
</form>



</div>



</template>

<script> 



import axios from 'axios';

export default ({
    
    name: 'AppAluno',

    data(){
      return {
        alunos: {},
        alunoId: null,
        alunoNome: null,
        alunoCurso: null,
      }
    },

    methods: {

      getInfo(aluno) {
        this.alunoNome = aluno.nome;
        this.alunoId = aluno.id;
        this.alunoCurso = aluno.curso
      },

      updateAluno(alunoNome, novoNome, novoCurso) {
        if (novoNome == null && novoCurso == "") {
          alert("Alteração inválida! Complete todos os campos antes de confirmar.")
        } else if (novoNome == null && novoCurso != "") {
          axios
              .post('http://localhost:8080/api/aluno', {
                'id': this.alunoId,
                'nome': alunoNome,
                'curso': novoCurso,
              }) .then(() => {
                this.getAluno();
                this.alunoNome = null,
                this.alunoCurso = null,
                this.novoNome = null,
                this.novoCurso = null
              })
        } else {
            axios
              .post('http://localhost:8080/api/aluno', {
                'id': this.alunoId,
                'nome': novoNome,
                'curso': novoCurso,
              }) .then(() => {
                this.getAluno();
                this.alunoNome = null,
                this.alunoCurso = null
                novoNome = null,
                novoCurso = null
              })

        }
       
      },
      
      getAluno() {

        axios
        .get('http://localhost:8080/api/aluno')
        .then((response) => {
          this.alunos = response.data;
        })
        .catch((error) => {
          console.log(error)
        })

      },
     
       postAluno(nome, curso) {
         if(nome == null || curso == null){

                               alert("Preencha todos os campos e tente novamente!")

         }  else {
           axios
                        .post('http://localhost:8080/api/aluno', {
                            'nome': this.alunoNome,
                            'curso': this.alunoCurso
                        })
                        .then(response => {
                            this.alunos.push(response.data);
                            this.alunoNome = null;
                            this.alunoId = null;
                            this.alunoCurso = null;
                        })
          
         }
            },
      deleteAluno(aluno) {
        if(confirm('Confirma a exclusão?')){
          axios
            .delete('http://localhost:8080/api/aluno/' + aluno.id)
            .then(() => {
              this.getAluno()
            })
        } else {
          this.getAluno()
        } 
      },

          

      
    },


    created() {
      this.getAluno();
    },
    components: {

    },
    computed: {


    }

   
    
  })
</script>
  
<style>

</style>