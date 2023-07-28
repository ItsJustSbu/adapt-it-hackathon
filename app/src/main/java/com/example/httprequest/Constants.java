package com.example.httprequest;

import static com.example.httprequest.SettingsActivity.isValidEmail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constants {

    public static String childId = "";

    public static String entityUsername ="";
    public static String race = "";
    public static String religion = "";
    public static String typey = "";
    public static String field = "";
    public static String gender = "";
    public static String heightFt = "";
    public static String heightIn = "";
    public static String location = "";

    public static String tmpage ="";
    public static String tmpabout ="";
    public static String tmpphone ="";

    public static ArrayList<WallpaperItem> wallpaperItems = new ArrayList<>();

    public static ArrayList<newsitem> matchespics = new ArrayList<>();

    public static ArrayList<matchInfo> matchesItems = new ArrayList<>();

    public static int mat = -1;

    public static int pfp = R.drawable.user;

//    public static void success(Activity activity){
//
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//    }

    public static String entity_partner ="205";
    public static int temppfp = R.drawable.user;

    public static boolean validaInputs(String username, String password, Context context) {

        if (username.isEmpty()){
            Toaster.show(context,"Email cannot be empty");
            return false;
        }

        if (!isValidEmail(username)){
            Toaster.show(context,"Email must be valid");
            return false;
        }

//        if (noSpecialChars(username)){
//            Toaster.show(context,"Email cannot contain any special characters");
//            return false;
//        }

        if (password.isEmpty()){
            Toaster.show(context,"Password cannot be empty");
            return false;
        }

//        if (noSpecialChars(password)){
//            Toaster.show(context,"Password cannot contain any special characters");
//            return false;
//        }

        return true;
    }

    public static String encryptSHA256(String password) {
        try {
            String salt = "moop";
            String saltedPassword = password + salt;

            MessageDigest dig = MessageDigest.getInstance("SHA-256");
            byte[] hash = dig.digest(saltedPassword.getBytes());

            StringBuilder hexstr = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexstr.append('0');
                }
                hexstr.append(hex);
            }
            return hexstr.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static boolean noSpecialChars(String str){
//        Pattern p = Pattern.compile("[^A-Za-z0-9@. !+/*\n#~:;{}%\t&()=<>]");
//        Matcher m = p.matcher(str);
//        return m.find();
//    }



    public static boolean isPasswordValid(String password) {

        String p = "^(?=.*[A-Za-z])(?=.*\\d).+$";
        Pattern P = Pattern.compile(p);
        Matcher m = P.matcher(password);
        return m.matches();
    }


    public static String unescapeSpecialCharacters(String input) {
        return input.replaceAll("&#44;", ",").replaceAll("&#46;", ".").replaceAll("&#60;", "<").replaceAll("&#62;", ">").replaceAll("&#47;", "/").replaceAll("&#63;", "?").replaceAll("&#58;", ":").replaceAll("&#64;", "@").replaceAll("&#126;", "~").replaceAll("&#125;", "}").replaceAll("&#123;", "{").replaceAll("&#39;", "'").replaceAll("&#35;", "#").replaceAll("&#93;", "]").replaceAll("&#91;", "[").replaceAll("&#33;", "!").replaceAll("&#34;", "\"").replaceAll("&#163;", "£").replaceAll("&#36;", "\\$").replaceAll("&#37;", "%").replaceAll("&#94;", "^").replaceAll("&#38;", "&").replaceAll("&#42;", "*").replaceAll("&#40;", "(").replaceAll("&#41;", ")").replaceAll("&#45;", "-").replaceAll("&#43;", "+").replaceAll("&#92;", "\\\\").replaceAll("&#124;", "|").replaceAll("&#10;", "\n").replaceAll("&#9;", "\t");
    }



//    public static String escapeSpecialCharacters(String input) {
//        return input.replaceAll(",", "&#44;").replaceAll("\\.", "&#46;").replaceAll("<", "&#60;").replaceAll(">", "&#62;").replaceAll("/", "&#47;").replaceAll("\\?", "&#63;").replaceAll(":", "&#58;").replaceAll("@", "&#64;").replaceAll("~", "&#126;").replaceAll("}", "&#125;").replaceAll("\\{", "&#123;").replaceAll("'", "&#39;").replaceAll("#", "&#35;").replaceAll("]", "&#93;").replaceAll("\\[", "&#91;").replaceAll("!", "&#33;").replaceAll("\"", "&#34;").replaceAll("£", "&#163;").replaceAll("\\$", "&#36;").replaceAll("%", "&#37;").replaceAll("\\^", "&#94;").replaceAll("&", "&#38;").replaceAll("\\*", "&#42;").replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;").replaceAll("-", "&#45;").replaceAll("\\+", "&#43;").replaceAll("\\\\", "&#92;").replaceAll("\\|", "&#124;").replaceAll("\n", "&#10;").replaceAll("\t", "&#9;");
//    }

    public static String removeSpecialCharacters(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : input.toCharArray()) {
            switch (c) {
                case ',':
                    break;
                case '.':
                    break;
                case '<':
                    break;
                case '>':
                    break;
                case '/':
                    break;
                case '?':
                    break;
                case ':':
                    break;
                case '@':
                    break;
                case '~':
                    break;
                case '}':
                    break;
                case '{':
                    break;
                case '\'':
                    break;
                case '#':
                    break;
                case ']':
                    break;
                case '[':
                    break;
                case '!':
                    break;
                case '\"':
                    break;
                case '£':
                    break;
                case '$':
                    break;
                case '%':
                    break;
                case '^':
                    break;
                case '&':
                    break;
                case '*':
                    break;
                case '(':
                    break;
                case ')':
                    break;
                case '-':
                    break;
                case '+':
                    break;
                case '\\':
                    break;
                case '|':
                    break;
                default:
                    stringBuilder.append(c);
                    break;
            }
        }

        return stringBuilder.toString();
    }


    public static String escapeSpecialCharacters(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : input.toCharArray()) {
            switch (c) {
                case ',':
                    stringBuilder.append("&#44;");
                    break;
                case '.':
                    stringBuilder.append("&#46;");
                    break;
                case '<':
                    stringBuilder.append("&#60;");
                    break;
                case '>':
                    stringBuilder.append("&#62;");
                    break;
                case '/':
                    stringBuilder.append("&#47;");
                    break;
                case '?':
                    stringBuilder.append("&#63;");
                    break;
                case ':':
                    stringBuilder.append("&#58;");
                    break;
                case '@':
                    stringBuilder.append("&#64;");
                    break;
                case '~':
                    stringBuilder.append("&#126;");
                    break;
                case '}':
                    stringBuilder.append("&#125;");
                    break;
                case '{':
                    stringBuilder.append("&#123;");
                    break;
                case '\'':
                    stringBuilder.append("&#39;");
                    break;
                case '#':
                    stringBuilder.append("&#35;");
                    break;
                case ']':
                    stringBuilder.append("&#93;");
                    break;
                case '[':
                    stringBuilder.append("&#91;");
                    break;
                case '!':
                    stringBuilder.append("&#33;");
                    break;
                case '\"':
                    stringBuilder.append("&#34;");
                    break;
                case '£':
                    stringBuilder.append("&#163;");
                    break;
                case '$':
                    stringBuilder.append("&#36;");
                    break;
                case '%':
                    stringBuilder.append("&#37;");
                    break;
                case '^':
                    stringBuilder.append("&#94;");
                    break;
                case '&':
                    stringBuilder.append("&#38;");
                    break;
                case '*':
                    stringBuilder.append("&#42;");
                    break;
                case '(':
                    stringBuilder.append("&#40;");
                    break;
                case ')':
                    stringBuilder.append("&#41;");
                    break;
                case '-':
                    stringBuilder.append("&#45;");
                    break;
                case '+':
                    stringBuilder.append("&#43;");
                    break;
                case '\\':
                    stringBuilder.append("&#92;");
                    break;
                case '|':
                    stringBuilder.append("&#124;");
                    break;
                case '\n':
                    stringBuilder.append("&#10;");
                    break;
                case '\t':
                    stringBuilder.append("&#9;");
                    break;
                default:
                    stringBuilder.append(c);
                    break;
            }
        }

        return stringBuilder.toString();
    }


    public static boolean validaInputs2(String username,String email,String password, Context context) {
        if (username.isEmpty()){
            Toaster.show(context,"Username cannot be empty");
            return false;
        }

//        if (noSpecialChars(username)){
//            Toaster.show(context,"Username cannot contain any special characters");
//            return false;
//        }

        if (email.isEmpty()){
            Toaster.show(context,"Email cannot be empty");
            return false;
        }

        if (!isValidEmail(email)){
            Toaster.show(context,"Email must be valid");
            return false;
        }

//        if (noSpecialChars(email)){
//            Toaster.show(context,"Email cannot contain any special characters");
//            return false;
//        }

        if (password.isEmpty()){
            Toaster.show(context,"Password cannot be empty");
            return false;
        }

//        if (noSpecialChars(password)){
//            Toaster.show(context,"Password cannot contain any special characters");
//            return false;
//        }
        if (password.length() < 8){
            Toaster.show(context,"Password requires a minimum of 8 characters");
            return false;
        }
        if (isPasswordValid(password)== false){
            Toaster.show(context,"Password must contain both characters and numbers");
            return false;
        }

        return true;
    }

    public static String age ="";
    public static String phone ="";
    public static String aboutme ="";

    public static int pfpindexx =-1;
    public static boolean isFirstTimeOnSEttings =true;

    public static String username = "huzii";

    public static String child_id = "70";
    public static String person_id = "11"; //was 10
    public static boolean isEntityPartnerNULL = false;
    public static String emaill = "huziibee@gmail.com";
    public static String password = "password";
    public static String typee = "Parent";

    public static boolean firstTimeOnProfile = true;

    public static boolean firstTimeVerified =true;

    public static boolean isEverythingCompleted = false;

//    public static int person_id = null;

    public static String capFirst(String string){
        if (string != null && !string.isEmpty()) {

            char firstChar = Character.toUpperCase(string.charAt(0));

            return firstChar + string.substring(1);
        }
        return string;
    }


    public static void animateWobble(final View view, final Animator.AnimatorListener animatorListener) {

        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat(View.ROTATION, -5f, 5f);


        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, rotationHolder);
        animator.setDuration(200);
        animator.setInterpolator(new CycleInterpolator(1));
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.setRepeatCount(0);


        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                if (animatorListener != null) {
                    animatorListener.onAnimationEnd(animation);
                }

                view.setRotation(0f);
            }
        });


        animator.start();
    }





    public static Uri profilePicture ;

}
