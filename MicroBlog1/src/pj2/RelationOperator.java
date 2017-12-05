package pj2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RelationOperator {
	//Here defines how to connector the database.
	private static final String connectionString ="jdbc:mysql://localhost:3306/microblog";
	//name for mysql, 'root' by default.
	private static final String dbUsername = "root";
	//user password for mysql, change to yours.
	private static final String dbPassword = "207710";
	
	
	
	//the SQL statement which operates with relation
	private static final String Focus = "INSERT INTO `microblog`.`relation`(source,target)VALUES(?,?);";
	private static final String Unfocus = "DELETE FROM `microblog`.`relation` where source = ? and target = ?";
	private static final String GetFocusing = "SELECT * FROM `microblog`.`relation` where source = ?";
	private static final String GetFans = "SELECT * FROM `microblog`.`relation` where target = ?";
	private static final String GetRelation = "SELECT * FROM `microblog`.`relation` where source = ? and target = ?";
	
	//the SQL statement which operates with user
	private static final String UpdateFansA = "UPDATE `microblog`.`user` SET fans = fans+1 where iduser = ?";
	private static final String UpdateFocusingA = "UPDATE `microblog`.`user` SET focusing = focusing+1 where iduser = ?";
	private static final String UpdateFansM = "UPDATE `microblog`.`user` SET fans = fans-1 where iduser = ?";
	private static final String UpdateFocusingM = "UPDATE `microblog`.`user` SET focusing = focusing-1 where iduser = ?";
	/**
	 * focus:insert to database relation
	 *       add fans
	 *       add focusing
	 * @param iduserSource
	 * @param focusingSource
	 * @param nicknameTarget
	 * @return
	 * @throws SQLException
	 */
	public static boolean focus(int iduserSource,int iduserTarget) throws SQLException{
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(Focus,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, iduserSource);
			ps.setInt(2, iduserTarget);
			ps.executeUpdate();
			System.out.println("before rs");
			System.out.println("before update");
			/*the focusing of source ++*/
			ps=c.prepareStatement(UpdateFocusingA,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, iduserSource);				
			ps.executeUpdate();
			/*the fans of target ++*/
			ps=c.prepareStatement(UpdateFansA,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, iduserTarget);				
			ps.executeUpdate();
			System.out.println("before true");
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
	 * unfocus
	 */
	public static boolean unfocus(int iduserSource,int iduserTarget) throws SQLException{
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(Unfocus,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, iduserSource);
			ps.setInt(2, iduserTarget);
			ps.executeUpdate();
			/*the focusing of source ++*/
			ps=c.prepareStatement(UpdateFocusingM,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, iduserSource);				
			ps.executeUpdate();
			/*the fans of target ++*/
			ps=c.prepareStatement(UpdateFansM,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, iduserTarget);				
			ps.executeUpdate();
			System.out.println("before true");
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
	 * find all users you are focusing
	 * @param source
	 * @return
	 * @throws SQLException
	 */
	/*GetFocusingUserSQL = "SELECT * FROM `microblog`.`relation` where source = ?";	*/
	public static List<Integer> getAllFocusingIdusers(int source)throws SQLException{
		List<Integer> focusingIdusers = new ArrayList<Integer>();
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(GetFocusing);//prepare the statement according to the SQL statement.
			ps.setInt(1,source);
			rs = ps.executeQuery();//Execute the Query
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs.next()) {				
				int target = rs.getInt("target");//get int value
				focusingIdusers.add(target);//add this User to the list
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
		return focusingIdusers;
	
	}
	
	
	
	
	
	/**
	 * find all fans of you
	 * @param target
	 * @return
	 * @throws SQLException
	 */
	/*GetFansSQL = "SELECT * FROM `microblog`.`relation` where target = ?";*/
	public static List getAllFans(int target) throws SQLException {
		List fans = new ArrayList();
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(GetFans);//prepare the statement according to the SQL statement.
			ps.setInt(1, target);
			rs = ps.executeQuery();//Execute the Query
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs.next()) {
				int idfans = rs.getInt("source");//get int value
				fans.add(idfans);
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
		return fans;
	}
	
	
	public static boolean isFocusing(int iduserSource, int iduserTarget) throws SQLException{
		
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(GetRelation);//prepare the statement according to the SQL statement.
			ps.setInt(1, iduserSource);
			ps.setInt(2, iduserTarget);
			rs = ps.executeQuery();//Execute the Query
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			if (rs.next()) {
				return true;
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
		return false;
	}

}
