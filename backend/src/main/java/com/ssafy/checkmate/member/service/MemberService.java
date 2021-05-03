package com.ssafy.checkmate.member.service;

import com.ssafy.checkmate.answer.dto.Answer;
import com.ssafy.checkmate.answer.repository.AnswerRepository;
import com.ssafy.checkmate.common.exception.LoginFailedException;
import com.ssafy.checkmate.common.security.Sha256;
import com.ssafy.checkmate.member.dto.Member;
import com.ssafy.checkmate.member.repository.MemberRepository;
import com.ssafy.checkmate.member.vo.SelectMember;
import com.ssafy.checkmate.question.dto.Question;
import com.ssafy.checkmate.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final JwtService jwtService;
    private final Sha256 sha256;
    private static int check, count = 0;

    public void signUp(Member member) {

        validateSignUp(member.getMemberEmail());
        member.setMemberPassword(sha256.encryption(member.getMemberPassword()));
        memberRepository.save(member);
    }

    public ResponseEntity<Map<String, Object>> fileUpload(MultipartFile proFile) {

        String filePath = "/files";
        String fileName = proFile.getOriginalFilename();

        int nameLen = fileName.length();
        int index = fileName.lastIndexOf('.');

        String subFileName = fileName.substring(0, index) + "_" + LocalDateTime.now();
        subFileName = subFileName.replace(":", "-");
        String subFileExtension = fileName.substring(index, nameLen);

        File saveFile = new File(filePath, "proFile_" + subFileName + subFileExtension);

        try {
            proFile.transferTo(saveFile);
            fileName = "http://k4a106.p.ssafy.io:8888/files/" + "proFile_" + subFileName + subFileExtension;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("fileUrl", fileName);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }

    public int validateSignUp(String memberEmail) {

        if (memberRepository.findMemberByMemberEmail(memberEmail) != null) {
            check = 1;
        } else {
            check = 0;
        }

        return check;
    }

    public int validateNickName(String memberNickname) {

        if (memberRepository.findMemberByMemberNickname(memberNickname) != null) {
            check = 1;
        } else {
            check = 0;
        }

        return check;
    }

    public ResponseEntity<Map<String, Object>> signIn(String memberEmail, String memberPassword) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String password = sha256.encryption(memberPassword);
        Member member = memberRepository.findMemberByMemberEmailAndMemberPassword(memberEmail, password);

        if (member == null) {
            throw new LoginFailedException("사용자 정보가 틀렸습니다.");
        }

        String token = jwtService.create(member);
        resultMap.put("accesstoken", token);
        resultMap.put("message", "Success");

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }

    public SelectMember selectMember(Long id) {

        Member member = memberRepository.findMemberByMemberId(id);
        SelectMember memberSelect = new SelectMember();

        memberSelect.setMemberEmail(member.getMemberEmail());
        memberSelect.setMemberNickname(member.getMemberNickname());
        memberSelect.setMemberNativeLang(member.getMemberNativeLang());
        memberSelect.setMemberProfileUrl(member.getMemberProfileUrl());
        memberSelect.setMemberPoint(member.getMemberPoint());
        memberSelect.setMemberIntroduce(member.getMemberIntroduce());
        memberSelect.setMemberTypeId(member.getMemberTypeId());

        return memberSelect;
    }

    public void chargePoint(Long memberId, int questionPoint) {

        Member member = memberRepository.findMemberByMemberId(memberId);
        member.setMemberPoint(member.getMemberPoint() - questionPoint);
        memberRepository.save(member);
    }

    public ResponseEntity<Map<LocalDate, Object>> activateLog(Long memberId) {

        List<Question> question = questionRepository.findQuestionsBymemberId(memberId);
        List<Answer> answer = answerRepository.findAnswerByMemberId(memberId);
        Map<LocalDate, Object> resultMap = new HashMap<LocalDate, Object>();

        for (int i = 0; i < question.size(); i++) {

            if (question.get(i).getQuestionDate() != null) {
                LocalDateTime templeDate = question.get(i).getQuestionDate();
                LocalDate templeDateLocalDate = templeDate.toLocalDate();

                if (resultMap.get(templeDateLocalDate) == null) {
                    resultMap.put(templeDateLocalDate, 1);
                } else {
                    resultMap.put(templeDateLocalDate, (int) resultMap.get(templeDateLocalDate) + 1);
                }
            }
        }

        for (int i = 0; i < answer.size(); i++) {

            if (answer.get(i).getAnswerDate() != null) {
                LocalDateTime templeDate = answer.get(i).getAnswerDate();
                LocalDate templeDateLocalDate = templeDate.toLocalDate();

                if (resultMap.get(templeDateLocalDate) == null) {
                    resultMap.put(templeDateLocalDate, 1);
                } else {
                    resultMap.put(templeDateLocalDate, (int) resultMap.get(templeDateLocalDate) + 1);
                }
            }
        }

        return new ResponseEntity<Map<LocalDate, Object>>(resultMap, HttpStatus.ACCEPTED);
    }
}
