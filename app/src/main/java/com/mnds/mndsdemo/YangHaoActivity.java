package com.mnds.mndsdemo;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mnds.mndsdemo.action.explorer_yanghao.Xhs8380ActionClickMainSearchEx;
import com.mnds.mndsdemo.action.explorer_yanghao.Xhs8380ActionDoInNoteDetailEx;
import com.mnds.mndsdemo.action.explorer_yanghao.Xhs8380ActionDoInVideoDetailEx;
import com.uu.txw.auto.AppInstance;
import com.uu.txw.auto.TaskHub;
import com.uu.txw.auto.common.utils.Logger;
import com.uu.txw.auto.task.data.CusScriptTask;
import com.uu.txw.auto.task.data.ScriptStatement;
import com.uu.txw.auto.task.data.ScriptStatementVar;
import com.uu.txw.auto.task.data.ScriptStatementVarList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class YangHaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yang_hao);

        findViewById(R.id.btn_start_yanghao).setOnClickListener(v -> {
            if (!AppInstance.getInstance().checkAccesibilityAndIdConfig(false, this)) {
                Logger.d("check access fail");
                return;
            }
            List<ScriptStatement> statementList = new ArrayList<>();
            statementList.add(new ScriptStatementVar("推荐页养号运行时长区间", ((EditText) findViewById(R.id.et_duration)).getText().toString()));
            statementList.add(new ScriptStatementVar("刷新推荐页间隔区间", ((EditText) findViewById(R.id.et_refresh)).getText().toString()));
            statementList.add(new ScriptStatementVar("笔记中关注概率", ((EditText) findViewById(R.id.et_gz)).getText().toString()));
            statementList.add(new ScriptStatementVar("笔记点赞概率", ((EditText) findViewById(R.id.et_dz)).getText().toString()));
            statementList.add(new ScriptStatementVar("笔记收藏概率", ((EditText) findViewById(R.id.et_sc)).getText().toString()));
            statementList.add(new ScriptStatementVar("笔记评论点赞概率", ((EditText) findViewById(R.id.et_pldz)).getText().toString()));
            statementList.add(new ScriptStatementVar("评论列表上滑次数区间", ((EditText) findViewById(R.id.et_plsh)).getText().toString()));
            statementList.add(new ScriptStatementVarList("匹配笔记标题关键词列表", ((EditText) findViewById(R.id.et_kw)).getText().toString()));


            TaskHub.start(this,
                    CusScriptTask.TYPE_BIND_UI,
                    "养号",
                    "com.xingin.xhs",
                    "com.xingin.xhs.index.v2.IndexActivityV2",
                    "com.xingin.xhs.index.v2.IndexActivityV2",
                    statementList,
                    Arrays.asList(Xhs8380ActionClickMainSearchEx.class,
                            Xhs8380ActionDoInNoteDetailEx.class,
                            Xhs8380ActionDoInVideoDetailEx.class));

        });
    }

}
