package com.rhizome.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	// 定数定義
	private static final String DB_DRIVER = "org.gjt.mm.mysql.Driver";
	private static final String DB_USER = "rezouser";
	private static final String DB_PASS = "rezo";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rezodb";

	/**
	 * コンストラクタ（private：インスタンス化禁止）
	 */
	private DBUtil() {}

	/**
	 * Connection情報を取得します。
	 * @return rezodbへのConnection情報
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DB_DRIVER);
		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		return con;
	}

	/**
	 * Connectionの切断を行います。
	 * @param con Connection情報
	 * @throws SQLException
	 */
	public static void closeConnection(Connection con) throws SQLException {
		if (con != null) {
			con.close();
		}
	}
}
