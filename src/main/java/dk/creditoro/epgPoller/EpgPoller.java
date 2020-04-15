package dk.creditoro.epgPoller;
// HttpClient Documentation
// http://hc.apache.org/httpcomponents-client-4.5.x/index.html

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


// Get From each channel
// https://tvtid-api.api.tv2.dk/api/tvtid/v1/epg/dayviews/2020-04-15?ch=1&ch=2&ch=3&ch=4&ch=5&ch=6&ch=7&ch=8&ch=14&ch=15&ch=31&ch=37&ch=70&ch=71&ch=77&ch=93&ch=94&ch=118&ch=133&ch=145&ch=153&ch=156&ch=157&ch=185&ch=219&ch=248&ch=10066&ch=10089&ch=10093&ch=10111&ch=10153&ch=10154&ch=10155&ch=12566&ch=12948&ch=15049&ch=2147483561


// Channels, id number,  title String , icon url(String), 
// https://tvtid-api.api.tv2.dk/api/tvtid/v1/schedules/channels


// TODO Make a class that holdes a channel, with the atribues ind parser. 
// Make a Terminal frontend, to chose what to download.
// Make a downloader for a channel, så it kan hold programs. 
// Make a class that is a program. 
// Maybe make a Hashmap of programs to add to Creditoro api.
public class EpgPoller {
	public static void main(String[] args) {
			CloseableHttpResponse response1 = null;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			StringBuffer responseContent = new StringBuffer();
		try {
			HttpHost target = new HttpHost("tvtid-api.api.tv2.dk", 80, "http");
			HttpGet getRequest = new HttpGet("/api/tvtid/v1/schedules/channels"); // KIG KIG KIG HER :D 

			response1 = httpclient.execute(target, getRequest);
			System.out.println("hello world");	
			System.out.println(response1.getStatusLine());
			HttpEntity entity1 = response1.getEntity();
			String content = new BufferedReader(new InputStreamReader( entity1.getContent() ))
				.lines().collect(Collectors.joining("\n"));
			
			//Check what kind of data that comes ind
			//System.out.println(content);
			parse(content);
			// do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(entity1);
		} catch(ClientProtocolException e){
			System.out.println(e);
		} catch(IOException e){
			System.out.println(e);
		}
		finally {
			try {
			response1.close();
			} catch(IOException e) {
				System.out.println(e);
			}
		}
	}
	public static String parse (String responebody){
		//JSONArray channels = new JSONArray(responebody);
		JSONObject channels = new JSONObject(responebody);
		JSONArray channelArray = channels.getJSONArray("channels");
		for(int i=0; i < channelArray.length(); i++){
			JSONObject channel = channelArray.getJSONObject(i);
			int id = channel.getInt("id");
			String title = channel.getString("title");
			String icon = channel.getString("icon");
			String logo = channel.getString("logo");
			String svglogo = channel.getString("svgLogo");
			// Not all have lang feels 
			// TODO fix lang input
			// String lang = channel.getString("lang");
			int sort = channel.getInt("sort");
			System.out.println(id + " " + title + " " + icon + " " + sort);
		}
			return "Hello world";
	}
}

