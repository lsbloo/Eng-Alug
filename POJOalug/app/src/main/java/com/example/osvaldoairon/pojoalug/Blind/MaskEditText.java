package com.example.osvaldoairon.pojoalug.Blind;

/**
 * Created by osvaldoairon on 21/02/18.
 */

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class MaskEditText {

        public static final String FORMAT_CPF = "###.###.###-##";
        public static final String FORMAT_FONE = "(##)#####-####"; //55 9 9 2 3 4 5 6 7 8
        public static final String FORMAT_CEP = "#####-###";
        public static final String FORMAT_DATE = "##/##/####";
        public static final String FORMAT_HOUR = "##:##";
        public static final String FORMAT_LOGIN = "########";
        public static final String FORMAT_PASS = "##########";

        public static final String FORMATO_DOUBLE = "##.##";

        /**
         * Método que deve ser chamado para realizar a formatação
         *
         * @param ediTxt
         * @param mask
         * @return
         */
        public static TextWatcher mask(final EditText ediTxt, final String mask) {
            return new TextWatcher() {
                boolean isUpdating;
                String old = "";

                @Override
                public void afterTextChanged(final Editable s) {}

                @Override
                public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

                @Override
                public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                    final String str = MaskEditText.unmask(s.toString());
                    String mascara = "";
                    if (isUpdating) {
                        old = str;
                        isUpdating = false;
                        return;
                    }
                    int i = 0;
                    for (final char m : mask.toCharArray()) {
                        if (m != '#' && str.length() > old.length()) {
                            mascara += m;
                            continue;
                        }
                        try {
                            mascara += str.charAt(i);
                        } catch (final Exception e) {
                            break;
                        }
                        i++;
                    }
                    isUpdating = true;
                    ediTxt.setText(mascara);
                    ediTxt.setSelection(mascara.length());
                }
            };
        }

        public static String unmask(final String s) {
            return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[)]", "");
        }
    }
