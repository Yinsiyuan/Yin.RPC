package handler;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import future.ResultFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import medium.Medium;
import model.Response;
import model.ServerRequest;

public class ServerHandler extends ChannelInboundHandlerAdapter  {
	private static final Executor exec = Executors.newFixedThreadPool(10);
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("服务器Handler:"+msg.toString());
//		ServerRequest serverRequest = JSONObject.parseObject(msg.toString(), ServerRequest.class);
//		System.out.println(serverRequest.getCommand());
		exec.execute(new Runnable() {
			
			@Override
			public void run() {
				ServerRequest serverRequest = JSONObject.parseObject(msg.toString(), ServerRequest.class);
				System.out.println(serverRequest.getCommand());
				Medium medium = Medium.newInstance();//生成中介者模式
				
				Response response = medium.process(serverRequest);
				
				//向客户端发送Resonse
				ctx.channel().writeAndFlush(JSONObject.toJSONString(response)+"\r\n");
			}
		});
//		Medium medium = Medium.newInstance();//生成中介者模式
//		
//		Response response = medium.process(serverRequest);
//		
//		//向客户端发送Resonse
//		ctx.channel().writeAndFlush(JSONObject.toJSONString(response)+"\r\n");
		
	}

//	@Override
//	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//		
//		if(evt instanceof IdleStateEvent){
//			IdleStateEvent event = (IdleStateEvent)evt;
//			
//			if(event.state().equals(IdleState.READER_IDLE)){
//				System.out.println("读空闲");
//			}
//			if(event.state().equals(IdleState.WRITER_IDLE)){
//				System.out.println("写空闲");
//			}
//			if(event.state().equals(IdleState.ALL_IDLE)){
//				System.out.println("读写空闲");
//				ctx.channel().writeAndFlush("ping\r\n");
//			}
//		}
//	}
	
	
	
}
