package com.sparta.spartatodo.reply.service;

import com.sparta.spartatodo.todo.dto.PageRequestDTO;
import com.sparta.spartatodo.todo.dto.PageResponseDTO;
import com.sparta.spartatodo.reply.dto.ReplyDTO;
import com.sparta.spartatodo.reply.dto.ReplyRequestDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReplyService {
    ReplyDTO register(Long bno, ReplyRequestDTO replyRequestDTO);
    ReplyDTO read(Long rno);
    ReplyDTO modify(Long rno, ReplyRequestDTO replyRequestDTO, String username);
    void remove(Long rno, String username);
    PageResponseDTO<ReplyDTO> getReplyListOfTodo(Long bno, PageRequestDTO pageRequestDTO);

}
