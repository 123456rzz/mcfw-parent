package org.mengchong.mcfw.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author liurui
 * @description: 日期转换工具
 * @date 2023/4/21 10:02
 */
public class DateUtils {

    /**
     * LocalDate 转 Date
     *
     * @param localDate
     * @return
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转Date
     *
     * @param localDateTime
     * @return
     */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date转LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 两个日期之间的月份(含开始,结束)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getMonthBetween(java.sql.Date startTime, java.sql.Date endTime) {

        List<String> list = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

        // 设置开始月份
        Calendar min = Calendar.getInstance();
        min.setTime(startTime);

        // 设置日期,保证最后一个日期参数 大于 开始时间日历
        Calendar max = Calendar.getInstance();
        max.setTime(endTime);

        while (min.before(max)) {
            list.add(simpleDateFormat.format(min.getTime()));
            min.add(Calendar.MONTH, 1);
        }

        return list;
    }

    /**
     * 两个日期之间的季度(含开始,结束)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getQuarterBetween(java.sql.Date startTime, java.sql.Date endTime) {

        List<String> list = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String[] numArr = null;

        // 设置开始月份
        Calendar min = Calendar.getInstance();
        min.setTime(startTime);

        // 设置日期,保证最后一个日期参数 大于 开始时间日历
        Calendar max = Calendar.getInstance();
        max.setTime(endTime);

        while (min.before(max)) {
            numArr = simpleDateFormat.format(min.getTime()).split("-");
            String quarter = getQuarter(Integer.parseInt(numArr[1]));
            //String quarterStr = numArr[0] + "第" + quarter + "季";
            String quarterStr = numArr[0] + quarter;
            if (list.indexOf(quarterStr) < 0) {
                list.add(quarterStr);
            }
            min.add(Calendar.MONTH, 1);
        }
        return list;
    }

    /**
     * 两个日期之间的年份(含开始,结束)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getYearBetween(java.sql.Date startTime, java.sql.Date endTime) {
        List<String> list = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");

        // 设置开始月份
        Calendar min = Calendar.getInstance();
        min.setTime(startTime);

        // 设置日期,保证最后一个日期参数 大于 开始时间日历
        Calendar max = Calendar.getInstance();
        max.setTime(endTime);

        while (min.before(max)) {
            list.add(simpleDateFormat.format(min.getTime()));
            min.add(Calendar.YEAR, 1);
        }

        return list;
    }

    /**
     * 根据月获得季度值
     *
     * @param month
     * @return
     */
    private static String getQuarter(int month) {
        String result;
        switch (month) {
            case 10:
            case 11:
            case 12:
                result = "-12";
                break;
            case 7:
            case 8:
            case 9:
                result = "-09";
                break;
            case 4:
            case 5:
            case 6:
                result = "-06";
                break;
            case 3:
            case 2:
            case 1:
                result = "-03";
                break;
            default:
                result = "-00";
                break;
        }
        return result;
    }
}
