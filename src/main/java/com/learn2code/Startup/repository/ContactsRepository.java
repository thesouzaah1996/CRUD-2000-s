package com.learn2code.Startup.repository;

import com.learn2code.Startup.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    List<Contacts> findByNameContainingIgnoreCase(String name);

    List<Contacts> findByTitle(String title);
}
