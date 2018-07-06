package medium;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import annotation.Remote;
import annotation.RemoteInvoke;
import controller.UserController;

@Component
public class InitMedium implements BeanPostProcessor{
	//中介者
	@Override
	public Object postProcessAfterInitialization(Object bean, String arg1) throws BeansException {
		if(bean.getClass().isAnnotationPresent(Remote.class)){
			Method[] methods = bean.getClass().getDeclaredMethods();//客户端那里用的是接口，所以getSuperClass
			for(Method m : methods){
//				String key = bean.getClass().getInterfaces()[0].getName()+"."+m.getName();
				String key = m.getName();//修改
				HashMap<String, BeanMethod> map = Medium.mediamap;
				BeanMethod beanMethod = new BeanMethod();
				beanMethod.setBean(bean);
				beanMethod.setMethod(m);
				map.put(key,beanMethod);
				System.out.println(key);
			}
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String arg1) throws BeansException {
		
		
		return bean;
	}

}
