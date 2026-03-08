package com.example.demo.ch11;

/**
 * リスト11.13 固定値を返すスタブ実装
 * 
 * @author shuji.w6e
 */
class RandomNumberGeneratorFixedResultStub implements RandomNumberGenerator {

	@Override
	public int nextInt() {
		return 1;
	}

}
