package model;
import java.sql.*;
import java.util.*;
public class DatabaseManager
{
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private String sql = null;
	private String user = null;
	private String passwd = null;
	private boolean status = false;
	public	DatabaseManager(String user,String passwd)
	{
		this.user = user;
		this.passwd = passwd;
	}
	public Connection loginDB()
	{
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/infor_manager?useUnicode=true&amp;characterEncoding=utf-8&useSSL=false";
		try
		{
			Class.forName(driverName);//加载并注册mysql类
			conn = DriverManager.getConnection(url,this.user,this.passwd);//获取连接对象
			return conn;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	//添加学生信息
	public boolean add(String name,String age,String id,String major)
	{
		conn = loginDB();
		sql="INSERT INTO infor (name,age,id,major) VALUES("+"'"+name+"'"+","+age+","+"'"+id+"'"+","+"'"+major+"'"+");";
		try
		{
			st = conn.createStatement();
			if(st.executeUpdate(sql) != -1)
				status = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeStatement(st);
			closeConnection(conn);
			return status;
		}
	}
	//根据学号判断学生是否在数据库中
	public boolean judge(String id)
	{
		conn = loginDB();
		sql = "SELECT * FROM infor WHERE id="+id+";";
		try
		{
			st = conn.createStatement();
			if (st.executeQuery(sql).next())
				status = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeStatement(st);
			closeConnection(conn);
			return status;
		}
	}
	//删除学生信息
	public boolean delete(String id)
	{
		conn = loginDB();
		sql = "DELETE FROM infor WHERE id="+id+";";
		try
		{
			st = conn.createStatement();
			if (st.executeUpdate(sql) != -1)
				status = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeStatement(st);
			closeConnection(conn);
		}
		return status;
	}
	//修改学生信息
	public boolean modify(String name,String age,String id,String major)
	{
		conn = loginDB();
		try
		{
			String newName = new String(name.getBytes("ISO-8859-1"),"utf-8");
			String newAge = new String(age.getBytes("ISO-8859-1"),"utf-8");
			String newId = new String(id.getBytes("ISO-8859-1"),"utf-8");
			String newMajor = new String(major.getBytes("ISO-8859-1"),"utf-8");
			sql = "UPDATE infor SET name='"+newName+"',age="+newAge+",major='"+newMajor+"' WHERE id='"+newId+"';";
			st = conn.createStatement();
			if (st.executeUpdate(sql) != -1)
				status=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeStatement(st);
			closeConnection(conn);
		}
		return status;
	}
	//查询学生信息
	public ArrayList search(String param1,String param2)
	{
		ArrayList al = new ArrayList();
		String newParam1 = null;
		conn = loginDB();
		try
		{
			newParam1 = new String(param1.getBytes("ISO-8859-1"),"utf-8");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if ( (param1 != "") && (param2 != "") )
		{
			sql = "select * from infor where name='"+newParam1+"' and id='"+param2+"';";
		}
		else
		{
			sql = "select * from infor where name='"+newParam1+"' or id='"+param2+"';";
		}
		try
		{
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next())
			{
				al.add(rs.getString(1));
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(st);
			closeConnection(conn);
		}
		return al;
	}
	//关闭连接对象
	public void closeConnection(Connection conn)
	{
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	//关闭SQL声明对象
	public void closeStatement(Statement st)
	{
		try
		{
			st.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void closeResultSet(ResultSet rs)
	{
		try
		{
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}