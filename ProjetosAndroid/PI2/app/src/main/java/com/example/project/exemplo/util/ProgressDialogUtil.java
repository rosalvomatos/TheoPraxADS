package com.example.project.exemplo.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.project.exemplo.R;

public class ProgressDialogUtil {

    static ProgressDialog progressDialog;
    static Context context;

    static void instantiateProgressDialog() {
        progressDialog = new ProgressDialog(context);
    }

    public static void instantiateContext(Context contextLocal) {
        context = contextLocal;
        instantiateProgressDialog();
    }

    public static void showProgressDialogUtil(boolean show, Context contextLocal) {
        try {
            progressDialog.setCancelable(false);
            if (show)
                progressDialog.show();
            else
                progressDialog.dismiss();
        } catch (Exception e) {
            Toast.makeText(context, context.getResources().getString(R.string.system_error), Toast.LENGTH_LONG).show();
        }
    }

    public static void setDialogMessage(int idMessage) {
        if (progressDialog != null) {
            progressDialog.setMessage(context.getResources().getText(idMessage));
        }
    }
}
