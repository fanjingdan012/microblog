package pj2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
* Handle all user affairs
* @author Fan Jingdan
*
*/
public class UserOperator {
	//Here defines how to connector the database.
	private static final String connectionString ="jdbc:mysql://localhost:3306/microblog";
	//name for mysql, 'root' by default.
	private static final String dbUsername = "root";
	//user password for mysql, change to yours.
	private static final String dbPassword = "207710";
	
	
	
	
	
	
	/**
	 * UPDATE
	 */
	private static final String UpdateIconurl = "UPDATE `microblog`.`user` SET  iconurl=? where iduser = ?";
	
	
	/**
	 * SELECT
	 */
	
	private static final String getAllPeopleSQL = "SELECT * FROM user";
	private static final String LoginNameSQL = "SELECT * FROM user where username = ?";
	private static final String LoginSQL = "SELECT * FROM user where username = ? and password = ?";
	private static final String RegSQL = "INSERT INTO user(username,password,nickname,comefrom,regtime) values(?,?,?,?,now())";
	private static final String GetUserByUsername = "SELECT * FROM user where username = ?";
	private static final String GetUserByIduser = "SELECT * FROM user where iduser = ?";
	private static final String GetUserByKeyword = "SELECT * FROM user where nickname like ?";
	private static final String GetHotUsers = "SELECT * FROM user order by fans desc limit ?";
	private static final String GetUsersByIduser = "SELECT * FROM user where ";
	
