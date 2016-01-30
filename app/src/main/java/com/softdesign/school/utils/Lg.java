package com.softdesign.school.utils;

import android.util.Log;

/**
 * Зарефакторить код логера в соответствии с данными на лекции рекомендациями, исспользовать подход DRY Don’t repeat yourself (не повторяй себя) - 
 * т.е. избегаем повторения уже ранее написанного кода + Javadoc, 
 * логер должен исспользовать различные уровни вывода логов (Verbose, debug, info, error, warn, assert ).
 */
public class Lg {

    /**
     * Префикс сообщений в логе.
     */
    private static final String PREFIX = "SCHOOL ";

    /**
     * Максимальная длина логгируемого сообщения.
     * При превышении значения имитируется перенос строки.
     */
    public static final int LOGCAT_BUFFER_SIZE = 3000;

    /**
     * Проверка логгирования.
     *
     * @return Boolean
     */
    private static boolean shouldLog() {
        // todo: Раскомментировать когда будет доступен класс с конфигом
        // return BuildConfig.IS_LOGCAT_LOGGER_ENABLED;
        return true;
    }

    /**
     * Отправляет в логгер {@link android.util.Log#VERBOSE} сообщение.
     *
     * @param tag Используется для идентификации источника сообщения.
     * @param msg Сообщение которое нужно залогировать.
     */
    public static void v(String tag, String msg) {
        helper(Log.VERBOSE, tag, msg);
    }

    /**
     * Отправляет в логгер {@link android.util.Log#DEBUG} сообщение.
     *
     * @param tag Используется для идентификации источника сообщения.
     * @param msg Сообщение которое нужно залогировать.
     */
    public static void d(String tag, String msg) {
        helper(Log.DEBUG, tag, msg);
    }

    /**
     * Отправляет в логгер {@link android.util.Log#INFO} сообщение.
     *
     * @param tag Используется для идентификации источника сообщения.
     * @param msg Сообщение которое нужно залогировать.
     */
    public static void i(String tag, String msg) {
        helper(Log.INFO, tag, msg);
    }

    /**
     * Отправляет в логгер {@link android.util.Log#WARN} сообщение.
     *
     * @param tag Используется для идентификации источника сообщения.
     * @param msg Сообщение которое нужно залогировать.
     */
    public static void w(String tag, String msg) {
        helper(Log.WARN, tag, msg);
    }

    /**
     * Отправляет в лог {@link android.util.Log#ERROR} сообщение.
     *
     * @param tag Используется для идентификации источника сообщения.
     * @param msg Сообщение которое нужно залогировать.
     */
    public static void e(String tag, String msg) {
        helper(Log.ERROR, tag, msg);
    }

    /**
     * Отправляет в лог {@link android.util.Log#ASSERT} сообщение.
     *
     * @param tag Используется для идентификации источника сообщения.
     * @param msg Сообщение которое нужно залогировать.
     */
    public static void a(String tag, String msg) {
        helper(Log.ASSERT, tag, msg);
    }

    /**
     * Обертка для логгера.
     * Выполняет задачи:
     * – Проверка на необходимость логгирования (подробнее: {@link #shouldLog()}).
     * – Форматирование вывода (подробнее: {@link #LOGCAT_BUFFER_SIZE}).
     *
     * @param priority Константа определяющая приоритет логгируемого сообщения.
     *                 Используются константы из пакета {@link android.util.Log},
     *                 например {@link android.util.Log#VERBOSE}
     * @param tag      Используется для идентификации источника сообщения.
     * @param msg      Сообщение которое нужно залогировать.
     */
    private static void helper(Integer priority, String tag, String msg) {
        if (shouldLog()) {
            String str = msg;

            while (str.length() > LOGCAT_BUFFER_SIZE) {
                String substr = str.substring(0, LOGCAT_BUFFER_SIZE);
                str = substr.substring(LOGCAT_BUFFER_SIZE);
                Log.println(priority, PREFIX + tag, substr);
            }

            Log.println(priority, PREFIX + tag, str);
        }
    }
}