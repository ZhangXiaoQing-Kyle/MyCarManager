package com.kyle.mycar.Fragment;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.kyle.mycar.Bean.MessageEvent;
import com.kyle.mycar.MyUtils.GlobalConstant;
import com.kyle.mycar.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.meta.AbstractSubscriberInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *
 */
public class DatePickerDialogFragment extends AppCompatDialogFragment {

    @BindView(R.id.date_picker_time)
    Button datePickerTime;
    @BindView(R.id.date_picker_cancel)
    Button datePickerCancel;
    @BindView(R.id.date_picker_confirm)
    Button datePickerConfirm;
    Unbinder unbinder;
    @BindView(R.id.date_picker)
    DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_date_picker_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.date_picker_time, R.id.date_picker_cancel, R.id.date_picker_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.date_picker_time:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        StringBuilder sb = new StringBuilder();
                        if (hourOfDay<10){
                            sb.append(0).append(hourOfDay);
                        }else {
                            sb.append(hourOfDay);
                        }
                        sb.append(":");
                        if (minute<10){
                            sb.append(0).append(minute);
                        }else {
                            sb.append(minute);
                        }
                        postMessage(sb.toString(),GlobalConstant.OILFRAGMENT_RETURN_TIME);
                    }
                }
                ,0,0,true);
                timePickerDialog.show();
                datePick();
                dismiss();
                break;
            case R.id.date_picker_cancel:
                dismiss();
                break;
            case R.id.date_picker_confirm:
                datePick();
                break;
        }
    }

    private void datePick() {
        int year = datePicker.getYear();
        int month = datePicker.getMonth()+1;
        int dayOfMonth = datePicker.getDayOfMonth();
        StringBuilder sb =new StringBuilder();
        sb.append(year).append("年");
        if (month<10){
            sb.append(0).append(month);
        }else {
            sb.append(month);
        }
        sb.append("月");
        if (dayOfMonth<10){
            sb.append(0).append(dayOfMonth);
        }else {
            sb.append(dayOfMonth);
        }
        sb.append("日");
        postMessage(sb.toString(), GlobalConstant.OILFRAGMENT_RETURN_DATE);
        dismiss();
    }

    private void postMessage(String str, int OilFragmentConstant) {
        MessageEvent msg = new MessageEvent();
        msg.setMsg(str.toString());
        msg.setFlag(OilFragmentConstant);
        EventBus.getDefault().post(msg);
    }
}