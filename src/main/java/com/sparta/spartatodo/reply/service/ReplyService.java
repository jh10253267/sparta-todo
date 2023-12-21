package com.sparta.spartatodo.reply.service;

import com.sparta.spartatodo.global.request.PageRequestDTO;
import com.sparta.spartatodo.global.request.PageResponseDTO;
import com.sparta.spartatodo.reply.dto.ReplyResponseDTO;
import com.sparta.spartatodo.reply.dto.ReplyRequestDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReplyService {
    ReplyResponseDTO register(Long bno, ReplyRequestDTO replyRequestDTO, String writer);
    ReplyResponseDTO read(Long rno);
    ReplyResponseDTO modify(Long rno, ReplyRequestDTO replyRequestDTO, String username);
    void remove(Long rno, String username);
    PageResponseDTO<ReplyResponseDTO> getReplyListOfTodo(Long bno, PageRequestDTO pageRequestDTO);

}
