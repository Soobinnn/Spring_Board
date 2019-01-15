package com.example.spring2.model.member.dto;

import java.sql.Date;
 
public class MemberVO 
{
    private String userId;
    private String userPw;
    private String userName; 
    private String userEmail; 
    private Date userRegdate; // java.sql.Date
    private Date userUpdatedate;
    private String authkey;
    private int authstatus;
    private String userAuthCode;
    
    public String getUserAuthCode() {
		return userAuthCode;
	}
	public void setUserAuthCode(String userAuthCode) {
		this.userAuthCode = userAuthCode;
	}
	public int getAuthstatus() {
		return authstatus;
	}
	public void setAuthstatus(int authstatus) {
		this.authstatus = authstatus;
	}
	// Getter/Setter
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserPw() {
        return userPw;
    }
    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public Date getUserRegdate() {
        return userRegdate;
    }
    public void setUserRegdate(Date userRegdate) {
        this.userRegdate = userRegdate;
    }
    public Date getUserUpdatedate() {
        return userUpdatedate;
    }
    public void setUserUpdatedate(Date userUpdatedate) {
        this.userUpdatedate = userUpdatedate;
    }
    
    public String getAuthkey() {
		return authkey;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	// toString()
    @Override
    public String toString() 
    {
        return "MemberVO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userEmail="
                + userEmail + ", userRegdate=" + userRegdate + ", userUpdatedate=" + userUpdatedate + "]";
    }
    
}