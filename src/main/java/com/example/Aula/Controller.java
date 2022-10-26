package com.example.Aula;

import java.util.List;
import java.util.Optional;

import com.example.Aula.Cliente;
import com.example.Aula.Repository;
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
    public void deleteClienteById(@PathVariable Long id){
        repository.deleteById(id);
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