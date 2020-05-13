package com.zhf;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.zhf.utils.MongoDBUtils;
import org.bson.Document;

import java.util.Collections;

/**
 * @author ZengHongFa
 * @create 2020/5/13 0013 22:36
 */
public class Test {
    public static void main(String[] args) {
        MongoDatabase mongoDatabase = MongoDBUtils.instance.getDatabase("zjdb");
        if (mongoDatabase != null) {
            System.out.println("获取数据库成功");
        } else {
            System.out.println("获取数据库失败");
            return;
        }
        MongoCollection<Document> collection = MongoDBUtils.instance.getCollection("zjdb", "person");
        if (collection != null) {
            Document document = new Document();
            document.put("name", "name0");
            MongoCursor<Document> mongoCursor = MongoDBUtils.instance.query(collection, document);
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }
        }
    }
}
