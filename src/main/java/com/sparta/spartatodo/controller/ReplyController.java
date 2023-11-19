package com.sparta.spartatodo.controller;

import com.sparta.spartatodo.dto.PageRequestDTO;
import com.sparta.spartatodo.dto.PageResponseDTO;
import com.sparta.spartatodo.dto.ReplyDTO;
import com.sparta.spartatodo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/replies")
public class ReplyController {
    private final ReplyService replyService;
    @GetMapping("/{rno}")
    public Map<String, Object> read(@PathVariable("rno") Long rno) {
        Map<String, Object> map = new HashMap<>();
        ReplyDTO replyDTO = replyService.read(rno);
        map.put("reply", replyDTO);
        return map;
    }
    @GetMapping("/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> pageResponseDTO = replyService.getReplyListOfTodo(bno, pageRequestDTO);

        return pageResponseDTO;
    }
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> modify(@PathVariable("rno") Long rno, ReplyDTO replyDTO) {
        Map<String, Object> map = new HashMap<>();
        ReplyDTO modReplyDTO = replyService.modify(rno, replyDTO);

        map.put("Modified Reply", modReplyDTO);
        return map;
    }
    @DeleteMapping("/{rno}")
    public Map<String, Object> remove(@PathVariable("rno") Long rno) {
        replyService.remove(rno);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Deleted!");

        return map;
    }


}
