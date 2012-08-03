package com.playlife.persistenceLayer.DAO.genericDAO;

import org.aopalliance.aop.Advice;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class FinderIntroductionAdvisor extends DefaultIntroductionAdvisor{
	private static final long serialVersionUID = 1L;

	public FinderIntroductionAdvisor(){
        super((Advice) new FinderIntroductionInterceptor());
    }
}
