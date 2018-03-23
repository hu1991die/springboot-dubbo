package com.feizi.dubbo.utils;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by feizi on 2018/3/23.
 */
public class CommonUtils {
    public static final String UTF8 = "UTF-8";
    public static String DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public CommonUtils() {
    }

    public static final boolean isEmpty(Object obj) {
        if(!(obj instanceof String)) {
            if(obj instanceof Object[]) {
                Object[] map2 = (Object[])((Object[])obj);
                return isEmpty(map2);
            } else if(obj instanceof Collection) {
                Collection map1 = (Collection)obj;
                return isEmpty(map1);
            } else if(obj instanceof Map) {
                Map map = (Map)obj;
                return isEmpty(map);
            } else {
                return obj == null;
            }
        } else {
            return obj == null || isEmptyContainNull(obj.toString());
        }
    }

    public static final boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static final boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static final boolean isEmptyContainNull(String input) {
        return input == null || input.trim().isEmpty() || input.trim().equalsIgnoreCase("null");
    }

    public static final boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static final boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static final boolean isNotEmpty(Collection<?> c) {
        return !isEmpty(c);
    }

    public static final boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static final boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static final String trim(String input) {
        return input == null?null:input.trim();
    }

    public static final String trim(String input, String emptyDefault) {
        return isEmpty(input)?emptyDefault:input.trim();
    }

    public static final String trimToEmpty(String input) {
        return isEmptyContainNull(input)?"":input.trim();
    }

    public static final String trimToEmpty(Object input) {
        return trimToEmpty(Objects.toString(input));
    }

    public static final boolean isNoneEmpty(List<Object> objs) {
        return !isAnyEmpty(objs);
    }

    public static final boolean isAnyEmpty(List<Object> objs) {
        return isEmpty((Collection)objs)?true:isAnyEmpty(objs.toArray());
    }

