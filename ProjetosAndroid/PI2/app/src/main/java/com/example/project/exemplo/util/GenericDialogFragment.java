package com.example.project.exemplo.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.project.exemplo.util.Interface.IGenericDialogFragment;

public class GenericDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private static final String EXTRA_ID = "id";
    private static final String EXTRA_MESSAGE = "message";
    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_BUTTONS = "buttons";
    private static final String DIALOG_TAG = "SimpleDialog";
    private Context context;
    private int mDialogId;

    public static GenericDialogFragment newDialog(Context c, int id, int title, int message, int[] buttonTexts) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ID, id);
        bundle.putInt(EXTRA_TITLE, title);
        bundle.putInt(EXTRA_MESSAGE, message);
        bundle.putIntArray(EXTRA_BUTTONS, buttonTexts);

        GenericDialogFragment dialog = new GenericDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mDialogId = getArguments().getInt(EXTRA_ID);
        int title = getArguments().getInt(EXTRA_TITLE);
        int message = getArguments().getInt(EXTRA_MESSAGE);
        int[] buttons = getArguments().getIntArray(EXTRA_BUTTONS);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);

        switch (buttons.length) {
            case 3:
                alertDialogBuilder.setNeutralButton(
                        buttons[2], this);

            case 2:
                alertDialogBuilder.setNegativeButton(
                        buttons[1], this);

            case 1:
                alertDialogBuilder.setPositiveButton(
                        buttons[0], this);
        }
        return alertDialogBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Activity activity = getActivity();
        if (activity instanceof IGenericDialogFragment) {
            IGenericDialogFragment listener =
                    (IGenericDialogFragment) activity;
            listener.onClickDialog(mDialogId, which);
        }
    }

    public void openDialog(FragmentManager supportFragmentManager) {
        try {
            if (supportFragmentManager != null) {
                Fragment dialogFragment =
                        supportFragmentManager.findFragmentByTag(DIALOG_TAG);
                if (dialogFragment == null) {
                    show(supportFragmentManager, DIALOG_TAG);
                }
            }
        } catch (Exception e) {
            Log.e("Erro", e.getMessage());
        }
    }

}
