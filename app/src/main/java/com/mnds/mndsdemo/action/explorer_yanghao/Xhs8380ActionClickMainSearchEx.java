package com.mnds.mndsdemo.action.explorer_yanghao;

import android.view.accessibility.AccessibilityEventExt;
import android.view.accessibility.AccessibilityNodeInfo;

import com.uu.txw.auto.accessibility.AccessibilityHelper;
import com.uu.txw.auto.action.Action;
import com.uu.txw.auto.annotation.EVENT_CLASS;
import com.uu.txw.auto.annotation.EVENT_TYPE;
import com.uu.txw.auto.common.utils.Logger;
import com.uu.txw.auto.task.GlobalVar;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mnmn
 * @DATE 2024/7/7
 * @DESC
 */
@EVENT_TYPE
@EVENT_CLASS(Xhs8380ActionClickMainSearchEx.UI_XHS_INDEX)
public class Xhs8380ActionClickMainSearchEx extends AccessibilityHelper implements Action {
    static final String UI_XHS_INDEX = "com.xingin.xhs.index.v2.IndexActivityV2";
    static final String UI_XHS_SEARCH = "com.xingin.alioth.search.GlobalSearchActivity";
    static final String UI_XHS_NOTE_DETAIL = "com.xingin.matrix.notedetail.NoteDetailActivity";
    static final String XHS_ID_MAIN_SEARCH = "com.xingin.xhs:id/hw4";
    static final String XHS_ID_SEARCH_HISTORY_ITEM = "com.xingin.xhs:id/fed";
    static final String XHS_ID_SEARCH_RV = "com.xingin.xhs:id/fhj";
    static final String XHS_ID_SEARCH_NOTE_LIKE = "com.xingin.xhs:id/ffk";
    static final String XHS_ID_SEARCH_NOTE_TITLE= "com.xingin.xhs:id/gfb";
    static final String XHS_ID_SEARCH_NOTE_TIME= "com.xingin.xhs:id/e09";
    static final String XHS_ID_MAIN_BOTTOM_MENU= "com.xingin.xhs:id/iqq";
    //笔记点击阅读次数
    //最长运行时间
    boolean hasClickMain;
    private long maxTime;
    private long startTime;

    private List<String> explorerTitleList = new ArrayList<>();
    ;

    @Override
    public void run(AccessibilityEventExt event) {
        int maxTimeMinites = ExtUtil.random(GlobalVar.getText("推荐页养号运行时长区间"));
        maxTime = maxTimeMinites * 60_000L;
        Logger.scriptD("最大运行时间", maxTimeMinites + "", "分钟");



        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - startTime > maxTime) {
            Logger.scriptD("超出最大运行时间,结束 ",maxTimeMinites + "","分钟");
            sleepLLL();
            performBackAppHome();
            performGestureClickByIdAndText(XHS_ID_MAIN_BOTTOM_MENU, "消息");
            stop();
            return;
        }

        if (!hasClickMain) {
            int delaySecond = ExtUtil.random(GlobalVar.getText("任务启动延迟区间"));
            long delay = delaySecond * 1000L;
            if (delay > 0) {
                Logger.scriptD("延迟启动 ",delaySecond + "","秒");
                sleep(delay);
            }

            hasClickMain = true;
            showNotifyToast("开始喽");

            performGestureClickByIdAndText(XHS_ID_MAIN_BOTTOM_MENU, "消息");

            sleepLLL();

            performGestureClickScreenRate(92/1080.0f, 2260/2340.0f);
            sleepL();
            performGestureClickScreenRate(92/1080.0f, 2260/2340.0f);

            sleepLLL();
        }

        int refreshCount = ExtUtil.random(GlobalVar.getText("刷新推荐页间隔区间"));
        if (refreshCount > 0 && explorerTitleList.size() >= refreshCount) {
            Logger.scriptD("浏览固定次数后刷新推荐页", refreshCount + "");
            //刷新
            performGestureClickScreenRate(92/1080.0f, 2260/2340.0f);
            sleepL();
            performGestureClickScreenRate(92/1080.0f, 2260/2340.0f);
            explorerTitleList.clear();
            sleepLLL();
            return;
        }

        if (!isRunning()) {
            return;
        }
        performGestureScrollForward();
        sleepLLL();

        AccessibilityNodeInfo nodeRv = findNodeById("com.xingin.xhs:id/fcc");
        if (nodeRv != null) {
            for (int i = 0; isRunning() && i < nodeRv.getChildCount(); i++) {
                AccessibilityNodeInfo child = nodeRv.getChild(i);
                if (child.getContentDescription() != null) {
                    String title = child.getContentDescription().toString();
                    List<String> titleKeyList = GlobalVar.getTextList("匹配笔记标题关键词列表");
                    if (titleKeyList.size() > 0) {
                        for (String s : titleKeyList) {
                            if (title.contains(s)) {
                                if (!explorerTitleList.contains(title)) {
                                    Logger.scriptD("匹配关键词", s);
                                    explorerTitleList.add(title);
                                    performGestureClick(child);
                                    return;
                                }
                            }
                        }
                    } else {
                        if (!explorerTitleList.contains(title)) {
                            explorerTitleList.add(title);
                            performGestureClick(child);
                            return;
                        }
                    }
                }
            }
        }
    }
}
