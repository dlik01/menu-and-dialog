package com.example.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Dialog1 extends DialogFragment {
  public interface Dialog1Listener {
    void onDialogPositiveClick(DialogFragment dialog);
    void onDialogNegativeClick(DialogFragment dialog);
  }

  public Dialog1Listener mListener;
  public Button btnYes, btnNo;

  public void onAttach(Activity activity) {
    super.onAttach(activity);
    try {
      mListener = (Dialog1Listener) activity;
    } catch (ClassCastException e) {
      throw new ClassCastException(activity.toString()
        + " must implement NoticeDialogListener");
    }
  }

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    getDialog().setTitle("Title!");
    View v = inflater.inflate(R.layout.dialog1, null);
    btnYes = v.findViewById(R.id.btnYes);
    btnYes.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mListener.onDialogPositiveClick(Dialog1.this);
      }
    });
    btnNo = v.findViewById(R.id.btnNo);
    btnNo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mListener.onDialogNegativeClick(Dialog1.this);
      }
    });
    return v;
  }
}
