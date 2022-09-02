package site.metacoding.red.domain.users;

import java.util.List;

public interface UsersDao {
	public void insert(Users users); // DTO 생각해보기
	public Users findById(Integer id);
	public List<Users> findAll();
	public void update(Users users); // DTO 생각해보기
	public void delete(Integer id);
}
