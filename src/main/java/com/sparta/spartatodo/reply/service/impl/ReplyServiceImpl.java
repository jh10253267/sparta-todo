package com.sparta.spartatodo.reply.service.impl;

import com.sparta.spartatodo.reply.domain.Reply;
import com.sparta.spartatodo.todo.domain.Todo;
import com.sparta.spartatodo.todo.dto.PageRequestDTO;
import com.sparta.spartatodo.todo.dto.PageResponseDTO;
import com.sparta.spartatodo.reply.dto.ReplyDTO;
import com.sparta.spartatodo.reply.dto.ReplyRequestDTO;
import com.sparta.spartatodo.reply.repository.ReplyRepository;
import com.sparta.spartatodo.todo.repository.TodoRepository;
import com.sparta.spartatodo.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;
    @Override
    public ReplyDTO register(Long bno, ReplyRequestDTO replyRequestDTO) {
        Optional<Todo> result = todoRepository.findById(bno);
        Todo todo = result.orElseThrow();

        Reply reply = Reply.builder()
                .todo(todo)
                .replyText(replyRequestDTO.getReplyText())
                .replyWriter(replyRequestDTO.getReplyWriter())
                .build();
        Reply createdReply = replyRepository.save(reply);

        return modelMapper.map(createdReply, ReplyDTO.class);
    }

    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        return modelMapper.map(reply, ReplyDTO.class);
    }

    @Override
    public ReplyDTO modify(Long rno, ReplyRequestDTO replyRequestDTO, String username) {
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        if(!reply.getReplyWriter().equals(username)) {
            return null;
        }
        reply.changeText(reply.getReplyText());
        Reply modReply = replyRepository.save(reply);
        return modelMapper.map(modReply, ReplyDTO.class);
    }

    @Override
    public void remove(Long rno, String username) {
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        if(!reply.getReplyWriter().equals(username)) {
            return;
        }
        replyRepository.deleteById(rno);
    }
    @Override
    public PageResponseDTO<ReplyDTO> getReplyListOfTodo(Long bno, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() -1, pageRequestDTO.getSize(), Sort.by("rno").ascending());
        Page<Reply> result = replyRepository.listOfTodo(bno, pageable);

        List<ReplyDTO> dtoList = result.getContent().stream().map(reply -> modelMapper.map(reply, ReplyDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
