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
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.data.entity.GankEntity;
import me.yifeiyuan.climb.module.web.WebActivity;
import me.yifeiyuan.climb.tools.TraceableClickListener;

/**
 * Created by 程     (http://weibo.com/alancheeen)
 * on 15/11/23
 */
public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankViewHolder> {

    private List<GankEntity> mDatas;
    private Context mContext;

    public GankAdapter(Context context, List<GankEntity> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gank_android, parent, false);
        return new GankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GankViewHolder holder, int position) {

        GankEntity entity = mDatas.get(position);
        holder.tvContent.setText(entity.desc);

        holder.itemView.setOnClickListener(new TraceableClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                WebActivity.comeOn(mContext, entity.url);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class GankViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.content)
        TextView tvContent;

        GankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
