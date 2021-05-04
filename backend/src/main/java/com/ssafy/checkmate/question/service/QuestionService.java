package com.ssafy.checkmate.question.service;

import com.ssafy.checkmate.member.service.MemberService;
import com.ssafy.checkmate.question.dto.Question;
import com.ssafy.checkmate.question.repository.QuestionRepository;
import com.ssafy.checkmate.question.vo.UpdateRequestQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    @Deprecated
    public static PageRequest of(int page, int size) {

        return of(page, size);
    }

    public void registerQuestion(Question question) {

        if (question.getQuestionPoint() > 0)
            memberService.chargePoint(question.getMemberId(), question.getQuestionPoint());

        questionRepository.save(question);
    }

    public ResponseEntity<Map<String, Object>> fileUpload(MultipartFile questionFile) {

        String filePath = "/files";
        String fileName = questionFile.getOriginalFilename();

        int nameLen = fileName.length();
        int index = fileName.lastIndexOf('.');

        String subFileName = fileName.substring(0, index) + "_" + LocalDateTime.now();
        subFileName = subFileName.replace(":", "-");
        String subFileExtension = fileName.substring(index, nameLen);

        File saveFile = new File(filePath, "question_" + subFileName + subFileExtension);

        try {
            questionFile.transferTo(saveFile);
            fileName = "http://k4a106.p.ssafy.io:8888/" + "question_" + subFileName + subFileExtension;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("fileUrl", fileName);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }

    public List<Question> getMemberQuestionList(Long memberId, int offset, int limit) {

        Pageable Pageable = PageRequest.of(offset, limit);

        return questionRepository.findQuestionsBymemberId(memberId, Pageable);
    }

    public int countMemberQuestionList(Long memberId) {

        return questionRepository.countQuestionsBymemberId(memberId);
    }

    public List<Question> getQuestionList(int listType, int offset, int limit) {

        LocalDate currentDate = LocalDate.now();
        Pageable pageable = PageRequest.of(offset, limit);

        switch (listType) {
            case 1: // 등록날짜순(내림차순)
                return questionRepository.findQuestionsByQuestionEndDateGreaterThanEqualAndQuestionStatusEqualsOrderByQuestionDateDesc(currentDate, 0, pageable);
            case 2: // 마감날짜순(오름차순)
                return questionRepository.findQuestionsByQuestionEndDateGreaterThanEqualAndQuestionStatusEqualsOrderByQuestionEndDate(currentDate, 0, pageable);
            case 3: // 포인트순(내림차순)
                return questionRepository.findQuestionsByQuestionEndDateGreaterThanEqualAndQuestionStatusEqualsOrderByQuestionPointDesc(currentDate, 0, pageable);
        }

        return null;
    }

    public int countQuestionList(int listType) {

        LocalDate currentDate = LocalDate.now();

        switch (listType) {
            case 1: // 등록날짜순(내림차순)
                return questionRepository.countQuestionsByQuestionEndDateGreaterThanEqualAndQuestionStatusEqualsOrderByQuestionDateDesc(currentDate, 0);
            case 2: // 마감날짜순(오름차순)
                return questionRepository.countQuestionsByQuestionEndDateGreaterThanEqualAndQuestionStatusEqualsOrderByQuestionEndDate(currentDate, 0);
            case 3: // 포인트순(내림차순)
                return questionRepository.countQuestionsByQuestionEndDateGreaterThanEqualAndQuestionStatusEqualsOrderByQuestionPointDesc(currentDate, 0);
        }

        return 0;
    }

    public Question getQuestion(Long questionId) {

        return questionRepository.findQuestionByQuestionId(questionId);
    }

    @Transactional
    public void updateQuestion(UpdateRequestQuestion updateRequestQuestion) {

        Question question = questionRepository.findQuestionByQuestionId(updateRequestQuestion.getQuestionId());
        question.setQuestionExplain(updateRequestQuestion.getQuestionExplain());
        questionRepository.save(question);
    }
}
