package number;

/**
 * 求两个正整数的最大公约数
 */
public class Gcd {

    /**
     * 方式1：采用减法
     * 要保证 y > x
     */
    int Gcd(int x, int y){
        if (x<y){
            return Gcd(y,x);
        }
        // 当y=0的时候是退出的条件
        if (y == 0){
            return x;
        }else {
            return Gcd(x-y,y) ;
        }
    }

    public static void main(String[] args) {
        int x = 42897 ;
        int y = 18644 ;

        int gcd = new Gcd().Gcd(x,y) ;
        System.out.println(gcd);
    }
}
