package gzhu.edu.cn;
import java.util.Scanner;

/**
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-10-02 11:05
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        if(a>b){
            System.out.print(a);
        }else{
            System.out.print(b);
        }
        scanner.close();
    }
}