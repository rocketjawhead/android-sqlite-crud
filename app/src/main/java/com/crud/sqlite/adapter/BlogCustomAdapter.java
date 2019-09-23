package com.crud.sqlite.adapter;



import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crud.sqlite.EditBlog;
import com.crud.sqlite.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class BlogCustomAdapter extends RecyclerView.Adapter<BlogCustomAdapter.ViewHolder> {

    private List<BlogAdapter> blogList;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title_blog, desc_blog;
        public ViewHolder(View itemView) {
            super(itemView);
            title_blog  = itemView.findViewById(R.id.title_blog);
            desc_blog   = itemView.findViewById(R.id.desc_blog);
        }
    }
    public BlogCustomAdapter (List<BlogAdapter> listBlog){
        this.blogList = listBlog;
    }

    @Override
    public BlogCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_blog, parent, false);
        return new ViewHolder(view);
    }

    public void add(BlogAdapter blog){
        blogList.add(blog);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BlogCustomAdapter.ViewHolder holder, int position) {
        final BlogAdapter blog = blogList.get(position);
        holder.title_blog.setText(blog.getTitle_blog());
        holder.desc_blog.setText(blog.getDesc_blog());
        ///edit
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), EditBlog.class);
                mIntent.putExtra("id",blog.getId());
                v.getContext().startActivity(mIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
