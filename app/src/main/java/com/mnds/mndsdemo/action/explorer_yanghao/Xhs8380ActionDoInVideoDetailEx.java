package com.mnds.mndsdemo.action.explorer_yanghao;

import android.view.accessibility.AccessibilityEventExt;

import com.uu.txw.auto.accessibility.AccessibilityHelper;
import com.uu.txw.auto.action.Action;
import com.uu.txw.auto.annotation.EVENT_CLASS;
import com.uu.txw.auto.annotation.EVENT_TYPE;
import com.uu.txw.auto.task.GlobalVar;

import java.util.Random;

/**
 * @Author mnmn
 * @DATE 2024/7/7
 * @DESC
 */
@EVENT_TYPE
@EVENT_CLASS("com.xingin.matrix.detail.activity.DetailFeedActivity")
public class Xhs8380ActionDoInVideoDetailEx extends AccessibilityHelper implements Action {
    @Override
    public void run(AccessibilityEventExt event) {

        sleepLL();
        int delayTimeSeccode = ExtUtil.random(GlobalVar.getText("播放视频时间区间"));
        if (delayTimeSeccode == 0) {
            performGestureClick(400 + new Random().nextInt(400) + new Random().nextFloat(), 600 + new Random().nextInt(600) + new Random().nextFloat());
            sleepLLL();
        } else {
            sleep(delayTimeSeccode + 1000);
        }
        performBack();
        //没有 关注 点赞 收藏 评论


    }
}
