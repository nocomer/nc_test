package nc_lib;

import android.app.Activity;
import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpInter extends Application {

	public Boolean bl_debug_mode = true;
	
	
	public JSONArray getCategory() {

		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
		JSONArray jsonArray = null;
		
		if(bl_debug_mode){
			//���˵�����+�����Ӳ˵�����
			String json = "[" +
							"{'MainMenu':'ע�Ὠ��ʦ', 'SecondaryMenu':'һ������ʦ����'}," +
							"{'MainMenu':'ע�Ὠ��ʦ', 'SecondaryMenu':'һ������ʦ����'}," +
							"{'MainMenu':'ע����ʦ', 'SecondaryMenu':'�м����ʦ'}" +
						  "]";
			try {
				jsonArray = new JSONArray(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			HttpGet get = new HttpGet("http://www.test.com/getData");
			try {
				HttpResponse response = client.execute(get);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent()));
				for (String s = reader.readLine(); s != null; s = reader
						.readLine()) {
					builder.append(s);
				}
				Log.i("json_str", builder.toString());
				jsonArray = new JSONArray(builder.toString());
				for (int i = 0; i < 2; ++i) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					Log.i("id", jsonObject.getInt("id") + "");
					Log.i("website_name", jsonObject.getString("site_name"));
					Log.i("website_url", jsonObject.getString("site_url"));
					Log.i("category", jsonObject.getInt("category") + "");
					Log.i("title", jsonObject.getString("title"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return jsonArray;
	}
	

	public JSONArray getData() {
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
		JSONArray jsonArray = null;
		
		if(bl_debug_mode){
			String str = "[" +
					"{'Category':'ע�Ὠ��ʦ', 'SecondaryCategory':'����','Title':Title'},'Data':'Data'" +
					"{'Category':'һ������ʦ', 'SecondaryCategory':'����'}," +
					"{'Category':'������Դ', 'SecondaryCategory':'XX'}" +
				  "]";
			
			try {
				jsonArray = new JSONArray(str);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			HttpGet get = new HttpGet("http://www.test.com/getData");
			try {
				HttpResponse response = client.execute(get);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent()));
				for (String s = reader.readLine(); s != null; s = reader
						.readLine()) {
					builder.append(s);
				}
				Log.i("json_str", builder.toString());
				jsonArray = new JSONArray(builder.toString());
				for (int i = 0; i < 2; ++i) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					Log.i("id", jsonObject.getInt("id") + "");
					Log.i("website_name", jsonObject.getString("site_name"));
					Log.i("website_url", jsonObject.getString("site_url"));
					Log.i("category", jsonObject.getInt("category") + "");
					Log.i("title", jsonObject.getString("title"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		return jsonArray;
	}
}