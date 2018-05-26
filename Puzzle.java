import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
class vertex{
	String v;
	int distance;
	String previous;
	int has;

	vertex(String v,int d,String p,int h){
		this.v=v;
		this.distance=d;
		this.previous=p;
		this.has=h;
	}
}

class Heaps{
	vertex[] A = new vertex[370000];

	int n=0;

	Heaps(vertex[] arr){
		A[0]=new vertex(null,0,null,0);
		for(int i=0;i<arr.length;i++){
			A[i+1]=arr[i];
		}
		n = arr.length;
		BuildHeap();
	}
	Heaps(){
		A[0]=new vertex(null,0,null,0);
	}

	public int size(){
		return n;
	}

	public vertex DeleteMin(){
		vertex min = A[1];
		vertex x = A[n];
		n--;
		PerDown(1, x);

		return min;
	}

	public void PerDown(int i, vertex x){
		int j;
		if(2*i>n){
			A[i]=x;
		}
		else if(2*i==n){
			if(A[2*i].distance<x.distance){
				A[i]=A[2*i];
				A[2*i]=x;
			}
			else{
				A[i]=x;
			}
		}
		else if(2*i<n){
			if(A[2*i].distance<A[2*i+1].distance){
				j=2*i;
			}
			else{
				j=2*i+1;
			}
			if(A[j].distance<x.distance){
				A[i]=A[j];
				PerDown(j,x);
			}
			else{
				A[i]=x;
			}
		}
	}

	public void insert(vertex str){
		n++;
		A[n]=str;
		int i = n;
		while(!(A[i/2].distance<=str.distance||i==1)){
			if(A[i/2].distance>str.distance){
				A[i]=A[i/2];
				A[i/2]=str;
			}
		}
	}

	public void BuildHeap(){
		for(int i = n/2; i>0; i--){
			PerDown(i, A[i]);
		}
	}
}

class Graph{
	public static int hash(String str){
		int i=0;
		int[] factorial = {1,1,2,6,24,120,720,5040,40320,362880};
		ArrayList<Integer> seen = new ArrayList<Integer>();
		for(int j=0;j<str.length();j++){
			int k = (int) Character.getNumericValue(str.charAt(j));
			if(k==16){
				k=9;
			}
			seen.add(k);
			int z=0;
			for(int l=0;l<seen.size();l++){
				if(seen.get(l)<k)
					z++;
			}
			i = i + (k-1-z)*factorial[8-j];
		}
		return i;
	}

	class vertexAndWeight{
		ArrayList<String> list;
		ArrayList<String> edgeList;
		vertexAndWeight(ArrayList<String> list,ArrayList<String> edgeList){
			this.list=list;
			this.edgeList=edgeList;
		}
	}
	public vertexAndWeight edgeFunc(String str){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> edgeList = new ArrayList<String>();
		int i = str.indexOf('G');
		//int h = hash(str);
		if(i==0){
			list.add(str.charAt(1)+"G"+str.substring(2,str.length()));
			edgeList.add(str.charAt(1) + "L");
			list.add(str.charAt(3)+str.substring(1,3)+"G"+str.substring(4,str.length()));
			edgeList.add(str.charAt(3) + "U");
		}
		else if(i==1){
			list.add("G"+str.charAt(0)+str.substring(2,str.length()));
			edgeList.add(str.charAt(0) + "R");
			list.add(str.substring(0,1)+str.charAt(2)+"G"+str.substring(3,str.length()));
			edgeList.add(str.charAt(2) + "L");
			list.add(str.substring(0,1)+str.charAt(4)+str.substring(2,4)+"G"+str.substring(5,str.length()));
			edgeList.add(str.charAt(4) + "U");
		}
		else if(i==2){
			list.add(str.charAt(0)+"G"+str.charAt(1)+str.substring(3,str.length()));
			edgeList.add(str.charAt(1) + "R");
			list.add(str.substring(0,2)+str.charAt(5)+str.substring(3,5)+"G"+str.substring(6,9));
			edgeList.add(str.charAt(5) + "U");
		}
		else if(i==3){
			list.add("G"+str.substring(1,3)+str.charAt(0)+str.substring(4,9));
			edgeList.add(str.charAt(0) + "D");
			list.add(str.substring(0,3)+str.charAt(4)+"G"+str.substring(5,9));
			edgeList.add(str.charAt(4) + "L");
			list.add(str.substring(0,3)+str.charAt(6)+str.substring(4,6)+"G"+str.substring(7,9));
			edgeList.add(str.charAt(6) + "U");
		}
		else if(i==4){
			list.add(str.charAt(0)+"G"+str.substring(2,4)+str.charAt(1)+str.substring(5,9));
			edgeList.add(str.charAt(1) + "D");
			list.add(str.substring(0,3)+"G"+str.charAt(3)+str.substring(5,9));
			edgeList.add(str.charAt(3) + "R");
			list.add(str.substring(0,4)+str.charAt(5)+"G"+str.substring(6,9));
			edgeList.add(str.charAt(5) + "L");
			list.add(str.substring(0,4)+str.charAt(7)+str.substring(5,7)+"G"+str.charAt(8));
			edgeList.add(str.charAt(7) + "U");
		}
		else if(i==5){
			list.add(str.substring(0,2)+"G"+str.substring(3,5)+str.charAt(2)+str.substring(6,9));
			edgeList.add(str.charAt(2) + "D");
			list.add(str.substring(0,4)+"G"+str.charAt(4)+str.substring(6,9));
			edgeList.add(str.charAt(4) + "R");
			list.add(str.substring(0,5)+str.charAt(8)+str.substring(6,8)+"G");
			edgeList.add(str.charAt(8) + "U");
		}
		else if(i==6){
			list.add(str.substring(0,3)+"G"+str.substring(4,6)+str.charAt(3)+str.substring(7,9));
			edgeList.add(str.charAt(3) + "D");
			list.add(str.substring(0,6)+str.charAt(7)+"G"+str.charAt(8));
			edgeList.add(str.charAt(7) + "L");
		}
		else if(i==7){
			list.add(str.substring(0,4)+"G"+str.substring(5,7)+str.charAt(4)+str.charAt(8));
			edgeList.add(str.charAt(4) + "D");
			list.add(str.substring(0,6)+"G"+str.charAt(6)+str.charAt(8));
			edgeList.add(str.charAt(6) + "R");
			list.add(str.substring(0,7)+str.charAt(8)+"G");
			edgeList.add(str.charAt(8) + "L");
		}		
		else if(i==8){
			list.add(str.substring(0,5)+"G"+str.substring(6,8)+str.charAt(5));
			edgeList.add(str.charAt(5) + "D");
			list.add(str.substring(0,7)+"G"+str.charAt(7));
			edgeList.add(str.charAt(7) + "R");
		}
		
		return new vertexAndWeight(list,edgeList);
	}

