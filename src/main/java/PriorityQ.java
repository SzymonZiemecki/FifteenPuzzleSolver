import java.util.PriorityQueue;

public class PriorityQ<N> extends PriorityQueue<Node> {

    public Node removeNode(Node n){
        for(Node k : this){
            if(k.equals(n)){
                this.remove(k);
                return k;
            }
        }
        return null;
    }

}
