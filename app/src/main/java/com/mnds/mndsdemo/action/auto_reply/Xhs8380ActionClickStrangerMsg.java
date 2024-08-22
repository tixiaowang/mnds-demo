package com.mnds.mndsdemo.action.auto_reply;

import android.view.accessibility.AccessibilityEventExt;
import android.view.accessibility.AccessibilityNodeInfo;

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
@EVENT_CLASS("com.xingin.im.ui.activity.StrangerMsgActivity")
public class Xhs8380ActionClickStrangerMsg extends AccessibilityHelper implements Action {

    @Override
    public void run(AccessibilityEventExt event) {
        AccessibilityNodeInfo rv = findNodeByClassName("androidx.recyclerview.widget.RecyclerView");
        if (rv != null) {
            for (int i = 0; isRunning() && i < rv.getChildCount(); i++) {
                AccessibilityNodeInfo child = rv.getChild(i);
                if (child == null) {
                    continue;
                }
                AccessibilityNodeInfo dot = findNodeById(child, "com.xingin.xhs:id/a1j");
                if (dot != null) {
                    performClick(child);
                    return;
                }
            }
            performBack();
        }
    }
}
