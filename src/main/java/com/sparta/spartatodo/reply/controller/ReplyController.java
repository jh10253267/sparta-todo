package com.sparta.spartatodo.reply.controller;

import com.sparta.spartatodo.todo.dto.PageRequestDTO;
import com.sparta.spartatodo.todo.dto.PageResponseDTO;
import com.sparta.spartatodo.reply.dto.ReplyDTO;
import com.sparta.spartatodo.reply.dto.ReplyRequestDTO;
import com.sparta.spartatodo.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Map<String, Object> modify(@PathVariable("rno") Long rno, ReplyRequestDTO replyRequestDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Map<String, Object> map = new HashMap<>();
        ReplyDTO modReplyDTO = replyService.modify(rno, replyRequestDTO, userDetails.getUsername());

        map.put("Modified Reply", modReplyDTO);
        return map;
    }
    @DeleteMapping("/{rno}")
    public Map<String, Object> remove(@PathVariable("rno") Long rno, @AuthenticationPrincipal UserDetails userDetails) {
        replyService.remove(rno, userDetails.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Deleted!");

        return map;
    }
    @PostMapping("/{bno}")
    public Map<String, Object> register(@PathVariable("bno") Long bno, @RequestBody ReplyRequestDTO replyRequestDTO) {
        Map<String, Object> map = new HashMap<>();
        ReplyDTO replyDTO = replyService.register(bno, replyRequestDTO);

        map.put("Reply", replyDTO);
        return  map;
    }


}
