package org.jxkh.security.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jxkh.security.model.SysUsers;

public interface SysUsersDao {

	public List<String> loadUserAuthorities(String userName);

	public SysUsers selectSysUsers(String userId);

	public void insertSysUsers(SysUsers users);

	public SysUsers findByUserAccount(String userAccount);

	public void deleteSysUsers(int userId);

	public SysUsers selectSysUsers(int userId);

	public void updateSysUser(SysUsers users);

	public List<SysUsers> selectUsers(String userId);

	public void deleteUsers(String userId);

	public void insertUsers(SysUsers sysUsers);

	public void updateUsers(SysUsers sysUsers);

	public void updateUserStatus(@Param("userId") String userId,
			@Param("status") String status);

	public String onlineUsers();

	public List<SysUsers> selectAllUsers(SysUsers sysUsers);

	public SysUsers getUserByAccount(String userAccount);

	public int selectAllUsersCount(SysUsers sysUsers);

//	public void updateMaileUserPwd(SysUsers users);

//	public void insertMailUser(SysUsers sysUsers);

//	public void deleteMailUser(String userName);

	public SysUsers checkLoginYwgl(Map<String, String> map);

	public SysUsers getUserInfobymobile(String userAccount);

	public int userifexist(String userId);

	public int useraccountifexist(String userAccount);

	public int userinfoifexist(Map<String, String> map);

	public int mailUserExit(String username);

}
