package com.example.mianshi;

public class Test {
    public static void main(String[] args) {
        print3jiaoxing();
        lingxing();
        juxing();
    }

    public static void print3jiaoxing() {
        int n = 5; //n表示输出空心三角形行数，这里以5行为例
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) { //控制每行最前面的空格
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) { //控制总的星号和空格
                if (i == 1 || i == n) {
                    /* 控制第一行的与最后一行的星号
                     * 当i等于n时，每次循环都只会走这一句
                     * 所以会打印2*n-1个星号 */
                    System.out.print("*");
                } else if (k == 1 || k == 2 * i - 1) { //控制第二行到n-1行的星号的输出
                    System.out.print("*");
                } else { //控制第二行到n-1行的空格的输出
                    System.out.print(" ");
                }
            }
            System.out.println(); //每行输出完毕后进行换行操作
        }
    }

    public static void lingxing() {
        int n = 5;
        //这里输出菱形的上半部分
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) { //控制每行最前面的空格
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) { //控制总的星号和空格
                if (i == 1) {
                    /* 控制第一行的与最后一行的星号
                     * 当i等于n时，每次循环都只会走这一句
                     * 所以会打印2*n-1个星号 */
                    System.out.print("*");
                } else if (k == 1 || k == 2 * i - 1) { //控制第二行到n-1行的星号的输出
                    System.out.print("*");
                } else { //控制第二行到n-1行的空格的输出
                    System.out.print(" ");
                }
            }
            System.out.println(); //每行输出完毕后进行换行操作
        }
        //这里输出菱形的下半部分
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) { //控制每行最前面的空格
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * (n - i) - 1; k++) { //控制总的星号和空格
                if (k == 1 || k == 2 * (n - i) - 1) { //控制第一个星号和最后一个星号
                    System.out.print("*");
                } else { //控制中间空心部分
                    System.out.print(" ");
                }
            }
            System.out.println(); //每行输出完毕后进行换行操作
        }
    }

    public static void juxing(){
        int n = 5;
        for (int i = 1; i <=n; i++) { //控制输出行数，由于行与行之间有空格效果，所以打印效果为矩形
            for (int j = 1; j <=n; j++) {
                if(i==1||i==n) { //控制第一行与最后一行的星号
                    System.out.print("*");
                }
                else if(j==1||j==n) { //控制第二行到倒数第二行星号
                    System.out.print("*");
                }else{ //控制第二行到倒数第二行空格
                    System.out.print(" ");
                }
            }
            System.out.println(); //每输出完一行进行换行操作
        }
    }

}
