package cis455.queryProcess;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable (tableName = "Normal")

public class NormalBarrel {
	private String word;
	private String originalUrl;
	private String normalizedUrl;
	private double tfidf;
	
	@DynamoDBHashKey (attributeName = "word")
	public String getWord() {
		return word;
	}
	
	public void setWord (String word) {
		this.word = word;
	}
	
	@DynamoDBRangeKey (attributeName = "url")
	public String getNormalizedURL () {
		return normalizedUrl;
	}
	
	@DynamoDBAttribute (attributeName = "originalurl")
	public String getOriginalURL() {
		return originalUrl;
	}
	
	@DynamoDBAttribute (attributeName = "tfidf")
	public double getTFIDF () {
		return tfidf;
	}
	
	public void setTFIDF (double tfidf) {
		this.tfidf = tfidf;
	}
	
}
