package Remote;

import java.util.List;

import model.Response;
import model.User;

public interface UserRemote {
	public Response saveUser(User user);
	public Response saveUsers(List<User> userlist);
}
