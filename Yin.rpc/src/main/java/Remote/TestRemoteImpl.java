package Remote;

import java.util.List;

import javax.annotation.Resource;

import annotation.Remote;
import model.Response;
import model.User;
import service.TestService;
import service.UserService;
import util.ResponseUtil;

@Remote
public class TestRemoteImpl implements TestRemote{
	
	@Resource
	private TestService service;
	
	public Response testUser(User user){
		service.test(user);
		Response response = ResponseUtil.createSuccessResponse(user);
		
		return response;
	}
	
//	public Response saveUsers(List<User> userlist){
//		service.saveUSerList(userlist);
//		Response response = ResponseUtil.createSuccessResponse(userlist);
//		
//		return response;
//	}
}
