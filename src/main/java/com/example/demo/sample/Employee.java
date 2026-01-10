package com.example.demo.sample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import lombok.Data;

/**
 * テキストファイルに「名前,苗字,メールアドレス」がカンマ区切りで定義される。</br>
 * 上記のテキストファイルから、Employeeオブジェクトを読み込むメソッドのテストを作成する。</br>
 * ・Employeeクラスにloadメソッドを定義する。</br>
 * ・loadメソッドはstaticメソッドとする。</br>
 * ・loadメソッドはInputStream型の引数を1つ持ち、戻り値をList<Employee＞型とする。</br>
 */
@Data
public class Employee {
	/** 名前 */
	private String firstName;
	/** 苗字 */
	private String lastName;
	/** メールアドレス */
	private String email;

	/**
	 * テキストファイルからEmployeeオブジェクトを読み込むメソッド
	 * 
	 * @param input バイトストリームで読み込んだデータ
	 * @return 読み込んだEmployeeオブジェクトのリスト
	 */
	public static List<Employee> load(FileInputStream input) {
		List<Employee> list = new LinkedList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
			for (;;) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				String[] values = line.split(",");
				Employee employee = new Employee();
				employee.setFirstName(values[0]);
				employee.setLastName(values[1]);
				employee.setEmail(values[2]);
				list.add(employee);
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}
}
