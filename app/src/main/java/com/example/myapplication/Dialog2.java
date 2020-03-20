package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class Dialog2 extends DialogFragment {
  final String LOG_TAG = "myLogs";
  public interface Dialog2Listener {
    void onDialogPositiveClick(DialogFragment dialog);
    void onDialogNegativeClick(DialogFragment dialog);
    void onDialogNeutralClick(DialogFragment dialog);
  }
  Dialog2Listener mListener;

  public void onAttach(Activity activity) {
    super.onAttach(activity);
    try {
      mListener = (Dialog2.Dialog2Listener) activity;
    } catch (ClassCastException e) {
      throw new ClassCastException(activity.toString()
        + " must implement NoticeDialogListener");
    }
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setMessage("Title")
      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
          mListener.onDialogPositiveClick(Dialog2.this);
        }
      })
      .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
          mListener.onDialogNegativeClick(Dialog2.this);
        }
      })
    .setNeutralButton("bb", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        mListener.onDialogNeutralClick(Dialog2.this);
      }
    });
    return builder.create();
  }
}
