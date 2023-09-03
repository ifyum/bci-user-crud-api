package cl.bci.user.crud.converters;

import cl.bci.user.crud.entity.Phone;
import cl.bci.user.crud.model.PhoneVo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PhoneEntity2Vo implements Converter<Phone, PhoneVo> {
    @Override
    public PhoneVo convert(Phone phone) {
        PhoneVo phoneVo = new PhoneVo();
        phoneVo.setNumber(phone.getNumber());
        phoneVo.setCountryCode(phone.getCountryCode());
        phoneVo.setCityCode(phone.getCityCode());
        return phoneVo;
    }
}
