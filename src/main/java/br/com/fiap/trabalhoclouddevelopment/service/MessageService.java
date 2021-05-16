package br.com.fiap.trabalhoclouddevelopment.service;

import br.com.fiap.trabalhoclouddevelopment.model.Message;

import java.util.List;

public interface MessageService {


    List<Message> findAll();

    Message findById(Long id);

    Message create(Message msg);

    Message update(Message msg, Long id);

    void delete(Long id);


}
