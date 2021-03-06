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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.github.lquiroli.menupager.R;

import java.util.ArrayList;

/**
 * A simple implementation of {@link com.github.lquiroli.menupager.widget.BaseMenuFragmentAdapter}. This class already performs all the lookup operations
 * over the provided collection. Extend this class and override {@link #getPage(int, java.util.ArrayList)} for an easy customization of your layout.
 * <p/>
 * <p>This class assumes that you decorated your collection with the correct annotations to work properly. Please see {@link com.github.lquiroli.menupager.annotation.Collection}
 * and {@link com.github.lquiroli.menupager.annotation.Label} for further details</p>
 * <p>Created by lorenzo.quiroli</p>
 */
public abstract class SimpleMenuFragmentAdapter extends BaseMenuFragmentAdapter {

    private ArrayList mItems;

    public SimpleMenuFragmentAdapter(FragmentManager fm, ArrayList items) {
        super(fm, items);
        mItems = items;
    }

    public ArrayList getItemCollection(Object item) {
        return ReflectUtils.reflectCollection(item);
    }

    public ArrayList determinePageCollection(int pageIndex, int[] menuStack) {

        ArrayList pageItems = mItems;

        for (int count = 0; count < pageIndex; count++) {
            Object item = pageItems.get(menuStack[count]);
            pageItems = getItemCollection(item);
        }

        return pageItems;
    }

    protected void onForwardAnimation(int oldPageIndex, int newPageIndex, int[] animations) {
        animations[0] = R.anim.menu_pager_current_in;
        animations[1] = R.anim.menu_pager_current_out;
    }

    protected void onBackwardAnimation(int oldPageIndex, int newPageIndex, int[] animations) {
        animations[0] = R.anim.menu_pager_back_in;
        animations[1] = R.anim.menu_pager_back_out;
    }

    public Object getItem(int itemIndex, int pageIndex, int[] menuStack) {
        return determinePageCollection(pageIndex, menuStack).get(itemIndex);
    }

    @Override
    protected void onDeletePage(Fragment page) {
        //Do nothing
    }
}
