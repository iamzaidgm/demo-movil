package mx.uach.fing.WeatherApp22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.logging.Logger;

import mx.uach.fing.WeatherApp22.models.Weather;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvwTemp =findViewById(R.id.tvwTemp);
        final TextView tvwCity = findViewById(R.id.tvwCity);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://raw.githubusercontent.com/luis190991/demo-movil/master/weather.json";
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Weather clima = new Gson().fromJson(response, Weather.class);
                tvwCity.setText(clima.city);
                tvwTemp.setText(clima.temp);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.getLogger("REQUEST WEATHER").warning(error.toString());
            }
        });

        queue.add(sr);

        String city = this.getIntent().getStringExtra("CITY");
    }
}