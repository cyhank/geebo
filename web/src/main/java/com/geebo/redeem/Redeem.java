package com.geebo.redeem;

import java.util.Date;

/**
 * @Author: Hank
 * @Desription
 * @Date: Created in 14:46 2020/3/24
 *
 * https://blog.csdn.net/zgjllf1011/article/details/79297512
 * 每一位兑换码有4部分构成：
 * [类型(1)，id(4)，随机码(n),校验码(1)]
 * 类型为每次生成兑换码的组id，这里只用了1个byte来存储，可以根据需要增加。
 * id为每次生成的每个兑换码的唯一id。
 * 随机码为每个兑换码的随机数。
 * 校验码用来在验证兑换码时进行校验。
 */
public class Redeem {
    static String stringtable = "abcdefghijkmnpqrstuvwxyz23456789";
    final static String password = "123456";

    //从byte转为字符表索引所需要的位数
    final static int convertByteCount = 5;

    public static void main(String[] args) throws Exception {
        ShowTime();
        System.out.println("=======================");
        create((byte) 1, 3, 12, password);
//
//        VerifyCode("48m6uwafmtia");
//        VerifyCode("4a36g5npamna");
//        VerifyCode("4a36g5naamna");
//        VerifyCode("dafe33234g435");
//        VerifyCode("ga8ehxsq6dja");
        System.out.println(24^1-1);

    }

    /**
     * 生成兑换码
     * 这里每一次生成兑换码的最大数量为int的最大值即2147483647
     *
     * @param groupid
     * @param codecount 生成个数
     * @param codelength 生成长度
     * @param password
     * @return
     */
    public static byte[] create(byte groupid, int codecount, int codelength, String password) {
        //8位的数据总长度
        int fullcodelength = codelength * convertByteCount / 8;
        //随机码对时间和id同时做异或处理
        //类型1，id4，随机码n,校验码1
        int randcount = fullcodelength - 6;//随机码有多少个

        //如果随机码小于0 不生成
        if (randcount <= 0) {
            return null;
        }
        for (int i = 0; i < codecount; i++) {
            //这里使用i作为code的id
            //生成n位随机码
            byte[] randbytes = new byte[randcount];
            for (int j = 0; j < randcount; j++) {
                randbytes[j] = (byte) (Math.random() * Byte.MAX_VALUE);
            }

            //存储所有数据
            ByteHapper byteHapper = ByteHapper.CreateBytes(fullcodelength);
            byteHapper.AppendNumber(groupid).AppendNumber(i).AppendBytes(randbytes);

            //计算校验码 这里使用所有数据相加的总和与byte.max 取余
            byte verify = (byte) (byteHapper.GetSum() % Byte.MAX_VALUE);
            byteHapper.AppendNumber(verify);

            //使用随机码与时间和ID进行异或
            for (int j = 0; j < 5; j++) {
                byteHapper.bytes[j] = (byte) (byteHapper.bytes[j] ^ (byteHapper.bytes[5 + j % randcount]));
            }

            //使用密码与所有数据进行异或来加密数据
            byte[] passwordbytes = password.getBytes();
            for (int j = 0; j < byteHapper.bytes.length; j++) {
                byteHapper.bytes[j] = (byte) (byteHapper.bytes[j] ^ passwordbytes[j % passwordbytes.length]);
            }

            //这里存储最终的数据
            byte[] bytes = new byte[codelength];

            //按6位一组复制给最终数组
            for (int j = 0; j < byteHapper.bytes.length; j++) {
                for (int k = 0; k < 8; k++) {
                    int sourceindex = j * 8 + k;
                    int targetindex_x = sourceindex / convertByteCount;
                    int targetindex_y = sourceindex % convertByteCount;
                    byte placeval = (byte) Math.pow(2, k);
                    byte val = (byte) ((byteHapper.bytes[j] & placeval) == placeval ? 1 : 0);
                    //复制每一个bit
                    bytes[targetindex_x] = (byte) (bytes[targetindex_x] | (val << targetindex_y));
                }
            }

            StringBuilder result = new StringBuilder();
            //编辑最终数组生成字符串
            for (int j = 0; j < bytes.length; j++) {
                result.append(stringtable.charAt(bytes[j]));
            }
            System.out.println("out string : " + result.toString());
        }
        ShowTime();
        return null;
    }

    /**
     * 验证兑换码
     *
     * @param code
     */
    public static void VerifyCode(String code) {
        byte[] bytes = new byte[code.length()];

        //首先遍历字符串从字符表中获取相应的二进制数据
        for (int i = 0; i < code.length(); i++) {
            byte index = (byte) stringtable.indexOf(code.charAt(i));
            bytes[i] = index;
        }

        //还原数组
        int fullcodelength = code.length() * convertByteCount / 8;
        int randcount = fullcodelength - 6;//随机码有多少个

        byte[] fullbytes = new byte[fullcodelength];
        for (int j = 0; j < fullbytes.length; j++) {
            for (int k = 0; k < 8; k++) {
                int sourceindex = j * 8 + k;
                int targetindex_x = sourceindex / convertByteCount;
                int targetindex_y = sourceindex % convertByteCount;

                byte placeval = (byte) Math.pow(2, targetindex_y);
                byte val = (byte) ((bytes[targetindex_x] & placeval) == placeval ? 1 : 0);

                fullbytes[j] = (byte) (fullbytes[j] | (val << k));
            }
        }

        //解密，使用密码与所有数据进行异或来加密数据
        byte[] passwordbytes = password.getBytes();
        for (int j = 0; j < fullbytes.length; j++) {
            fullbytes[j] = (byte) (fullbytes[j] ^ passwordbytes[j % passwordbytes.length]);
        }

        //使用随机码与时间和ID进行异或
        for (int j = 0; j < 5; j++) {
            fullbytes[j] = (byte) (fullbytes[j] ^ (fullbytes[5 + j % randcount]));
        }

        //获取校验码 计算除校验码位以外所有位的总和
        int sum = 0;
        for (int i = 0; i < fullbytes.length - 1; i++) {
            sum += fullbytes[i];
        }
        byte verify = (byte) (sum % Byte.MAX_VALUE);

        //校验
        if (verify == fullbytes[fullbytes.length - 1]) {
            System.out.println(code + " : verify success!");
        } else {
            System.out.println(code + " : verify failed!");
        }
    }

    public static void ShowTime() {
        Date date = new Date();
        long times = date.getTime();//时间戳
        System.out.println("time  : " + times);
    }
}
