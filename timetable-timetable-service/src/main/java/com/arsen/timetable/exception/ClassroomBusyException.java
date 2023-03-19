package com.arsen.timetable.exception;

public class ClassroomBusyException extends RuntimeException{

    public ClassroomBusyException() {
    }

    public ClassroomBusyException(String message) {
        super(message);
    }
}
