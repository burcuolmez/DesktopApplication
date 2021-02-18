package src;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;

public class ApiClient {
	Management m = new Management();
	@SuppressWarnings("null")
	public ApiClient() throws IOException {
		URL url = new URL("https://backend.etkinlik.io/api/v2/events");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("GET");
		con.setRequestProperty("X-Etkinlik-Token", "c85b7c800a41e28d5fad9c4029e188fb");
		con.setRequestProperty("accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json");
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
				String inputLine;
				String content ="";
				while ((inputLine = in.readLine()) != null) {
				    content+=(inputLine);
				}
		Gson g=new Gson();
		JsonObject values=g.fromJson(content, JsonObject.class);
		JsonArray arr = values.getAsJsonArray("items");
		m.CreateEvents(arr);
		
	}
}
