package com.test.microservice.mainweb.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.microservice.mainweb.vo.DepositResult;
import com.test.microservice.mainweb.vo.WithdrawResult;

@FeignClient(name = "withdraw-service")
@RibbonClient(name = "withdraw-service")
public interface WithdrawServiceProxy {

	@GetMapping("doWithdraw/{accounName}/{bathToWithdraw}")
	public WithdrawResult doWithDraw(@PathVariable("accounName") String accounName,
			@PathVariable("bathToWithdraw") String bathToWithdraw);

}
