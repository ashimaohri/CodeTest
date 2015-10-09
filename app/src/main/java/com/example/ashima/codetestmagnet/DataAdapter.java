package com.example.ashima.codetestmagnet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by Ashima on 10/8/2015.
 */
public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {

    List<DataPojo> data;
    Context context;
    VolleySingleton volleySingleton;
    ImageLoader imageLoader;
    LayoutInflater layoutInflater;


    public DataAdapter(Context c, List<DataPojo> dataList) {
        this.context = c;
        this.data = dataList;
        layoutInflater = LayoutInflater.from(c);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();

    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = layoutInflater.inflate(R.layout.list_item_cell, parent, false);
        DataViewHolder viewHolder = new DataViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {
        DataPojo pojo = data.get(position);
        holder.tv_name.setText(pojo.getEmotText());
        if (pojo.getIconLink() != null) {
            imageLoader.get(pojo.getIconLink(), new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.img_icon.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class DataViewHolder extends RecyclerView.ViewHolder {
    TextView tv_name;
    ImageView img_icon;

    public DataViewHolder(View itemView) {
        super(itemView);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        img_icon = (ImageView) itemView.findViewById(R.id.image_view);
    }

}
