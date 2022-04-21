package com.example.aluno.controller;

import com.example.aluno.model.Aluno;
import com.example.aluno.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:8081" })
@RestController
@AllArgsConstructor
@RequestMapping(value="/api")
@ResponseBody
public class AlunoController {

    @Autowired
    AlunoRepository repository;


    public AlunoController() {
        super();
    }

    @GetMapping("/aluno")
    public List<Aluno> getAllAlunos() {
        return repository.findAll();
    }

    @GetMapping("/aluno/{id}")
    public Aluno getAlunoById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("/aluno")
    public Aluno saveAluno(@RequestBody Aluno aluno) {
        return repository.save(aluno);
    }

    @DeleteMapping("/aluno/{id}")
    public void deleteAluno(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @PutMapping("/aluno/{id}")
    public Aluno atualizaAluno(@RequestBody Aluno aluno) {

        return repository.save(aluno);
    }

}
