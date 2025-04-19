package U3_EstructurasLineales.Arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class boludes {

    public static void main(String[] args) {
        ArrayList<Integer> myarr = new ArrayList<>();
        System.out.println(myarr);
        myarr.addAll(Arrays.asList(1, 2, 4, 70, 20));
        System.out.println(myarr);
        myarr.remove(Integer.valueOf(70));
        System.out.println(myarr);


    }
}
