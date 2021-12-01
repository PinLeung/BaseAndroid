package com.youyu.common.utils;

import android.view.View;

/**
 * @author YouYu
 * @date 2021/3/12
 * @description
 */
public abstract class OnNoDoubleClickListener implements View.OnClickListener {

    private long startTime;
    private int viewId;

    @Override
    public void onClick(View v) {
        if (v.getId() == viewId) {
            if (System.currentTimeMillis() - startTime > Constants.INTERVAL) {
                onNoDoubleClick(v);
                startTime = System.currentTimeMillis();
                viewId = v.getId();
            }
        } else {
            startTime = System.currentTimeMillis();
            viewId = v.getId();
            onNoDoubleClick(v);
        }
    }

    public abstract void onNoDoubleClick(View view);

}