    public static final boolean isNoneEmpty(Object... objs) {
        if(objs != null && objs.length != 0) {
            Object[] arr$ = objs;
            int len$ = objs.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object obj = arr$[i$];
                if(isEmpty(obj)) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static final boolean isAnyEmpty(Object... objs) {
        if(objs != null && objs.length != 0) {
            Object[] arr$ = objs;
            int len$ = objs.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object obj = arr$[i$];
                if(isEmpty(obj)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    public static final boolean isAllEmpty(Object... objs) {
        if(objs != null && objs.length != 0) {
            Object[] arr$ = objs;
            int len$ = objs.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object obj = arr$[i$];
                if(!isEmpty(obj)) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static final boolean endsWithAnyIgnoreCase(CharSequence string, CharSequence... searchStrings) {
        if(string != null && string.length() != 0 && searchStrings != null && searchStrings.length != 0) {
            CharSequence[] arr$ = searchStrings;
            int len$ = searchStrings.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                CharSequence searchString = arr$[i$];
                if(endsWithIgnoreCase(string, searchString)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    private static final boolean endsWithIgnoreCase(CharSequence str, CharSequence suffix) {
        return endsWith(str, suffix, true);
    }

    private static final boolean endsWith(CharSequence str, CharSequence suffix, boolean ignoreCase) {
        if(str != null && suffix != null) {
            if(suffix.length() > str.length()) {
                return false;
            } else {
                int strOffset = str.length() - suffix.length();
                return regionMatches(str, ignoreCase, strOffset, suffix, 0, suffix.length());
            }
        } else {
            return str == null && suffix == null;
        }
    }

    private static final boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {
        if(cs instanceof String && substring instanceof String) {
            return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
        } else {
            int index1 = thisStart;
            int index2 = start;
            int tmpLen = length;

            while(tmpLen-- > 0) {
                char c1 = cs.charAt(index1++);
                char c2 = substring.charAt(index2++);
                if(c1 != c2) {
                    if(!ignoreCase) {
                        return false;
                    }

                    if(Character.toUpperCase(c1) != Character.toUpperCase(c2) && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    public static final boolean equals(String input1, String input2) {
        if(input1 == input2) {
            return true;
        } else if(input1 != null && input2 != null) {
            input1 = input1.trim();
            input2 = input2.trim();
            return input1 instanceof String && input2 instanceof String?input1.equals(input2):regionMatches(input1, false, 0, input2, 0, Math.max(input1.length(), input2.length()));
        } else {
            return false;
        }
    }

    public static final boolean notEquals(String input1, String input2) {
        return !equals(input1, input2);
    }

    public static final boolean equalsIgnoreCase(String input1, String input2) {
        if(input1 != null && input2 != null) {
            if(input1 == input2) {
                return true;
            } else {
                input1 = input1.trim();
                input2 = input2.trim();
                return input1.length() != input2.length()?false:regionMatches(input1, true, 0, input2, 0, input1.length());
            }
        } else {
            return input1 == input2;
        }
    }

    public static final boolean notEqualsIgnoreCase(String input1, String input2) {
        return !equalsIgnoreCase(input1, input2);
    }

    public static final boolean isTrue(String input) {
        Boolean bool = Boolean.valueOf(input == null?null:input.toLowerCase());
        return Boolean.TRUE.equals(bool);
    }

    public static final boolean isNotTrue(String input) {
        return !isTrue(input);
    }

    public static final boolean isFalse(String input) {
        if(isEmptyContainNull(input)) {
            return false;
        } else {
            Boolean bool = Boolean.valueOf(input == null?null:input.toLowerCase());
            return Boolean.FALSE.equals(bool);
        }
    }

    public static final boolean isNotFalse(String input) {
        return !isFalse(input);
    }

    public static final <T> List<T> emptyList() {
        return new ArrayList();
    }

    public static final <T> List<T> emptyList(List<T> list) {
        return list == null? (List<T>) emptyList() :list;
    }

    public static final <K, V> Map<K, V> emptyMap() {
        return new HashMap();
    }

    public static final <K, V> Map<K, V> emptyMap(Map<K, V> map) {
        return map == null? (Map<K, V>) emptyMap() :map;
    }

    public static final <K, V> Map<K, V> stableMap(int size) {
        return new HashMap(size, 1.0F);
    }

    public static final short parseShort(Object data) {
        if(data == null) {
            return (short)0;
        } else {
            try {
                return data instanceof Short?((Short)data).shortValue():Short.valueOf(trim(String.valueOf(data))).shortValue();
            } catch (Exception var2) {
                return (short)0;
            }
        }
    }

    public static final int parseInt(Object data) {
        return parseInt(data, 0);
    }

    public static final int parseInt(Object data, int def) {
        if(data == null) {
            return def;
        } else {
            try {
                return data instanceof Integer?((Integer)data).intValue():Integer.valueOf(trim(String.valueOf(data))).intValue();
            } catch (Exception var3) {
                return def;
            }
        }
    }

    public static final long parseLong(Object data) {
        return parseLong(data, 0L);
    }

    public static final long parseLong(Object data, long def) {
        if(data == null) {
            return def;
        } else {
            try {
                return data instanceof Long?((Long)data).longValue():Long.valueOf(trim(String.valueOf(data))).longValue();
            } catch (Exception var4) {
                return def;
            }
        }
    }

    public static final double parseDouble(Object data) {
        return parseDouble(data, 0.0D);
    }

    public static final double parseDouble(Object data, double def) {
        if(data != null) {
            try {
                double value = def;
                if(data != null) {
                    if(data instanceof BigDecimal) {
                        value = ((BigDecimal)data).doubleValue();
                    } else if(data instanceof Double) {
                        value = ((Double)data).doubleValue();
                    } else {
                        value = Double.valueOf(trim(String.valueOf(data))).doubleValue();
                    }
                }

                return value == 0.0D?0.0D:roundHalfUp(value, 2);
            } catch (Exception var5) {
                ;
            }
        }

        return def;
    }

    public static final Date parseDate(Object value) {
        if(value == null) {
            return null;
        } else if(value instanceof Calendar) {
            return ((Calendar)value).getTime();
        } else if(value instanceof Date) {
            return (Date)value;
        } else {
            long longValue = -1L;
            if(value instanceof Number) {
                longValue = ((Number)value).longValue();
                return new Date(longValue);
            } else {
                if(value instanceof String) {
                    String strVal = trimToEmpty(value);
                    if(strVal.length() == 0) {
                        return null;
                    }

                    int length = strVal.length();
                    if(strVal.indexOf(45) != -1) {
                        String dateFormat1;
                        if(strVal.length() == DEFFAULT_DATE_FORMAT.length()) {
                            dateFormat1 = DEFFAULT_DATE_FORMAT;
                        } else if(strVal.length() == 10) {
                            dateFormat1 = "yyyy-MM-dd";
                        } else {
                            dateFormat1 = "yyyy-MM-dd HH:mm:ss.SSS";
                        }

                        SimpleDateFormat e = new SimpleDateFormat(dateFormat1);

                        try {
                            return e.parse(strVal);
                        } catch (ParseException var8) {
                            throw illegalArgumentException("can not cast to Date, value : " + strVal);
                        }
                    }

                    if(length == "yyyyMMddHHmmss".length()) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

                        try {
                            return dateFormat.parse(strVal);
                        } catch (ParseException var9) {
                            throw illegalArgumentException("can not cast to Date, value : " + strVal);
                        }
                    }

                    longValue = Long.parseLong(strVal);
                }

                if(longValue < 0L) {
                    throw illegalArgumentException("can not cast to Date, value : " + value);
                } else {
                    return new Date(longValue);
                }
            }
        }
    }

    public static final BigDecimal roundHalfUp(BigDecimal value, int scale) {
        return value == null?null:value.setScale(scale, 6);
    }

    public static final double roundHalfUp(double value, int scale) {
        BigDecimal decimal = new BigDecimal(value);
        return roundHalfUp(decimal, scale).doubleValue();
    }

    public static final byte[] toBinary(String content) {
        try {
            return content.getBytes("UTF-8");
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException("unsupport charset: UTF-8");
        }
    }

    public static final String toString(byte[] data) {
        return toString(data, "UTF-8");
    }

    public static final String toString(byte[] data, String characterEncoding) {
        if(data != null && data.length != 0) {
            try {
                return new String(data, characterEncoding);
            } catch (UnsupportedEncodingException var3) {
                throw new RuntimeException("unsupport charset: " + characterEncoding);
            }
        } else {
            return null;
        }
    }

    public static final int size(List<?> list) {
        return list == null?0:list.size();
    }

    public static final int size(Collection<?> c) {
        return c == null?0:c.size();
    }

    public static final <K, V> void put(Map<K, V> map, K key, V value) {
        if(map != null && key != null && value != null) {
            map.put(key, value);
        }

    }

    public static final <T> T getValue(Map<String, T> dataMap, String key) {
        Object value = dataMap.get(key);
        return value == null?null: (T) value;
    }

    public static final <T> T get(T[] array, int index, T def) {
        int arrayLength = array == null?0:array.length;
        return get(array, arrayLength, index, def);
    }

    public static final <T> T get(T[] array, int arrayLength, int index, T def) {
        return index >= 0 && index < arrayLength?array[index]:def;
    }

    public static final IllegalStateException illegalStateException(Throwable t) {
        return new IllegalStateException(t);
    }

    public static final IllegalStateException illegalStateException(String message) {
        return new IllegalStateException(message);
    }

    public static final IllegalStateException illegalStateException(String message, Throwable t) {
        return new IllegalStateException(message, t);
    }

    public static final IllegalArgumentException illegalArgumentException(String message) {
        return new IllegalArgumentException(message);
    }

    public static final UnsupportedOperationException unsupportedMethodException() {
        return new UnsupportedOperationException("unsupport this method");
    }

    public static final Throwable foundRealThrowable(Throwable t) {
        Throwable cause = t.getCause();
        return cause == null?t:foundRealThrowable(cause);
    }

    public static final String formatThrowable(Throwable t) {
        if(t == null) {
            return "";
        } else {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        }
    }

    public static final String formatThrowableForHtml(Throwable t) {
        String ex = formatThrowable(t);
        return ex.replaceAll("\n\t", " ");
    }

    public static final <T> T newInstance(Class<T> klass) {
        try {
            return klass.newInstance();
        } catch (Exception var2) {
            throw new IllegalArgumentException("instance class[" + klass + "] with ex:", var2);
        }
    }

    public static final <T> T newInstance(String className) {
        try {
            return (T) newInstance(Class.forName(className));
        } catch (Exception var2) {
            throw new IllegalArgumentException("instance class[" + className + "] with ex:", var2);
        }
    }

    public static final Class<?> classForName(String className) {
        try {
            return Class.forName(className);
        } catch (Exception var2) {
            throw new IllegalArgumentException("classForName(" + className + ")  with ex:", var2);
        }
    }

    public static final String urlDecodeUTF8(String input) {
        if(input == null) {
            return null;
        } else {
            try {
                return URLDecoder.decode(input, "UTF-8");
            } catch (Exception var2) {
                throw illegalStateException((Throwable)var2);
            }
        }
    }

    public static final String urlEncodeUTF8(String input) {
        if(input == null) {
            return null;
        } else {
            try {
                return URLEncoder.encode(input, "UTF-8");
            } catch (Exception var2) {
                throw illegalStateException((Throwable)var2);
            }
        }
    }

    public static final InputStream getInputStreamFromClassPath(String filename) {
        return isEmpty(filename)?null:CommonUtils.class.getClassLoader().getResourceAsStream(filename);
    }

    public static final String castToJavaFilePath(String path) {
        String FILE_SEPERATOR = "/";
        if(path == null) {
            return null;
        } else {
            path = replace(path, "\\", FILE_SEPERATOR);
            path = replace(path, "\\\\", FILE_SEPERATOR);
            path = replace(path, "//", FILE_SEPERATOR);
            path = replace(path, "////", FILE_SEPERATOR);
            path = replace(path, "//////", FILE_SEPERATOR);
            path = replace(path, "////////", FILE_SEPERATOR);
            path = replace(path, "/", FILE_SEPERATOR);
            path = replace(path, "//", FILE_SEPERATOR);
            path = replace(path, "///", FILE_SEPERATOR);
            path = replace(path, "////", FILE_SEPERATOR);
            path = replace(path, "${FILE_SEPERATOR}", FILE_SEPERATOR);
            return path;
        }
    }

    public static final String replace(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    public static final String replace(String text, String searchString, String replacement, int max) {
        if(!isEmpty(text) && !isEmpty(searchString) && replacement != null && max != 0) {
            int start = 0;
            int end = text.indexOf(searchString, start);
            if(end == -1) {
                return text;
            } else {
                int replLength = searchString.length();
                int increase = replacement.length() - replLength;
                increase = increase < 0?0:increase;
                increase *= max < 0?16:(max > 64?64:max);

                StringBuilder buf;
                for(buf = new StringBuilder(text.length() + increase); end != -1; end = text.indexOf(searchString, start)) {
                    buf.append(text.substring(start, end)).append(replacement);
                    start = end + replLength;
                    --max;
                    if(max == 0) {
                        break;
                    }
                }

                buf.append(text.substring(start));
                return buf.toString();
            }
        } else {
            return text;
        }
    }

    public static final String deleteWhitespace(String input) {
        if(isEmpty(input)) {
            return input;
        } else {
            int sz = input.length();
            char[] chs = new char[sz];
            int count = 0;

            for(int i = 0; i < sz; ++i) {
                if(!Character.isWhitespace(input.charAt(i))) {
                    chs[count++] = input.charAt(i);
                }
            }

            if(count == sz) {
                return input;
            } else {
                return new String(chs, 0, count);
            }
        }
    }
}
