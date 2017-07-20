package kost.golok.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import java.util.ArrayList;

import kost.golok.absenpelatih.R;
import kost.golok.object.Student;
import kost.golok.utility.Component;

public class AttendanceAdapter extends ArrayAdapter<Student> {

    public static ArrayList<Student> sSelectedStudents = new ArrayList<>();

    public AttendanceAdapter(Context context, ArrayList<Student> studentList) {
        super(context, 0, studentList);
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final Student currentStudent = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_absen, parent, false);
        }

        if (currentStudent != null) {
            // Add the student to attended student list when the attend radio button is clicked
            RadioButton rbAttend = (RadioButton) view.findViewById(R.id.rb_item_attendance_check_attend);
            rbAttend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    sSelectedStudents.add(currentStudent);
                }
            });

            // Remove the student from attended student list when the not attend radio button is clicked
            RadioButton rbNotAttend = (RadioButton) view.findViewById(R.id.rb_item_attendance_check_not_attend);
            rbNotAttend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sSelectedStudents.remove(currentStudent);
                }
            });

            // Check the radio button based on the attended student list
            if(sSelectedStudents.contains(currentStudent)){
                rbAttend.setChecked(true);
            }

            Component.setText(view, R.id.tv_item_absen_nama_murid, currentStudent.getName());
        }
        return view;
    }

}