package com.leemin.minhtrung.ateamnews.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.leemin.minhtrung.ateamnews.Adapters.BaiBaoAdapter;
import com.leemin.minhtrung.ateamnews.Adapters.NewsAdapter;
import com.leemin.minhtrung.ateamnews.DetailNewsActivity;
import com.leemin.minhtrung.ateamnews.R;
import com.leemin.minhtrung.ateamnews.bean.BaiBao;
import com.leemin.minhtrung.ateamnews.bean.Category;
import com.leemin.minhtrung.ateamnews.lib.XMLDOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 5/6/2017.
 */

public class MyFragment extends Fragment {

    private static final String TAG = "TESTED";
    String label;

    ListView listView;
    Category category;
    ArrayList<BaiBao> baiBaos;
    BaiBaoAdapter baiBaoAdapter;
    RecyclerView recyclerView;
    NewsAdapter musicAdapter;

    //    public static MyFragment newInstance(String fragmentLabel) {
//        MyFragment fragment = new MyFragment();
//        Bundle args = new Bundle();
//        args.putString("label", fragmentLabel);
//        fragment.setArguments(args);
//        return fragment;
//    }
    public static MyFragment newInstance(Category category) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("label", category.getName());
        args.putSerializable("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
  /*        TextView tv = (TextView) view.findViewById(R.id.label);

        //If the fragment was created by the TabHost, return empty view
        if (getArguments() == null) return view;
        label = getArguments().getString("label", "");
        category = (Category) getArguments().getSerializable("category");
        if (category != null) {
            tv.setText(category.getName());
            baiBaos = new ArrayList<BaiBao>();
            listView = (ListView) view.findViewById(R.id.listViewNew);
            // tiến hành bỏ data vào list view
            //TODO (3):   Tạo một thread khởi chạy lấy dữ liệu từ url
            //http://vnexpress.net/rss/the-gioi.rss
            new ReadData().execute(category.getLinkrss());
            //TODO (13): Cà kiện cho item Click
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getContext(), DetailNewsActivity.class);
                    intent.putExtra("url", baiBaos.get(position).getLink());
                    startActivity(intent);
                }
            });


        } else {
            tv.setText(label);
            baiBaos = new ArrayList<BaiBao>();
            listView = (ListView) view.findViewById(R.id.listViewNew);
            // tiến hành bỏ data vào list view
            //TODO (3):   Tạo một thread khởi chạy lấy dữ liệu từ url
            //http://vnexpress.net/rss/the-gioi.rss
            new ReadData().execute("http://vnexpress.net/rss/the-gioi.rss");
            //TODO (13): Cà kiện cho item Click
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getContext(), DetailNewsActivity.class);
                    intent.putExtra("url", baiBaos.get(position).getLink());
                    startActivity(intent);
                }
            });
        }*/
        recyclerView = (RecyclerView) view.findViewById(R.id.recyleView_musics);
        if (getArguments() == null) return view;
        category = (Category) getArguments().getSerializable("category");
        if (category == null) {category = new Category(); category.setLinkrss("http://vnexpress.net/rss/the-gioi.rss");}
        initView();
        return view;
    }

    public void initViewOld() {

    }

    public void initView() {
        //TODO RecycleView (1) Ánh xạ
//        FragmentListLayout fragmentListLayout = (FragmentListLayout) getFragmentManager().findFragmentById(R.id.fragment_list_music);
//        recyclerView = (RecyclerView) fragmentListLayout.getView().findViewById(R.id.recyleView_musics);
        //recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyleView_musics);
        // để tối ưu hóa dữ liệu

        recyclerView.setHasFixedSize(true);
        //TODO RecycleView (2) Chọn loại hiển thị (linear, tongle, horizontal)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //TODO RecycleView (3) setlayoutManager cho recycleview
        recyclerView.setLayoutManager(linearLayoutManager);
        //TODO RecycleView (5) Chọn đường chia item
        DividerItemDecoration dividerItemDecoration =  new DividerItemDecoration(this.getContext(),linearLayoutManager.getOrientation());
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
//        Drawable drawable = ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.item_divider);
//        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        //TODO RecycleView (6) Thêm animation
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //TODO RecycleView (4) tiếp hành lấy arraylist và bỏ vào adapter ==> setAdapter cho recycleview

        baiBaos = new ArrayList<BaiBao>();


        new ReadData().execute(category.getLinkrss());


    }




    //TODO (1):   Tạo class đọc data
    // String : đường dẫn, integer : xử lý, String: giá trị trả về
    class ReadData extends AsyncTask<String, Integer, String> implements NewsAdapter.ClickListener{

        @Override
        protected String doInBackground(String... params) {
            //Return lại giá trị nội dung từ url đọc được, đầu vào ở params[0]
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getContext(), "down load xong được link +" + category.getLinkrss(), Toast.LENGTH_LONG).show();
            //TODO (7): Bắt đầu đọc từng giá trị trong xml lấy về từ "s"
            //Toast.makeText(getApplicationContext(),s, LENGTH_LONG).show();
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);  // document chứa toàn bộ giá trị
            // tùy theo Rss mà ta có nội dung thích hơp
            NodeList nodeListTitle = document.getElementsByTagName("item"); // chứa list các <item>
            NodeList nodeListDes = document.getElementsByTagName("description");// chứa list các <description>
            //Bắt đầu duyệt từ nodelist và lấy các phần tử cần thiết
            // Lấy từ ngoài vào trong , ngoài là Document --> NodeList --> Element --> dùng parser để get value bằng matcher
            // khởi tạo những gía trị cần
            String title = "";
            String link = "";
            String description = "";
            String image = "";
            for (int i = 0; i < nodeListTitle.getLength(); i++) {
                // Element là phần tử trong nodelist
                Element element = (Element) nodeListTitle.item(i);
                title = parser.getValue(element, "title"); // lấy string nằm trong thẻ title
                link = parser.getValue(element, "link");

                // lấy hết hình ảnh trong CDATA ra
                String cData = nodeListDes.item(i + 1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cData);
                if (matcher.find()) {
                    // nếu đúng
                    image = matcher.group(1);
                }
                BaiBao baiBao = new BaiBao(title, link, image);
                baiBaos.add(baiBao);
            }
            // TODO (12):   Tiến hành bỏ data vào list view
//            baiBaoAdapter = new BaiBaoAdapter(getContext(), android.R.layout.simple_list_item_1, baiBaos);
//            listView.setAdapter(baiBaoAdapter);

            musicAdapter = new NewsAdapter(baiBaos, getActivity().getApplicationContext());
            musicAdapter.setOnItemClickListener(new NewsAdapter.ClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    Log.d(TAG, "onItemClick position: " + position);
                }

                @Override
                public void onItemLongClick(int position, View v) {

                }});
            recyclerView.setAdapter(musicAdapter);
            super.onPostExecute(s);
            //TODO (5):   Kiểm tra xem thử đã đọc được chưa, đến đây debug lần 1
            //Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onItemClick(int position, View v) {

        }

        @Override
        public void onItemLongClick(int position, View v) {

        }
    }

    //TODO (2):    Lên internet tại : http://khoapham.vn/KhoaPhamTraining/android/snipet/
    // tìm với từ khóa docnoidung rồi copy về
    private static String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
