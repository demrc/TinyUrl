package com.example.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.urlshortener.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailAndPassword(String email,String password);//bu metod user nesnesinin email adressiyle eşleşmesi için
}
