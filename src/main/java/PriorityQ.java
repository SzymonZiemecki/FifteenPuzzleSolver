import java.util.PriorityQueue;

public class PriorityQ<E> extends PriorityQueue<Node> {

    public Node getNode(Node n){
        for(Node k : this){
            if(k.equals(n)){
                return k;
            }
        }
        return null;
    }

    public void Update(Node k,int prio){
                this.remove(k);
                k.priority=prio;
                this.add(k);
    }

}
