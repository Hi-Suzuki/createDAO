package com.rhizome.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.rhizome.dao.EmployeeDAO;

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
						// 【2】ならば、条件入力
						// 条件の入力
						String name = null;
						if ("2".equals(line)) {
							System.out.println("\n検索したい名前を入力してください");
							try {
								name = reader.readLine();
							} catch(IOException e) {
								throw new Exception("ERROR:0003", e);
							}
						}

						// DAOの呼び出し
						EmployeeDAO dao = new EmployeeDAO();
						dao.selectByParam(name);
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