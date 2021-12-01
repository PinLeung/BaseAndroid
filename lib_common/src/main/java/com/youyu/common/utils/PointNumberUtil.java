package com.youyu.common.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pin_leung
 * @date 2021/9/15
 * @description  限制输入框输入带小数点数量
 */
public class PointNumberUtil {

    public static void EditTwo(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {

            int selectionStart;
            int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                selectionStart =  editText.getSelectionStart();
                selectionEnd =  editText.getSelectionEnd();
                if (!PointNumberUtil.isOnlyPointNumber( editText.getText().toString()) && s.length() > 0) {
                    //删除多余输入的字（不会显示出来）
                    s.delete(selectionStart - 1, selectionEnd);
                    editText.setText(s);
                    editText.setSelection(s.length());
                }
            }
        });
    }


    public static void EditOne(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {

            int selectionStart;
            int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                selectionStart =  editText.getSelectionStart();
                selectionEnd =  editText.getSelectionEnd();
                if (!PointNumberUtil.isOnlyOnePointNumber( editText.getText().toString()) && s.length() > 0) {
                    //删除多余输入的字（不会显示出来）
                    s.delete(selectionStart - 1, selectionEnd);
                    editText.setText(s);
                    editText.setSelection(s.length());
                }
            }
        });
    }
    public static boolean isOnlyPointNumber(String number) {
        Pattern pattern = Pattern.compile("^\\d+\\.?\\d{0,2}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    public static boolean isOnlyOnePointNumber(String number) {
        Pattern pattern = Pattern.compile("^\\d+\\.?\\d{0,1}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
