package com.amkiing.paytonkawa.exception;
import java.util.Map;
public record ErrorResponses(
        Map<String, String> errors
) {

}
