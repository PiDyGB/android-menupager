/*
   Copyright (C) 2015 Lorenzo Quiroli

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.github.lquiroli.menupager.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.lquiroli.menupager.R;

import java.util.ArrayList;

/**
 * A simple implementation of a {@link com.github.lquiroli.menupager.widget.MenuPager.Adapter}. This adapter renders simple list items and fills them with
 * the appropriate label
 * <p/>
 * <p>This class assumes that you decorated your collection with the correct annotations to work properly. Please see {@link com.github.lquiroli.menupager.annotation.Collection}
 * and {@link com.github.lquiroli.menupager.annotation.Label} for further details</p>
 * <p>Created by lorenzo.quiroli</p>
 */
public final class SimpleMenuRecyclerAdapter extends MenuPager.Adapter<SimpleMenuRecyclerAdapter.ViewHolder> {

    private ArrayList mData;

    public SimpleMenuRecyclerAdapter(ArrayList data) {
        mData = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_menu_item, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Object item = mData.get(i);
        viewHolder.menuItemLabel.setText(ReflectUtils.reflectLabel(item));
        if (ReflectUtils.reflectCollection(item).size() == 0) {
            viewHolder.menuItemForward.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends MenuPager.ViewHolder {

        private TextView menuItemLabel;
        private ImageView menuItemForward;

        public ViewHolder(View itemView) {
            super(itemView);
            menuItemLabel = (TextView) itemView.findViewById(R.id.menu_item_label);
            menuItemForward = (ImageView) itemView.findViewById(R.id.menu_item_forward);
        }
    }

}
