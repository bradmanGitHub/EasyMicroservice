package com.test.microservice.withdraw.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.microservice.withdraw.vo.WithdrawResult;

@RestController
public class WithdrawRest {
	
	@Autowired
	Environment environment;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("doWithdraw/{accounName}/{bathToWithdraw}")
	public WithdrawResult doWithDraw(@PathVariable("bathToWithdraw") String bathToWithdraw,
			@PathVariable("accounName") String accounName){
		WithdrawResult withdrawResult = new WithdrawResult();
		withdrawResult.setStatus("Withdraw Success:"+bathToWithdraw);
		withdrawResult.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		//insert transaction
		String sqlForTransaction = "INSERT INTO MONEY_TRANSACTION (transaction_type, from_account, bath) VALUES ('withdraw','"+accounName+"',"+bathToWithdraw+")";
		jdbcTemplate.execute(sqlForTransaction);
		
		//update account
		String sqlForAccount = "update MONEY_ACCOUNT SET TOTAL_MONEY = TOTAL_MONEY-"+bathToWithdraw+" WHERE FIRST_NAME = '"+accounName+"'";
		jdbcTemplate.execute(sqlForAccount);
		
		return withdrawResult;
	}
}