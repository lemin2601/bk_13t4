package com.leemin.minhtrung.ateamnews;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.leemin.minhtrung.ateamnews.Settings.SettingsActivity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class MainActivity extends AppCompatActivity {

    //load ban đầu
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();

        new AsyncTask<String, Void, Boolean>() {
            public boolean isOnline() {
                try {
                    int timeoutMs = 1500;
                    Socket sock = new Socket();
                    SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);
                    sock.connect(sockaddr, timeoutMs);
                    sock.close();

                    return true;
                } catch (IOException e) {
                    return false;
                }
            }

            @Override
            protected Boolean doInBackground(String... params) {
                // your load work
                return isOnline();
            }

            @Override
            protected void onPostExecute(Boolean result) {
                Toast.makeText(getApplicationContext(), result + "", Toast.LENGTH_SHORT).show();
                // kiểm tra nếu chưa đăng nhập thì đăng nhập
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }
        }.execute("");


    }

}