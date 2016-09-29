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

package me.yifeiyuan.climb.base;

import android.support.annotation.MenuRes;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import me.yifeiyuan.climb.R;
import me.yifeiyuan.climb.module.main.OpenDrawerEvent;
import me.yifeiyuan.climb.tools.bus.OldDriver;

/**
 * Created by 程序亦非猿 on 16/9/29.
 *
 * 脱离 ActionBar 单纯使用 Toolbar
 */
public abstract class ToolbarFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener {

    // 不能为 null
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    /**
     * @param title 标题
     * @param menu 菜单
     */
    public void setupToolbar(String title, @MenuRes int menu) {

        mToolbar.setTitle(title);
        mToolbar.inflateMenu(menu);
        mToolbar.setOnMenuItemClickListener(this);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu_white));
        mToolbar.setNavigationOnClickListener(v -> {
            OldDriver.getIns().post(new OpenDrawerEvent());
        });
//        ((AppCompatActivity)mActivity).setSupportActionBar(mToolbar);
//        ((AppCompatActivity)mActivity).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
