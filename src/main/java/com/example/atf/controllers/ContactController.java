package com.example.atf.controllers;

import com.example.atf.models.jsons.ClientJson;
import com.example.atf.models.jsons.ContactJson;
import com.example.atf.services.ClientService;
import com.example.atf.services.ContactService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/contact")
@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @ApiOperation(value="Get all information about client, phone, type in list")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(contactService.getAll());
    }

    @ApiOperation(value="Get types of contact type")
    @GetMapping("/getTypes")
    public ResponseEntity<?> getTypes(){
        return ResponseEntity.ok(contactService.getTypes());
    }

    @ApiOperation(value="Get information about client with contacts")
    @GetMapping("/getAllByUser/{id}")
    public ResponseEntity<?> getAllByUser(@PathVariable Long id){
        return ResponseEntity.ok(contactService.getAllByUser(id));
    }

    @ApiOperation(value="Add contact to client")
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ContactJson contactJson){
        return ResponseEntity.ok(contactService.add(contactJson));
    }

    @ApiOperation(value="Update phone number or type of client")
    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody ContactJson contactJson){
        return ResponseEntity.ok(contactService.update(contactJson));
    }

    @ApiOperation(value="Delete contact from db")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(contactService.delete(id));
    }
}
