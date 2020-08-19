package com.example.demo.repository;


import com.example.demo.entity.MessageVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageVO,String> {
}
