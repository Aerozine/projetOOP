import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//TODO
// implementing a reshape function w/ new question , and 2 answer ( must search in the tree if answer == other answer)
// switching for vector ?
public class tree {

public Node[] nodeArray;
    public tree(String Filename) throws FileNotFoundException {
        Scanner scanner ;
        scanner = new Scanner(new File(Filename));
        int numberOfLine = scanner.nextInt();
        /* FIXME , what is the utility of this text? */
        System.out.println(scanner.nextLine());
        this.nodeArray = new Node[numberOfLine];
        for (int i = 0; i < numberOfLine; i++)
            this.nodeArray[i] = new Node();
        // init an array of Node
        // if a node is a question , then link to the object in the array
        for (int i = 0; scanner.hasNextLine(); i++) {
            // if it s a question , set the element as a branch and set theirs branch
            String type = scanner.next();
            if (type.equals("?")) {
                this.nodeArray[i] = new NodeBranch(this.nodeArray[scanner.nextInt() - 1], this.nodeArray[scanner.nextInt() - 1], scanner.nextLine());
                } else if (type.equals("=")) {
                this.nodeArray[i] = new Node(scanner.nextLine());
                }


        }
    }
}
