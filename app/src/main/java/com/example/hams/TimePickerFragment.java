package com.example.hams;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker.
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it.
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time the user picks.
        String hourString;
        String minuteString;
        if(hourOfDay < 10){
            //concatenate string with a 0 in front
            hourString = "0" + String.valueOf(hourOfDay);
        }else{
            hourString = String.valueOf(hourOfDay);
        }

        if(minute < 10){
            minuteString = "0" + String.valueOf(minute);
        }else{
            minuteString = String.valueOf(minute);
        }
        String formattedTime = hourString + ":" + minuteString; // Adjust formatting as needed
        ((UpcomingShifts)getActivity()).setSelectedTime(formattedTime, this.getTag());
    }

}

