package com.mnds.mndsdemo.action.explorer_yanghao;

import android.text.TextUtils;

import java.util.List;
import java.util.Random;

/**
 * @Author mnmn
 * @DATE 2024/7/22
 * @DESC
 */
public class ExtUtil {

    static int random(String start, String end) {
        try {
            if (!TextUtils.isEmpty(start) && !TextUtils.isEmpty(end)) {
                int bound = Integer.parseInt(end);
                int i = Integer.parseInt(start);
                return i + new Random().nextInt(bound - i);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    static int random(String period) {
        try {
            if (!TextUtils.isEmpty(period) && period.contains("-")) {
                String[] split = period.split("-");
                if (split.length == 2) {
                    int bound = Integer.parseInt(split[1]);
                    int i = Integer.parseInt(split[0]);
                    return i + new Random().nextInt(bound - i);
                }
            }

        } catch (Exception e) {

        }
        return 0;
    }

    static String random(List<String> list) {
        if (list != null && list.size() > 0) {
            return list.get(new Random().nextInt(list.size()));
        }
        return "";
    }
}
