package com.tistory.devilnangel.systeminfo.util;

import com.tistory.devilnangel.systeminfo.common.Unit;

/**
 * @author k, Created on 16. 1. 24.
 */
public class UnitTranasform {

    public static long transformLong(long num, Unit unit) {
        switch (unit) {
            case KB: return num /1024;
            case MB: return num /1024/1024;
            case GB: return num /1024/1024/1024;
            default: return num;
        }
    }

    public static int transformInt(int num, Unit unit) {
        switch (unit) {
            case KB: return num /1024;
            case MB: return num /1024/1024;
            case GB: return num /1024/1024/1024;
            default: return num;
        }
    }
}
