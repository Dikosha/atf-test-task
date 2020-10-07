package com.example.atf.controllers;

import com.example.atf.models.jsons.ClientJson;
import com.example.atf.services.ClientService;
import com.example.atf.services.ContactService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/client")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value="Get All clients from database")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(clientService.getAll());
    }

    @ApiOperation(value="Add client to db")
    @PostMapping("/add")
    public ResponseEntity<?> addClient(@RequestBody ClientJson clientJson){
        return ResponseEntity.ok(clientService.add(clientJson));
    }

    @ApiOperation(value="Update client information")
    @PatchMapping("/update")
    public ResponseEntity<?> updateClient(@RequestBody ClientJson clientJson){
        return ResponseEntity.ok(clientService.update(clientJson));
    }

    @ApiOperation(value="Delete just client without contacts from db")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){
        return ResponseEntity.ok(clientService.delete(id));
    }

}
