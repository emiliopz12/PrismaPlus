package com.prismaplus.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class Utils {

    Context context;
   public  MaterialDialog materialDialog;

    public Utils(){
        this.context = context;
    }

    public void showProgess(Context ctx,String title){
        materialDialog = new MaterialDialog.Builder(ctx)
                .content(title)
                .autoDismiss(false)
                .progress(true, 0)
                .show();
    }

    public void hideProgress(){
        if(materialDialog != null){
            materialDialog.dismiss();
        }
    }


    public void data(Context ctx,String title){
        new MaterialDialog.Builder(ctx)
                .title("Eliminar Avería")
                .content("Desea eliminar la averia")
                .positiveText("Aceptar")
                .negativeText("Cancelar")
                .onPositive((dialog, which) -> {

                })
                .show();
    }

    public  String createkey(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }


    @TargetApi(23)
    public int verifyPermissions(Context ctx, String permission){
        return ContextCompat.checkSelfPermission(ctx,permission);
    }

    public void configChooserCamera(Context context, Activity activity, int CAMERA_PIC_REQUEST){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
    }

    public String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void setError(EditText editText){
        Drawable drawable = editText.getBackground();
        drawable.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        editText.setBackground(drawable);
    }
}