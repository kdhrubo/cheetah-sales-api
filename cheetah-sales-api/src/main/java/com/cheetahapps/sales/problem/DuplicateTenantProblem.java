package com.cheetahapps.sales.problem;

import java.net.URI;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import lombok.Getter;

public class DuplicateTenantProblem extends AbstractThrowableProblem {
 
   
	private static final long serialVersionUID = 1L;
	private static final URI TYPE
      = URI.create("https://nirvaanacrm.com/tenant/duplicate");
	@Getter
	private final String errorCode = "USER-002";
 
    public DuplicateTenantProblem(String msg) {
        super(
          TYPE,
          "Duplicate Company",
          Status.CONFLICT,
          msg);
    }
 
}