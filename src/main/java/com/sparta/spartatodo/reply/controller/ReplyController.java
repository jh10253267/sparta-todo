package com.sparta.spartatodo.reply.controller;

import com.sparta.spartatodo.global.request.PageRequestDTO;
import com.sparta.spartatodo.global.request.PageResponseDTO;
import com.sparta.spartatodo.global.response.Response;
import com.sparta.spartatodo.reply.dto.ReplyResponseDTO;
import com.sparta.spartatodo.reply.dto.ReplyRequestDTO;
import com.sparta.spartatodo.reply.service.ReplyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1/replies")
public class ReplyController {
    private final ReplyService replyService;
    @GetMapping("/{rno}")
    public Response<ReplyResponseDTO> read(@PathVariable("rno") Long rno) {
        ReplyResponseDTO replyResponseDTO = replyService.read(rno);
        return Response.success(replyResponseDTO);
    }
    @GetMapping("/list/{tno}")
    public Response<PageResponseDTO<ReplyResponseDTO>> getList(@PathVariable("tno") Long tno, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyResponseDTO> pageResponseDTO = replyService.getReplyListOfTodo(tno, pageRequestDTO);

        return Response.success(pageResponseDTO);
    }
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<ReplyResponseDTO> modify(@PathVariable("rno") Long rno, @RequestBody @Valid ReplyRequestDTO replyRequestDTO, @AuthenticationPrincipal UserDetails userDetails, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        ReplyResponseDTO modReplyResponseDTO = replyService.modify(rno, replyRequestDTO, userDetails.getUsername());

        return Response.success(modReplyResponseDTO);
    }
    @DeleteMapping("/{rno}")
    public Response<Void> remove(@PathVariable("rno") Long rno, @AuthenticationPrincipal UserDetails userDetails) {
        replyService.remove(rno, userDetails.getUsername());

        return Response.success();
    }
    @PostMapping("/{tno}")
    public Response<ReplyResponseDTO> register(@PathVariable("tno") Long tno, @RequestBody @Valid ReplyRequestDTO replyRequestDTO, @AuthenticationPrincipal UserDetails userDetails) {
        ReplyResponseDTO replyResponseDTO = replyService.register(tno, replyRequestDTO, userDetails.getUsername());

        return  Response.success(replyResponseDTO);
    }


}
