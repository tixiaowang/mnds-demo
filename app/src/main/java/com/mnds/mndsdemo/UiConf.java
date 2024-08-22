package com.mnds.mndsdemo;

import com.mnds.mndsdemo.condition.ConditionXhsMain;
import com.uu.txw.auto.annotation.UI_CONDITION;

/**
 * @Author mnmn
 * @DATE 2024/8/22
 * @DESC
 */
public class UiConf {
    public static final String XHS_PACKAGE_NAME = "com.xingin.xhs";

    @UI_CONDITION(ConditionXhsMain.class)
    public static final String XHS_MAIN = XHS_PACKAGE_NAME + ".index.v2.IndexActivityV2";
}
