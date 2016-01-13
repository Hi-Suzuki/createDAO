package com.rhizome.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		System.out.println("システムを開始します");
		try {
			new Main().start();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("システムを終了します");
	}

	/**
	 * メイン処理です
	 * @throws Exception
	 */
	private void start() throws Exception {
		// 入力用インスタンスの生成
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;

		// 値入力および判定
		// ※【1 or 2】を入力するまで繰り返す
		while (true) {
			System.out.println();
			System.out.println("※サンプルのため、1のみ実装済み");
			System.out.println("1:検索　2:登録　3:更新　4:削除　0:終了");

			try {
				line = reader.readLine();
			} catch(IOException e) {
				throw new Exception("ERROR:0001", e);
			}

			// 終了処理
			if ("0".equals(line)) {
				break;
			}

			// DB操作処理
			switch(line) {
			//********************************
			//            検索処理
			//********************************
			case "1":
				// 値入力および判定
				// ※【1 or 2】を入力するまで繰り返す
				while(true) {
					System.out.println();
					System.out.println("1:全検索　 2:名前検索　0:キャンセル");
					try {
						line = reader.readLine();
					} catch (IOException e) {
						throw new Exception("ERROR:0002", e);
					}

					switch(line) {
					//********************************
					//            終了処理
					//********************************
					case "0":
						break;

					//********************************
					//            検索処理
					//********************************
					case "1":
					case "2":
						// 【1 or 2】の判定
						boolean flgOne = "1".equals(line);
						Connection con = null;
						try {
							// 検索することが決まったら、DB接続用のJDBCドライバ読み込み
							Class.forName("org.gjt.mm.mysql.Driver");

							// Connectionの生成
							con = DriverManager.getConnection(
									  "jdbc:mysql://127.0.0.1:3306/rezodb"
									, "rezouser"
									, "rezo");

							// SQLを決定（１、２で変わる）
							String sql = "";
							if (flgOne) {
								sql = "SELECT * FROM employee";
							} else {
								sql = "SELECT * FROM employee WHERE nm_employee LIKE ?";
							}

							// PreparedStatementの生成
							PreparedStatement stmt = con.prepareStatement(sql);

							// 処理の分岐：【2】が選択されている場合
							if (flgOne == false) {
								// 条件の入力
								System.out.println("\n検索したい名前を入力してください");
								try {
									line = reader.readLine();
								} catch(IOException e) {
									throw new Exception("ERROR:0003", e);
								}

								// 条件をSQLに加える
								stmt.setString(1, "%" + line + "%");
							}

							// SQLの実行
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
						} catch(SQLException e) {
							System.out.println("DB連携でエラーが発生しました");
							e.printStackTrace();
						} finally {
							// Connectionの切断
							if (con != null) {
								con.close();
							}
						}
						break;
					default:
						System.out.println("1-2を選択してください！");
						continue;
					}
					break;
				}
				break;
			default:
				System.out.println("0-1以外は実装してません！");
				continue;
			}
		}
	}
}