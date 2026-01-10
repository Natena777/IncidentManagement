package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.converter.DefaultConverter;
import org.example.incidentmanagement.dto.createRequest.CrCaseCommentRequestDto;
import org.example.incidentmanagement.dto.response.CaseCommentRespDto;
import org.example.incidentmanagement.entity.CaseComments;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.CaseCommentMapper;
import org.example.incidentmanagement.repository.CaseCommentRepository;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.service.interfaces.CaseCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseCommentServiceImpl implements CaseCommentService {

    private final CurrentUserService currentUserService;
    private final CaseCommentRepository caseCommentRepository;
    private final CaseCommentMapper caseCommentMapper;
    private final DefaultConverter defaultConverter;
    Logger logger = LoggerFactory.getLogger(CaseCommentServiceImpl.class);

    public CaseCommentServiceImpl(CurrentUserService currentUserService,
                                  CaseCommentRepository caseCommentRepository,
                                  CaseCommentMapper caseCommentMapper,
                                  DefaultConverter defaultConverter) {
        this.currentUserService = currentUserService;
        this.caseCommentRepository = caseCommentRepository;
        this.caseCommentMapper = caseCommentMapper;
        this.defaultConverter = defaultConverter;
    }



    @Override
    public CaseCommentRespDto addComment(Integer caseId, CrCaseCommentRequestDto requestDto) {
        String caseNumber = defaultConverter.getCaseNumber(caseId);

        logger.info("Called Add Comment on Case : {} From User: {}", caseNumber, currentUserService.getCurrentUsername());

        if (caseNumber == null){
            throw new CustomException(ResponseCodes.INVALID_CASE_NUMBER);
        }

        CaseComments caseComment = caseCommentMapper.toCaseCommentEntity(requestDto, caseId, currentUserService.getCurrentUserId());
        caseCommentRepository.save(caseComment);


        CaseCommentRespDto result = caseCommentMapper.toCaseCommentResponse(caseComment);

        return result;
    }

    @Override
    public CaseCommentRespDto updateComment(CrCaseCommentRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteComment(Integer id) {
        logger.info("Called Delete Comment on Id: {}",  id);
        caseCommentRepository.deleteById(id);
    }

    @Override
    public List<CaseCommentRespDto> getAllComments() {
        logger.info("Called Get All Comments");
        List<CaseComments> caseComments = caseCommentRepository.findAll();

        List<CaseCommentRespDto> result = caseComments.stream()
                .map(comment -> { CaseCommentRespDto convertedData = caseCommentMapper.toCaseCommentResponse(comment);
                    return convertedData;
                }).toList();

        return result;
    }

    @Override
    public List<CaseCommentRespDto> getCommentsByCaseId(Integer caseId) {
        String caseNumber = defaultConverter.getCaseNumber(caseId);
        logger.info("Called Get All Comments On Case id {}, Number: {}", caseId, caseNumber);
        List<CaseComments> caseComments = caseCommentRepository.findByCaseId(caseId);

        List<CaseCommentRespDto> result = caseComments.stream()
                .map(comment -> { CaseCommentRespDto convertedData = caseCommentMapper.toCaseCommentResponse(comment);
                    return convertedData;
                }).toList();

        return result;
    }
}
