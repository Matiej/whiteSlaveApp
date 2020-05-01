package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.dto.BankAccountQueryDto;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.dto.CompanyPersonsQueryDto;
import com.whiteslave.whiteslaveApp.archiveReport.entity.AuthorizedClerksResponseEntity;
import com.whiteslave.whiteslaveApp.archiveReport.entity.BankAccountEntity;
import com.whiteslave.whiteslaveApp.archiveReport.entity.PartnersResponseEntity;
import com.whiteslave.whiteslaveApp.archiveReport.entity.RepresentativesResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Entity2DtoQueryViewConverter {

    public List<CompanyPersonsQueryDto> convert2CompanyPersonsQueryDto(List<Object> personsList) {
        List<CompanyPersonsQueryDto> companyPersonsQueryDtoList = new ArrayList<>();
        personsList.forEach(person -> {
            if (person instanceof RepresentativesResponseEntity) {
                RepresentativesResponseEntity reprPerson = (RepresentativesResponseEntity) person;
                companyPersonsQueryDtoList
                        .add(CompanyPersonsQueryDto.builder()
                                .id(reprPerson.getId())
                                .companyName(reprPerson.getCompanyName())
                                .firstName(reprPerson.getFirstName())
                                .lastName(reprPerson.getLastName())
                                .nip(reprPerson.getNip())
                                .pesel(reprPerson.getPesel())
                                .build());
            }

            if (person instanceof AuthorizedClerksResponseEntity) {
                AuthorizedClerksResponseEntity acPerson = (AuthorizedClerksResponseEntity) person;
                companyPersonsQueryDtoList
                        .add(CompanyPersonsQueryDto.builder()
                                .id(acPerson.getId())
                                .companyName(acPerson.getCompanyName())
                                .firstName(acPerson.getFirstName())
                                .lastName(acPerson.getLastName())
                                .nip(acPerson.getNip())
                                .pesel(acPerson.getPesel())
                                .build());
            }

            if (person instanceof PartnersResponseEntity) {
                PartnersResponseEntity partnerPerson = (PartnersResponseEntity) person;
                companyPersonsQueryDtoList
                        .add(CompanyPersonsQueryDto.builder()
                                .id(partnerPerson.getId())
                                .companyName(partnerPerson.getCompanyName())
                                .firstName(partnerPerson.getFirstName())
                                .lastName(partnerPerson.getLastName())
                                .nip(partnerPerson.getNip())
                                .pesel(partnerPerson.getPesel())
                                .build());
            }
        });
        return companyPersonsQueryDtoList;
    }

    public List<BankAccountQueryDto> convert2BankAccountQueryDto(List<BankAccountEntity> bankAccountEntityList) {
        return bankAccountEntityList
                .stream()
                .map(bankAccountEntity -> BankAccountQueryDto
                        .builder()
                        .id(bankAccountEntity.getId())
                        .bankAccount(bankAccountEntity.getBankAccountNumber())
                        .build())
                .collect(Collectors.toList());
    }
}
