package com.example.demo.wrapper;

import com.example.demo.entities.Tidslinje;

import java.util.Objects;

public class tidslinjeCommandWrapper {

    public tidslinjeCommandWrapper(String command, Tidslinje tidslinje) {
        this.command = command;
        this.tidslinje = tidslinje;
    }

    private String  command;

    @Override
    public String toString() {
        return "tidslinjeCommandWrapper{" +
                "command='" + command + '\'' +
                ", tidslinje=" + tidslinje +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof tidslinjeCommandWrapper)) return false;
        tidslinjeCommandWrapper that = (tidslinjeCommandWrapper) o;
        return Objects.equals(command, that.command) && Objects.equals(tidslinje, that.tidslinje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, tidslinje);
    }

    private Tidslinje tidslinje;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Tidslinje getTidslinje() {
        return tidslinje;
    }

    public void setTidslinje(Tidslinje tidslinje) {
        this.tidslinje = tidslinje;
    }
}
