package com.khadri.spring.mvc.rest.account.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.khadri.spring.mvc.rest.account.entity.Account;

@Repository
public class AccountDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertAccount(Account acc) {
		System.out.println("Entered into AccountDAO : insertAccount(-)");

		String sql = "insert into ACCOUNT_DATA(FIRST_NAME,LAST_NAME,FATHER_NAME,AGE,EMAIL,PHONE_NUMBER,ALTERNATIVE_PHONE_NUMBER,ACCOUNT_TYPE,CURRENT_ADDRESS,PERMANENT_ADDRESS,ADHAAR_NUMBER,PAN_NUMBER,NOMINEE_NAME,NOMINEE_ADHAAR_NUMBER,NOMINEE_ACCOUNT_NUMBER,ACCOUNT_NUMBER) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		String sql1 = "insert into CIBIL_DATA values(?,?,?) ";

		int result = jdbcTemplate.update(sql, acc.getFirstName(), acc.getLastName(), acc.getName(), acc.getAge(),
				acc.getEmail(), acc.getPhoneNo(), acc.getAlternativePhoneNo(), acc.getAccountType(),
				acc.getCurrentAddress(), acc.getPermanentAddress(), acc.getAdhaarNo(), acc.getPanNo(),
				acc.getNomineeName(), acc.getNomineeAdhaarNo(), acc.getNomineeAcNo(), acc.getAccountNumber());

		int result1 = jdbcTemplate.update(sql1, acc.getAdhaarNo(), acc.getPanNo(), acc.getCibilScore());

		System.out.println(result + " Rows Inserted Successfully in customer_register");
		System.out.println(result1 + " Rows Inserted Successfully in CIBIL_DATA");

	}

	public List<Account> readAllAccounts() {
		System.out.println("Entered into AccountDAO : readAllAccounts(-)");

		String sql = "select * from ACCOUNT_DATA";
		RowMapper<Account> rowMapper = rowMapperForAccount();
		List<Account> listOfStudent = jdbcTemplate.query(sql, rowMapper);

		return listOfStudent;

	}

	public void modifyAccount(Account acc) {
		System.out.println("Entered into AccountDAO : modifyAccount(-)");

		String sql = "update ACCOUNT_DATA set FIRST_NAME=?, LAST_NAME=?,FATHER_NAME=?,AGE=?,EMAIL=?,PHONE_NUMBER=?,ALTERNATIVE_PHONE_NUMBER=?,ACCOUNT_TYPE=?,CURRENT_ADDRESS=?,PERMANENT_ADDRESS=?,NOMINEE_NAME=?,NOMINEE_ADHAAR_NUMBER=?,NOMINEE_ACCOUNT_NUMBER=? where ADHAAR_NUMBER=? and PAN_NUMBER=?;";
		int result = jdbcTemplate.update(sql, acc.getFirstName(), acc.getLastName(), acc.getName(), acc.getAge(),
				acc.getEmail(), acc.getPhoneNo(), acc.getAlternativePhoneNo(), acc.getAccountType(),
				acc.getCurrentAddress(), acc.getPermanentAddress(), acc.getNomineeName(), acc.getNomineeAdhaarNo(),
				acc.getNomineeAcNo(), acc.getAdhaarNo(), acc.getPanNo());
		System.out.println(result + " Rows Updated Successfully");

	}

	public Account readAccount(String pan) {
		System.out.println("Entered into AccountDAO : readAccount(-)");

		String sql = "select * from ACCOUNT_DATA where PAN_NUMBER='" + pan + "'";
		RowMapper<Account> rowMapper = rowMapperForAccount();
		Account acc = jdbcTemplate.queryForObject(sql, rowMapper);

		return acc;
	}

	private RowMapper<Account> rowMapperForAccount() {

		Optional<RowMapper<Account>> optional = Optional.ofNullable((resultSet, rowNum) -> {
			Account acc = new Account();

			acc.setFirstName(resultSet.getString("FIRST_NAME"));
			acc.setLastName(resultSet.getString("LAST_NAME"));
			acc.setName(resultSet.getString("FATHER_NAME"));
			acc.setAge(resultSet.getString("AGE"));
			acc.setEmail(resultSet.getString("EMAIL"));
			acc.setPhoneNo(resultSet.getString("PHONE_NUMBER"));
			acc.setAlternativePhoneNo(resultSet.getString("ALTERNATIVE_PHONE_NUMBER"));
			acc.setAccountType(resultSet.getString("ACCOUNT_TYPE"));
			acc.setCurrentAddress(resultSet.getString("CURRENT_ADDRESS"));
			acc.setPermanentAddress(resultSet.getString("PERMANENT_ADDRESS"));
			acc.setAdhaarNo(resultSet.getString("ADHAAR_NUMBER"));
			acc.setPanNo(resultSet.getString("PAN_NUMBER"));
			acc.setNomineeName(resultSet.getString("NOMINEE_NAME"));
			acc.setNomineeAdhaarNo(resultSet.getString("NOMINEE_ADHAAR_NUMBER"));
			acc.setNomineeAcNo(resultSet.getString("NOMINEE_ACCOUNT_NUMBER"));
			acc.setAccountNumber(resultSet.getString("ACCOUNT_NUMBER"));

			return acc;
		});

		return optional.isEmpty() ? null : optional.get();
	}

	public void deleteAccount(String panNo) {
		System.out.println("Entered into AccountDAO : deleteAccount(-)");

		String sql = "delete from ACCOUNT_DATA where PAN_NUMBER='" + panNo+"'";
		int result = jdbcTemplate.update(sql);
		System.out.println(result + " Record deleted ");
	}

	public Account viewAccount(String panNo) {
		System.out.println("Entered into AccountDAO : viewAccount(-)");

		String sql = "select * from ACCOUNT_DATA where PAN_NUMBER='" + panNo + "'";
		RowMapper<Account> rowMapper = rowMapperForAccount();
		Account acc = jdbcTemplate.queryForObject(sql, rowMapper);

		return acc;

	}

}
