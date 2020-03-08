package com.example.royal.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.royal.R;

public class UserDialog extends AppCompatDialogFragment {
    TextView rateapp,rateclup,rateservic,comment;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.userinfo, null);
        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        rateclup=view.findViewById(R.id.ratecl);
        rateservic=view.findViewById(R.id.rateser);
        rateapp=view.findViewById(R.id.rateap);
        comment=view.findViewById(R.id.commen);


        return builder.create();
    }
}
