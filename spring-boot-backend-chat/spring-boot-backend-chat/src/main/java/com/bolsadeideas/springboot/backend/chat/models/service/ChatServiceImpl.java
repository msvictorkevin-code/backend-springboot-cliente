package com.bolsadeideas.springboot.backend.chat.models.service;

import com.bolsadeideas.springboot.backend.chat.models.dao.ChatRepository;
import com.bolsadeideas.springboot.backend.chat.models.documents.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<Mensaje> findFirst10ByOrOrderByFechaDesc() {
        return chatRepository.findFirst10ByOrderByFechaDesc();
    }

    @Override
    public Mensaje guardar(Mensaje mensaje) {
        return chatRepository.save(mensaje);
    }
}
