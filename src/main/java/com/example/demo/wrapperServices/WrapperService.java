package com.example.demo.wrapperServices;

import com.example.demo.entities.Tidslinje;
import com.example.demo.wrapper.tidslinjeCommandWrapper;

public class WrapperService {


    public static tidslinjeCommandWrapper assembletidslinjeCommandWrapper(String command, Tidslinje tidslinje){
        tidslinjeCommandWrapper wrapper = new tidslinjeCommandWrapper(command,tidslinje);
        return wrapper;
    }
    public static String decideCommand(Tidslinje tidslinje, Long timestamp){
        if (tidslinje.getIsdeleted() != null && tidslinje.getIsdeleted())
            return "REMOVE";
        else if(tidslinje.getTimestampCreated() > timestamp)
            return "ADD";
        else if(tidslinje.getTimestampChanged() > timestamp)
            return  "CHANGE";
        else
            return  "NOTHING";
    }

    public   static tidslinjeCommandWrapper assembletidslinjeCommandWrapper(Tidslinje tidslinje, Long timestamp){
        //return assembletidslinjeCommandWrapper(decideCommand(tidslinje,timestamp),tidslinje);
        return assembletidslinjeCommandWrapper(decideCommand(tidslinje,timestamp),tidslinje);
    }
}
