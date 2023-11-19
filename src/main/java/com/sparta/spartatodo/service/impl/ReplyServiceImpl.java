package com.sparta.spartatodo.service.impl;

import com.sparta.spartatodo.domain.Reply;
import com.sparta.spartatodo.dto.PageRequestDTO;
import com.sparta.spartatodo.dto.PageResponseDTO;
import com.sparta.spartatodo.dto.ReplyDTO;
import com.sparta.spartatodo.repository.ReplyRepository;
import com.sparta.spartatodo.service.ReplyService;
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
    private final ModelMapper modelMapper;
    @Override
    public ReplyDTO register(ReplyDTO todoReplyDTO) {
        Reply reply = modelMapper.map(todoReplyDTO, Reply.class);

        Reply modReply = replyRepository.save(reply);

        return modelMapper.map(modReply, ReplyDTO.class);
    }

    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        return modelMapper.map(reply, ReplyDTO.class);
    }

    @Override
    public ReplyDTO modify(Long rno, ReplyDTO replyDTO) {
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        reply.changeText(reply.getReplyText());
        Reply modReply = replyRepository.save(reply);
        return modelMapper.map(modReply, ReplyDTO.class);
    }

    @Override
    public void remove(Long rno) {
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
