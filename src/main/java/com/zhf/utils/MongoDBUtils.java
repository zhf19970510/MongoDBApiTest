package com.zhf.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.lang.reflect.Modifier;

/**
 * @author ZengHongFa
 * @create 2020/5/13 0013 21:44
 */
public enum  MongoDBUtils {
    instance;   // 枚举元素代表了此类的一个元素
    private MongoClient mongoClient;
    static {
        String ip ="localhost";
        int port = 27017;
        // 连接mongdb服务器
        instance.mongoClient = new MongoClient(ip, port);

    }

    public MongoDatabase getDatabase(String db){
        MongoDatabase zjdb = mongoClient.getDatabase(db);
        return zjdb;
    }

    public MongoCollection<Document> getCollection(String dbName,String colName){
        MongoDatabase mongoDatabase = getDatabase(dbName);
        if(mongoDatabase!=null){
            return mongoDatabase.getCollection(colName);
        }
        return null;
    }

    //查询
    public MongoCursor<Document> query(MongoCollection<Document> coll, Bson filter){
        return coll.find(filter).iterator();
    }

    //插入
    public void insert(MongoCollection<Document> coll,Document doc){
        coll.insertOne(doc);
    }

    // 更新
    public void  update(MongoCollection<Document>coll,Document querydoc,Document updatedoc){
        Document motify = new Document();
        motify.put("$set",updatedoc);
    }

    // 删除
    public void delete(MongoCollection<Document>coll, Document document){
        coll.deleteMany(document);
    }
}
