import java.util.*;

public class lab12 {
    static void DFS(int v, boolean[] visited, int n, int[][] matrix){
            Stack<Integer> stiva = new Stack<>();
            stiva.push(v);
            int s;
            while(!stiva.empty()){
                s = stiva.peek();
                stiva.pop();
                if(!visited[s]){
                    if(n<=100) System.out.print(s + " ");
                    visited[s]=true;
                    for(int i=0; i<n; i++){
                        if(!visited[i] && matrix[s][i]==1){
                            stiva.push(i);
                        }
                    }
                }
            }
        }
    static void treeDFS(int v, boolean[] visited, int n, int[][] matrix, int[][] treeMatrix){
        Stack<Integer> stiva = new Stack<>();
        stiva.push(v);
        int s;
        while(!stiva.empty()){
            s = stiva.peek();
            stiva.pop();
            if(!visited[s]){
                visited[s]=true;
                for(int i=0; i<n; i++){
                    if(!visited[i] && matrix[s][i]==1){
                        if(!stiva.contains(i)){
                            treeMatrix[s][i]=1;
                            treeMatrix[i][s]=1;
                        }
                        stiva.push(i);
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        int n;
        try {
            n=Integer.parseInt(args[0]);
        } catch (final NumberFormatException e) {
            System.out.println("nu este numar");
            n=Integer.parseInt(System.console().readLine());
        }
        int[][] matrix=new int[n][n];
        int x;
        int edge=(int)(Math.random()*(n*(n-1)/2));
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                x=(int)(Math.random()*10)%2;
                if(x==1) edge--;
                matrix[i][j]=x;
                matrix[j][i]=x;
                if(edge == 0) break;
            }
        }
      if(n<=100) {
          for (int i = 0; i < n; i++) {
              for (int j = 0; j < n; j++) {
                  System.out.print(matrix[i][j] + " ");
              }
              System.out.println();
          }
      }
        boolean[] visited=new boolean[n];
        if(n<=100) System.out.println("Componenta 1:");
        DFS(0, visited, n, matrix);
        boolean connected=true;
        int notVisited=0;
        for (int i=0; i<n; i++){
            if(!visited[i]) {
                connected=false;
                notVisited=i;
                break;
            }
        }
        if(!connected){
            int comp=2;
            while(!connected){
                System.out.println();
                System.out.println("Componenta "+comp+":");
                DFS(notVisited,visited,n,matrix);
                for (int i=0; i<n; i++){
                    connected=true;
                    if(!visited[i]) {
                        connected=false;
                        notVisited=i;
                        break;
                    }
                }
                comp++;
            }
        }
        else {
            System.out.println();
            System.out.println("Este conex");
            int[][] treeMatrix=new int[n][n];
            for (int i=0; i<n; i++) visited[i]=false;
            for (int i=0; i<n; i++){
                for (int j=0; j<n; j++) treeMatrix[i][j]=0;
            }
            long startTime = System.nanoTime();
            treeDFS(0, visited, n, matrix, treeMatrix);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            if(n>=100)System.out.println("Timpul:"+totalTime);
            if(n<=100) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(treeMatrix[i][j]);
                    }
                    System.out.println();
                }
            }
        }
    }
}
