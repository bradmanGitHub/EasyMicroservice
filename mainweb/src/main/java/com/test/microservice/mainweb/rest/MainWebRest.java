package com.test.microservice.mainweb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.microservice.mainweb.service.DepositServiceProxy;
import com.test.microservice.mainweb.service.WithdrawServiceProxy;
import com.test.microservice.mainweb.vo.DepositResult;
import com.test.microservice.mainweb.vo.TransferResult;
import com.test.microservice.mainweb.vo.WithdrawResult;

@RestController
public class MainWebRest {
	
	@Autowired
	private DepositServiceProxy depositServiceProxy;
	
	@Autowired
	private WithdrawServiceProxy withdrawServiceProxy;
	
	@GetMapping("/doDeposit/{accountName}/{bathToDeposit}")
	public DepositResult doDeposit(@PathVariable("bathToDeposit") String bathToDeposit,
			@PathVariable("accountName") String accountName){
		DepositResult depositResult = depositServiceProxy.doDeposit(accountName, bathToDeposit);
        return depositResult;
	}
	
	@GetMapping("doWithdraw/{accountName}/{bathToWithdraw}")
	public WithdrawResult doWithDraw(@PathVariable("bathToWithdraw") String bathToWithdraw,
			@PathVariable("accountName") String accountName){
		WithdrawResult withdrawResult = withdrawServiceProxy.doWithDraw(accountName, bathToWithdraw);
		return withdrawResult;
	}
	
	@GetMapping("doTransfer/{accountNameFrom}/{accountNameTo}/{bathToTransfer}")
	public TransferResult doTransfer(@PathVariable("accountNameFrom") String accountNameFrom, 
			@PathVariable("accountNameTo") String accountNameTo,
			@PathVariable("bathToTransfer") String bathToTransfer){
		
		//withDraw from accountNameFrom
		WithdrawResult withdrawResult = withdrawServiceProxy.doWithDraw(accountNameFrom, bathToTransfer);
		
		//deposit to accountNameTo
		DepositResult depositResult = depositServiceProxy.doDeposit(accountNameTo, bathToTransfer);
		
		TransferResult transerResult = new TransferResult();
		transerResult.setStatus("Trnsfer : "+bathToTransfer+" done.");
		transerResult.setWithdrawPort(withdrawResult.getPort());
		transerResult.setDepositPort(depositResult.getPort());
		
		return transerResult;
	}
}