	Graph(){
	}

	public ArrayList<String> Dijkstra(String s, String t, int[] d){
		int[] Distance = new int[362880];
		int[] known = new int[362880];
		String[] previous = new String[362880];
		for(int i=0;i<362880;i++){
			Distance[i]=Integer.MAX_VALUE;
		}
		Heaps heap = new Heaps();
		heap.insert(new vertex(s,0,"none",hash(s)));
		int goal = hash(t);

		vertexAndWeight neighbours;
		while(heap.size()!=0){
			 vertex V = heap.DeleteMin();
			 int hashOfv = V.has;
			 known[hashOfv] = 1;
			 if(goal==hashOfv){
			 	break;
			 }
			  neighbours = edgeFunc(V.v);
			 for(int i=0;i<neighbours.list.size();i++){
			 	String n = neighbours.list.get(i);
			 	int hashOfn = hash(n);
			 	if(known[hashOfn]==0){
				 	int dist = V.distance + d[(int) Character.getNumericValue((char) neighbours.edgeList.get(i).charAt(0))];
				 	if(dist<Distance[hashOfn]){
				 		Distance[hashOfn]=dist;
			 			previous[hashOfn]=V.v;
			 			heap.insert(new vertex(n,dist,V.v,hashOfn));
				 	}
				}
			 }
		}

		String current = t;
		ArrayList<String> path = new ArrayList<String>();
		while(!current.equals(s)){
			int curr = hash(current);
			String prev = previous[curr];
			if(prev==null){
				break;
			}
			
			neighbours = edgeFunc(prev);
			path.add(neighbours.edgeList.get(neighbours.list.indexOf(current)));
			current = prev;
		}

		if(!current.equals(s)){
			return null;
		}

		return path;
	}
}

public class Puzzle{

	public static void main(String args[]){		
		Graph g = new Graph();
		try {
		FileInputStream fstream = new FileInputStream(args[0]);
		FileOutputStream fstream1 = new FileOutputStream(args[1],false);
		PrintStream p = new PrintStream(fstream1);
		Scanner s = new Scanner(fstream);
		int n = s.nextInt();
		s.nextLine();
		for(int z=0;z<n;z++){
			String str = s.nextLine();
			String start = str.substring(0, 9);
			String goal = str.substring(10,19);
			int[] d_i = new int[9];

			for(int i=1;i<9;i++){
				d_i[i]=s.nextInt();
			}
			if(z!=n-1)
			s.nextLine();

			int d=0;
			ArrayList<String> path = g.Dijkstra(start,goal,d_i);
			if(path!=null){
				for(int i=path.size()-1;i>=0;i--){
					d = d + d_i[(int) Character.getNumericValue(path.get(i).charAt(0))];
				}
				p.println(path.size()+" "+d);
				int flag=0;
				for(int i=path.size()-1;i>=0;i--){
					if(flag!=0){
						p.print(" ");
					}
					p.print(path.get(i));
					flag=1;
				}
				p.println("");
			}
			else{
				p.println(-1+" "+-1);
				p.println("");
			}
		}
		p.println("");
		}catch(FileNotFoundException e) {}
	}

}
