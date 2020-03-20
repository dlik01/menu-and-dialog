package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Dialog1.Dialog1Listener, Dialog2.Dialog2Listener  {
  private Button btdlg1, btdlg2, btdlg3, btdlg4, btdlg5, btdlg6;
  private TextView tv;
  int DIALOG_TIME = 1;
  int DIALOG_DATE = 2;
  int myYear = 2011;
  int myMonth = 02;
  int myDay = 03;
  int myHour = 14;
  int myMinute = 35;

  Dialog1 dlg1;
  Dialog2 dlg2;
  Context mContext;
  Activity mActivity;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if(mContext == null){
      mContext = MainActivity.this;
    }
    if(mActivity == null){
      mActivity = MainActivity.this;
    }


    dlg1 = new Dialog1();
    dlg2 = new Dialog2();


    setContentView(R.layout.activity_main);
    btdlg1 = findViewById(R.id.btdlg1);
    btdlg2 = findViewById(R.id.btdlg2);
    btdlg3 = findViewById(R.id.btdlg3);
    btdlg4 = findViewById(R.id.btdlg4);
    btdlg5 = findViewById(R.id.btdlg5);
    btdlg6 = findViewById(R.id.btdlg6);
    tv = findViewById(R.id.tView);

    /**
     * Работа с диалогами
     * https://developer.android.com/guide/topics/ui/dialogs
     */

    btdlg1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder
          .setTitle("Your Title")
          .setMessage("Click yes to exit!")
          .setCancelable(false)
          .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
              tv.setText(R.string.pressPositiveDialog1);
            }
          })
          .setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
              tv.setText(R.string.pressNegativeDialog1);
            }
          });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
      }
    });

    btdlg2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showDialog(DIALOG_TIME);
      }
    });
    btdlg3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showDialog(DIALOG_DATE);
      }
    });
    btdlg4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogFragment dialog = new Dialog1();
        dialog.show(getSupportFragmentManager(),"Dialog1");
      }
    });
    btdlg5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogFragment dialog = new Dialog2();
        dialog.show(getSupportFragmentManager(),"Dialog2");
      }
    });
    btdlg6.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(R.string.get_color)
          .setItems(R.array.color, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              tv.setText(R.string.dialog_6);
            }
          })
        .show();
      }
    });
  }



  TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      myHour = hourOfDay;
      myMinute = minute;
      tv.setText("Time is " + myHour + " hours " + myMinute + " minutes");
    }
  };

  DatePickerDialog.OnDateSetListener myCallBack2 = new DatePickerDialog.OnDateSetListener() {

    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
      myYear = year;
      myMonth = monthOfYear;
      myDay = dayOfMonth;
      tv.setText("Today is " + myDay + "/" + myMonth + "/" + myYear);
    }
  };


  protected Dialog onCreateDialog(int id) {
    if (id == 1) {
      TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
      return tpd;
    }
    if (id == 2) {
      DatePickerDialog tpd = new DatePickerDialog(this, myCallBack2, myDay, myMonth, myYear);
      return tpd;
    }
    return super.onCreateDialog(id);
  }

  /**
   * https://developer.android.com/guide/topics/ui/menus
   *
   * onCreateOptionsMenu отвечает за отображение меню
   * getMenuInflater().inflate(ссылка меню из ресурсов, menu)
   * либо menu.add()
   */

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);  // определяем наше меню через код menu.add()
    return super.onCreateOptionsMenu(menu);
  }

  /**
   * onOptionsItemSelected Отвечает за обработку нажатий на пункты меню
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.line1:
        Toast.makeText(getBaseContext(), "press menu " + item.getTitle(), Toast.LENGTH_LONG).show();
        break;
      case R.id.line2:
        Toast.makeText(getBaseContext(), "press menu " + item.getTitle(), Toast.LENGTH_LONG).show();
        break;
      case R.id.line3:
        Toast.makeText(getBaseContext(), "press menu " + item.getTitle(), Toast.LENGTH_LONG).show();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * Методы для обратного вызовать для Dialog1 и Dialog2
   */
  @Override
  public void onDialogPositiveClick(DialogFragment dialog) {
    Toast.makeText(mContext, "id " + dialog.getId(), Toast.LENGTH_SHORT).show();
    tv.setText(R.string.pressPositive);
    dialog.dismiss();
  }

  @Override
  public void onDialogNegativeClick(DialogFragment dialog) {
    tv.setText(R.string.pressNegetive);
    dialog.dismiss();
  }

  @Override
  public void onDialogNeutralClick(DialogFragment dialog) {
    tv.setText(R.string.pressNeutral);
    dialog.dismiss();
  }
}
