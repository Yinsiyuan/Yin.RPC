package client;

import com.alibaba.fastjson.JSONObject;

import future.ResultFuture;
import handler.SimpleClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;
import model.ClientRequest;
import model.Response;

public class NettyClient {
	
	private static ChannelFuture f = null;
	
	static{
		String host = "localhost";
		int port = 8080;
		
		EventLoopGroup work = new NioEventLoopGroup();
		try {
		Bootstrap boot = new Bootstrap();
		boot.group(work)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<Channel>() {

						@Override
						protected void initChannel(Channel ch) throws Exception {
							ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
							ch.pipeline().addLast(new StringDecoder());//字符串解码器
							ch.pipeline().addLast(new StringEncoder());//字符串编码器
							ch.pipeline().addLast(new SimpleClientHandler());//业务逻辑处理处
						}
			});
		
			f = boot.connect(host, port).sync();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static Response send(ClientRequest request){
		f.channel().writeAndFlush(JSONObject.toJSONString(request)+"\r\n");
		ResultFuture future = new ResultFuture(request);
		return future.get();
	}
	
	public static void main(String[] args) {
//		long start = System.currentTimeMillis();
//		
//		long end = System.currentTimeMillis();
//		long count = end =start;
//		System.out.println("总计花费:"+count);

	}
	
}
