package com.cheetahapps.sales.problem;

import java.net.URI;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import lombok.Getter;

public class DeleteProblem extends AbstractThrowableProblem {
 
   
	private static final long serialVersionUID = 1L;
	private static final URI TYPE
      = URI.create("https://nirvaanacrm.com/error");
	@Getter
	private final String errorCode = "DATA-003";
 
    public DeleteProblem(String msg) {
        super(
          TYPE,
          "Issue with delete",
          Status.NOT_FOUND,
          msg);
    }
 
}