package com.mnds.mndsdemo.action.refresh_msg;

import android.view.accessibility.AccessibilityEventExt;

import com.uu.txw.auto.accessibility.AccessibilityHelper;
import com.uu.txw.auto.action.Action;
import com.uu.txw.auto.annotation.EVENT_CLASS;
import com.uu.txw.auto.annotation.EVENT_TYPE;

/**
 * @Author mnmn
 * @DATE 2024/7/7
 * @DESC
 */
@EVENT_TYPE
@EVENT_CLASS("com.xingin.xhs.index.v2.IndexActivityV2")
public class Xhs8380ActionRefreshMessage extends AccessibilityHelper implements Action {
    private String XHS_ID_MAIN_BOTTOM_MENU = "com.xingin.xhs:id/iqq";


    @Override
    public void run(AccessibilityEventExt event) {

        sleepLL();
        while (findNodeById("com.xingin.xhs:id/cc9") == null) {
            performGestureClickByIdAndText(XHS_ID_MAIN_BOTTOM_MENU, "消息");
            sleepLL();
            performGestureScrollBack();
            sleepLLL();
        }

        if (isRunning() && isMainUi()) {
            performGestureScrollBack();
        }
        stop();
    }

    private boolean isMainUi() {
        return findNodeById(XHS_ID_MAIN_BOTTOM_MENU) != null;
    }
}
