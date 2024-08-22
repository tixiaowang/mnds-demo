package com.mnds.mndsdemo.action.auto_reply;

import android.view.accessibility.AccessibilityEventExt;
import android.view.accessibility.AccessibilityNodeInfo;

import com.uu.txw.auto.DelayKit;
import com.uu.txw.auto.accessibility.AccessibilityHelper;
import com.uu.txw.auto.action.Action;
import com.uu.txw.auto.annotation.EVENT_CLASS;
import com.uu.txw.auto.annotation.EVENT_TYPE;
import com.uu.txw.auto.common.utils.DateUtil;
import com.uu.txw.auto.common.utils.Logger;
import com.uu.txw.auto.task.GlobalVar;

/**
 * @Author mnmn
 * @DATE 2024/7/7
 * @DESC
 */
@EVENT_TYPE
@EVENT_CLASS("com.xingin.xhs.index.v2.IndexActivityV2")
public class Xhs8380ActionClickMessageMenuWatch extends AccessibilityHelper implements Action {
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

        while (isRunning() && isMainUi()) {
            Logger.scriptD("监听中 " + DateUtil.getMdHms(System.currentTimeMillis()));
            //监听关注

            //监听评论
            AccessibilityNodeInfo nodeDotPl = findNodeById("com.xingin.xhs:id/b9d");
            if (nodeDotPl != null && "true".equals(GlobalVar.getText("回复评论"))) {
                Logger.scriptD("有新评论");
                performClick(nodeDotPl);
                return;
            }

            AccessibilityNodeInfo rv = findNodeById("com.xingin.xhs:id/fyd");
            if (rv != null) {
                if ("true".equals(GlobalVar.getText("回复私信"))) {
                    Logger.d("rv.getChildCount() " + rv.getChildCount());
                    for (int i = 0; isRunning() && i < rv.getChildCount(); i++) {
                        AccessibilityNodeInfo child = rv.getChild(i);
                        if (child == null) {
                            continue;
                        }
                        AccessibilityNodeInfo dot = findNodeById(child, "com.xingin.xhs:id/a1j");
                        Logger.d("dot " + dot);
                        if (dot != null) {
                            performClick(child);
                            return;
                        }
                    }
                }

                DelayKit.sleepLLL();
            }
        }
    }

    private boolean isMainUi() {
        return findNodeById(XHS_ID_MAIN_BOTTOM_MENU) != null;
    }
}
