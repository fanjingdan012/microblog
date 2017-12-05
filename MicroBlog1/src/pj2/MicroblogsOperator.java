package pj2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MicroblogsOperator {
	private static final String connectionString ="jdbc:mysql://localhost:3306/microblog";
	private static final String dbUsername = "root";
	private static final String dbPassword = "207710";

	
	
	/**
	 * SELECT
	 */
	/*get mb from db microblogs*/	
	private static final String GetMBByIdpublisher = "SELECT * FROM `microblog`.`microblogs` where idpublisher = ? order by time desc";
	private static final String GetMBByIdmicroblogs = "SELECT * FROM `microblog`.`microblogs` where idmicroblogs = ?";
	private static final String GetMBByKeyword = "SELECT * FROM microblogs where   txt   like ? order by time desc";
	private static final String GetMBToSee = "SELECT * FROM `microblog`.`microblogs` where ";
	private static final String GetNewMB = "SELECT * FROM `microblog`.`microblogs` order by time desc limit ?";
	
	/**
	 * DELETE
	 */
	private static final String DelMBByIdmicroblogs = "DELETE FROM `microblog`.`microblogs` WHERE idmicroblogs =?";
	
	
	//the SQL statement which operates with microblogs
	private static final String FwMicroBlogSQL = "INSERT INTO `microblog`.`microblogs`(txt,idpublisher,idoriginalmicroblog,time,publishericonurl,publishernickname,videourl,picurl)VALUES(?,?,?,now(),?,?,?,?)";
	private static final String PublishMicroBlogSQL = "INSERT INTO `microblog`.`microblogs`(txt,idpublisher,time,publishericonurl,publishernickname,videourl,picurl)VALUES(?,?,now(),?,?,?,?)";
	private static final String GetAllMicroBlogs = "SELECT * FROM `microblog`.`microblogs` order by time desc limit 10";
	
	
	/**
	 * UPDATE
	 */
	private static final String UpdateMBFwnumA = "UPDATE `microblog`.`microblogs` SET fwnum = fwnum+1 where idmicroblogs = ?";
	private static final String UpdateUserMBnumA = "UPDATE `microblog`.`user` SET mbnum = mbnum+1 where iduser = ?";
	/**
	 * get microblogs sent by one user
	 * @param idpublisher
	 * @return
	 * @throws SQLException
	 */
	/*private static final String GetMicroBlogByIdpublisherSQL = "SELECT * FROM `microblog`.`microblogs` where idpublisher = ?";*/
	public static List<MicroBlog> getMBByIdpublisher(int idpublisher) throws SQLException {
		List<MicroBlog> microblogs = new ArrayList<MicroBlog>();
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(GetMBByIdpublisher);//prepare the statement according to the SQL statement.
			ps.setInt(1, idpublisher);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idmicroblogs = rs.getInt("idmicroblogs");
				String txt = rs.getString("txt");
				//int idpublisher = rs.getInt("idpublisher");
				int idoriginalmicroblog = rs.getInt("idoriginalmicroblog");
				Timestamp time = rs.getTimestamp("time");
				String publishericonurl = rs.getString("publishericonurl");
				String publishernickname = rs.getString("publishernickname");
				int fwnum = rs.getInt("fwnum");
				int commentnum = rs.getInt("commentnum");
				String videourl = rs.getString("videourl");
				String picurl = rs.getString("picurl");
				//add this User to the list
				microblogs.add(new MicroBlog(idmicroblogs, txt,idpublisher, idoriginalmicroblog, time,publishericonurl,publishernickname,fwnum,commentnum,videourl,picurl));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Notice!Always close the connection after using.
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return microblogs;
	}
	
	
	
	
	/**
	 * get mb by idmicroblogs
	 */
	public static MicroBlog getMBByIdmicroblogs(int idmicroblogs) throws SQLException {
		MicroBlog microblog = null;
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(GetMBByIdmicroblogs);//prepare the statement according to the SQL statement.
			ps.setInt(1, idmicroblogs);
			rs = ps.executeQuery();//Execute the Query
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs.next()) {
				//int idmicroblogs = rs.getInt("idmicroblogs");
				String txt = rs.getString("txt");
				int idpublisher = rs.getInt("idpublisher");
				int idoriginalmicroblog = rs.getInt("idoriginalmicroblog");
				Timestamp time = rs.getTimestamp("time");
				String publishericonurl = rs.getString("publishericonurl");
				String publishernickname = rs.getString("publishernickname");
				int fwnum = rs.getInt("fwnum");
				int commentnum = rs.getInt("commentnum");
				String videourl = rs.getString("videourl");
				String picurl = rs.getString("picurl");
				//add this User to the list
				microblog = new MicroBlog(idmicroblogs, txt,idpublisher, idoriginalmicroblog, time,publishericonurl,publishernickname,fwnum,commentnum,videourl,picurl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Notice!Always close the connection after using.
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return microblog;
	}
	
	
	
	
	/**
	 * insert the microblog to database
	 * @param txt
	 * @param idpublisher
	 * @param publishericonurl
	 * @param publishernickname
	 * @return
	 * @throws SQLException
	 */
	/*"INSERT INTO `microblog`.`microblogs`(txt,idpublisher,idoriginalmicroblog,time,publishericonurl,publishernickname,videourl,picurl)VALUES(?,?,?,now(),?,?,?,?)";*/
	public static boolean publishMicroBlog(String txt1, int idpublisher, int idoriginalmicroblog,int idfwmb,String publishericonurl,String publishernickname,String videourl,String picurl)throws SQLException{
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String [] txts = new String[41];
		    txts[2] = (String) txt1.replaceAll("<Î¢Ð¦>", "<img src=images/faces/F_1.gif>");
			txts[3] = (String) txts[2].replaceAll("<ÄÑ¹ý>", "<img src=images/faces/F_2.gif>");
			txts[4] = (String) txts[3].replaceAll("<Ï²»¶>", "<img src=images/faces/F_3.gif>");
			
			for (int i = 4;i<40;i++){
				txts[i+1] = txts[i].replaceAll("<F"+i+">", "<img src=images/faces/F_"+i+".gif>");
			}
            String txt = txts[40];
			if(idoriginalmicroblog==0){//not fw
				
				ps = c.prepareStatement(PublishMicroBlogSQL,Statement.RETURN_GENERATED_KEYS);
			    ps.setString(1, txt);
				ps.setInt(2, idpublisher);
				ps.setString(3, publishericonurl);
				ps.setString(4, publishernickname);
				ps.setString(5, videourl);
				ps.setString(6, picurl);
				System.out.println("publish");
			}
			else{
			    ps = c.prepareStatement(FwMicroBlogSQL,Statement.RETURN_GENERATED_KEYS);
			    ps.setString(1, txt);
				ps.setInt(2, idpublisher);
				ps.setInt(3,idoriginalmicroblog );
				ps.setString(4, publishericonurl);
				ps.setString(5, publishernickname);
				ps.setString(6, videourl);
				ps.setString(7, picurl);
		    }
			
			
			System.out.println(ps);
			ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
			if (rs.next()){
				if(idoriginalmicroblog==0){//not fw
					ps = c.prepareStatement(UpdateUserMBnumA,Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1,idpublisher );
					ps.executeUpdate();
					return true;
				}
				else{
					//original mb.fwnum++
					ps = c.prepareStatement(UpdateMBFwnumA,Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idoriginalmicroblog);
					ps.executeUpdate();
				    rs = ps.getGeneratedKeys();
					
					if(idfwmb!=idoriginalmicroblog){
						//fwmb.fwnum++
					    ps = c.prepareStatement(UpdateMBFwnumA,Statement.RETURN_GENERATED_KEYS);
						ps.setInt(1, idfwmb);
						ps.executeUpdate();
					    rs = ps.getGeneratedKeys();					
					}
				    
				    
				    //user mbnum++
					ps = c.prepareStatement(UpdateUserMBnumA,Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1,idpublisher );
					ps.executeUpdate();
				    return true;
				}		    
			}			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//Notice!Always close the connection after using.
			if (ps != null) {
				ps.close();
			}
			if(rs != null){
				rs.close();
			}
			if (c != null) {
				c.close();
			}	
			
		}
		return false;
	}

	/**
	 * 
	 * @param iduser
	 * @return
	 * @throws SQLException
	 */
	public static List<MicroBlog> getMBToSee(int iduser) throws SQLException {
		List<MicroBlog> microblogs = new ArrayList<MicroBlog>();
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<Integer> focusingIdusers = RelationOperator.getAllFocusingIdusers(iduser);
			PreparedStatement ps1 = null;
			String SQL = GetMBToSee;
			
			for(int focusingIduser:focusingIdusers){
				SQL = SQL+"idpublisher = "+focusingIduser+" or ";
			}
			SQL=SQL+"idpublisher = "+iduser+" order by time desc limit 20";			
			System.out.println(SQL);
			ps1 = c.prepareStatement(SQL);
			ResultSet rs1 = ps1.executeQuery();//Execute the Query
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs1.next()) {
				int idmicroblogs = rs1.getInt("idmicroblogs");
				String txt = rs1.getString("txt");
				int idpublisher = rs1.getInt("idpublisher");
				int idoriginalmicroblog = rs1.getInt("idoriginalmicroblog");
				Timestamp time = rs1.getTimestamp("time");
				String publishericonurl = rs1.getString("publishericonurl");
				String publishernickname = rs1.getString("publishernickname");
				int fwnum = rs1.getInt("fwnum");
				int commentnum = rs1.getInt("commentnum");
				String videourl = rs1.getString("videourl");
				String picurl = rs1.getString("picurl");
				//add this User to the list
				microblogs.add(new MicroBlog(idmicroblogs, txt,idpublisher, idoriginalmicroblog, time,publishericonurl,publishernickname,fwnum,commentnum,videourl,picurl));
			}
			if (rs1 != null) {
				rs1.close();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Notice!Always close the connection after using.
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return microblogs;
	}
	
	
	/**
	 * all mb
	 */
	public static List<MicroBlog> getAllMicroBlogs() throws SQLException {
		List<MicroBlog> microblogs = new ArrayList<MicroBlog>();
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			/*List focusingIdusers = getAllFocusingIdusers(iduser);
			for(int focusingIduser:focusingIdusers){
				focusingIduser
			}*/
			ps = c.prepareStatement(GetAllMicroBlogs);//prepare the statement according to the SQL statement.
			rs = ps.executeQuery();//Execute the Query
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs.next()) {
				int idmicroblogs = rs.getInt("idmicroblogs");
				String txt = rs.getString("txt");
				int idpublisher = rs.getInt("idpublisher");
				int idoriginalmicroblog = rs.getInt("idoriginalmicroblog");
				Timestamp time = rs.getTimestamp("time");
				String publishericonurl = rs.getString("publishericonurl");
				String publishernickname = rs.getString("publishernickname");
				int fwnum = rs.getInt("fwnum");
				int commentnum = rs.getInt("commentnum");
				String videourl = rs.getString("videourl");
				String picurl = rs.getString("picurl");
				//add this User to the list
				microblogs.add(new MicroBlog(idmicroblogs, txt,idpublisher, idoriginalmicroblog, time,publishericonurl,publishernickname,fwnum,commentnum,videourl,picurl));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Notice!Always close the connection after using.
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return microblogs;
	}
	
	
	
	
	
	/**
	 * delete
	 */
	
	public static boolean delMBByIdmicroblogs(int idmicroblogs) throws SQLException {
		MicroBlog microblog = null;
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(DelMBByIdmicroblogs);//prepare the statement according to the SQL statement.
			ps.setInt(1, idmicroblogs);
			rs = ps.executeQuery();//Execute the Query
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			//Notice!Always close the connection after using.
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		
	}
	
	
	
	
	/**
	 * getMBByKeyword
	 */
	
	public static List<MicroBlog> getMBByKeyword(String keyword) throws SQLException {
		List<MicroBlog> microblogs = new ArrayList<MicroBlog>();
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			/*List focusingIdusers = getAllFocusingIdusers(iduser);
			for(int focusingIduser:focusingIdusers){
				focusingIduser
			}*/
			ps = c.prepareStatement(GetMBByKeyword);//prepare the statement according to the SQL statement.
			ps.setString(1, "%"+keyword+"%");
			System.out.println(ps);
			rs = ps.executeQuery();//Execute the Query
			
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs.next()) {
				int idmicroblogs = rs.getInt("idmicroblogs");
				String txt = rs.getString("txt");
				int idpublisher = rs.getInt("idpublisher");
				int idoriginalmicroblog = rs.getInt("idoriginalmicroblog");
				Timestamp time = rs.getTimestamp("time");
				String publishericonurl = rs.getString("publishericonurl");
				String publishernickname = rs.getString("publishernickname");
				int fwnum = rs.getInt("fwnum");
				int commentnum = rs.getInt("commentnum");
				String videourl = rs.getString("videourl");
				String picurl = rs.getString("picurl");
				//add this User to the list
				microblogs.add(new MicroBlog(idmicroblogs, txt,idpublisher, idoriginalmicroblog, time,publishericonurl,publishernickname,fwnum,commentnum,videourl,picurl));
			    System.out.println("add"+txt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Notice!Always close the connection after using.
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return microblogs;
	}
	
	
	/**
	 * all mb
	 */
	public static List<MicroBlog> getNewMB(int n) throws SQLException {
		List<MicroBlog> microblogs = new ArrayList<MicroBlog>();
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			/*List focusingIdusers = getAllFocusingIdusers(iduser);
			for(int focusingIduser:focusingIdusers){
				focusingIduser
			}*/
			ps = c.prepareStatement(GetNewMB);//prepare the statement according to the SQL statement.
			ps.setInt(1, n);
			rs = ps.executeQuery();//Execute the Query
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs.next()) {
				int idmicroblogs = rs.getInt("idmicroblogs");
				String txt = rs.getString("txt");
				int idpublisher = rs.getInt("idpublisher");
				int idoriginalmicroblog = rs.getInt("idoriginalmicroblog");
				Timestamp time = rs.getTimestamp("time");
				String publishericonurl = rs.getString("publishericonurl");
				String publishernickname = rs.getString("publishernickname");
				int fwnum = rs.getInt("fwnum");
				int commentnum = rs.getInt("commentnum");
				String videourl = rs.getString("videourl");
				String picurl = rs.getString("picurl");
				//add this User to the list
				microblogs.add(new MicroBlog(idmicroblogs, txt,idpublisher, idoriginalmicroblog, time,publishericonurl,publishernickname,fwnum,commentnum,videourl,picurl));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Notice!Always close the connection after using.
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return microblogs;
	}
	
	
}
