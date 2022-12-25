package se8.chap13Date;

import org.assertj.core.util.Arrays;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static java.lang.System.out;

/**
 * @author: justin
 * @date: 2022/10/24
 */
public class FinalPractice {

    public static void main(String[] args){
         String[] dayOfWeek = {" 日 ", " 一 ", " 二 ", " 三 ", " 四 ", " 五 ", " 六 "};
         LocalDate initLocalDate = LocalDate.of(2014, 5, 1);
         LocalDate endLocalDate = LocalDate.of(2014, 6, 1);
         int currentWeek = 0;
         int currentDay = 0;
         int[][] calendar = new int[5][7];

        Arrays.asList(dayOfWeek).forEach(out::print);
        out.println("");

         int day = 0;
         while(initLocalDate.isBefore(endLocalDate)){
             currentDay = initLocalDate.getDayOfWeek().getValue();
             day = initLocalDate.getDayOfMonth();
             boolean isWeekend = (currentDay % 7) ==0;
             if (isWeekend)
                     calendar[currentWeek][0] = day;
             else
                    calendar[currentWeek][currentDay] = day;

             //out.printf("星期%s,%s ", initLocalDate.getDayOfWeek().getValue(), initLocalDate.format(DateTimeFormatter.ofPattern("dd")));

             initLocalDate = initLocalDate.plus(1, ChronoUnit.DAYS);
             if (initLocalDate.getDayOfWeek().getValue() == 7){
                 currentWeek +=1;
                 //out.println("");
             }
         }

         String zero = "0";
         int fixLen = 2;

         String value ="";
         for(int i=0;i < 5; i++){
             for(int j=0; j<7; j++){
                  if (calendar[i][j] == 0)
                      value = "    ";
                  else{
                      String tempStr = (zero + String.valueOf(calendar[i][j]));
                       tempStr = tempStr.substring(tempStr.length()-fixLen);

                      value = " "+ tempStr+ " ";
                  }

                  System.out.printf(value);
             }
             out.println("");
         }
    }
}
