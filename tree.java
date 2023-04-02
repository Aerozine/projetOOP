import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

//TODO
// implementing a reshape function w/ new question , and 2 answer ( must search in the tree if answer == other answer)

public class tree {

public Vector<Node> nodeArray;
//got vectored
    public tree(String Filename) throws FileNotFoundException {
        Scanner scanner ;
        scanner = new Scanner(new File(Filename));
        int numberOfLine = scanner.nextInt();
        /* FIXME , what is the utility of this text? */
        System.out.println(scanner.nextLine());
        this.nodeArray = new Vector<Node>(numberOfLine);
        // numberOfLine element not set ? -> equivalent to add numberOfLine element with add
        for (int i = 0; i < numberOfLine; i++)
            this.nodeArray.add(new Node());
        // init an array of Node
        // if a node is a question , then link to the object in the array
        for (int i = 0; scanner.hasNextLine(); i++) {
            // if it s a question , set the element as a branch and set theirs branch
            String type = scanner.next();
            if (type.equals("?")) {
                this.nodeArray.set(i ,new NodeBranch(this.nodeArray.get(scanner.nextInt() - 1), this.nodeArray.get(scanner.nextInt() - 1), scanner.nextLine()));
                } else if (type.equals("=")) {
                this.nodeArray.set(i,new Node(scanner.nextLine()));
                }


        }
    }
    public void reshape(Node indice,String newQuestion , String alternativeAnswer){
    //TODO
    //does the node indice the better way to specify the place to modify first?
        // using .add() to add an element and set or setElementAt() to modify a certain element
    }
}
