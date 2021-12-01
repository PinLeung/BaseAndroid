package com.youyu.common.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pin_leung
 * @date 2021/9/15
 * @description 手机号码
 */
public class EditUtil {

    public static void setEdiText(EditText view){
        final boolean[] isDel = {false};
        view.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                EditUtil.onTextChanged344(view,s.toString(), isDel[0]);
                isDel[0] =false;
            }
        });
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL){
                    int se=view.getSelectionStart();
                    if (se==4){
                        view.setSelection(3);
                    }
                    if (se==9){
                        view.setSelection(8);
                    }
                    isDel[0] =true;
                }
                return false;
            }
        });
    }

    public static void onTextChanged344(EditText view, String text,boolean isDelect) {
        if (view== null || text == null || text.length() == 0) return;
        //分隔符
        char space = ' ';
        int indexSpace1 = 3;
        int indexSpace2 = 8;
        //光标问题
        int index = view.getSelectionStart();
        StringBuilder sb = new StringBuilder();
        //1.取出所有字符，去掉' '和非法字符
        for (int i = 0; i < text.length(); i++) {
            //如果数字数大于11位，去掉后面的数字
            if(sb.length() >= 11){
                break;
            }
            //是否合法字符(0~9)
            Pattern pattern = Pattern.compile("^[0-9]*$");
            Matcher matcher = pattern.matcher(String.valueOf(text.charAt(i)));
            if (text.charAt(i) != space && matcher.matches()) {
                sb.append(text.charAt(i));
            }
        }

        //2.根据长度追加' '
        if(sb.length() > indexSpace1){
            sb.insert(indexSpace1, space);
            if (index==4&&!isDelect){
                index++;
            }
        }
        if(sb.length() > indexSpace2){
            sb.insert(indexSpace2, space);
            if (index==9){
                index++;
            }
        }
        //3.设置文本和光标位置
        if(!sb.toString().equals(text)){
            view.setText(sb.toString());
            if (index>sb.length()){
                index=sb.length();
            }
            view.setSelection(index);
        }
    }

    /**
     * 获得已输入的电话号，不包括空格
     * @param editText 输入控件
     * @return 电话号
     */
    public static String getPhoneNumber(EditText editText){
        if (editText== null || editText.getText() == null) return "";
        String text = editText.getText().toString();
        char space = ' ';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != space) {
                sb.append(text.charAt(i));
            }
        }
        return sb.toString();
    }
}
