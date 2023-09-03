package cl.bci.user.crud.service.Impl;


import cl.bci.user.crud.converters.PhoneEntity2Vo;
import cl.bci.user.crud.model.PhoneVo;
import cl.bci.user.crud.repository.PhoneRepository;
import cl.bci.user.crud.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    PhoneEntity2Vo phoneEntity2Vo;

    @Override
    public List<PhoneVo> getPhones(final UUID userId) {
        return phoneRepository.findByUserId(userId).stream().map(phoneEntity2Vo::convert).collect(Collectors.toList());
    }
}
