package factory;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZooKeeperFactory {
	public static CuratorFramework client;
	
	public static CuratorFramework getClient(){
		if(client == null){
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);//重试机制
			client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
			client.start();
			
		}
		
		return client;
	}
	
	public static void main(String[] args) {
		try {
			String s = "balabala";
			CuratorFramework client = ZooKeeperFactory.getClient();
			if(client != null){
				client.create().forPath("/netty1",s.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
}
