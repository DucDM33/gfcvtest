package com.mduc.gfinternal.repository;

import com.mduc.gfinternal.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Page<Contact> findByNameContaining(String name, Pageable pageable);
    Page<Contact> findByNameContainingAndContact(String name, Boolean contact, Pageable pageable);
}