	//load the mysql driver to the memory.
	//without this driver the program will not know how to connect to the database.
	//we only need to load this driver ONCE.
	//so the code is in the static constructor.
	static {
		try {
			//class name for mysql driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
/**
* Get all people in the database
* @return a list contains all people
* @throws SQLException
*/
	
	public static List<User> getAllPeople() throws SQLException {
		List<User> people = new ArrayList<User>();
		//notice, this Connection is java.sql.Connection
		//try to get the connection by DriverManager.
		//you should always obtain a Connection before you query the database
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//prepare the statement according to the SQL statement.
			ps = c.prepareStatement(getAllPeopleSQL);
			//Execute the Query
			rs = ps.executeQuery();
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs.next()) {
				//get int value
				int iduser = rs.getInt("iduser");
				//get string value
				String name = rs.getString("username");
				String nickname=rs.getString("nickname");
				String password = rs.getString("password");
				//get Timestamp value
				//Notice: java.sql.Timestamp, not java.sql.Time
				//Timestamp regTime = rs.getTimestamp("regdate");
				String comefrom = rs.getString("comefrom");
				String iconurl = rs.getString("iconurl");
				int focusing = rs.getInt("focusing");
				int fans = rs.getInt("fans");
				int mbnum = rs.getInt("mbnum");
				//add this User to the list
				people.add(new User(iduser, name,nickname, password, comefrom,iconurl,focusing,fans,mbnum));
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
		return people;
	}
	
	
	
	
	
	
	
	
	/**
	 * login:find the user in database
	 *       return the user
	 */

    public static User login(String username,String password) throws SQLException{
    	Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//prepare the statement according to the SQL statement.
			ps = c.prepareStatement(LoginNameSQL);
			ps.setString(1, username);
			System.out.println(ps);
			//Execute the Query
			rs = ps.executeQuery();
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			if(!rs.next()){
				System.out.println("no such user");
				
				return null;
				//TODO
			}
			else{
				ps = c.prepareStatement(LoginSQL);
				ps.setString(1, username);
				ps.setString(2, password);
				System.out.println(ps);
			    rs = ps.executeQuery();
				if(rs.next()){	
						System.out.println("find user!"+rs.getString("username"));
						//get int value
						int iduser = rs.getInt("iduser");
						//get string value
						String nickname=rs.getString("nickname");
						String comefrom = rs.getString("comefrom");
						String iconurl = rs.getString("iconurl");
						int focusing = rs.getInt("focusing");
						int fans = rs.getInt("fans");
						int mbnum = rs.getInt("mbnum");
						User foundUser = new User(iduser,username,nickname,password,comefrom,iconurl,focusing,fans,mbnum);
						return foundUser;
				}
				else{
					System.out.println("password wrong");
					return null;
					//TODO
				}
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
		return null;
    }
    
    
    
    
    
    
    
    
    /**
     * regUser:INSERT data to database
     * @param user
     * @return
     * @throws SQLException
     */
	public static boolean regUser(User user) throws SQLException {
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		//ResultSet rs = null;
		try {
			ps = c.prepareStatement(RegSQL,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getComefrom());
			System.out.println(ps);
			ps.executeUpdate();
			
			ResultSet newid = ps.getGeneratedKeys();
			if (newid.next()){
				user.setIduser(newid.getInt(1));
				PreparedStatement ps2 = c.prepareStatement(GetUserByIduser);
				ps2.setInt(1, user.getIduser());
				ResultSet rs2 = ps2.executeQuery();
				if (rs2.next()){
					user.setRegTime(rs2.getTimestamp("regtime"));
				}
				return true;
			}else{
				return false;
			}
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
	 * get user by username
	 */
	public static User getUserByUsername(String username) throws SQLException {
		User user = null;
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(GetUserByUsername);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				int iduser = rs.getInt("iduser");
				String nickname=rs.getString("nickname");
				String password = rs.getString("password");
				//Timestamp regTime = rs.getTimestamp("regdate");
				String comefrom = rs.getString("comefrom");
				String iconurl = rs.getString("iconurl");
				int focusing = rs.getInt("focusing");
				int fans = rs.getInt("fans");
				int mbnum = rs.getInt("mbnum");
				user = new User(iduser, username,nickname, password, comefrom,iconurl,focusing,fans,mbnum);
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
		return user;
	}
	
	

	/**
	 * get user by username
	 */
	public static User getUserByIduser(int iduser) throws SQLException {
		User user = null;
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(GetUserByIduser);
			ps.setInt(1, iduser);
			rs = ps.executeQuery();
			while (rs.next()) {
				//int iduser = rs.getInt("iduser");
				String username = rs.getString("username");
				String nickname=rs.getString("nickname");
				String password = rs.getString("password");
				//Timestamp regTime = rs.getTimestamp("regdate");
				String comefrom = rs.getString("comefrom");
				String iconurl = rs.getString("iconurl");
				int focusing = rs.getInt("focusing");
				int fans = rs.getInt("fans");
				int mbnum = rs.getInt("mbnum");
				user = new User(iduser, username,nickname, password, comefrom,iconurl,focusing,fans,mbnum);
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
		return user;
	}
	
	/**
	 * getUser by key
	 */
	public static List<User> getUserByKeyword(String keyword) throws SQLException {
		List<User> people = new ArrayList<User>();
		//notice, this Connection is java.sql.Connection
		//try to get the connection by DriverManager.
		//you should always obtain a Connection before you query the database
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//prepare the statement according to the SQL statement.
			ps = c.prepareStatement(GetUserByKeyword);
			ps.setString(1, "%"+keyword+"%");
			//Execute the Query
			rs = ps.executeQuery();
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs.next()) {
				//get int value
				int sid = rs.getInt("iduser");
				//get string value
				String name = rs.getString("username");
				String nickname=rs.getString("nickname");
				String password = rs.getString("password");
				//get Timestamp value
				//Notice: java.sql.Timestamp, not java.sql.Time
				//Timestamp regTime = rs.getTimestamp("regdate");
				String comefrom = rs.getString("comefrom");
				String iconurl = rs.getString("iconurl");
				int focusing = rs.getInt("focusing");
				int fans = rs.getInt("fans");
				int mbnum = rs.getInt("mbnum");
				//add this User to the list
				people.add(new User(sid, name,nickname, password, comefrom,iconurl,focusing,fans,mbnum));
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
		return people;
	}
	
	
	
	/**
	 * update icon
	 */
	public static boolean UpdateIconurl(String iconurl, int iduser) throws SQLException {
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(UpdateIconurl);
			ps.setString(1, iconurl);
			ps.setInt(2, iduser);
			ps.executeUpdate();
			return true;
			
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
	
	
	
	
	/**
	 * get hot users
	 */
	public static List<User> getHotUsers(int num) throws SQLException {
		List<User> people = new ArrayList<User>();
		//notice, this Connection is java.sql.Connection
		//try to get the connection by DriverManager.
		//you should always obtain a Connection before you query the database
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//prepare the statement according to the SQL statement.
			ps = c.prepareStatement(GetHotUsers);
			ps.setInt(1, num);
			//Execute the Query
			rs = ps.executeQuery();
			//get data line by line.
			//stops if rs.next() is false, which means no more lines available
			while (rs.next()) {
				//get int value
				int iduser = rs.getInt("iduser");
				//get string value
				String name = rs.getString("username");
				String nickname=rs.getString("nickname");
				String password = rs.getString("password");
				//get Timestamp value
				//Notice: java.sql.Timestamp, not java.sql.Time
				//Timestamp regTime = rs.getTimestamp("regdate");
				String comefrom = rs.getString("comefrom");
				String iconurl = rs.getString("iconurl");
				int focusing = rs.getInt("focusing");
				int fans = rs.getInt("fans");
				int mbnum = rs.getInt("mbnum");
				//add this User to the list
				people.add(new User(iduser, name,nickname, password, comefrom,iconurl,focusing,fans,mbnum));
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
		return people;
	}
	
	
	
	/**
	 * getFans
	 * @return
	 * @throws SQLException
	 */
	
	public static List<User> getFans(int idTarget) throws SQLException {
		List<User> people = new ArrayList<User>();
		//notice, this Connection is java.sql.Connection
		//try to get the connection by DriverManager.
		//you should always obtain a Connection before you query the database
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<Integer> fansid = RelationOperator.getAllFans(idTarget);
			String SQL = GetUsersByIduser;
			for(int idfans:fansid){
				SQL=SQL+"iduser="+idfans+" or ";
			}
			
			if(!SQL.equals("SELECT * FROM user where ")){
				SQL = SQL.substring(0,SQL.length()-4);
				System.out.println(SQL);
				ps = c.prepareStatement(SQL);
				rs = ps.executeQuery();
				//get data line by line.
				//stops if rs.next() is false, which means no more lines available
				while (rs.next()) {
					//get int value
					int iduser = rs.getInt("iduser");
					//get string value
					String name = rs.getString("username");
					String nickname=rs.getString("nickname");
					String password = rs.getString("password");
					//get Timestamp value
					//Notice: java.sql.Timestamp, not java.sql.Time
					//Timestamp regTime = rs.getTimestamp("regdate");
					String comefrom = rs.getString("comefrom");
					String iconurl = rs.getString("iconurl");
					int focusing = rs.getInt("focusing");
					int fans = rs.getInt("fans");
					int mbnum = rs.getInt("mbnum");
					//add this User to the list
					people.add(new User(iduser, name,nickname, password, comefrom,iconurl,focusing,fans,mbnum));
				}
				return people;
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
		return people;
	}
	/**
	 * getFans
	 * @return
	 * @throws SQLException
	 */
	
	public static List<User> getFocusing(int idSource) throws SQLException {
		List<User> people = new ArrayList<User>();
		//notice, this Connection is java.sql.Connection
		//try to get the connection by DriverManager.
		//you should always obtain a Connection before you query the database
		Connection c = DriverManager.getConnection(connectionString,dbUsername, dbPassword);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<Integer> fansid = RelationOperator.getAllFocusingIdusers(idSource);
			String SQL = GetUsersByIduser;
			for(int idfans:fansid){
				SQL=SQL+"iduser="+idfans+" or ";
			}
			
			if(!SQL.equals("SELECT * FROM user where ")){
				SQL = SQL.substring(0,SQL.length()-4);
				System.out.println(SQL);
				ps = c.prepareStatement(SQL);
				rs = ps.executeQuery();
				//get data line by line.
				//stops if rs.next() is false, which means no more lines available
				while (rs.next()) {
					//get int value
					int iduser = rs.getInt("iduser");
					//get string value
					String name = rs.getString("username");
					String nickname=rs.getString("nickname");
					String password = rs.getString("password");
					//get Timestamp value
					//Notice: java.sql.Timestamp, not java.sql.Time
					//Timestamp regTime = rs.getTimestamp("regdate");
					String comefrom = rs.getString("comefrom");
					String iconurl = rs.getString("iconurl");
					int focusing = rs.getInt("focusing");
					int fans = rs.getInt("fans");
					int mbnum = rs.getInt("mbnum");
					//add this User to the list
					people.add(new User(iduser, name,nickname, password, comefrom,iconurl,focusing,fans,mbnum));
				}
				return people;
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
		return people;
	}
	

}
