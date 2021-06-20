package com.bolsadeideas.springboot.backend.chat.models.service;

import com.bolsadeideas.springboot.backend.chat.models.documents.Mensaje;

import java.util.List;

public interface ChatService {

    List<Mensaje> findFirst10ByOrOrderByFechaDesc();

    Mensaje guardar(Mensaje mensaje);
}

