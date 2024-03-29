package core;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.logging.Logger;

public class DBHelperMongoDB {
    private static final Logger LOGGER = Logger.getLogger(DBHelperMongoDB.class.getName());
    MongoCollection<Document> collection;
    DBCollection table;

    public DBHelperMongoDB() {

        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("testdb");
        table = db.getCollection("items");
        LOGGER.info("MongoDb Connected ");

    }


    public void add(String bookID, String bookTitle, String bookPrice) {
        BasicDBObject book = new BasicDBObject()
                .append("Book ID", bookID)
                .append("Book Title", bookTitle)
                .append("Book Price", bookPrice);
        table.insert(book);
        LOGGER.info("MongoDb 1 Row added ");

    }

    public void delete(String bookID) {

        BasicDBObject delete = new BasicDBObject();
        delete.put("Book ID", bookID);
        table.remove(delete);
        LOGGER.info("MongoDb 1 Row deleted ");
    }

    public void update(String bookID, String bookTitle, String bookPrice) {
        BasicDBObject old = new BasicDBObject("Book ID", bookID);
        BasicDBObject newData = new BasicDBObject().append("Book Title", bookTitle)
                .append("Book Price", bookPrice);
        BasicDBObject updateDoc = new BasicDBObject("$set", newData);
        table.update(old, updateDoc);
        LOGGER.info("MongoDb 1 Row updated ");


    }

}
