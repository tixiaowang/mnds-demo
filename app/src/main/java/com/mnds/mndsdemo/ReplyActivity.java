package com.mnds.mndsdemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mnds.mndsdemo.action.auto_reply.Xhs8380ActionClickCommentMetion;
import com.mnds.mndsdemo.action.auto_reply.Xhs8380ActionClickMessageMenuWatch;
import com.mnds.mndsdemo.action.auto_reply.Xhs8380ActionClickStrangerMsg;
import com.mnds.mndsdemo.action.auto_reply.Xhs8380ActionSendMsgInChat;
import com.uu.txw.auto.AppInstance;
import com.uu.txw.auto.TaskHub;
import com.uu.txw.auto.common.utils.Logger;
import com.uu.txw.auto.common.utils.SP;
import com.uu.txw.auto.task.TaskController;
import com.uu.txw.auto.task.data.CusScriptTask;
import com.uu.txw.auto.task.data.ScriptStatement;
import com.uu.txw.auto.task.data.ScriptStatementVar;
import com.uu.txw.auto.task.data.ScriptStatementVarList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReplyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        initView();
    }

    private void initView() {
        ((EditText) findViewById(R.id.et_hs1)).setText(SP.getString("KEY_REPLY_HS_1", ""));
        ((EditText) findViewById(R.id.et_hs2)).setText(SP.getString("KEY_REPLY_HS_2", ""));
        ((EditText) findViewById(R.id.et_hs3)).setText(SP.getString("KEY_REPLY_HS_3", ""));
        ((EditText) findViewById(R.id.et_hs4)).setText(SP.getString("KEY_REPLY_HS_4", ""));
        ((EditText) findViewById(R.id.et_hs5)).setText(SP.getString("KEY_REPLY_HS_5", ""));
        ((EditText) findViewById(R.id.et_pl_hs1)).setText(SP.getString("KEY_REPLY_PL_HS_1", ""));
        ((EditText) findViewById(R.id.et_pl_hs2)).setText(SP.getString("KEY_REPLY_PL_HS_2", ""));
        ((EditText) findViewById(R.id.et_pl_hs3)).setText(SP.getString("KEY_REPLY_PL_HS_3", ""));
        ((EditText) findViewById(R.id.et_gz_hs1)).setText(SP.getString("KEY_REPLY_GZ_HS_1", ""));
        ((EditText) findViewById(R.id.et_gz_hs2)).setText(SP.getString("KEY_REPLY_GZ_HS_2", ""));
        ((EditText) findViewById(R.id.et_gz_hs3)).setText(SP.getString("KEY_REPLY_GZ_HS_3", ""));

        ((CheckBox) findViewById(R.id.cb_reply_first)).setChecked(SP.getBoolean("KEY_REPLY_SX_FIRST", false));
        ((CheckBox) findViewById(R.id.cb_reply_gz)).setChecked(SP.getBoolean("KEY_REPLY_GZ", false));
        ((CheckBox) findViewById(R.id.cb_reply_pl)).setChecked(SP.getBoolean("KEY_REPLY_PL", false));
        ((CheckBox) findViewById(R.id.cb_reply_sx)).setChecked(SP.getBoolean("KEY_REPLY_SX", false));

        findViewById(R.id.btn_start_yanghao).setOnClickListener(v -> {
            if (!AppInstance.getInstance().checkAccesibilityAndIdConfig(false, this)) {
                Logger.d("check access fail");
                return;
            }
            List<ScriptStatement> statementList = new ArrayList<>();
            String s1 = ((EditText) findViewById(R.id.et_hs1)).getText().toString();
            String s2 = ((EditText) findViewById(R.id.et_hs2)).getText().toString();
            String s3 = ((EditText) findViewById(R.id.et_hs3)).getText().toString();
            String s4 = ((EditText) findViewById(R.id.et_hs4)).getText().toString();
            String s5 = ((EditText) findViewById(R.id.et_hs5)).getText().toString();

            String sPl1 = ((EditText) findViewById(R.id.et_pl_hs1)).getText().toString();
            String sPl2 = ((EditText) findViewById(R.id.et_pl_hs2)).getText().toString();
            String sPl3 = ((EditText) findViewById(R.id.et_pl_hs3)).getText().toString();
            String sGz1 = ((EditText) findViewById(R.id.et_gz_hs1)).getText().toString();
            String sGz2 = ((EditText) findViewById(R.id.et_gz_hs2)).getText().toString();
            String sGz3 = ((EditText) findViewById(R.id.et_gz_hs3)).getText().toString();

            boolean checked = ((CheckBox) findViewById(R.id.cb_reply_first)).isChecked();
            boolean checkedGz = ((CheckBox) findViewById(R.id.cb_reply_gz)).isChecked();
            boolean checkedPl = ((CheckBox) findViewById(R.id.cb_reply_pl)).isChecked();
            boolean checkedSx = ((CheckBox) findViewById(R.id.cb_reply_sx)).isChecked();

            SP.putString("KEY_REPLY_HS_1", s1);
            SP.putString("KEY_REPLY_HS_2", s2);
            SP.putString("KEY_REPLY_HS_3", s3);
            SP.putString("KEY_REPLY_HS_4", s4);
            SP.putString("KEY_REPLY_HS_5", s5);

            SP.putString("KEY_REPLY_PL_HS_1", sPl1);
            SP.putString("KEY_REPLY_PL_HS_2", sPl2);
            SP.putString("KEY_REPLY_PL_HS_3", sPl3);
            SP.putString("KEY_REPLY_GZ_HS_1", sGz1);
            SP.putString("KEY_REPLY_GZ_HS_2", sGz2);
            SP.putString("KEY_REPLY_GZ_HS_3", sGz3);

            SP.putBoolean("KEY_REPLY_SX_FIRST", checked);
            SP.putBoolean("KEY_REPLY_GZ", checkedGz);
            SP.putBoolean("KEY_REPLY_PL", checkedPl);
            SP.putBoolean("KEY_REPLY_SX", checkedSx);


            String collect = Stream.of(
                    s1,
                    s2,
                    s3,
                    s4,
                    s5
            ).filter(s -> !TextUtils.isEmpty(s)).collect(Collectors.joining(TaskController.CUS_SCRIPT_INIT_STATEMENT_VAR_DELIMITER));
            String collectPl = Stream.of(
                    sPl1,
                    sPl2,
                    sPl3
            ).filter(s -> !TextUtils.isEmpty(s)).collect(Collectors.joining(TaskController.CUS_SCRIPT_INIT_STATEMENT_VAR_DELIMITER));
            String collectGz = Stream.of(
                    sGz1,
                    sGz2,
                    sGz3
            ).filter(s -> !TextUtils.isEmpty(s)).collect(Collectors.joining(TaskController.CUS_SCRIPT_INIT_STATEMENT_VAR_DELIMITER));
            statementList.add(new ScriptStatementVarList("随机话术组", collect));
            statementList.add(new ScriptStatementVarList("评论随机话术组", collectPl));
            statementList.add(new ScriptStatementVarList("关注随机话术组", collectGz));
            statementList.add(new ScriptStatementVar("只回第一条", checked));
            statementList.add(new ScriptStatementVar("回复关注", checkedGz));
            statementList.add(new ScriptStatementVar("回复评论", checkedPl));
            statementList.add(new ScriptStatementVar("回复私信", checkedSx));

            TaskHub.start(this, CusScriptTask.TYPE_BIND_UI,
                    "自动回复",
                    "com.xingin.xhs",
                    "com.xingin.xhs.index.v2.IndexActivityV2",
                    "com.xingin.xhs.index.v2.IndexActivityV2",
                    statementList,
                    Arrays.asList(Xhs8380ActionClickMessageMenuWatch.class,
                            Xhs8380ActionClickCommentMetion.class,
                            Xhs8380ActionClickStrangerMsg.class,
                            Xhs8380ActionSendMsgInChat.class)
            );
        });

    }

}
