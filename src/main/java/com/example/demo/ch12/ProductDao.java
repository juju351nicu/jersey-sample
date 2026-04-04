package com.example.demo.ch12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * ProductDao: DBConnector経由で製品テーブルを操作するDAO
 */
public class ProductDao {

	/**
	 * 製品名リストを取得する
	 *
	 * @param con 接続済みConnection
	 * @return 製品名リスト
	 * @throws SQLException SQL実行時例外
	 */
	public List<String> getList(Connection con) throws SQLException {
		String sql = "SELECT name FROM products ORDER BY id";
		List<String> result = new LinkedList<>();

		try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				result.add(rs.getString("name"));
			}
		}

		return result;
	}

	/**
	 * 製品名を追加する
	 *
	 * @param con         接続済みConnection
	 * @param productName 追加する製品名
	 * @throws SQLException SQL実行時例外
	 */
	public void insert(Connection con, String productName) throws SQLException {
		String sql = "INSERT INTO products(name) VALUES(?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, productName);
			ps.executeUpdate();
		}
	}
}