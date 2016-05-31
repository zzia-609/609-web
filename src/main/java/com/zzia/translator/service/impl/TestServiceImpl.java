package com.zzia.translator.service.impl;

import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements com.zzia.translator.service.TestService {

	@Override
	public void test() {
		System.out.println(123);

	}

}
