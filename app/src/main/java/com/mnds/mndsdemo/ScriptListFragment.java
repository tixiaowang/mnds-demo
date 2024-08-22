package com.mnds.mndsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class ScriptListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mRootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_script_list, null);

        mRootView.findViewById(R.id.btn_start_yanghao).setOnClickListener(v -> startActivity(new Intent(getActivity(), YangHaoActivity.class)));
        mRootView.findViewById(R.id.btn_start_reply).setOnClickListener(v -> startActivity(new Intent(getActivity(), ReplyActivity.class)));
        return mRootView;
    }

}
