import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class JDBCCallablestatement {
	private static final String URL="jdbc:mysql://localhost:3306/books";
	private static final String USERNAME="Tiger";
	private static final String PWD="123456";
	
	public static void invokePro(){
		Connection connection=null;
		CallableStatement cstmt=null;
		try {
		//a.�������������ؾ����������
		Class.forName("com.mysql.jdbc.Driver");	
		//b.�����ݿ⽨������
		connection =DriverManager.getConnection(URL, USERNAME,PWD);
		//c.����sql��ִ��
		cstmt= connection.prepareCall("{call addTwoNum(?,?,?)}");
		cstmt.setInt(1, 10);
		cstmt.setInt(2, 10);
		cstmt.registerOutParameter(3, Types.INTEGER);
		cstmt.execute();
		//d.�������� ����ѯ��
		int result=cstmt.getInt(3);
		System.out.println(result);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
				try {
					if(cstmt!=null) cstmt.close();
					if(connection!=null) 	connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	public static void main(String[] args) {
		invokePro();
	}
}
