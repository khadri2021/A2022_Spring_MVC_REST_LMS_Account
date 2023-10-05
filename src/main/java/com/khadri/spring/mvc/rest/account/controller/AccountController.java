package com.khadri.spring.mvc.rest.account.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.khadri.spring.mvc.rest.account.dto.AccountDTO;
import com.khadri.spring.mvc.rest.account.entity.Account;
import com.khadri.spring.mvc.rest.account.form.AccountForm;
import com.khadri.spring.mvc.rest.account.service.AccountService;
import com.khadri.spring.mvc.rest.account.utility.AccountNumber;

@RestController
@RequestMapping("form")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/add/register")
	public String addApplicationForm(AccountForm accountForm) {

		Function<AccountForm, AccountDTO> convertFormToDto = (form) -> {
			AccountDTO dto = new AccountDTO();
			dto.setFirstName(form.getfName());
			dto.setLastName(form.getlName());
			dto.setName(form.getName());
			dto.setAge(form.getsAge());
			dto.setEmail(form.getEmail());
			dto.setPhoneNo(form.getPhone());
			dto.setAlternativePhoneNo(form.getAlterPhone());
			dto.setAccountType(form.getType());
			dto.setCurrentAddress(form.getcAddress());
			dto.setPermanentAddress(form.getpAddress());
			dto.setAdhaarNo(form.getAdhaar());
			dto.setPanNo(form.getPan());
			dto.setNomineeName(form.getnName());
			dto.setNomineeAdhaarNo(form.getAno());
			dto.setNomineeAcNo(form.getAno());
			dto.setAccountNumber(AccountNumber.generateAccountNumber());
			dto.setCibil(form.getCibil());
			return dto;
		};

		AccountDTO accDTO = Optional.ofNullable(accountForm).stream().map(convertFormToDto).findFirst().get();

		accountService.addApplication(accDTO);

		return "inserted record!!!";
	}

	@PostMapping("/modify/register")
	public String modifyAccountForm(AccountForm accountForm) {

		Function<AccountForm, AccountDTO> convertFormToDto = (form) -> {
			AccountDTO dto = new AccountDTO();

			dto.setFirstName(form.getfName());
			dto.setLastName(form.getlName());
			dto.setName(form.getName());
			dto.setAge(form.getsAge());
			dto.setEmail(form.getEmail());
			dto.setPhoneNo(form.getPhone());
			dto.setAlternativePhoneNo(form.getAlterPhone());
			dto.setAccountType(form.getType());
			dto.setCurrentAddress(form.getcAddress());
			dto.setPermanentAddress(form.getpAddress());
			dto.setAdhaarNo(form.getAdhaar());
			dto.setPanNo(form.getPan());
			dto.setNomineeName(form.getnName());
			dto.setNomineeAdhaarNo(form.getAno());
			dto.setNomineeAcNo(form.getAco());
			dto.setCibil(form.getCibil());
			return dto;
		};

		AccountDTO accountDTO = Optional.ofNullable(accountForm).stream().map(convertFormToDto).findFirst().get();

		accountService.modifyAccount(accountDTO);

		return "updated record!!!";
	}

	@RequestMapping("/view/all/registers")
	public ResponseEntity<List<AccountForm>> viewAccountForms() {

		List<AccountDTO> listOfAccountsDto = accountService.getAllAccounts();

		Function<AccountDTO, AccountForm> convertfromDtoToForm = (dto) -> {
			AccountForm form = new AccountForm();
			form.setfName(dto.getFirstName());
			form.setlName(dto.getLastName());
			form.setName(dto.getName());
			form.setsAge(dto.getAge());
			form.setEmail(dto.getEmail());
			form.setPhone(dto.getPhoneNo());
			form.setAlterPhone(dto.getAlternativePhoneNo());
			form.setType(dto.getAccountType());
			form.setcAddress(dto.getCurrentAddress());
			form.setpAddress(dto.getPermanentAddress());
			form.setAdhaar(dto.getAdhaarNo());
			form.setPan(dto.getPanNo());
			form.setnName(dto.getNomineeName());
			form.setAno(dto.getNomineeAdhaarNo());
			form.setAco(dto.getNomineeAcNo());
			form.setAcno(dto.getAccountNumber());
			form.setCibil(dto.getCibil());
			return form;
		};

		List<AccountForm> allAccounts = listOfAccountsDto.stream().map(convertfromDtoToForm)
				.collect(Collectors.toList());

		return new ResponseEntity<List<AccountForm>>(allAccounts, HttpStatus.OK);
	}

	@DeleteMapping("/delete/register")
	public String deleteCustomerForm(@RequestParam("pan") String pan) {
		accountService.deleteAccount(pan);

		return "deleted record!!!";
	}

	@GetMapping("/view/register")
	public ResponseEntity<Account> viewCustomerForm(@RequestParam("pan") String pan) {

		return new ResponseEntity<Account>(accountService.viewAccount(pan), HttpStatus.OK);

	}

}
