package Yin.rpc.cousumer.zk;

import java.util.HashSet;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

import Yin.rpc.cousumer.core.ChannelManager;
import Yin.rpc.cousumer.core.NettyClient;
import io.netty.channel.ChannelFuture;

public class ServerWatcher implements CuratorWatcher {

	public void process(WatchedEvent event) throws Exception {
		System.out.println("process------------------------");
		CuratorFramework client = ZooKeeperFactory.getClient();
		String path = event.getPath();
		client.getChildren().usingWatcher(this).forPath(path);
		List<String> newServerPaths = client.getChildren().forPath(path);
		System.out.println(newServerPaths);
		ChannelManager.realServerPath.clear();
		for(String p :newServerPaths){
			String[] str = path.split("#");
			ChannelManager.realServerPath.add(str[0]+"#"+str[1]);//去重
		}
		
		ChannelManager.clearChnannel();
		for(String realServer:ChannelManager.realServerPath){
			String[] str = realServer.split("#");
			ChannelFuture channnelFuture = NettyClient.b.connect(str[0], Integer.valueOf(str[1]));
			ChannelManager.addChnannel(channnelFuture);		
		}
	}
}
