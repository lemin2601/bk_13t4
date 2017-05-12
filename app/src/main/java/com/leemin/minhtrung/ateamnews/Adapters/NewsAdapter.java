package com.leemin.minhtrung.ateamnews.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.leemin.minhtrung.ateamnews.DetailNewsActivity;
import com.leemin.minhtrung.ateamnews.MainViewActivity;
import com.leemin.minhtrung.ateamnews.MainViewNewsActivity;
import com.leemin.minhtrung.ateamnews.R;
import com.leemin.minhtrung.ateamnews.bean.BaiBao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Admin on 5/9/2017.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder> implements Filterable {
    private static final String TAG = "TEST";
    // TODO (4) Tạo các data để lưu trữ dữ liệu
    ArrayList<BaiBao> dataMusics;
    Context context;

    private static ClickListener clickListener;

    public NewsAdapter(ArrayList<BaiBao> dataMusics, Context context) {
        this.dataMusics = dataMusics;
        this.context = context;
    }
   // private final OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(BaiBao item);
    }

    @Override
    public NewsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO (7) tiến hành inflater và khởi tạo view trả về
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_recycleview, parent, false);
        return new NewsAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsAdapterViewHolder holder, int position) {
        //TODO (8) Tiến hành khởi gán giá trị cho view
        final BaiBao music = dataMusics.get(position);
        holder.textView.setText(music.getTitle());
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG,"clicked");
//                Intent intent = new Intent(getApplicationContext(), DetailNewsActivity.class);
//            }
//        });
        Picasso.with(getApplicationContext()).load(music.getImage()).into(holder.imageView);
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(music.getLink()))
                .build();
        holder.shareButton.setShareContent(linkContent);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), DetailNewsActivity.class);
//                intent.putExtra(DetailNewsActivity.URLDETAIL,music.getLink());
//                getApplicationContext().startActivity(intent);
//            }
//        });
       // holder.bind(dataMusics.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataMusics.size();
    }

    //TODO add search view
    private List<BaiBao> orig;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<BaiBao> results = new ArrayList<BaiBao>();
                if (orig == null)
                    orig = dataMusics;
                if (constraint != null) {
                    if (orig != null & orig.size() > 0) {
                        for (final BaiBao g : orig) {
                            if (g.getTitle().toLowerCase().contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataMusics = (ArrayList<BaiBao>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    // TODO (1) Tạo một mucsic ViewHolder
    public class NewsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        //TODO (5) Khởi tạo view hiểm thị item
        TextView textView;
        ImageView imageView;
        Button btnShare;
        ShareButton shareButton;

        // TODO (2) Tạo Contructor cho nó
        public NewsAdapterViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            //TODO (6) Ánh xạ các item
            textView = (TextView) itemView.findViewById(R.id.textViewNewsRecyl);

            imageView = (ImageView) itemView.findViewById(R.id.imageViewNewsRecyl);
            btnShare = (Button) itemView.findViewById(R.id.share_facebook_NewsRecyl);
            shareButton = (ShareButton) itemView.findViewById(R.id.share_button);

            //TODO (11) add sự kiện cho các button trên item
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        NewsAdapter.clickListener = clickListener;
    }

}
