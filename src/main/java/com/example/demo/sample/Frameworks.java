package com.example.demo.sample;

import com.example.demo.sample.model.ApplicationServer;
import com.example.demo.sample.model.Database;

/**
 * リスト19.18 Frameworksクラス</br>
 * 組み合わせテスト</br>
 * あるWEBアプリケーションフレームワークのサポート状況は下記となっている。</br>
 * この時、各環境を引数として指定し、サポートされる組み合わせならばtrueを返すメソッドと、そのテストを作成せよ。</br>
 * ----------------------------------------------------- </br>
 * アプリケーションサーバ•••••••••データベース••••••••••サポート•</br>
 * ----------------------------------------------------- </br>
 * GlassFish••••••••••••••••••Oracle••••••••••••••◯••••••</br>
 * GlassFish••••••••••••••••••DB2•••••••••••••••••◯••••••</br>
 * GlassFish••••••••••••••••••PostgreSQL••••••••••◯••••••</br>
 * GlassFish••••••••••••••••••MySQL•••••••••••••••◯••••••</br>
 * JBoss••••••••••••••••••••••Oracle••••••••••••••×••••••</br>
 * JBoss••••••••••••••••••••••DB2•••••••••••••••••◯••••••</br>
 * JBoss••••••••••••••••••••••PostgreSQL••••••••••◯••••••</br>
 * JBoss••••••••••••••••••••••MySQL•••••••••••••••×••••••</br>
 * Tomcat•••••••••••••••••••••Oracle••••••••••••••×••••••</br>
 * Tomcat•••••••••••••••••••••DB2•••••••••••••••••×••••••</br>
 * Tomcat•••••••••••••••••••••PostgreSQL••••••••••×••••••</br>
 * Tomcat•••••••••••••••••••••MySQL•••••••••••••••◯••••••</br>
 * ----------------------------------------------------- </br>
 * ・FrameworksクラスにisSupportメソッドを定義する。</br>
 * ・isSupportメソッドはstaticメソッドとする。</br>
 * ・isSupportメソッドは、ApplicationServer型の引数、Database型の引数を持ち、戻り値をboolean型とする。</br>
 * ・ApplicationServer型、Database型はそれぞれenumとして定義する。</br>
 */
public class Frameworks {

	public static boolean isSupport(ApplicationServer appServer, Database db) {
		switch (appServer) {
		case GlassFish:
			return true;
		case Tomcat:
			return db == Database.MySQL;
		case JBoss:
			return db == Database.DB2 || db == Database.PostgreSQL;
		default:
			return false;
		}
	}

}
