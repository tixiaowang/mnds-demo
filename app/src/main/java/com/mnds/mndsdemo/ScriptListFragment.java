package com.mnds.mndsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mnds.mndsdemo.action.refresh_msg.Xhs8380ActionRefreshMessage;
import com.uu.txw.auto.AppInstance;
import com.uu.txw.auto.TaskHub;
import com.uu.txw.auto.common.utils.Logger;
import com.uu.txw.auto.task.data.CusScriptTask;
import com.uu.txw.auto.util.ToastUtil;

import java.io.File;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class ScriptListFragment extends Fragment {

    private Timer timer = new Timer();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mRootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_script_list, null);

        mRootView.findViewById(R.id.btn_start_yanghao).setOnClickListener(v -> startActivity(new Intent(getActivity(), YangHaoActivity.class)));
        mRootView.findViewById(R.id.btn_start_reply).setOnClickListener(v -> startActivity(new Intent(getActivity(), ReplyActivity.class)));
        mRootView.findViewById(R.id.btn_start_refresh).setOnClickListener(v -> {

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //检查sdcard
                    Logger.d("check sdcard");
                    File xhsRefreshMsg = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "xhs_refresh_msg");
                    if (xhsRefreshMsg.exists()) {
                        startRefresh();
                        xhsRefreshMsg.delete();
                    }
                }
            }, 5000, 5000);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //5分钟执行执行
                    startRefresh();
                }
            }, 10000, 300000);


        });
        mRootView.findViewById(R.id.btn_stop_refresh).setOnClickListener(v -> {
            timer.cancel();
        });
        mRootView.findViewById(R.id.btn_check_permission).setOnClickListener(v -> {
            if (AppInstance.getInstance().checkAccesibilityAndIdConfig(false, getActivity())) {
                ToastUtil.show("有权限");
            } else {
                ToastUtil.show("无权限");
            }
        });
        return mRootView;
    }

    private void startRefresh() {
        Logger.d("准备执行 刷新聊天界面");
        TaskHub.start(null, CusScriptTask.TYPE_BIND_UI,
                "刷新聊天界面",
                "com.xingin.xhs",
                "com.xingin.xhs.index.v2.IndexActivityV2",
                "com.xingin.xhs.index.v2.IndexActivityV2",
                null,
                Arrays.asList(Xhs8380ActionRefreshMessage.class)
        );
    }

}
