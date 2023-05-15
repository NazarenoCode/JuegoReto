package com.example.retojuegooficial;
import com.mongodb.client.*;
import org.bson.Document;

public class ConexionMongo {
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;

    MongoCollection<Document> mongoCollection;
    
    public ConexionMongo(String host ){

        mongoClient = MongoClients.create(host);

    }

    public MongoDatabase getDatabase(String databaseName){
        return mongoDatabase = mongoClient.getDatabase(databaseName);
    }

    public MongoCollection<Document> getCollection (String collectionName){
        return mongoCollection = mongoDatabase.getCollection(collectionName);
    }

    public void close(){
        mongoClient.close();
    }

}
