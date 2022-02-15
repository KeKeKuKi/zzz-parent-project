package per.zzz.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * 工具类，处理时间
 */
@SuppressWarnings("all")
public final class TimeUtils {
    /**
     * 常规格式
     */
    public static final String FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";

    /**
     * 常规格式没有年份
     */
    public static final String FORMAT_NORMAL_NO_YEAR = "MM-dd HH:mm:ss";

    /**
     * 常规格式只有月和日
     */
    public static final String FORMAT_NORMAL_ONLY_MONTH_AND_DATE = "MM-dd";

    /**
     * 时间格式器
     * 最小单位为秒
     * 最大单位为月
     */
    private static final SimpleDateFormat mFormatterMonthToSecond = new SimpleDateFormat("HH/dd HH:mm:ss", Locale.getDefault());

    /**
     * 格式化时间
     *
     * @param format 格式
     * @param date   时间
     * @return 格式化后的字符串
     */
    public static String format(String format, Object date) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取执行任务前的时间到目标时间的时间差
     * 公式：targetTime - firstTime
     *
     * @param firstTime  执行任务前的时间
     * @param targetTime 目标时间
     * @return 时间差
     */
    public static long getDifferBetweenTime(long firstTime, long targetTime) {
        long differTime = targetTime - firstTime;
        return differTime;
    }

    /**
     * 获取第一次执行任务的延迟时间，精确到小时
     *
     * @param hour 选择的小时
     * @return 第一次执行任务的延迟时间
     */
    public static long getFirstSendTaskDelayedTime(int hour) {
        long currentTime = System.currentTimeMillis();
        long firstSendTaskTime = getDifferBetweenTime(currentTime, getTodayTargetTime(hour));
        return firstSendTaskTime;
    }

    /**
     * 获取第一次执行任务的延迟时间，精确到分
     *
     * @param hour   选择的小时
     * @param minute 选择的分钟
     * @return 第一次执行任务的延迟时间
     */
    public static long getFirstSendTaskDelayedTime(int hour, int minute) {
        long currentTime = System.currentTimeMillis();
        long firstSendTaskTime = getDifferBetweenTime(currentTime, getTodayTargetTime(hour, minute));

        return firstSendTaskTime;
    }

    /**
     * 获取第一次执行任务的延迟时间，精确到秒
     *
     * @param hour   选择的小时
     * @param minute 选择的分钟
     * @param second 选择的秒数
     * @return 第一次执行任务的延迟时间
     */
    public static long getFirstSendTaskDelayedTime(int hour, int minute, int second) {
        long currentTime = System.currentTimeMillis();
        long firstSendTaskTime = getDifferBetweenTime(currentTime, getTodayTargetTime(hour, minute, second));

        return firstSendTaskTime;
    }

    /**
     * 初始化Calendat
     *
     * @return
     */
    public static Calendar getCalendar(int hour) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * 获取某一小时时刻的时间戳，以当天为基准
     *
     * @param hour 指定的小时，若小于0，日期会-1，若大于24会+1
     * @return 当天某一小时时刻的时间戳
     */
    public static long getTodayTargetTime(int hour) {
        Calendar cal = getCalendar(hour);
        long time = cal.getTimeInMillis();
        return time;
    }

    /**
     * 获取某一小时某一分钟时刻的时间戳，以当天为基准
     *
     * @param hour   指定的小时，若小于0，日期会-1，若大于24会+1
     * @param minute 指定的分钟，若小于0，小时会-1，若大于60会+1
     * @return 当天某一小时时刻的时间戳某一分钟时刻的时间戳
     */
    public static long getTodayTargetTime(int hour, int minute) {
        Calendar cal = getCalendar(hour);
        cal.set(Calendar.MINUTE, minute);
        long time = cal.getTimeInMillis();
        return time;
    }

    /**
     * 获取某一小时某一分钟某一秒时刻的时间戳，以当天为基准
     *
     * @param hour   指定的小时，若小于0，日期会-1，若大于24会+1
     * @param minute 指定的分钟，若小于0，小时会-1，若大于60会+1
     * @param second 指定的秒数，若小于0，分钟会-1，若大于60会+1
     * @return 当天某一小时时刻的时间戳某一分钟时刻的时间戳
     */
    public static long getTodayTargetTime(int hour, int minute, int second) {
        Calendar cal = getCalendar(hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        long time = cal.getTimeInMillis();
        return time;
    }

    /**
     * 获取当前时间
     *
     * @return 已格式化的时间
     */
    public static String getCurrentTime() {
        return mFormatterMonthToSecond.format(System.currentTimeMillis());
    }

    /**
     * 时间毫秒数转天+时+分+秒格式
     *
     * @return 已格式化的时间
     */
    public static String toTimeStr(long time) {
        long days = time / 86400000;
        long hours = (time % 86400000) / 3600000;
        long min = (time % 3600000) / 60000;
        long sec = (time % 60000) / 1000;
        return days + "天" + hours + "小时" + min + "分钟" + sec + "秒";
    }

}
