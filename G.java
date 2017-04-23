import java.util.ArrayList;    
import java.util.HashMap;    
import java.util.List;    
import java.util.*;
import java.io.FileReader;
public class G {
    Map<Node, List<Integer>> map = new HashMap<Node, List<Integer>>();
    
    public class Node {
        int data;
        String color;
        int p;
        int d;
        int f;
        Node(int d){
            this.data = d;
            this.color = "white";
            this.d = -1;
            this.f = -1;
        }
    }
    
    public void readFile(Scanner in){
        int key = in.nextInt();
        while(in.hasNext()){
            int keylast = key;
            ArrayList<Integer> temp = new ArrayList<Integer>();
            int value = in.nextInt();
            temp.add(value);
            key = in.nextInt();
//            System.out.println("! " + key2);
            while(key == keylast){
                
                value = in.nextInt();
                temp.add(value);
                key = in.nextInt();
            }
//            System.out.println("key " + keylast + " temp " + temp);
            Node d = new Node(keylast);
            this.map.put(d,temp); 
        }
    }
    
    public static void main(String[] args) throws Exception {
        G g = new G();
        Scanner in = new Scanner(new FileReader("data.txt"));
        g.readFile(in);
    }
}
