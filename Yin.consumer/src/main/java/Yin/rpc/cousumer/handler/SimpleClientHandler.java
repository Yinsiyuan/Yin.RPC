package Yin.rpc.cousumer.handler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import Yin.rpc.cousumer.core.ResultFuture;
import Yin.rpc.cousumer.param.Response;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter implements ChannelHandler {
	private static final Executor exec = Executors.newFixedThreadPool(10);
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		final Object m = msg;
		if(msg.toString().equals("ping")){
			System.out.println("收到读写空闲ping,向服务端发送pong");
			ctx.channel().writeAndFlush("pong\r\n");
		}
		
		//设置response
//		final Response response = JSONObject.parseObject(msg.toString(), Response.class);
//		System.out.println("SimpleClientHandler中的Response:"+JSONObject.toJSONString(response));
		exec.execute(new Runnable() {
			
			public void run() {
				Response response = JSONObject.parseObject(m.toString(), Response.class);
				System.out.println("SimpleClientHandler中的Response:"+JSONObject.toJSONString(response));
				ResultFuture.receive(response);				
			}
		});
//		ResultFuture.receive(response);//通过response的ID可以在map中找到对应的Request,并为相应的request设置response,使得调用get()客户端得到结果
	}
	
}
