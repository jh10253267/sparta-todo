package com.sparta.spartatodo.service;

import com.sparta.spartatodo.dto.PageRequestDTO;
import com.sparta.spartatodo.dto.PageResponseDTO;
import com.sparta.spartatodo.dto.ReplyDTO;
import com.sparta.spartatodo.dto.ReplyRequestDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReplyService {
    ReplyDTO register(Long bno, ReplyRequestDTO replyRequestDTO);
    ReplyDTO read(Long rno);
    ReplyDTO modify(Long rno, ReplyRequestDTO replyRequestDTO, String username);
    void remove(Long rno, String username);
    PageResponseDTO<ReplyDTO> getReplyListOfTodo(Long bno, PageRequestDTO pageRequestDTO);

}
