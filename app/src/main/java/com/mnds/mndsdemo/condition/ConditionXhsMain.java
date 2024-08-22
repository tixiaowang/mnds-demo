package com.mnds.mndsdemo.condition;

import com.uu.txw.auto.accessibility.AccessibilityHelper;
import com.uu.txw.auto.condition.Condition;

/**
 * @Author mnmn
 * @DATE 2024/8/18
 * @DESC
 */
public class ConditionXhsMain implements Condition {
    @Override
    public boolean c() {
        return AccessibilityHelper.findNodeById("com.xingin.xhs:id/iqq") != null;
    }
}
