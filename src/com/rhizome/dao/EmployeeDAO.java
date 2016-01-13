package com.rhizome.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rhizome.dto.Employee;

/**
 * employeeテーブル用DAO
 * @author IT-College
 *
 */
public class EmployeeDAO {
	public ArrayList<Employee> selectByParam(String name) throws SQLException {
		// 仮引数nameに値が入っているか判定
		boolean flgEmpty = (name == null || "".equals(name));
		ArrayList<Employee> aryEmployee = null;
		Connection con = null;

		try {
			// JDBCドライバの読み込み
			Class.forName("org.gjt.mm.mysql.Driver");

			// Connectionの生成
			con = DriverManager.getConnection(
					  "jdbc:mysql://127.0.0.1:3306/rezodb"
					, "rezouser"
					, "rezo");

			// SQLの生成
			StringBuffer strSQL = new StringBuffer("SELECT * FROM employee ");

			// 引数に値が入っていたら条件追加
			if (flgEmpty == false) {
				strSQL.append("WHERE nm_employee LIKE ?");
			}

			// PreparedStatementの生成
			PreparedStatement stmt = con.prepareStatement(strSQL.toString());

			// 条件の設定
			if (flgEmpty == false) {
				stmt.setString(1, "%" + name + "%");
			}

			// 検索の実行
			ResultSet rs = stmt.executeQuery();

			// 実行結果の格納
			aryEmployee = new ArrayList<>();
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setIdEmployee(rs.getInt("id_employee"));
				emp.setNmEmployee(rs.getString("nm_employee"));
				emp.setKnEmployee(rs.getString("kn_employee"));
				emp.setMail(rs.getString("mail_address"));
				emp.setPassword(rs.getString("password"));
				emp.setIdDepartment(rs.getInt("id_department"));
				aryEmployee.add(emp);
			}

			// PreparedStatementの切断
			stmt.close();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return aryEmployee;
	}
}
