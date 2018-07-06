package handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import future.ResultFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import model.Response;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter implements ChannelHandler {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg.toString().equals("ping")){
			System.out.println("收到读写空闲ping,向服务端发送pong");
			ctx.channel().writeAndFlush("pong\r\n");
		}
		
		//设置response
		Response response = JSONObject.parseObject(msg.toString(), Response.class);
		ResultFuture.receive(response);//通过response的ID可以在map中找到对应的Request,并为相应的request设置response,使得调用get()客户端得到结果
	}
	
}
