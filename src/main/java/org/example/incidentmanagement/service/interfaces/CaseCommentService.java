package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.createRequest.CrCaseCommentRequestDto;
import org.example.incidentmanagement.dto.response.CaseCommentRespDto;

import java.util.List;

public interface CaseCommentService {

    CaseCommentRespDto addComment(Integer caseId, CrCaseCommentRequestDto requestDto);
    CaseCommentRespDto updateComment(CrCaseCommentRequestDto requestDto);
    void deleteComment(Integer id);

    List<CaseCommentRespDto> getAllComments();
    List<CaseCommentRespDto> getCommentsByCaseId(Integer caseId);


}
