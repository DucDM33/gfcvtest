package com.mduc.gfinternal.service;

import com.mduc.gfinternal.model.Contact;
import com.mduc.gfinternal.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;
    public Contact createContact(Contact contact) {
        Contact newContact = new Contact();
        newContact.setName(contact.getName());
        newContact.setEmail(contact.getEmail());
        newContact.setContact(false);
        newContact.setPhoneNumber(contact.getPhoneNumber());
        newContact.setMessage(contact.getMessage());
        return contactRepository.save(newContact);
    }

    public Page<Contact> getContacts(String name, Boolean contact, Pageable pageable) {
        if (contact == null) {
            return contactRepository.findByNameContaining(name, pageable);
        } else {
            return contactRepository.findByNameContainingAndContact(name, contact, pageable);
        }
    }
    public Contact updateContactStatus(Integer id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
        contact.setContact(true);
        return contactRepository.save(contact);
    }
}
