package com.mnds.mndsdemo.action.auto_reply;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityEventExt;
import android.view.accessibility.AccessibilityNodeInfo;

import com.uu.txw.auto.accessibility.AccessibilityHelper;
import com.uu.txw.auto.action.Action;
import com.uu.txw.auto.annotation.EVENT_CLASS;
import com.uu.txw.auto.annotation.EVENT_TYPE;
import com.uu.txw.auto.common.utils.DpUtil;
import com.uu.txw.auto.common.utils.Logger;
import com.uu.txw.auto.task.GlobalVar;

import java.util.List;
import java.util.Random;

/**
 * @Author mnmn
 * @DATE 2024/7/7
 * @DESC
 */
@EVENT_TYPE
@EVENT_CLASS("com.xingin.im.ui.activity.ChatActivity")
public class Xhs8380ActionSendMsgInChat extends AccessibilityHelper implements Action {

    @Override
    public void run(AccessibilityEventExt event) {
        //如果是群 跳过
//        if (findNodeById("com.xingin.xhs:id/gjx") != null) {
//            performBack();
//            sleepLL();
//            sleepLL();
//            L.scriptD("return");
//            return;
//        }
        List<AccessibilityNodeInfo> listMsg = findNodeListById("com.xingin.xhs:id/aw7");
        //检查自己发过没
        boolean hasSendFirst = false;
        List<AccessibilityNodeInfo> avatarList = findNodeListById("com.xingin.xhs:id/hq2");
        int avgScreen = DpUtil.getScreenWidth() / 2;
        for (AccessibilityNodeInfo avatar : avatarList) {
            Rect outBounds = new Rect();
            avatar.getBoundsInScreen(outBounds);
            int avatarAvg = (outBounds.left + outBounds.right) / 2;
            if (avatarAvg > avgScreen) {
                hasSendFirst = true;
                Logger.scriptD("发过了");
            }
        }

        boolean sendOnlyFirst = "true".equals(GlobalVar.getText("只回第一条"));
        if (sendOnlyFirst) {
            Logger.scriptD("只回第一条");
        }
        if ((!hasSendFirst || !sendOnlyFirst) && listMsg.size() > 0) {
            AccessibilityNodeInfo accessibilityNodeInfo = listMsg.get(0);
            Logger.d("文本 " + accessibilityNodeInfo.getText());


            Rect outBounds = new Rect();
            accessibilityNodeInfo.getBoundsInScreen(outBounds);
            Logger.d("outBounds: " + outBounds);

            List<AccessibilityNodeInfo> nodeListInfosById = findNodeListById("com.xingin.xhs:id/dkk");
            AccessibilityNodeInfo parent = null;
            for (AccessibilityNodeInfo nodeInfo : nodeListInfosById) {
                Rect outBounds1 = new Rect();
                nodeInfo.getBoundsInScreen(outBounds1);
                if (outBounds1.contains(outBounds)) {
                    parent = nodeInfo;
                    break;
                }
            }
            if (parent != null) {
                AccessibilityNodeInfo nodeAvatar = findNodeById("com.xingin.xhs:id/hq2");
                if (nodeAvatar != null) {
                    Rect outBounds1 = new Rect();
                    nodeAvatar.getBoundsInScreen(outBounds1);

                    int avg = (outBounds1.left + outBounds1.right) / 2;
                    Logger.d("avg " + avg);
                    Logger.d("avgScreen " + avgScreen);
                    if (avg < avgScreen) {
                        Logger.scriptD("最后一条是对方发的！");
                        List<String> textList = GlobalVar.getTextList("随机话术组");
                        if (textList.size() > 0) {
                            performSetText(textList.get(new Random().nextInt(textList.size())));
                            sleepLL();
                            performGestureClickByText("发送");
                        }
                    }
                }
            }
        }
        sleepLLL();
        performBack();
        sleepLL();
    }
}
