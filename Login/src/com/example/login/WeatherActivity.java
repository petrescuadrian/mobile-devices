package com.example.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.login.model.Location;
import com.example.login.model.Weather;

import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;


public class WeatherActivity extends Activity {

	
	private TextView cityText;
	private TextView condDescr;
	private TextView temp;
	private TextView press;
	private TextView windSpeed;
	private TextView windDeg;
	
	private TextView hum;
	private ImageView imgView;
	
	
	
	

			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);

		LocationManager locationManager = (LocationManager)
				getSystemService(Context.LOCATION_SERVICE);
				double latitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
				double longitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
		
		String city = null;
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(latitude,
                    longitude, 1);
            if (addresses.size() > 0)
                System.out.println(addresses.get(0).getLocality());
            city = addresses.get(0).getLocality();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
		//String city = "Bucharest,RO";
		
		cityText = (TextView) findViewById(R.id.WcityText);
		condDescr = (TextView) findViewById(R.id.WcondDescr);
		temp = (TextView) findViewById(R.id.Wtemp);
		hum = (TextView) findViewById(R.id.Whum);
		press = (TextView) findViewById(R.id.Wpress);
		windSpeed = (TextView) findViewById(R.id.WwindSpeed);
		windDeg = (TextView) findViewById(R.id.WwindDeg);
		imgView = (ImageView) findViewById(R.id.WcondIcon);
		
		JSONWeatherTask task = new JSONWeatherTask();
		task.execute(new String[]{city});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

	
	private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {
		
		@Override
		protected Weather doInBackground(String... params) {
			Weather weather = new Weather();
			String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

			try {
				weather = JSONWeatherParser.getWeather(data);
				
				// Let's retrieve the icon
				weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));
				
			} catch (JSONException e) {				
				e.printStackTrace();
			}
			return weather;
		
	}
		
		
		
	@Override
		protected void onPostExecute(Weather weather) {			
			super.onPostExecute(weather);
			
			if (weather.iconData != null && weather.iconData.length > 0) {
				Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length); 
				imgView.setImageBitmap(img);
			}
			
			
			
			cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
			condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
			temp.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + "�C");
			hum.setText("" + weather.currentCondition.getHumidity() + "%");
			press.setText("" + weather.currentCondition.getPressure() + " hPa");
			windSpeed.setText("" + weather.wind.getSpeed() + " mps");
			windDeg.setText("" + weather.wind.getDeg() + "�");
				
		}






	
  }
}
