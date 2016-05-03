package cis455.g02.storage;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
/**
 * Crawled table for check if the url is crawled
 * fields: url
 */
@Entity
public class Crawled {
	
	@PrimaryKey
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	


	

}
