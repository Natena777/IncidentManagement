package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.requests.CrCasesStatChangePermRequestDto;
import org.example.incidentmanagement.dto.response.CrCaseStatChangePermRespDto;
import org.example.incidentmanagement.entity.CaseStatChangePerm;
import org.example.incidentmanagement.mappers.CaseStatChangeMapper;
import org.example.incidentmanagement.repository.CaseStatChangePermRepository;
import org.example.incidentmanagement.service.CaseStatChangePermService;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.service.DefaultConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseStatChangePermServiceImpl implements CaseStatChangePermService {

    private final CaseStatChangePermRepository caseStatChangePermRepository;
    private final CaseStatChangeMapper caseStatChangeMapper;
    private final DefaultConverter defaultConverter;
    private final CurrentUserService currentUserService;
    Logger logger = LoggerFactory.getLogger(CaseStatChangePermServiceImpl.class);

    public CaseStatChangePermServiceImpl(CaseStatChangePermRepository caseStatChangePermRepository, CaseStatChangeMapper caseStatChangeMapper,
                                         DefaultConverter defaultConverter, CurrentUserService currentUserService) {
        this.caseStatChangePermRepository = caseStatChangePermRepository;
        this.caseStatChangeMapper = caseStatChangeMapper;
        this.defaultConverter = defaultConverter;
        this.currentUserService = currentUserService;
    }


    @Override
    public CrCaseStatChangePermRespDto addPermission(CrCasesStatChangePermRequestDto requestDto) {
        logger.info("Called Add Permission to Group For Change Status");
        var permissions = paramConverterToEntity(requestDto.isCanRead(),
                requestDto.isCanEdit(),
                requestDto.isCanDelete(),
                requestDto.isCanChange());

        //Map And Save In Database
        CaseStatChangePerm caseStatChangePerm = caseStatChangeMapper.toCaseStatChangePermEntity(requestDto,
                currentUserService.getCurrentUserId(), permissions.get(0), permissions.get(1), permissions.get(2), permissions.get(3));
        caseStatChangePermRepository.save(caseStatChangePerm);

        CrCaseStatChangePermRespDto resultResponse = caseStatChangeMapper.toCrCaseStatusResponse(caseStatChangePerm);

        return resultResponse;
    }

    @Override
    public void removePermission(Integer id) {
        logger.info("Called Remove Permission from Group For Change Status");
        caseStatChangePermRepository.deleteById(id);

    }

    private List<Integer> paramConverterToEntity(boolean read, boolean edit, boolean delete, boolean change){
        return List.of(
                defaultConverter.booleanToInteger(read),
                defaultConverter.booleanToInteger(edit),
                defaultConverter.booleanToInteger(delete),
                defaultConverter.booleanToInteger(change)
        );
    }

}
