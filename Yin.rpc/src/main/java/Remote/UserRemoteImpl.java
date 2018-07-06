package Remote;

import java.util.List;

import javax.annotation.Resource;

import annotation.Remote;
import model.Response;
import model.User;
import service.UserService;
import util.ResponseUtil;

@Remote
public class UserRemoteImpl implements UserRemote{
	
	@Resource
	private UserService service;
	
	public Response saveUser(User user){
		service.saveUSer(user);
		Response response = ResponseUtil.createSuccessResponse(user);
		
		return response;
	}
	
	public Response saveUsers(List<User> userlist){
		service.saveUSerList(userlist);
		Response response = ResponseUtil.createSuccessResponse(userlist);
		
		return response;
	}
}
