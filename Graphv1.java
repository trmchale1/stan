import java.util.*;
import java.io.*;

// Currently a good implementation on depth first search

public class Graph {
    private HashMap<Integer, Node> nodeLookup = new HashMap<Integer,Node>();
    private HashMap<Integer, Node> transpose = new HashMap<Integer,Node>();
    private HashMap<Integer,Node> heap = new HashMap<Integer,Node>(); 
    
    int timet = 0;
    int time = 0;
    public static class Node {
       private int id;
       private String color;
       private int pi;
       private int disc;
       private int fin;
       LinkedList<Node> adjacent = new LinkedList<Node>();
       private Node(int id){
            this.id = id;
       }

    public void addAdj(Node node){
           adjacent.add(node);
    } 

    public void printAdj(){
        for(Node i : adjacent){
            System.out.print(i.id + " ");        
        }
    }
}
/// heap code start
    
public Node heap-max(){
        Node temp = heap.get(0);
        heap.remove(0);
        return temp;
    }
     
    public Node heap-extract-max(){
        if(heap.size() < 1){
            System.out.println("Error - heap underflow");
        }
        Node max = heap-max();
        heap.put(0,heap.get(heap.size()-1));
        this.heap-size = this.heap-size - 1;
        max-heapify();
        return max;
    }

    public max-heapify(int i){
        Node l = left();
        Node r = right();
        if(l.index <= this.heap-size && l > i)
            largest = l;
        else (r <= this.heap-size r > i)
            largest = r;
        if largest != i
            exchange A[i] A[largest]
            max-heapify(largest);
    }
// heap code end

    public void thashNode(Node node){
        transpose.put(node.id,node);
    }

    public void hashNode(Node node){
        nodeLookup.put(node.id,node);
    }

    public boolean hashContains(int num){
        return nodeLookup.containsKey(num);
    }

    public boolean thashContains(int num){
            return transpose.containsKey(num);
    }
    
    public Node getNode(int num){
        return nodeLookup.get(num);
    }

     public Node getNodeT(int num){
        return transpose.get(num);
    }

    public void print() {
       for(Integer i : nodeLookup.keySet()){
            Node temp = nodeLookup.get(i);           
           System.out.println(temp.id + " " + temp.disc + " " + temp.fin + " " + temp.pi);
       }
    } 

    public void printT() {
        for(Integer i : transpose.keySet()){
            Node m = transpose.get(i);
            System.out.println(m.id + " " + m.disc + " " + m.fin + " " + m.pi);
            }
    }


   public void DFS() {
    for(Integer i : nodeLookup.keySet()) {
        Node temp = nodeLookup.get(i);
        temp.color = "white";
        hashNode(temp); 

    }
    this.time = 0;
    for(Integer i : nodeLookup.keySet()) {
        Node temp  = nodeLookup.get(i);
            if(temp.color == "white"){
                DFSvisit(temp);
            }
     }
   }

    public void DFSvisit(Node temp) {
        this.time = this.time + 1;
        temp.disc = this.time;
        temp.color = "gray";
        for(Node i : temp.adjacent){   
            if(i.color == "white"){
                i.pi = temp.id;
                DFSvisit(i);
            }
        }
        temp.color = "black";
        this.time = this.time + 1;
        temp.fin = this.time;
        hashNode(temp);
    }

    public void DFST() {
    for(Integer i : transpose.keySet()) {
        Node temp = transpose.get(i);
        temp.color = "white";
        thashNode(temp);                                                  
    }
    this.timet = 0;
    int fin = 0;
    int bestfin = 0;

    // must choose base upon most highest fin
    for(Integer i : nodeLookup.keySet()){
            Node temp = nodeLookup.get(i); 
        System.out.println(temp.fin);
            if(temp.fin >= fin){
                fin = temp.fin;
                bestfin = temp.id;
            }
    }
        Node index = transpose.get(bestfin);
        if(index.color == "white"){
            DFSvisitt(index);
        }
    
    }
     
    public void DFSvisitt(Node temp) {
        this.timet = this.timet + 1;
        temp.disc = this.timet;
        temp.color = "gray";
        for(Node i : temp.adjacent){
            if(i.color == "white"){
                i.pi = temp.id;
                DFSvisitt(i);
            }
        }
        temp.color = "black";
        this.timet = this.timet + 1;
        temp.fin = this.timet;
        thashNode(temp);
    }

    public static void main(String[] args) throws FileNotFoundException{
    Scanner in = new Scanner(new File("test.txt"));
    Graph G = new Graph();
    Graph T = new Graph();
    int temp1, temp2;            
    while(in.hasNextInt()){
        temp1 = in.nextInt();
        temp2 = in.nextInt();
        if(!G.hashContains(temp1)){
            Node node1 = new Node(temp1);
            if(!G.hashContains(temp2)){
                Node node2 = new Node(temp2);
                G.hashNode(node2);
                node1.addAdj(node2);
                G.hashNode(node1);
            } else if(G.hashContains(temp2)){
                Node node2 = G.getNode(temp2);
                node1.addAdj(node2);
                G.hashNode(node1);
            }
        } else if (G.hashContains(temp1)){
            Node node1 = G.getNode(temp1);
            if(G.hashContains(temp2)){
                Node node2 = G.getNode(temp2);
                node1.addAdj(node2);
                G.hashNode(node1);
            } else if (!G.hashContains(temp2)){
                Node node2 = new Node(temp2);
                node1.addAdj(node2);
                G.hashNode(node2);
                G.hashNode(node1);
            }
        }
        if(!G.thashContains(temp2)){
            Node i = new Node(temp2);
            if(!G.thashContains(temp1)){
                Node j = new Node(temp1);
                G.thashNode(j);
                i.addAdj(j);
                G.thashNode(i);
            } else if(G.thashContains(temp1)){
                Node j = G.getNodeT(temp1);
                i.addAdj(j);
                G.thashNode(i);
            }
        } else if (G.thashContains(temp2)){
            Node i = G.getNodeT(temp2);
            if(G.thashContains(temp1)){
                Node j = G.getNodeT(temp1);
                i.addAdj(j);
                G.thashNode(i);
            } else if (!G.thashContains(temp1)){
                Node j = new Node(temp1);
                i.addAdj(j);
                G.thashNode(j);
                G.thashNode(i);
            }         
                                                                                   
        }             
}
G.DFS();

G.print();

System.out.println("----------------------------");
G.printT();
G.DFST();
System.out.println("----------------------------");
G.printT();
//T.DFS();
//T.print();
    }
}
