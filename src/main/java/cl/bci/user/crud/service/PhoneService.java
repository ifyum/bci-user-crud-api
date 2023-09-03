package cl.bci.user.crud.service;


import cl.bci.user.crud.model.PhoneVo;

import java.util.List;
import java.util.UUID;

public interface PhoneService {
    List<PhoneVo> getPhones(final UUID userId);
}
