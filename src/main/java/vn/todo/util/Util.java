package vn.todo.util;

import org.springframework.validation.BindingResult;
import java.util.StringJoiner;

public class Util {
    public static String getHttpErrorMessage(BindingResult result) {
        StringJoiner joiner = new StringJoiner("<br>");
        result.getFieldErrors().forEach(
                fe -> {
                    String msg = fe.getDefaultMessage();
                    if (!msg.startsWith(fe.getField())) {
                        msg = fe.getField() + ' ' + msg;
                    }
                    joiner.add(msg);
                });
        return joiner.toString();
    }
}