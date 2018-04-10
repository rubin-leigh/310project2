package LoginFiles;

public class Response {
	private String loginStatus;
	private String completedStatus;
	private String signUpStat;
	
	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getCompletedStatus() {
		return completedStatus;
	}

	public void setCompletedStatus(String completedStatus) {
		this.completedStatus = completedStatus;
	} 
	
	public String getsignUpStatus() {
		return signUpStat;
	}

	public void setSignUpStatus(String signUpStatus) {
		this.signUpStat = signUpStatus;
	}
	
}
