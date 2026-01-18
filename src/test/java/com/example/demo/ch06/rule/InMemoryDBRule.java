package com.example.demo.ch06.rule;

import com.example.demo.ch06.withoutrule.InMemoryDB;

public class InMemoryDBRule {

	InMemoryDB db = null;

//    @Override
//    public Statement apply(final Statement base, Description  description) {
//        db = new InMemoryDB();
//        return new Statement() {
//
//            @Override
//            public void evaluate() throws Throwable {
//                db.start();
//                try {
//                    base.evaluate();
//                } finally {
//                    db.shutdownNow();
//                }
//            }
//        };
//    }
}
