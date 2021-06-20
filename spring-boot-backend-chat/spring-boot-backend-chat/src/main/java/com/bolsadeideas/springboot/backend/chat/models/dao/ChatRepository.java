package com.bolsadeideas.springboot.backend.chat.models.dao;

import com.bolsadeideas.springboot.backend.chat.models.documents.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Mensaje, String> {

    List<Mensaje> findFirst10ByOrderByFechaDesc();
}
