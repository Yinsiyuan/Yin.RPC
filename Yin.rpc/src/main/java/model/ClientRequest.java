package model;

import java.util.concurrent.atomic.AtomicLong;

public class ClientRequest {
	private Long id ;
	private Object content;//方法参数
	private static AtomicLong realID = new AtomicLong(0);
	private String command;//media.map里的key
	
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ClientRequest(){
		id =  realID.incrementAndGet();
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}
	
	
}
