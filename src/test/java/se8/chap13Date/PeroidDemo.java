package se8.chap13Date;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import static java.lang.System.out;

public class PeroidDemo {
    public static void main(String[] args) {
        out.print("輸入出生年月日（yyyy-mm-dd）：");
        LocalDate birth = LocalDate.parse(new Scanner(System.in).nextLine());
        LocalDate now = LocalDate.now();
        //Peroid是日期差, 不表示比日小的單位
        Period period = Period.between(birth, now);
        out.printf("你活了 %d 年 %d 月 %d 日%n",
                period.getYears(), period.getMonths(), period.getDays());
    }

    /***
     * ChronoUnit是 TemporalUnit的實例
     */
    @Test
    public void temporalUnitTest(){
        System.out.println(LocalDate.of(1979, 12, 21)
                .plus(5, ChronoUnit.DAYS)
                .plus(1, ChronoUnit.MONTHS)
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd E"))
        );
    }

    /***
     * 民國年
     */
    @Test
    public void minguoDateTest(){
        out.println(MinguoDate.of(111, 5, 10)
                .atTime(LocalTime.of(23, 00, 00))
        );

        out.println(MinguoDate.now());
    }

    @Test
    public void toWestToMinguoTest(){
        LocalDate localDate = LocalDate.of(1979, 12, 21);
        MinguoDate minguoDate = MinguoDate.from(localDate);
        out.println(minguoDate);
    }
}
