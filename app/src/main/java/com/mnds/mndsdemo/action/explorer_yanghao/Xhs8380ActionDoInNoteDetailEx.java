package com.mnds.mndsdemo.action.explorer_yanghao;

import android.text.TextUtils;
import android.view.accessibility.AccessibilityEventExt;
import android.view.accessibility.AccessibilityNodeInfo;

import com.uu.txw.auto.accessibility.AccessibilityHelper;
import com.uu.txw.auto.action.Action;
import com.uu.txw.auto.action.AndroidCurrentActivity;
import com.uu.txw.auto.annotation.EVENT_CLASS;
import com.uu.txw.auto.annotation.EVENT_TYPE;
import com.uu.txw.auto.common.utils.DateUtil;
import com.uu.txw.auto.common.utils.Logger;
import com.uu.txw.auto.common.utils.SP;
import com.uu.txw.auto.task.GlobalVar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author mnmn
 * @DATE 2024/7/7
 * @DESC
 */
@EVENT_TYPE
@EVENT_CLASS(Xhs8380ActionClickMainSearchEx.UI_XHS_NOTE_DETAIL)
public class Xhs8380ActionDoInNoteDetailEx extends AccessibilityHelper implements Action {
    private static final String ID_AUTH_LABLE = "com.xingin.xhs:id/jm8";
    private static final String ID_COMMENT_ITEM = "com.xingin.xhs:id/il4";
    private static final String ID_COMMENT_LIKE_BTN = "com.xingin.xhs:id/ezl";

    private int followCount;

