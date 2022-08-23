package ydzhao.weixin.tuisong.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ClassName JiNianRi
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 17:32
 */
public class JiNianRi {
    /**
     * 恋爱
     */
    static String lianAi = "2020-01-01";
    /**
     * 领证
     */
//    static String linZheng = "2020-01-01";
    /**
     * 结婚
     */
//    static String jieHun = "2020-01-01";
    /**
     * 生日
     */
    static String shengRi = "2000-11-20";



    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 距离date还有多少天
     * @param date
     * @return
     */
//    public static int before(String date) {
//        int day = 0;
//        try {
//            long time = simpleDateFormat.parse(date).getTime() - System.currentTimeMillis();
//            day = (int) (time / 86400000L);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return day;
//    }


    /**
     * 已经过去date多少天
     * @param date
     * @return
     */
//    public static int after(String date) {
//        int day = 0;
//        try {
//            long time = (System.currentTimeMillis() - simpleDateFormat.parse(date).getTime());
//            day = (int) (time / 86400000L);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return day;
//    }

    /**
     * 结婚
     * @return
     */
//    public static int getJieHun() {
//        return before(jieHun);
//    }
    /**
     * 领证
     * @return
     */
//    public static int getLinZhen() {
//        return before(linZheng);
//    }
    /**
     * 恋爱
     * @return
     */
    public static int getLianAi() {
//        return before(lianAi);
        return getBirthDay(lianAi);
    }

    /**
     * 生日
     * @return
     */

    public static int getShengRi(){
//        return after(shengRi);
        return getBirthDay(shengRi);
    }

    public static int getAge() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long ShengRi = simpleDateFormat.parse(shengRi).getTime();
        long time = System.currentTimeMillis();
        //当前时间和生日的差值
        long cha = time - ShengRi;
        int age = (int) (cha / (365L * 24 * 3600 * 1000));
        return age;
    }

    public static int getBirthDay(String addtime) {
        int days = 0;
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String clidate = addtime;
            Calendar cToday = Calendar.getInstance(); // 存今天
            Calendar cBirth = Calendar.getInstance(); // 存生日
            cBirth.setTime(myFormatter.parse(clidate)); // 设置生日
            cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
            if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
                // 生日已经过了，要算明年的了
                days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
                days += cBirth.get(Calendar.DAY_OF_YEAR);
            } else {
                // 生日还没过
                days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }



    public static void main(String[] args) throws ParseException {
        System.out.println(getLianAi());
        System.out.println(getShengRi());
        System.out.println(getAge());

    }


}
