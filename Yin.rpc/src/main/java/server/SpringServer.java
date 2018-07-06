package server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import service.UserService;

@Configuration
@ComponentScan("\\")
public class SpringServer {
	public static void main(String[] args) {
		ApplicationContext context = 
		          new AnnotationConfigApplicationContext(SpringServer.class);
		
	}
}
