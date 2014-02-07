package br.ufmt.teia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				final TextView txt = (TextView)findViewById(R.id.textView1);
				txt.setText("");
//				final ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//				final NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				
				String NOMEARQUIVO = "data123456.txt";
				Context context = getApplicationContext();
				
				File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS), NOMEARQUIVO);
				FileOutputStream fileOutputStream = null;
				try {
					boolean append = true;
					fileOutputStream = new FileOutputStream(file, append);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				Writer writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
				
				final WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
				final List<ScanResult> scanResults = wifiManager.getScanResults();
				for (ScanResult scanResult : scanResults) {
					txt.setText(txt.getText() + "\n" + scanResult.SSID + " -> " + WifiManager.calculateSignalLevel(scanResult.level, 1000) + ", " + scanResult.BSSID);
					try {
						writer.write("\"" + scanResult.SSID + "\", " + WifiManager.calculateSignalLevel(scanResult.level, 100) + "\n");
					} catch (IOException e) {
						txt.setText("ops");
						continue;
					}
				}
				
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
