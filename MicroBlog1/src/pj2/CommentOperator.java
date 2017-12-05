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

public class CommentOperator {
	private static final String connectionString ="jdbc:mysql://localhost:3306/microblog";
	private static final String dbUsername = "root";
	private static final String dbPassword = "207710";
	
	
	/**
	 * SELECT
	 */
	private static final String GetCommentByMB = "SELECT * FROM `microblog`.`comment` where idtargetmicroblog = ? order by time desc";
	//the SQL statement which operates with comment
	private static final String CommentMicroBlog = "INSERT INTO `microblog`.`comment`(txt,idpublisher,idtargetmicroblog,time,publishernickname)VALUES(?,?,?,now(),?);";
	
	//other SQL
	private static final String UpdateCommentnum = "UPDATE `microblog`.`microblogs` SET  commentnum=commentnum+1 where idmicroblogs = ?";
	
	
	
	/**
	 * comment
	 * @param txt
	 * @param idpublisher
	 * @param idtargetmicroblog
	 * @return
	 * @throws SQLException
	 */
	public static boolean commentMicroBlog(String txt,int idpublisher,int idtargetmicroblog,String publishernickname)throws SQLException{
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(CommentMicroBlog,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, txt);
			ps.setInt(2, idpublisher);
			ps.setInt(3,idtargetmicroblog);
			ps.setString(4,publishernickname);
			ps.executeUpdate();
			ps = c.prepareStatement(UpdateCommentnum,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idtargetmicroblog);
			ps.executeUpdate();
			return true;
						
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//Notice!Always close the connection after using.
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}	
			
		}
		return false;
	}
	/**
	 * get comment to see
	 */
	public static List<Comment> getCommentByMB(int idtargetmicroblog) throws SQLException {
		List<Comment> comments = new ArrayList<Comment>();
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			/*List focusingIdusers = getAllFocusingIdusers(iduser);
			for(int focusingIduser:focusingIdusers){
				focusingIduser
			}*/
			ps = c.prepareStatement(GetCommentByMB);//prepare the statement according to the SQL statement.
			ps.setInt(1, idtargetmicroblog);
			rs = ps.executeQuery();//Execute the Query
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs.next()) {
				int idcomment = rs.getInt("idcomment");
				String txt = rs.getString("txt");
				int idpublisher = rs.getInt("idpublisher");
				//int idtargetmicroblog = rs.getInt("idtargetmicroblog");
				Timestamp time = rs.getTimestamp("time");
				String publishernickname = rs.getString("publishernickname");
				//add this User to the list
				comments.add(new Comment(idcomment, txt,idpublisher, idtargetmicroblog, time,publishernickname));
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
		return comments;
	}
	
}
