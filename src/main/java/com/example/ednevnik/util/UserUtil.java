package com.example.ednevnik.util;

import com.example.ednevnik.model.teacher.Teacher;
import org.springframework.security.core.context.SecurityContextHolder;

public final class UserUtil {

    public static Long getTeacherNumber() {
        return ((Teacher) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTeacherNumber();
    }
}
