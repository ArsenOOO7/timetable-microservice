package com.arsen.timetable.exception;

public class TeacherBusyException extends RuntimeException{

    public TeacherBusyException() {
    }

    public TeacherBusyException(String message) {
        super(message);
    }
}
