package com.test.microservice.deposit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.microservice.deposit.vo.DepositResult;

@RestController
public class DepositRest {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@GetMapping("/doDeposit/{accounName}/{bathToDeposit}")
	public DepositResult doDeposit(@PathVariable("accounName") String accounName,
			@PathVariable("bathToDeposit") String bathToDeposit
			){
		
		DepositResult depositResult = new DepositResult();
		depositResult.setStatus("Deposit Success:"+bathToDeposit);
		depositResult.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		//insert transaction
		String sqlForTransaction = "INSERT INTO MONEY_TRANSACTION (transaction_type, from_account, bath) VALUES ('deposit','"+accounName+"',"+bathToDeposit+")";
		jdbcTemplate.execute(sqlForTransaction);
		
		//udpate account
		String sqlForAccount = "update MONEY_ACCOUNT SET TOTAL_MONEY = TOTAL_MONEY+"+bathToDeposit+" WHERE FIRST_NAME = '"+accounName+"'";
		jdbcTemplate.execute(sqlForAccount);
		
		return depositResult;
	}
}
