package com.example.android.jsonurl;

        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import org.w3c.dom.Text;

        import java.util.ArrayList;
        import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

   private TextView type, code, fbid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        type = (TextView) findViewById(R.id.type);
        code = (TextView) findViewById(R.id.code);
        fbid = (TextView) findViewById(R.id.fbid);

        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "https://graph.facebook.com/me";
        String jsonStr = sh.makeServiceCall(url);


            if (jsonStr != null) {
                try {
                    JSONObject jsonO = new JSONObject(jsonStr);
                    JSONObject jsonObj = jsonO.getJSONObject("error");
                    String typeS = jsonObj.getString("type");
                    String codeS = jsonObj.getString("code");
                    String fbtrace_id = jsonObj.getString("fbtrace_id");
                    type.setText(typeS);
                    code.setText(codeS);
                    fbid.setText(fbtrace_id);

                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
            } else {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}


