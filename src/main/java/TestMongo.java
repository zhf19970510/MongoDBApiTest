import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZengHongFa
 * @create 2020/5/12 0012 22:33
 */
public class TestMongo {
    public static void main(String[] args) {
        // 连接mongdb服务器
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // 查找mongodb有哪些数据库
        List<String> dbNames = mongoClient.getDatabaseNames();
        for (String name : dbNames) {
            System.out.println(name);
        }

        // 连接到指定数据库
        MongoDatabase zjdb = mongoClient.getDatabase("zjdb");
        if (zjdb != null) {
            System.out.println("获取数据库成功");
        } else {
            System.out.println("获取数据库失败");
            return;
        }

        MongoCollection<Document> collection = zjdb.getCollection("person");
        if (collection != null) {
            Document document = new Document();
            document.put("name", "name0");
            FindIterable<Document> findIterable = collection.find(document);
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }
        }

        // 插入一条数据
//        Document document = new Document();
//        document.put("name","aaa");
//        document.put("age",18);
//        collection.insertOne(document);

        List<Document> list = new ArrayList<Document>();
        for (int i = 0; i < 10; i++) {
            Document d = new Document();
            d.put("name","name"+i+10);
            d.put("age",i+10);
            list.add(d);
        }
        collection.insertMany(list);

        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }

        //更新数据
        Document cond = new Document();
        cond.put("name","aaa");
        Document content = new Document();
        content.put("$set",new Document("age",100).append("class","三年级"));

        collection.updateMany(cond,content);

        FindIterable<Document> findIterable2 = collection.find();
        MongoCursor<Document> mongoCursor2 = findIterable2.iterator();
        while (mongoCursor2.hasNext()) {
            System.out.println(mongoCursor2.next());
        }

        //删除数据
        collection.deleteMany(new Document("name","aa"));
        FindIterable<Document> findIterable3 = collection.find();
        MongoCursor<Document> mongoCursor3 = findIterable3.iterator();
        while (mongoCursor3.hasNext()) {
            System.out.println(mongoCursor3.next());
        }
    }
}
