package com.cheetahapps.sales.problem;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
class ExceptionHandling implements ProblemHandling {

	@Override
    public boolean isCausalChainsEnabled() {
        return true;
    }
	
}