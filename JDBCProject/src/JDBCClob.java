import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class JDBCClob {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String USERNAME = "scott";
	private static final String PWD = "tiger";

	//ͨ��jdbc�洢���ı����ݣ�С˵��CLOB
	//����CLOB���ͣ�setCharacterStream
	public static void clobDemo() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			// a.�������������ؾ����������
			Class.forName("oracle.jdbc.OracleDriver");// ���ؾ����������
			// b.�����ݿ⽨������
			connection = DriverManager.getConnection(URL, USERNAME, PWD);
			
			String sql = "insert into mynovel values(?,?)";
			// c.����sql��ִ��(��ɾ�ġ���)
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, 1);
			File file = new File("E:\\all.txt");
			InputStream in = new FileInputStream( file) ;
			Reader reader = new InputStreamReader( in   ,"UTF-8") ;//ת���� �������ñ���
			pstmt.setCharacterStream(2, reader,  (int)file.length());
			int count =pstmt.executeUpdate() ;
			// d.������
			if (count > 0) {  
				System.out.println("�����ɹ���");
			}
			
			reader.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				 if(pstmt!=null) pstmt.close();// ����.����
				 if(connection!=null)connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//��ȡС˵
	public static void clobReaderDemo() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ; 
		try {
			// a.�������������ؾ����������
			Class.forName("oracle.jdbc.OracleDriver");// ���ؾ����������
			// b.�����ݿ⽨������
			connection = DriverManager.getConnection(URL, USERNAME, PWD);
			
			String sql = "select NOVEL from mynovel where id = ? ";
			// c.����sql��ִ��(��)
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, 1);
			
			rs = pstmt.executeQuery() ;
			//setXxxx getXxxx      setInt  getInt
			if(rs.next())
			{
				Reader reader = rs.getCharacterStream("NOVEL") ;
				Writer writer = new FileWriter("src/С˵.txt");
				
				char[] chs = new char[100] ;
				int len = -1;
				while(  (len = reader.read(chs)) !=-1 ) {
					writer.write( chs,0,len  );
				}
				writer.close();
				reader.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				 if(pstmt!=null) pstmt.close();// ����.����
				 if(connection!=null)connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) {
//		clobDemo() ;
		clobReaderDemo() ;
	}
}
