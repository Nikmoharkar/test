package com.fastacash.opensdk.utils;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

import com.fastacash.opensdk.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gaurav Gupta <gaurav@thegauravgupta.com>
 * @since 09/Dec/2014
 */

public class Utility {

    /**
     * Just pass in the InputStream and it will read the whole file and return it as a string
     * <p/>Eg: Utility.loadStringFromInputStream(getResources().openRawResource(R.raw.churches));
     *
     * @param is an InputStream which can be from a file or raw resource or asset
     * @return String
     */
    public static String loadStringFromInputStream(InputStream is) {
        String st;
        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            st = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return st;

    }

    public static int convertDpToPixel(float dp, Context context) {

        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) (dp * (metrics.densityDpi / 160f));
        return px;
    }

    public static boolean isValidEmailAddress(String email) {

        if (TextUtils.isEmpty(email)) {
            return false;
        }
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateInputs(EditText editText, int length) {
        if (editText == null) {
            return false;
        }

        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            return false;
        }

        if (editText.getText().toString().length() == length) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateInputs(EditText editText) {
        if (editText == null) {
            return false;
        }
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Send sms to the given number
     *
     * @param context
     * @param number
     * @param message
     * @return
     */
    public static boolean sendSms(Context context, String number, String message) {
        if (!Utility.checkSimAvailable(context)) {
            Toast.makeText(context, "Sim Card Not Available. Please insert a valid sim card", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(context, "SMS sent", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            Toast.makeText(context, "SMS push Failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Check if the sim is available to send text message
     *
     * @param context
     * @return
     */
    public static boolean checkSimAvailable(Context context) {
        boolean result = false;
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = manager.getSimState();
        switch (simState) {
            case TelephonyManager.SIM_STATE_READY:
                result = true;
                break;
        }
        return result;
    }


    /**
     * Get the time elapsed since the date object passed
     *
     * @param pastDate
     * @return String representation of Time Elapsed as 3 hour 4 min 5 sec
     */
    public static String getDateAgo(Date pastDate) {
        Date currentDate = new Date();
        long diffInMillisec = currentDate.getTime() - pastDate.getTime();
        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec);
        long seconds = diffInSec % 60;
        diffInSec /= 60;
        long minutes = diffInSec % 60;
        diffInSec /= 60;
        long hours = diffInSec % 24;
        diffInSec /= 24;
        long days = diffInSec;

        String st = "";
        if (days > 0) {
            st += days + " day ";
        }
        if (hours > 0) {
            st += hours + " hour ";
        }
        if (minutes > 0) {
            st += minutes + " min ";
        }
        if (seconds > 0) {
            st += seconds + " sec";
        }

        return st;
    }

    /**
     * Get the date in String format
     *
     * @param date
     * @return date in String format as 0:28 AM, 12 Jan 2015
     */
    public static String getDateTime(Date date) {
        String st;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        st = calendar.get(Calendar.HOUR) + ":" +
                calendar.get(Calendar.MINUTE) + " " +
                calendar.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.US) + ", " +

                (calendar.get(Calendar.DATE) + 1) + " " +
                calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US) + " " +
                calendar.get(Calendar.YEAR);


        return st;
    }


    /**
     * @param context
     * @return IMEI for GSM and MEID for CDMA
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }


    /**
     * @param context
     * @return
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getLine1Number();
    }

    /**
     * @param context
     * @return
     */
    public static String getPhoneOperator(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getNetworkOperatorName();
    }


    /**
     * @param context
     * @return
     */
    public static List<String> getImagePaths(Context context) {
        ContentResolver cr = context.getContentResolver();
        Cursor cursor;
        LinkedList<String> paths = new LinkedList<String>();
        String[] projection = {MediaStore.Images.Media.DATA};
        cursor = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                paths.add(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
            }
        }
        cursor.close();
        return paths;
    }

    /**
     * @param context
     * @param path
     * @return the thumbnail of the bitmap of given path, null if path is invalid
     */
    public static Bitmap getImageThumbnails(Context context, String path) {
        Bitmap bitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path), 90, 90);
        return bitmap;
    }

    /**
     * @param context
     * @param path
     * @return the full bitmap of given path, null if path is invalid
     */
    public static Bitmap getImageFull(Context context, String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return bitmap;
    }

    /**
     * @param context
     * @param path
     * @return the bitmap of given path scaled by half, null if path is invalid
     */
    public static Bitmap getImageHalf(Context context, String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if (bitmap == null) {
            return null;
        } else {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        }
        return bitmap;
    }

    /**
     * @param context
     * @param path
     * @return the bitmap of given path scaled if more than 1000, null if path is invalid
     */
    public static Bitmap getImageReasonable(Context context, String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if (bitmap == null) {
            return null;
        } else {
            int larger = (bitmap.getWidth() > bitmap.getHeight()) ? bitmap.getWidth() : bitmap.getHeight();

            if (larger > 1000) {
                int scale = larger / 1000 + 1;
                bitmap = ThumbnailUtils.extractThumbnail(bitmap, bitmap.getWidth() / scale, bitmap.getHeight() / scale);

            } else {
                bitmap = ThumbnailUtils.extractThumbnail(bitmap, bitmap.getWidth(), bitmap.getHeight());
            }
        }
        return bitmap;
    }

    public static Bitmap getLetterBitmap(String text, int backgroundColor, int textColor) {

        Bitmap bmp = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        Paint paint = new Paint();

        paint.setColor(backgroundColor);
        canvas.drawPaint(paint);

        if (text != null) {
            paint.setColor(textColor);
            paint.setTextSize(120);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(text, 100, 140, paint);
        }

        return bmp;
    }

    public static void setBackgroundColor(View view, int color) {
        Drawable background = view.getBackground();
        background.setColorFilter(new LightingColorFilter(0xFF000000, color));
        view.setBackground(background);
    }

    /**
     * Get the last known location from all providers return best reading that is as accurate as minAccuracy meters and was taken no longer than minAge milliseconds ago, if none, return null.
     * If any of the parameters is less than or equal to zero, return best possible reading
     *
     * @param context
     * @param minAccuracy in meters
     * @param maxAge      in milliseconds
     * @return Location
     */
    public static Location getLastKnownLocation(Context context, float minAccuracy, long maxAge) {

        Location bestResult = null;
        float bestAccuracy = Float.MAX_VALUE;
        long bestAge = Long.MIN_VALUE;
        LocationManager locationManager;

        List<String> matchingProviders = (locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE)).getAllProviders();

        for (String provider : matchingProviders) {

            Location location = locationManager.getLastKnownLocation(provider);

            if (location != null) {

                float accuracy = location.getAccuracy();
                long time = location.getTime();

                if (accuracy < bestAccuracy) {

                    bestResult = location;
                    bestAccuracy = accuracy;
                    bestAge = time;

                }
            }
        }

        if (minAccuracy > 0 && maxAge > 0) {
            // Return best reading or null
            if (bestAccuracy > minAccuracy || (System.currentTimeMillis() - bestAge) > maxAge) {
                return null;
            } else {
                return bestResult;
            }
        } else {
            return bestResult;
        }
    }

    /**
     * @param context
     * @return
     */
    public static List<Contact> getContacts(Context context) {
        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        LinkedList<Contact> contacts = new LinkedList<Contact>();
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                Contact contact = new Contact();
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                contact.name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    //Get phone numbers
                    Cursor phoneCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    LinkedList<String> phoneNumber = new LinkedList<String>();
                    while (phoneCur.moveToNext()) {
                        String phoneNo = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneNumber.add(phoneNo);
                    }
                    contact.phoneNumbers = phoneNumber.toArray(new String[phoneNumber.size()]);
                    phoneCur.close();
                    //Get email addresses
                    Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
                    LinkedList<String> emailAddress = new LinkedList<String>();
                    while (emailCur.moveToNext()) {
                        String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        emailAddress.add(email);
                    }
                    contact.emailAddresses = emailAddress.toArray(new String[emailAddress.size()]);
                    emailCur.close();
                    contacts.add(contact);
                }
            }
        }
        return contacts;
    }


    public static class Contact {
        public String name;
        public String[] phoneNumbers;
        public String[] emailAddresses;

        public Contact() {
        }

        public static List<JSONObject> toJsonObjectList(List<Contact> contacts) {
            List<JSONObject> jContacts = new ArrayList<JSONObject>(contacts.size());
            for (Contact contact : contacts) {
                try {
                    jContacts.add(new JSONObject(contact.toJson()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return jContacts;
        }

        public String toJson() {
            return (new Gson()).toJson(this);
        }

        public static List<Contact> fromJsonObjectList(List<JSONObject> jContacts) {
            List<Contact> contacts = new ArrayList<Contact>(jContacts.size());
            for (JSONObject jContact : jContacts) {
                contacts.add(Contact.fromJson(jContact.toString()));
            }
            return contacts;
        }

        public static Contact fromJson(String json) {
            return (new Gson()).fromJson(json, Contact.class);
        }

    }

    public static class Image {
        public String devicePath;
        public String url;
        public String type;

        public Image() {
        }

        public static List<JSONObject> toJsonObjectList(List<Image> images) {
            List<JSONObject> jImages = new ArrayList<JSONObject>(images.size());
            for (Image image : images) {
                try {
                    jImages.add(new JSONObject(image.toJson()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return jImages;
        }

        public String toJson() {
            return (new Gson()).toJson(this);
        }

        public static List<Image> fromJsonObjectList(List<JSONObject> jImages) {
            List<Image> images = new ArrayList<Image>(jImages.size());
            for (JSONObject jImage : jImages) {
                images.add(Image.fromJson(jImage.toString()));
            }
            return images;
        }

        public static Image fromJson(String json) {
            return (new Gson()).fromJson(json, Image.class);
        }
    }

    /**
     * This method is used to check that the application is installed or not in device
     *
     * @param context
     * @param intent
     * @return
     */
    public static boolean isAppInstalled(Context context, Intent intent) {
        try {
            List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY);
            return list.size() > 0;
        } catch (Exception e) {
            // in case if exception caught retuning false
            return false;
        }

    }

    /**
     * This method is used to copy the text t the clip board
     *
     * @param text
     * @param context
     */
    public static void copyToClipboard(String text, Context context) {
        ClipboardManager clipMan = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("FastaShort", text);
        clipMan.setPrimaryClip(clip);
        Toast.makeText(context, "Copied", Toast.LENGTH_LONG).show();
    }

    /**
     * This is used to check the given URL is valid or not.
     *
     * @param url
     * @return
     */
    public static String isValidUrl(String url, Context context) {
        String errorMessage = context.getResources().getString(R.string.invalidUrlTect);
        if (url == null) {
            return errorMessage;
        }
        if (url.isEmpty()) {
            return "Url should not be empty.";
        }
        url = url.toLowerCase();
        if (url.startsWith("www.")) {
            url = "http://" + url;
        }
        if (URLUtil.isValidUrl(url)) {
            return null;
        }
        return errorMessage;
    }

    public static boolean checkURL(CharSequence input) {
        if (TextUtils.isEmpty(input)) {
            return false;
        }
        Pattern URL_PATTERN = Patterns.WEB_URL;
        boolean isURL = URL_PATTERN.matcher(input).matches();
        if (!isURL) {
            String urlString = input + "";
            if (URLUtil.isNetworkUrl(urlString)) {
                try {
                    new URL(urlString);
                    isURL = true;
                } catch (Exception e) {
                }
            }
        }
        return isURL;
    }

    /**
     * Shows the Alet Dialog
     *
     * @param context
     * @param message
     * @param positiveButtonLabel
     */
    public static void showAlertDialog(Context context, String message, String positiveButtonLabel) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Alert");

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    /**
     * Checks that pin is valid or not
     *
     * @param pin
     * @return
     */
    public static String isValidPIN(String pin, Context context) {
        if (pin != null && pin.length() > 0) {
            return null;
        }
        return context.getResources().getString(R.string.pinMendatoryText);
    }

    /**
     * Checks that weather number of clicks are valid or not, max clicks are accepetd till one million
     *
     * @param clicks
     * @return
     */
    public static String isValidClicks(int clicks, Context context) {
        if (clicks <= 1000000) {
            return null;
        }
        return context.getResources().getString(R.string.maxClicksText);
    }

    /**
     * Checks that weather number of days are valid or not, max days are accepetable are 14.
     *
     * @param days
     * @return
     */
    public static String isValidExpiryDays(int days, Context context) {
        if (days <= 14 && days > 0) {
            return null;
        }
        return context.getResources().getString(R.string.maxExpirationDaysText);
    }

    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isInternetConnectionAvaliable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }
}

