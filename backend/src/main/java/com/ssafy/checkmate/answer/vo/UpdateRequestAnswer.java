package com.ssafy.checkmate.answer.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class UpdateRequestAnswer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long answerId;

    @NonNull
    private String answerContext;

    private String answerReplyUrl;
}