package com.cheetahapps.sales.problem;

import java.net.URI;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import lombok.Getter;

public class NoDataFoundProblem extends AbstractThrowableProblem {
 
   
	private static final long serialVersionUID = 1L;
	private static final URI TYPE
      = URI.create("https://nirvaanacrm.com/not-found");
	@Getter
	private final String errorCode = "DATA-001";
 
    public NoDataFoundProblem(String msg) {
        super(
          TYPE,
          "No data found",
          Status.NOT_FOUND,
          msg);
    }
 
}