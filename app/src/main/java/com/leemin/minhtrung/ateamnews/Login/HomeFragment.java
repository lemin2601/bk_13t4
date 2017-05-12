package com.leemin.minhtrung.ateamnews.Login;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.leemin.minhtrung.ateamnews.LoginActivity;
import com.leemin.minhtrung.ateamnews.MainViewActivity;
import com.leemin.minhtrung.ateamnews.R;
import com.leemin.minhtrung.ateamnews.TestEveryThing;
import com.leemin.minhtrung.ateamnews.lib.XMLDOMParser;
import com.squareup.picasso.Picasso;

import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeFragment extends Fragment {

    private ImageView profile_pic = null;
    private TextView tv = null;
    private Button logoutButton = null;
    private Profile profile = null;
    private Button continueButton = null;
    Button button;
    Button buttonTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        profile_pic = (ImageView) view.findViewById(R.id.profile_pic);
        tv = (TextView) view.findViewById(R.id.tv_name);
        logoutButton = (Button) view.findViewById(R.id.logout_button);
        continueButton = (Button) view.findViewById(R.id.continue_button);

        button = (Button) view.findViewById(R.id.setting_button);
        buttonTest = (Button) view.findViewById(R.id.test_everything_button);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            profile = (Profile) bundle.getParcelable(LoginFragment.PARCEL_KEY);
        } else {
            profile = Profile.getCurrentProfile();
        }


        tv.setText("Welcome \n" + profile.getName());

        Picasso.with(getActivity())
                .load(profile.getProfilePictureUri(400, 400).toString())
                .into(profile_pic);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainViewActivity.class);
                intent.putExtra(LoginFragment.PARCEL_KEY, profile);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()

                        .setContentTitle("XXXXX")
                        .setContentDescription(
                                "XXXXX in real time")
                        .setContentUrl(Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName()))
                        .build();

                ShareDialog shareDialog = new ShareDialog(getActivity());
                shareDialog.show(linkContent);
                Toast.makeText(getContext(), "NÈ", Toast.LENGTH_SHORT).show();

            }
        });

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO (3):   Tạo một thread khởi chạy lấy dữ liệu từ url

                new AsyncTask<String,Integer,String>(){

                    @Override
                    protected String doInBackground(String... params) {
                        return "";// XMLDOMParser.docNoiDung_Tu_URL(params[0]);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), TestEveryThing.class);
                        intent.putExtra("noiDung",s);
                        startActivity(intent);

                    }
                }.execute("http://www.24h.com.vn/an-ninh-hinh-su/nu-ke-toan-bi-ke-la-ke-dao-ep-len-doi-luc-tai-san-c51a873184.html");

            }
        });
    }

    private void logout() {
        LoginManager.getInstance().logOut();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, new LoginFragment());
        fragmentTransaction.commit();
    }


}
