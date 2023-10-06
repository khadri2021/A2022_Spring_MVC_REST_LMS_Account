package com.khadri.spring.mvc.rest.account.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khadri.spring.mvc.rest.account.dao.AccountDAO;
import com.khadri.spring.mvc.rest.account.dto.AccountDTO;
import com.khadri.spring.mvc.rest.account.entity.Account;

@Service
public class AccountService {

	@Autowired
	private AccountDAO accDao;

	public void addApplication(AccountDTO appDTO) {

		Function<AccountDTO, Account> convertDtoToEntity = (dto) -> {
			Account acc = new Account();
			acc.setFirstName(dto.getFirstName());
			acc.setLastName(dto.getLastName());
			acc.setName(dto.getName());
			acc.setAge(dto.getAge());
			acc.setEmail(dto.getEmail());
			acc.setPhoneNo(dto.getPhoneNo());
			acc.setAlternativePhoneNo(dto.getAlternativePhoneNo());
			acc.setAccountType(dto.getAccountType());
			acc.setCurrentAddress(dto.getCurrentAddress());
			acc.setPermanentAddress(dto.getPermanentAddress());
			acc.setAdhaarNo(dto.getAdhaarNo());
			acc.setPanNo(dto.getPanNo());
			acc.setNomineeName(dto.getNomineeName());
			acc.setNomineeAdhaarNo(dto.getNomineeAdhaarNo());
			acc.setNomineeAcNo(dto.getNomineeAcNo());
			acc.setAccountNumber(dto.getAccountNumber());
			acc.setCibilScore(dto.getCibil());
			return acc;
		};

		Account acc = Optional.ofNullable(appDTO).stream().map(convertDtoToEntity).findFirst().get();

		accDao.insertAccount(acc);

	}

	public List<AccountDTO> getAllAccounts() {

		List<Account> listOfCustomers = accDao.readAllAccounts();

		Function<Account, AccountDTO> convertEntityToDto = (account) -> {
			AccountDTO dto = new AccountDTO();

			dto.setFirstName(account.getFirstName());
			dto.setLastName(account.getLastName());
			dto.setName(account.getName());
			dto.setAge(account.getAge());
			dto.setEmail(account.getEmail());
			dto.setPhoneNo(account.getPhoneNo());
			dto.setAlternativePhoneNo(account.getAlternativePhoneNo());
			dto.setAccountType(account.getAccountType());
			dto.setCurrentAddress(account.getCurrentAddress());
			dto.setPermanentAddress(account.getPermanentAddress());
			dto.setAdhaarNo(account.getAdhaarNo());
			dto.setPanNo(account.getPanNo());
			dto.setNomineeName(account.getNomineeName());
			dto.setNomineeAdhaarNo(account.getNomineeAdhaarNo());
			dto.setNomineeAcNo(account.getNomineeAcNo());
			dto.setAccountNumber(account.getAccountNumber());

			return dto;
		};

		List<AccountDTO> listOfAccountDto = listOfCustomers.stream().map(convertEntityToDto)
				.collect(Collectors.toList());

		return listOfAccountDto;
	}

	public AccountDTO getAccount(String pan) {

		Account foundAccount = accDao.readAccount(pan);

		Function<Account, AccountDTO> convertEntityToDto = (account) -> {
			AccountDTO dto = new AccountDTO();
			dto.setFirstName(account.getFirstName());
			dto.setLastName(account.getLastName());
			dto.setName(account.getName());
			dto.setAge(account.getAge());
			dto.setEmail(account.getEmail());
			dto.setPhoneNo(account.getPhoneNo());
			dto.setAlternativePhoneNo(account.getAlternativePhoneNo());
			dto.setAccountType(account.getAccountType());
			dto.setCurrentAddress(account.getCurrentAddress());
			dto.setPermanentAddress(account.getPermanentAddress());
			dto.setAdhaarNo(account.getAdhaarNo());
			dto.setPanNo(account.getPanNo());
			dto.setNomineeName(account.getNomineeName());
			dto.setNomineeAdhaarNo(account.getNomineeAdhaarNo());
			dto.setNomineeAcNo(account.getNomineeAcNo());

			return dto;
		};

		AccountDTO appAccountDTO = Optional.ofNullable(foundAccount).stream().map(convertEntityToDto).findFirst().get();

		return appAccountDTO;
	}

	public void modifyAccount(AccountDTO accountDTO) {

		Function<AccountDTO, Account> convertDtoToEntity = (dto) -> {
			Account acc = new Account();
			acc.setFirstName(dto.getFirstName());
			acc.setLastName(dto.getLastName());
			acc.setName(dto.getName());
			acc.setAge(dto.getAge());
			acc.setEmail(dto.getEmail());
			acc.setPhoneNo(dto.getPhoneNo());
			acc.setAlternativePhoneNo(dto.getAlternativePhoneNo());
			acc.setAccountType(dto.getAccountType());
			acc.setCurrentAddress(dto.getCurrentAddress());
			acc.setPermanentAddress(dto.getPermanentAddress());
			acc.setAdhaarNo(dto.getAdhaarNo());
			acc.setPanNo(dto.getPanNo());
			acc.setNomineeName(dto.getNomineeName());
			acc.setNomineeAdhaarNo(dto.getNomineeAdhaarNo());
			acc.setNomineeAcNo(dto.getNomineeAcNo());
			acc.setCibilScore(dto.getCibil());
			return acc;

		};

		Account account = Optional.ofNullable(accountDTO).stream().map(convertDtoToEntity).findFirst().get();

		accDao.modifyAccount(account);
	}

	public void deleteAccount(String pan) {

		accDao.deleteAccount(pan);

	}

	public Account viewAccount(String pan) {

		return accDao.viewAccount(pan);

	}

}
