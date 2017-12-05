package pj2;
import java.sql.Timestamp;
public class User {
    private int iduser;
    private String username;
    private String nickname;
    private String password;
    private Timestamp regTime;
    private String comefrom;
    private String iconurl;
    private int fans;
    private int focusing;
    private int mbnum;
    public User(String username, String nickname, String password, String comefrom,String iconurl,int focusing,int fans,int mbnum) {
		super();
		this.username = username;
		this.nickname=nickname;
		this.password = password;
		this.comefrom = comefrom;
		this.iconurl = iconurl;
		this.focusing = focusing;
		this.fans = fans;
		this.mbnum = mbnum;
    }
    //TODO            
    public User(int iduser, String username, String nickname, String password, String comefrom,String iconurl,int focusing,int fans,int mbnum) {
		super();
		this.iduser = iduser;
		this.username = username;
		this.nickname=nickname;
		this.password = password;
		this.comefrom = comefrom;
		this.iconurl = iconurl;
		this.focusing = focusing;
		this.fans = fans;
		this.mbnum = mbnum;
    }
   
    public int getIduser() {
    	return iduser;
    }
    public void setIduser(int iduser) {
    	this.iduser = iduser;
    }
    public String getUsername() {
    	return username;
    }
    public void setUsername(String username) {
    	this.username = username;
    }
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    public Timestamp getRegTime() {
    	return regTime;
    }
    public void setRegTime(Timestamp regTime) {
    	this.regTime = regTime;
    }
    public String getComefrom() {
    	return comefrom;
    }
    public void setComefrom(String comefrom) {
    	this.comefrom = comefrom;
    }
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
	public String getIconurl() {
		return iconurl;
	}
	public void setFans(int fans) {
		this.fans = fans;
	}
	public int getFans() {
		return fans;
	}
	public void setFocusing(int focusing) {
		this.focusing = focusing;
	}
	public int getFocusing() {
		return focusing;
	}
	public void setMbnum(int mbnum) {
		this.mbnum = mbnum;
	}
	public int getMbnum() {
		return mbnum;
	}
}
