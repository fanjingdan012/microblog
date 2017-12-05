package pj2;


import java.sql.Timestamp;

public class Comment {
	private int idcomment;
	private String txt;
	private int idpublisher;
	private int idtargetmicroblog;
	private Timestamp time;
	private String publishernickname;
	public Comment(int idcomment,String txt, int idpublisher, int idtargetmicroblog, Timestamp time,String publishernickname){
		super();
		this.idcomment=idcomment;
		this.txt=txt;
		this.idpublisher = idpublisher;
		this.idtargetmicroblog = idtargetmicroblog;
		this.time= time;
		this.setPublishernickname(publishernickname);
	}
	public Comment(String txt, int idpublisher, int idtargetmicroblog, Timestamp time,String publishernickname){
		super();
		this.txt=txt;
		this.idpublisher = idpublisher;
		this.idtargetmicroblog = idtargetmicroblog;
		this.time= time;
		this.setPublishernickname(publishernickname);
	}
	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
	}
	public int getIdcomment() {
		return idcomment;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getTxt() {
		return txt;
	}
	public void setIdpublisher(int idpublisher) {
		this.idpublisher = idpublisher;
	}
	public int getIdpublisher() {
		return idpublisher;
	}
	public void setIdtargetmicroblog(int idtargetmicroblog) {
		this.idtargetmicroblog = idtargetmicroblog;
	}
	public int getIdtargetmicroblog() {
		return idtargetmicroblog;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setPublishernickname(String publishernickname) {
		this.publishernickname = publishernickname;
	}
	public String getPublishernickname() {
		return publishernickname;
	}

}
