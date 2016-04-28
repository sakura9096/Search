package cis455.queryProcess;

import java.util.ArrayList;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.Attribute;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableKeysAndAttributes;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.KeysAndAttributes;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;

public class DynamoDBDatabase {
	
	private static DynamoDBDatabase database = null;
	static AmazonDynamoDBClient dynamoDB;
	
	private DynamoDBDatabase() {
		
	}
	
	public static DynamoDBDatabase getInstance () {
		if (database == null) {
			database = new DynamoDBDatabase();
			try {
				init();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		return database;
	}

	
	 private static void init() throws Exception {
        /*
         * The ProfileCredentialsProvider will return your [default]
         * credential profile by reading from the credentials file located at
         * (/Users/fanglinlu/.aws/credentials).
         */
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider("default").getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (/Users/fanglinlu/.aws/credentials), and is in valid format.",
                    e);
        }
        dynamoDB = new AmazonDynamoDBClient(credentials);
        Region usEast = Region.getRegion(Regions.US_EAST_1);
        dynamoDB.setRegion(usEast);
	 }
	 
	 public ArrayList<TFIDFURLWrapper> getURLsFromWord (String word) {
//		TableKeysAndAttributes tableKeysAndAttributes = new TableKeysAndAttributes ("Fancy-Bareel");
//		tableKeysAndAttributes.addHashOnlyPrimaryKeys( , , );
		 ArrayList<TFIDFURLWrapper> result = new ArrayList<TFIDFURLWrapper> ();
		 QueryRequest qRequest = new QueryRequest ("Fancy-Barrel");
		 qRequest.setKeyConditionExpression ("word=:" + word);
		 
		 QueryResult qr = dynamoDB.query (qRequest);
		 
		 for (Map<String, AttributeValue> itemsValue: qr.getItems()) {
			 AttributeValue tfidfAttribute = itemsValue.get("tfidf");
			 String tfidfAttributeStr = tfidfAttribute.getN();
			 Double tfidf = Double.parseDouble (tfidfAttributeStr);
			 
			 
			 TFIDFURLWrapper tfuw = new TFIDFURLWrapper(tfidf, itemsValue.get("url").getS());
			 result.add(tfuw);
			 
		 }
		 
		 return result;
		 
		
	 }

	  
}
