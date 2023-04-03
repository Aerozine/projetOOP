// ancestor of Leaf and branch
// has a string in entry
public class Node {
    private String data;
    public Node(String data){
    this.data=data;
    }
    public Node(){
        this.data=null;
        
    }
    public String getData(){
    return data;
    }
    public void setData(String data){
        this.data=data;
    }
}
