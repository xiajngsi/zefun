package com.zefun.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 字符串工具类
 * 
 * @author sfit0254
 * @date 2014-4-9
 */
public class StringUtil {

    /**
     * 获取毫秒类型主键，附加七位纳秒值
     * @return String
     */
    public static String getKey() {
        Long curMillis = System.currentTimeMillis();
        Long curNanos = Math.abs(System.nanoTime());
        String nanos = curNanos.toString();
        return curMillis + nanos.substring(nanos.length() - 6);
    }

    /**
     * 获取指定num位随机数字
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:22:45
    * @param num 数字
    * @return int
     */
    public static int getRandomNum(int num) {
        int ranNum = 0;
        switch (num) {
            case 3:
                ranNum = (int) ((Math.random() * 9 + 1) * 100);
                break;
            case 4:
                ranNum = (int) ((Math.random() * 9 + 1) * 1000);
                break;
            case 5:
                ranNum = (int) ((Math.random() * 9 + 1) * 10000);
                break;
            default:
                ranNum = (int) ((Math.random() * 9 + 1) * 100000);
                break;
        }
        return ranNum;
    }

    /**
     * 判断字符串是否为空
     * 
     * @param str
     *            需判断的字符串
     * @return 为空返回true；否则返回false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     * 
     * @param str
     *            需判断的字符串
     * @return 不为空返回true；否则返回false
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 检查用户名是否合法 只能包含字母与数字，且5-25位
     * @param userName 用户名
     * @return boolean
     */
    public static boolean checkUserName(String userName) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{5,25}$");
        Matcher m = p.matcher(userName);
        return m.matches();
    }

    /**
     * 判断字符串是否包含不可见字符
     * 
     * @param str
     *            校验字符串
     * @return 包含返回false，不包含返回true
     */
    public static boolean isValid(String str) {
        Pattern p = Pattern.compile("[^\\s*|\t|\r|\n]{1,}");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断手机号码是否合法
     * @param phoneNumber 手机号码
     * @return 合法返回true；否则返回false
     */
    public static boolean isMobile(final String phoneNumber) {
        // Pattern p =
        // Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        // Matcher m = p.matcher(mobile);
        // return m.matches();

        if (StringUtil.isEmpty(phoneNumber)) {
            return false;
        }
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0,6-7])|(14[5,7]))\\d{8}$");
            Matcher m = p.matcher(phoneNumber);
            return m.matches();
        }
        catch (Exception e) {
            return false;
        }

    }

    /**
     * 校验密码
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:24:52
    * @param password 密码
    * @return boolean
     */
    public static boolean isValidPassword(String password) {
        try {
            // Pattern p =
            // Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$");
            Pattern p = Pattern.compile("^[A-Za-z0-9]{6,18}$");
            Matcher m = p.matcher(password);
            return m.matches();
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验昵称
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:25:15
    * @param nickname 昵称
    * @return boolean
     */
    public static boolean isValidUserNickName(String nickname) {
        try {
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]|[a-zA-z0-9]*\b[a-z]*|[A-z]*|[0-9]*|[\u4e00-\u9fa5]*$");
            Matcher m = p.matcher(nickname);
            return m.matches();
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断EMAIL是否合法
     * @param email 邮箱
     * @return 合法返回true；否则返回false
     */
    public static boolean isEmail(final String email) {
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 密码加密
     * @param password
     *            需加密的密码
     * @return 加密后的密码:盐值
     */
    public static String encryptPwd(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[24];
        random.nextBytes(salt);
        password = mD5(password + salt);
        return password + ":" + salt;
    }

    /**
     * MD5加密
     * @param s 需加密的字符串
     * @return 加密后的32位字符串
     */
    public static String mD5(String s) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取客户端IP
     * @param request request
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getRemoteAddr();
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                }
                catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 判空
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:27:21
    * @param s 参数
    * @return boolean
     */
    public static boolean isNull(String s) {
        return (s == null || s.trim().length() == 0 || "null".equals(s.trim().toLowerCase()));
    }

    /**
     * 数字
     */
    static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                                   'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
                                   'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '+', '/' };

    /**
     * 把10进制的数字转换成64进制
     * @param number 数字
     * @param shift 转换
     * @return String
     */
    public static String compressNumber(long number, int shift) {
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << shift;
        long mask = radix - 1;
        do {
            buf[--charPos] = DIGITS[(int) (number & mask)];
            number >>>= shift;
        } while (number != 0);
        return new String(buf, charPos, (64 - charPos));
    }

    /**
     * 把64进制的字符串转换成10进制
     * @param decompStr d
     * @return long
     */
    public static long unCompressNumber(String decompStr) {
        long result = 0;
        for (int i = decompStr.length() - 1; i >= 0; i--) {
            if (i == decompStr.length() - 1) {
                result += getCharIndexNum(decompStr.charAt(i));
                continue;
            }
            for (int j = 0; j < DIGITS.length; j++) {
                if (decompStr.charAt(i) == DIGITS[j]) {
                    result += ((long) j) << 6 * (decompStr.length() - 1 - i);
                }
            }
        }
        return result;
    }

    /**
     *
     * @param ch ch
     * @return long
     */
    private static long getCharIndexNum(char ch) {
        int num = ((int) ch);
        if (num >= 48 && num <= 57) {
            return num - 48;
        }
        else if (num >= 97 && num <= 122) {
            return num - 87;
        }
        else if (num >= 65 && num <= 90) {
            return num - 29;
        }
        else if (num == 43) {
            return 62;
        }
        else if (num == 47) {
            return 63;
        }
        return 0;
    }
    
}