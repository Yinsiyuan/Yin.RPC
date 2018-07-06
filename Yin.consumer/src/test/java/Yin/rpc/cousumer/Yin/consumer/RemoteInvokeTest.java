package Yin.rpc.cousumer.Yin.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import Yin.rpc.cousumer.annotation.RemoteInvoke;
import Yin.rpc.cousumer.param.Response;
import Yin.rpc.user.TestRemote;
import Yin.rpc.user.User;
import Yin.rpc.user.UserRemote;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RemoteInvokeTest.class)
@ComponentScan("\\")
public class RemoteInvokeTest {
	public static List<User> list = new ArrayList<User>();
	@RemoteInvoke
	public static TestRemote userremote;
	public static User user;
	public static Long count = 0l;
	
	static{
		user = new User();
		user.setId(1000);
		user.setName("张三");
	}
	@Test
	public void testSaveUser(){
		User user = new User();
		user.setId(1000);
		user.setName("张三");
		userremote.testUser(user);
//		Long start = System.currentTimeMillis();
//		for(int i=1;i<10000;i++){
//			userremote.saveUser(user);
//		}
//		Long end = System.currentTimeMillis();
//		Long count = end-start;
//		System.out.println("总计时:"+count/1000+"秒");
		
	}			


}
