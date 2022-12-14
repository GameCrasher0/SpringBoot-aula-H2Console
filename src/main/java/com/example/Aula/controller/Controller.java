package com.example.Aula.controller;

import java.util.List;
import java.util.Optional;

import com.example.Aula.entity.Cliente;
import com.example.Aula.dto.ClienteDTO;
import com.example.Aula.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/cliente/v1"})
public class Controller {
    @Autowired
    Repository repository;

    public Controller() {
    }

    @PostMapping
    public Cliente creat(@RequestBody Cliente cliente) {
        Cliente clienteSaved = (Cliente)this.repository.save(cliente);
        return clienteSaved;
    }

    @GetMapping({"/{id}"})
    @ResponseBody
    public Optional<Cliente> getClineteById(@PathVariable Long id) {
        Optional<Cliente> clienteReturned = this.repository.findById(id);
        return clienteReturned;
    }

    @DeleteMapping({"/{id}"})
    public String deleteClienteById(@PathVariable Long id) {
        try{
            Optional<Cliente> cliente = Optional.of(repository.getById(id));
            if(cliente.isPresent()){
                repository.deleteById(id);
                return  "Cliente de " + id + "deletado com sucesso!";
            }else{
                throw new Exception("Cliente inexistente!");
            }

        }catch(Exception e){
            e.printStackTrace();
            return "O cliente de " + id + " não existe para ser deletado" +
                    " Por favor, entre em contato com o atendimento 666 666 666 ";
        }

    }

    @GetMapping
    public List<Cliente> listClientes(){
        return repository.findAll();
    }

    @PutMapping("/atualize/{id}")
    public String updateClienteById(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id){
        Optional<Cliente> velhoCliente = repository.findById(id);
        if(velhoCliente.isPresent()){
            Cliente cliente = velhoCliente.get();
            cliente.setEndereco(clienteDTO.getEndereco());
            repository.save(cliente);
            return "Cliente de id " + cliente.getId() + " atualizado com sucesso";
        }else{
            return "Cliente de id " + id + " não existe";
        }

    }

}