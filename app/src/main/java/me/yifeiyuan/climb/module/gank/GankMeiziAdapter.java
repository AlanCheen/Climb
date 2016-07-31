/*
 * Copyright (C) 2016, 程序亦非猿
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.yifeiyuan.climb.module.gank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.data.GAndEntity;
import me.yifeiyuan.climb.ui.view.RatioImageView;

/**
 * Created by 程序亦非猿 on 16/7/28.
 */
public class GankMeiziAdapter extends RecyclerView.Adapter<GankMeiziAdapter.GankViewHolder>{

    private List<GAndEntity> mDatas;
    private Context mContext;

    public GankMeiziAdapter(Context context, List<GAndEntity> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.gank_item_meizi, parent, false);

        return new GankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GankViewHolder holder, int position) {

        GAndEntity entity = mDatas.get(position);

        Glide.with(mContext)
                .load(entity.url)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .override(300,400)
//                .crossFade(R.anim.fade_in)
                .centerCrop()
                .into(holder.ivMeizi);

        holder.itemView.setOnClickListener(v -> {
            if (null != mOnItemClickListener) {
                mOnItemClickListener.onItemClick(holder,entity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class GankViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_meizi)
        RatioImageView ivMeizi;

        public GankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private onItemClickListener mOnItemClickListener;

    public interface onItemClickListener {
        void onItemClick(GankViewHolder holder,GAndEntity entity);
    }
}
