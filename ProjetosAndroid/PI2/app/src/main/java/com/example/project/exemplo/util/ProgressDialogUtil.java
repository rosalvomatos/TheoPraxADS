package com.example.project.exemplo.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

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

    public static void showProgressDialogUtil(boolean exibir, Context contextLocal) {
        try {
            progressDialog.setCancelable(false);
            if (exibir)
                progressDialog.show();
            else
                progressDialog.dismiss();
        } catch (Exception e) {
            Toast.makeText(context, "Erro no Sistema", Toast.LENGTH_LONG).show();
        }
    }

    public static void setDialogMessage(int idMessage) {
        if (progressDialog != null) {
            progressDialog.setMessage(context.getResources().getText(idMessage));
        }
    }
}
