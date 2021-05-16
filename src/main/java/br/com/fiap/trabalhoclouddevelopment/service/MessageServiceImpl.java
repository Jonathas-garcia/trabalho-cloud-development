package br.com.fiap.trabalhoclouddevelopment.service;

import br.com.fiap.trabalhoclouddevelopment.model.Message;
import br.com.fiap.trabalhoclouddevelopment.repository.MessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Message> findAll() {
        return repository.findAll().stream().filter(Message::isAtivo).collect(Collectors.toList());
    }

    @Override
    public Message findById(Long id) {
        return getMensagemById(id);
    }

    @Override
    public Message create(Message msg) {
        return repository.save(msg);
    }

    @Override
    public Message update(Message msg, Long id) {
        Message mensagem = getMensagemById(id);
        mensagem.setMessage(msg.getMessage());

        Message savedMsg = repository.save(mensagem);
        return savedMsg;
    }

    @Override
    public void delete(Long id) {
        Message msg = getMensagemById(id);
        msg.setAtivo(false);
        repository.save(msg);
    }

    private Message getMensagemById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
