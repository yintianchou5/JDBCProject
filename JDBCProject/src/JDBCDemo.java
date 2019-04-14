import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//���ݿ�����
//					����jar						����������								�����ַ���
//Oracle		ojdbc-x.jar				oracle.jdbc.OracleDriver					jdbc:oracle:thin:@localhost:1521:ORCL
//MySQL		mysql-connector-java-x.jar		com.mysql.jdbc.Driver					jdbc:mysql://localhost:3306/���ݿ�ʵ����
//SqlServer	sqljdbc-x.jar		com.microsoft.sqlserver.jdbc.SQLServerDriver		jdbc:microsoft:sqlserver:localhost:1433;databasename=���ݿ�ʵ����
//
//ʹ��jdbc�������ݿ�ʱ����������ݿ�����˸�����ֻ��Ҫ�滻�����������������ࡢ�����ַ������û���������
public class JDBCDemo {
	private static final String URL="jdbc:mysql://localhost:3306/books";
	private static final String USERNAME="Tiger";
	private static final String PWD="123456";
	
	public static void update(){
		Connection connection=null;
		Statement stmt=null;
		try {
		//a.�������������ؾ����������
		Class.forName("com.mysql.jdbc.Driver");	
		//b.�����ݿ⽨������
		connection =DriverManager.getConnection(URL, USERNAME,PWD);
		//c.����sql��ִ��
		stmt= connection.createStatement();
		//String sql="insert into users values(1,'zs')";
		//String sql="update users set name='ww' where id=1";
		String sql="delete from users where id=1";
		int count=stmt.executeUpdate(sql);
		//d.�������� ����ѯ��
		if(count>0) {
			System.out.println("�����ɹ���");
		}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
				try {
					if(stmt!=null) stmt.close();
					if(connection!=null) 	connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	public static void query() {
		Connection connection=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
		//a.�������������ؾ����������
		Class.forName("com.mysql.jdbc.Driver");	
		//b.�����ݿ⽨������
		connection =DriverManager.getConnection(URL, USERNAME,PWD);
		//c.����sql
		stmt= connection.createStatement();
		String sql="select * from users";
		//ִ��
		rs=stmt.executeQuery(sql);
		//d.�������� ����ѯ��
		while(rs.next()) {
			int uno=rs.getInt("id");
			String uname=rs.getString("name");
			//int uno=rs.getInt(1);
			//String uname=rs.getString(2);
			System.out.println(uno+"---"+uname);
		}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
				try {
					if(rs!=null) rs.close();
					if(stmt!=null) stmt.close();
					if(connection!=null) 	connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	public static void main(String[] args) {
		//update();
		query();
	}
}
