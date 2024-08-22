package com.mnds.mndsdemo.action.auto_reply;

import android.view.accessibility.AccessibilityEventExt;
import android.view.accessibility.AccessibilityNodeInfo;

import com.uu.txw.auto.accessibility.AccessibilityHelper;
import com.uu.txw.auto.action.Action;
import com.uu.txw.auto.annotation.EVENT_CLASS;
import com.uu.txw.auto.annotation.EVENT_TYPE;
import com.uu.txw.auto.task.GlobalVar;

import java.util.List;
import java.util.Random;

/**
 * @Author mnmn
 * @DATE 2024/7/7
 * @DESC
 */
@EVENT_TYPE
@EVENT_CLASS("com.xingin.im.ui.message.inner.v2.MsgV2Activity")
public class Xhs8380ActionClickCommentMetion extends AccessibilityHelper implements Action {

    @Override
    public void run(AccessibilityEventExt event) {
        sleepLLL();
        List<AccessibilityNodeInfo> nodeList = findNodeListById("com.xingin.xhs:id/fzl");
        if (nodeList.size() > 0) {
            performClick(nodeList.get(0));
            sleepLLL();
            List<String> textList = GlobalVar.getTextList("评论随机话术组");
            if (textList.size() > 0) {
                performSetText(textList.get(new Random().nextInt(textList.size())));
                sleepLL();
                performGestureClickByText("发送");
            }
            sleepLL();
            performBack();
        }
    }

}
