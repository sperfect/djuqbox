package gr.sperfect.djuqbox.webapp.shared.data;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class UserRoomRole extends BaseDataClass {

	public UserRoomRole() {
		// needed by json
		// needed by Objectify
	}

	private Long userId;
	private Long roomId;
	private Long roleId;

	Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getUserId() {
		return userId;
	}

	public  void setUserId(Long userId) {
		this.userId = userId;
	}

	public  Long getRoleId() {
		return roleId;
	}

	public  void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
