import org.omg.CORBA.Current;

import java.lang.reflect.Array;
import java.util.Scanner;

public class fdaSort {

    static void Exchange(int arr[][], int i, int j) {
        for (int k = 0; k < 100; k++) {
            int temp = arr[i][k];
            arr[i][k] = arr[j][k];
            arr[j][k] = temp;
        }
    }// 数组排序交换

    static int Sort(int arr[][], int n) {
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                for (int j = 2; j < 11; j++) {
                    // if((arr[k][j]==0)&&(arr[i][j]==-1)){
                    // Exchange(arr,k,i);
                    // break;
                    // }
                    // else if((arr[k][j]==-1)&&(arr[i][j]==0)){
                    // break;
                    // }
                    // else
                    if (arr[k][j] < arr[i][j]) {
                        Exchange(arr, k, i);
                        break;
                    } else if (arr[k][j] > arr[i][j])
                        break;
                    else {

                    }
                }
            }
        } // 数组排序
        int max = arr[0][1];
        for (int i = 1; i < n; i++) {
            if (arr[i][1] > max)
                max = arr[i][1];
        }
        return max;
    }

    static boolean CreateNew(int arr[][], int tf[], int choc[], int n) {
        for (int i = 0; i < n; i++) {
            if (tf[i] == 1 && choc[0] == 0) {
                for (int j = 2; j <= choc[1] + 2; j++) {
                    if ((arr[i][j] == 0 && choc[j] == -1) || (arr[i][j] == 1 && choc[j] == -1))
                        return true;
                    else if (arr[i][j] != choc[j])
                        break;
                    else {
                    }
                }
            } else if (tf[i] == 2 && choc[0] == 1) {
                for (int j = 2; j <= choc[1] + 2; j++) {
                    if ((arr[i][j] == 0 && choc[j] == -1) || (arr[i][j] == 1 && choc[j] == -1))
                        return true;
                    else if (arr[i][j] != choc[j])
                        break;
                    else {
                    }
                }
            } else {
            }
        }
        return false;
    }
    // 建树---------------------------------------------------------------------
    static Node CreatTree(int[][] array, int n){//n为个数，array为排序后数组
        Node root=null;
        Node newNode=new Node();
        if(root==null){
            root=newNode;
        }
        for (int i = 0;i<n;i++) {
            Node Current = root;
            Node Past = null;
            for (int j = 2; j < array[i][1] + 1; j++) {
                if (array[i][j] == 0) {
                    Past = Current;
                    Current = Past.left;
                    if (Current==null){
                        Current=newNode;
                        Past.left=Current;
                    }
                    Current.parent = Past;
                } else {
                    Past = Current;
                    Current = Past.right;
                    if (Current==null){
                        Current=newNode;
                        Past.right=Current;
                    }
                    Current.parent = Past;
                }
                if (j == array[i][1] + 1) {
                    Current.statue = array[i][0];
                }
            }
        }
        Node Current=root;
        for(int i = 0; i<5;i++){

            System.out.print(Current.right);
            Current=Current.left;
        }
        return root;
    }
//------------------------------------------------------------------------------
    public static void main(String[] args) {
        int[][] array = new int[100][100];
        int[] tf = new int[100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++)
                array[i][j] = -1;
            tf[i] = 0;
        }
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            array[i][0] = m;
            m = sc.nextInt();
            array[i][1] = m;
            for (int j = 2; j <= m + 1; j++) {
                array[i][j] = sc.nextInt();
            }
        }
        int max = fdaSort.Sort(array, n);
        System.out.println("==================分割线=============");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= array[i][1] + 1; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println(" ");
        }
        Node Tree=fdaSort.CreatTree(array,n);
        for (int i = 0; i < n; i++) {
            if (array[i][1] == max) {
                if (array[i][0] == 0) {
                    // TODO这里放false的，可以定义字符串也可以list也可以数组。
                    tf[i] = 1;
                } else {
                    // TODO这里放true的，同上。
                    tf[i] = 2;
                }
            }
        }
        for (int i = 3, j = 0; j < n; j++) {
            if (tf[j] == 0) {
                if (fdaSort.CreateNew(array, tf, array[j], n)) {
                    tf[j] = i;
                    i++;
                } else {
                    tf[j] = array[j][0] + 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(tf[i] + " ");
        }
    }
}