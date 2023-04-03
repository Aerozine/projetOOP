import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

//TODO
// implementing a reshape function w/ new question , and 2 answer ( must search in the tree if answer == other answer)

public class tree {
private String initext;
private Vector<Node> nodeArray;
//other structure than array doesn't permit to conserve a link to an object that will be later defined as child
    public tree(String Filename) throws FileNotFoundException {
        Scanner scanner ;
        scanner = new Scanner(new File(Filename));
        int numberOfLine = scanner.nextInt();
        /* FIXME , what is the utility of this text? */
        this.initext=scanner.nextLine();
        this.nodeArray = new Vector<Node>(numberOfLine);
        // numberOfLine element not set ? -> equivalent to add numberOfLine element with add
        for (int i = 0; i < numberOfLine; i++)
            this.nodeArray.add(new Node());
        // init an array of Node
        // if a node is a question , then link to the object in the array
        Stack<Integer> cords = new Stack<>();
        for (int i = 0; scanner.hasNextLine(); i++) {
            // if it s a question , set the element as a branch and set theirs branch
            String type = scanner.next();
            if (type.equals("?")) {
                cords.push(scanner.nextInt() - 1);
                cords.push(scanner.nextInt() - 1);
                this.nodeArray.set(i, new NodeBranch(scanner.nextLine()));
            } else if (type.equals("=")) {
                this.nodeArray.set(i, new Node(scanner.nextLine()));
            }


        }
        for(int i = numberOfLine-1; i >= 0; i--){
           if(this.nodeArray.get(i) instanceof NodeBranch && !cords.empty()){
            Node node=this.nodeArray.get(i);
               ((NodeBranch)node).setNegative(this.nodeArray.get(cords.pop()));
               ((NodeBranch)node).setPositive(this.nodeArray.get(cords.pop()));
           }
        }
    }
public String getInitext(){
        return initext;
}
public Node getFirstNode(){
return nodeArray.get(0);
}
    public void reshape(Node question,Node Answer,String newQuestion , String alternativeAnswer,boolean alternativeIsYes){
    this.nodeArray.add(new Node(alternativeAnswer));
        if(alternativeIsYes)
            this.nodeArray.add(new NodeBranch(nodeArray.get(nodeArray.size()-1),Answer,newQuestion));
        else
            this.nodeArray.add(new NodeBranch(Answer,nodeArray.get(nodeArray.size()-1),newQuestion));

        if(((NodeBranch)question).getPositive()==Answer){}
//sth like this
// clean and optimize reshape
            // modify the base file and clear

        //TODO
    //does the node indice the better way to specify the place to modify first?
        // using .add() to add an element and set or setElementAt() to modify a certain element
    }
}
