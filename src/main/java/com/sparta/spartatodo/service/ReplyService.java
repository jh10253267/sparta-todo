package com.sparta.spartatodo.service;

import com.sparta.spartatodo.dto.PageRequestDTO;
import com.sparta.spartatodo.dto.PageResponseDTO;
import com.sparta.spartatodo.dto.ReplyDTO;

public interface ReplyService {
    ReplyDTO register(ReplyDTO todoReplyDTO);
    ReplyDTO read(Long rno);
    ReplyDTO modify(Long rno, ReplyDTO replyDTO);
    void remove(Long rno);
    PageResponseDTO<ReplyDTO> getReplyListOfTodo(Long bno, PageRequestDTO pageRequestDTO);

}
