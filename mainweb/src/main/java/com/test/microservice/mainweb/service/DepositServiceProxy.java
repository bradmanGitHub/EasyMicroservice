package com.test.microservice.mainweb.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.microservice.mainweb.vo.DepositResult;

@FeignClient(name = "deposit-service")
@RibbonClient(name = "deposit-service")
public interface DepositServiceProxy {

	@GetMapping("/doDeposit/{accounName}/{bathToDeposit}")
	public DepositResult doDeposit(@PathVariable("accounName") String accounName,
			@PathVariable("bathToDeposit") String bathToDeposit);

}
