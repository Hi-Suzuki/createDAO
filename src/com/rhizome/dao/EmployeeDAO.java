package com.rhizome.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * employeeテーブル用DAO
 * @author IT-College
 *
 */
public class EmployeeDAO {
	public void selectByParam(String name) throws SQLException {
		// 仮引数nameに値が入っているか判定
		boolean flgEmpty = (name == null || "".equals(name));
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

			// 実行結果の判定
			if (rs.next()) {
				System.out.println("*********検索結果*********");
				System.out.println("社員ID\t社員名\tフリガナ\tMail\tパスワード\t部署ID");
				System.out.print(rs.getInt("id_employee"));
				System.out.print("\t" + rs.getString("nm_employee"));
				System.out.print("\t" + rs.getString("kn_employee"));
				System.out.print("\t" + rs.getString("mail_address"));
				System.out.print("\t" + rs.getString("password"));
				System.out.print("\t" + rs.getInt("id_department"));
				System.out.println();
				// 2件目以降はループ
				while(rs.next()) {
					System.out.print(rs.getInt("id_employee"));
					System.out.print("\t" + rs.getString("nm_employee"));
					System.out.print("\t" + rs.getString("kn_employee"));
					System.out.print("\t" + rs.getString("mail_address"));
					System.out.print("\t" + rs.getString("password"));
					System.out.print("\t" + rs.getInt("id_department"));
					System.out.println();
				}
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
	}
}
