package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhoneTransferMapper {
    PhoneTransferMapper PHONE_TRANSFER_MAPPER = Mappers.getMapper(PhoneTransferMapper.class);
    PhoneTransferDto entityToDtoPhoneTransfer(Phone phoneTransferEntity);
    @Mapping(target = "id", ignore = true)
    Phone dtoToEntityPhoneTransfer(PhoneTransferDto phoneTransferDto);

}