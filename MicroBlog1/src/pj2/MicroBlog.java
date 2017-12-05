package pj2;

import java.sql.Timestamp;

public class MicroBlog {
	private int idmicroblogs;
	private String txt;
	private int idpublisher;
	private int idoriginalmicroblog;
	private Timestamp time;
	private String publishericonurl;
	private String publishernickname;
	private int fwnum;
	private int commentnum;
	private String videourl;
	private String picurl;
	

	public MicroBlog(int idmicroblogs,String txt, int idpublisher, int idoriginalmicroblog,Timestamp time,String publishericonurl,String publishernickname,int fwnum, int commentnum,String videourl,String picurl){
		this.idmicroblogs = idmicroblogs;
		this.txt = txt;
		this.idpublisher =idpublisher;
		this.idoriginalmicroblog =idoriginalmicroblog;
		this.time = time;
		this.publishericonurl = publishericonurl;
		this.publishernickname = publishernickname;
		this.fwnum=fwnum;
		this.commentnum=commentnum;
		this.videourl=videourl;
		this.picurl=picurl;
	}
	public void setIdmicroblogs(int idmicroblogs) {
		this.idmicroblogs = idmicroblogs;
	}
	public int getIdmicroblogs() {
		return idmicroblogs;
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
	public void setIdoriginalmicroblog(int idoriginalmicroblog) {
		this.idoriginalmicroblog = idoriginalmicroblog;
	}
	public int getIdoriginalmicroblog() {
		return idoriginalmicroblog;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setPublishericonurl(String publishericonurl) {
		this.publishericonurl = publishericonurl;
	}
	public String getPublishericonurl() {
		return publishericonurl;
	}
	public void setPublishernickname(String publishernickname) {
		this.publishernickname = publishernickname;
	}
	public String getPublishernickname() {
		return publishernickname;
	}
	public void setFwnum(int fwnum) {
		this.fwnum = fwnum;
	}
	public int getFwnum() {
		return fwnum;
	}
	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}
	public int getCommentnum() {
		return commentnum;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getPicurl() {
		return picurl;
	}

}
