package com.leemin.minhtrung.ateamnews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.leemin.minhtrung.ateamnews.DetailNewsActivity;
import com.leemin.minhtrung.ateamnews.R;
import com.leemin.minhtrung.ateamnews.bean.BaiBao;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.leemin.minhtrung.ateamnews.R.id.imageView;
/**
 * Created by leemin on 3/27/17.
 */
// TODO (11):   Khởi tạo adapter để hiển thị
public class BaiBaoAdapter extends ArrayAdapter<BaiBao> {


    public BaiBaoAdapter(Context context, int resource, List<BaiBao> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.listview_item, null);
        }
        BaiBao p = getItem(position);
        if (p != null) {
            // Gán giá trị cho title
            TextView txtTitle = (TextView) view.findViewById(R.id.textView);
            txtTitle.setText(p.getTitle());
            // gÁN GIÁ TRỊ CHO HÌNH ẢNH
            ImageView img = (ImageView) view.findViewById(imageView);
            Picasso.with(getContext()).load(p.getImage()).into(img);
        }
        return view;
    }

}