package com.example.demo.service;

import com.example.demo.dao.AccountDao;
import com.example.demo.sample.model.Account;

/**
 * リスト20.8 認証クラス
 * 
 * あるアプリケーションのアカウント認証クラスを作成する。<br/>
 * アカウント情報はデータベースに保存されており、AccountDaoインタフェースでAccountクラスとして取得できる設計とした。<br/>
 * AccountDaoインタフェースを利用する認証クラスを作成し、認証メソッドのテストを作成せよ。<br/>
 * ただし、AccountDaoの実装クラスはまだ実装されていないため、スタブを使用すること。<br/>
 * ・Authenticationクラスに、authenticateメソッドを定義する。<br/>
 * ・authenticateメソッドは、String型の引数を2つ持ち、戻り値をAccount型とする。<br/>
 * ・Authenticationクラスに、フィールドとしてAccountDaoオブジェクトを持つ<br/>
 * ・authenticateメソッドでは、AccountDaoオブジェクトからAccountオブジェクトを取得する。<br/>
 * ・authenticateメソッドでは、AccountDaoオブジェクトが取得でき、かつパスワードが一致したならば、Accountオブジェクトを返す。<br/>
 */
public class Authentication {

	AccountDao dao = null;

	public Account authenticate(String userId, String password) {
		assert dao != null;
		Account account = dao.findOrNull(userId);
		if (account == null)
			return null;
		return account.getPassword().equals(password) ? account : null;
	}
}
