package com.sparta.spartatodo.service;

import com.sparta.spartatodo.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ReplyServiceTest {
    @Autowired
    private ReplyService replyService;

    @DisplayName("[Reply] [Service] [ReadOne]")
    @Test
    void givenRno_whenDoRead_thenReturnsReplyDTO() {
        //Given
        Long rno = 1L;
        //When
        //Then
        ReplyDTO replyDTO = replyService.read(rno);
        log.info(replyDTO);
    }
}