package com.douye.leetcode;

public class 腾讯云智_ip字符转转二进制 {
    public static void main(String[] args) {
        System.out.println(convertBinary("128.127.1.126"));
    }


    /**
     * 将ip地址转换为二进制
     * @param ip
     * @return
     */
    public static String convertBinary(String ip) {
        String[] strings = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        int length = strings.length;
        for (int i = 0; i < length; i++) {
            sb.append(binary(strings[i], 2));
            if (i != length - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    /**
     * 十进制转n进制
     * @param string
     * @return
     */
    public static String binary(String string, int n) {
        int[] res = new int[8];
        int index = 7;
        Integer value = Integer.valueOf(string);
        while (value != 0) {
            int i = value % n;
            value /= n;
            // 最后计算得到的元素放在在最左边
            res[index--] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int re : res) {
            sb.append(re);
        }
        return sb.toString();
    }

    // todo
    public static String hexToDecimal(String hex) {
        return null;
    }
}
