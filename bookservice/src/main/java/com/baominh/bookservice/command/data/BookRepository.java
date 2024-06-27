package com.baominh.bookservice.command.data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String>{

}
