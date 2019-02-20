package corefuction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import bean.Weather;

public class visitUrl {

	public String getCityNameByIp(String ip) throws ClientProtocolException,
			IOException {
		String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";
		String city = "";
		String province = "";
		url = url + ip;
		HttpGet get = new HttpGet(url);

		// 使用HttpClient发送get请求，获得返回结果HttpResponse
		HttpClient client = new DefaultHttpClient();

		HttpResponse response = client.execute(get);

		// 读取返回结果
		if (response.getStatusLine().getStatusCode() == 200) {
			// 获取城市
			String entityString = EntityUtils.toString(response.getEntity());
			//System.out.println(entityString);

			JSONObject obj = JSONObject.fromObject(entityString);

			province = new String(obj.getString("province"));
			city = new String(obj.getString("city"));

			//System.out.println(province + '\t' + city);

		}
		return city;
	}

	public ArrayList<Weather> getWeatherByCityName(String cityName) throws ParseException,
			IOException {
		Weather weather=null;
		ArrayList<Weather> weatherlist=new ArrayList<Weather>();
		String url = "http://apix.sinaapp.com/weather/?appkey=1&city=";
		url=url+cityName;
		HttpGet get = new HttpGet(url);
		// 使用HttpClient发送get请求，获得返回结果HttpResponse
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(get);
		// 读取返回结果
		if (response.getStatusLine().getStatusCode() == 200) {
			// 获取天气json
			String entityString = EntityUtils.toString(response.getEntity());
			JSONArray jsonArr = new JSONArray().fromObject(entityString);
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject obj = jsonArr.getJSONObject(i);
				String Title = obj.getString("Title");
				String Description = obj.getString("Description");
				String PicUrl = obj.getString("PicUrl");
				String Url = obj.getString("Url");
				/*
				System.out.println(Title + '\t' + Description + '\t' + PicUrl
						+ '\t' + Url);
				*/
				weather=new Weather();
				weather.setTitle(Title);
				weather.setDescription(Description);
				weather.setPicUrl(PicUrl);
				weatherlist.add(weather);
			}

		}
		return weatherlist;
	}
	/*
	public static void main(String args[]) throws ParseException, IOException {
		
		String result="";
		// 公用获取数据方法
		InputStream in = response.getEntity().getContent();
		int l;
		byte[] buff = new byte[10000];
		while ((l = in.read(buff)) != -1) {
			result += new String(buff);
		}
		System.out.println(result);

	}
	
		new visitUrl().getCityNameByIp("112.80.65.143");
	}
	*/
}
