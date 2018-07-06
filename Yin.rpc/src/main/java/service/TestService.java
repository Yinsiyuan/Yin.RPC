package service;

import org.springframework.stereotype.Service;

import model.User;

@Service
public class TestService {
	public void test(User user){
		System.out.println("调用了TestService.test");
	}
}
