package com.mduc.gfinternal.controller;

import com.mduc.gfinternal.model.Contact;
import com.mduc.gfinternal.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    ContactService contactService;
    @PostMapping("/new")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact savedContact = contactService.createContact(contact);
        return ResponseEntity.ok(savedContact);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Contact>> getContacts(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "contact", required = false) Boolean contact,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        Page<Contact> contacts = contactService.getContacts(name, contact, pageable);

        return ResponseEntity.ok(contacts);
    }
}