    @Override
    public void run(AccessibilityEventExt event) {


        sleepRandom(5000);

        //滑动图片
        AccessibilityNodeInfo nodeInfosById3 = findNodeById("com.xingin.xhs:id/cjz");
        if (nodeInfosById3 != null) {
            if (nodeInfosById3.getParent().getContentDescription() != null) {
                try {
                    String s = nodeInfosById3.getParent().getContentDescription().toString();
//                图片描述 图片,第1张,共 1张,双指左划或右划即可查看更多内容
                    Pattern compile = Pattern.compile("共 (\\d+)张");
                    Matcher matcher = compile.matcher(s);
                    if (matcher.find()) {
                        int count = Integer.parseInt(matcher.group(1)) - 1 - new Random().nextInt(2);
                        for (int i = 0; isRunning() && i < count; i++) {
                            if (AndroidCurrentActivity.isCurrentActivity(Xhs8380ActionClickMainSearchEx.UI_XHS_NOTE_DETAIL)) {
                                performGestureScrollLeft();
                                sleepLLL();
                                sleepRandom(2000);
                            } else {
                                break;
                            }
                        }
                    }
                } catch (Exception e) {

                }
            }
        }

        AccessibilityNodeInfo nodeInfosById = findNodeById("com.xingin.xhs:id/followTV");
        if (nodeInfosById != null && "关注".equals(nodeInfosById.getText().toString())) {
            if (new Random().nextInt(100) < GlobalVar.tryGetLong(GlobalVar.getText("笔记中关注概率"), 0)) {
                if (followCount < ExtUtil.random(GlobalVar.getText("笔记中关注次数上限区间"))) {
                    performGestureClick(nodeInfosById);
                    followCount++;
                    sleepLLL();
                }
            }
        }
        performGestureScrollForward();
        sleepLLL();
        AccessibilityNodeInfo likeBtn = findNodeById("com.xingin.xhs:id/geh");
        AccessibilityNodeInfo collectBtn = findNodeById("com.xingin.xhs:id/gcn");
        if (likeBtn != null) {
            CharSequence description = likeBtn.getContentDescription();
            if (description != null && description.toString().startsWith("点赞")) {
                if (new Random().nextInt(100) < GlobalVar.tryGetLong(GlobalVar.getText("笔记点赞概率"), 0)) {
                    String spLikeKey = "每日笔记点赞次数上限区间" + DateUtil.getyMd(System.currentTimeMillis());
                    int spLikeCount = SP.getInt(spLikeKey, 0);
                    if (spLikeCount < ExtUtil.random(GlobalVar.getText("每日笔记点赞次数上限区间"))) {
                        performClick(likeBtn);
                        sleepLLL();
                        SP.putInt(spLikeKey, spLikeCount + 1);
                    }
                }
            }
        }
        if (collectBtn != null) {
            CharSequence description = collectBtn.getContentDescription();
            if (description != null && description.toString().startsWith("收藏")) {
                if (new Random().nextInt(100) < GlobalVar.tryGetLong(GlobalVar.getText("笔记收藏概率"), 0)) {
                    String spLikeKey = "每日笔记收藏次数上限区间" + DateUtil.getyMd(System.currentTimeMillis());
                    int spLikeCount = SP.getInt(spLikeKey, 0);
                    if (spLikeCount < ExtUtil.random(GlobalVar.getText("每日笔记收藏次数上限区间"))) {
                        performClick(collectBtn);
                        sleepLLL();
                        SP.putInt(spLikeKey, spLikeCount + 1);
                    }
                }
            }
        }
        sleepLLL();
        if (!isRunning()) {
            return;
        }
        performGestureScrollForward();
        sleepLLL();

        if (new Random().nextInt(100) < GlobalVar.tryGetLong(GlobalVar.getText("笔记评论概率"), 0)) {
            String spLikeKey = "每日笔记评论次数上限区间" + DateUtil.getyMd(System.currentTimeMillis());
            int spLikeCount = SP.getInt(spLikeKey, 0);
            if (spLikeCount < ExtUtil.random(GlobalVar.getText("每日笔记评论次数上限区间"))) {
                SP.putInt(spLikeKey, spLikeCount + 1);
                String ramComment = ExtUtil.random(GlobalVar.getTextList("笔记评论列表"));
                if (!TextUtils.isEmpty(ramComment)) {
                    performGestureClick("com.xingin.xhs:id/e1d");
                    sleepLLL();
                    String comment = ramComment;
                    performSetText("com.xingin.xhs:id/f9d", comment);
                    sleepLLL();
                    performGestureClickByText("发送");
                    sleepLLL();
                }
            }
        }


        List<String> commentList = new ArrayList<>();
        int scrollCount = ExtUtil.random(GlobalVar.getText("评论列表上滑次数区间"));
        if (scrollCount == 0) {
            scrollCount = 10 + new Random().nextInt(10);
        }
        Logger.scriptD("评论列表上滑次数", scrollCount + "");
        for (int i = 0; isRunning() && i < scrollCount; i++) {
            if (!AndroidCurrentActivity.isCurrentActivity(Xhs8380ActionClickMainSearchEx.UI_XHS_NOTE_DETAIL)) {
                break;
            }
            Logger.scriptD("上滑第", i + "", "次");
            performGestureScrollForward();
            sleepLLL();
            sleepLLL();

            if (!isRunning()) {
                return;
            }

            List<AccessibilityNodeInfo> list = findNodeListById("com.xingin.xhs:id/ez4");
            for (AccessibilityNodeInfo accessibilityNodeInfo : list) {
                AccessibilityNodeInfo nodeInfosById1 = findNodeById(accessibilityNodeInfo, "com.xingin.xhs:id/jnd");
//                if(nodeInfosById1 != null && nodeInfosById1.getText() != null &&
//                        (nodeInfosById1.getText().toString().contains("分钟前") || nodeInfosById1.getText().toString().contains("小时前"))){
//                }
                if (nodeInfosById1 != null && nodeInfosById1.getText() != null) {
                    if (commentList.contains(nodeInfosById1.getText().toString())) {
                        continue;
                    } else {
                        commentList.add(nodeInfosById1.getText().toString());
                    }
                }
                AccessibilityNodeInfo nodeInfosById2 = findNodeById(accessibilityNodeInfo, "com.xingin.xhs:id/ezl");
                if (nodeInfosById2.getContentDescription() != null && nodeInfosById2.getContentDescription().toString().startsWith("点赞")) {

                    if (new Random().nextInt(100) < GlobalVar.tryGetLong(GlobalVar.getText("笔记评论点赞概率"), 0)) {
                        String spLikeKey = "每日笔记评论点赞次数上限区间" + DateUtil.getyMd(System.currentTimeMillis());
                        int spLikeCount = SP.getInt(spLikeKey, 0);
                        if (spLikeCount < ExtUtil.random(GlobalVar.getText("每日笔记评论点赞次数上限区间"))) {
                            SP.putInt(spLikeKey, spLikeCount + 1);
                            performGestureClick(nodeInfosById2);
                            sleepLLL();
                        }
                    }
                }
            }

            List<AccessibilityNodeInfo> nodeInfosById1List = findNodeListById("com.xingin.xhs:id/f0_");
            if (nodeInfosById1List.size() > 0) {
                List<String> textList = new ArrayList<>();
                for (AccessibilityNodeInfo accessibilityNodeInfo : nodeInfosById1List) {
                    if (accessibilityNodeInfo.getText() != null) {
                        textList.add(accessibilityNodeInfo.getText().toString());
                    }
                }
                if (textList.contains("- 到底了 -")) {
                    Logger.scriptD("到底了");
                    break;
                }
            }
        }
        performBack();
    }
}
