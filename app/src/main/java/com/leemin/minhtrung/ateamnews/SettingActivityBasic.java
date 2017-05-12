package com.leemin.minhtrung.ateamnews;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.leemin.minhtrung.ateamnews.R;

public class SettingActivityBasic extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        StringBuilder builder = new StringBuilder();

        builder.append("\n" + "Hiển thị trên thanh trạng thái:\t" + sharedPrefs.getBoolean("show_nofication", true));

        builder.append("\n" + "Tự động đồng bộ:\t" + sharedPrefs.getBoolean("sync_auto", false));
        builder.append("\n" + "Loại đồng bộ tin tức:\t" + sharedPrefs.getString("sync_news", "-1"));
        builder.append("\n" + "Thời gian đồng bộ tin tức:\t" + sharedPrefs.getString("sync_time", "Not known to us"));

        builder.append("\n" + "Loại Tin Tức :\t" + sharedPrefs.getBoolean("news_default", true));
        builder.append("\n" + "Loại The thao :\t" + sharedPrefs.getBoolean("news_the_thao", true));

        Toast.makeText(getApplicationContext(),builder.toString(),Toast.LENGTH_LONG).show();
    }
}
