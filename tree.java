import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;
public class tree {
    private final String initext;
    private final Vector<Node> nodeArray;
    public tree(String Filename) throws Exception {
        Scanner scanner;
        scanner = new Scanner(new File(Filename));
        int numberOfLine = scanner.nextInt();
        this.initext = scanner.nextLine();
        this.nodeArray = new Vector<>(numberOfLine);
        // numberOfLine element not set ? -> equivalent to add numberOfLine element with add
        // init an array of Node
        // if a node is a question , then link to the object in the array
        Stack<Integer> cords = new Stack<>();
        while(scanner.hasNextLine()) {
            // if it s a question , set the element as a branch and set theirs branch
            String type = scanner.next();
            if (type.equals("?")) {
                cords.push(scanner.nextInt() - 1);
                cords.push(scanner.nextInt() - 1);
                this.nodeArray.add( new NodeBranch(scanner.nextLine()));
            } else if (type.equals("=")) {
                this.nodeArray.add( new Node(scanner.nextLine()));
            }else {
                throw new IllegalArgumentException("a line isn't a question nor a response");
            }
        }
        //create link
        for (int i = numberOfLine - 1; i >= 0; i--) {
            if (this.nodeArray.get(i) instanceof NodeBranch && !cords.empty()) {
                Node node = this.nodeArray.get(i);
                ((NodeBranch) node).setNegative(this.nodeArray.get(cords.pop()));
                ((NodeBranch) node).setPositive(this.nodeArray.get(cords.pop()));
            }
        }

        scanner.close();
    }

    public String getInitext() {
        return initext;
    }

    public Node getFirstNode() {
        return nodeArray.get(0);
    }
    //to beautify but it works
    public void reshape(Node question, Node Answer, String newQuestion, String alternativeAnswer, boolean alternativeIsYes) {
        this.nodeArray.add(new Node(alternativeAnswer));
        if (alternativeIsYes) this.nodeArray.add(new NodeBranch(nodeArray.lastElement(), Answer, newQuestion));
        else this.nodeArray.add(new NodeBranch(Answer, nodeArray.lastElement(), newQuestion));

        if (((NodeBranch) question).getPositive() == Answer) {
            ((NodeBranch) question).setPositive(nodeArray.lastElement());
        } else {
            ((NodeBranch) question).setNegative(nodeArray.lastElement());
        }
        actualizeTheFile((NodeBranch) question);
    }
// somme changes need to be made
    private void actualizeTheFile(NodeBranch question) {

        int index = this.nodeArray.indexOf(question);
        // jump to the line
        // check if info is correct and if-not(pos or neg !=) rewrite the good version
        // after that , go to the end of the file and write the 2 new nodes
        int size = this.nodeArray.size();

        //renaming Filename will overwrite the textfile.txt and modify the game (make a copy of the original textfile before this)
        File file = new File("file.txt");
        BufferedReader reader = null;
        RandomAccessFile randomAccessFile = null;
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        int lineNumber = 0;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == index + 1) {

                    int pos = this.nodeArray.indexOf(question.getPositive());
                    int neg = this.nodeArray.indexOf(question.getNegative());
                    //rewrite the line and append it for the question
                    //the parsing must be rewritten here
                    stringBuilder.append("new line of question");
                } else {
                    stringBuilder.append(line);
                }
                stringBuilder.append(System.lineSeparator());
            }// append store the whole file on an array , does it is the best way to do it ?
            //should we use an RandomAccessFile ?
            //when readLine==null ,append the 2 new node
            //the parsing must be rewritten here
            stringBuilder.append("new node 1");
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append("new node 2");
            stringBuilder.append(System.lineSeparator());
            // Write the modified content back to the file
            //need to clarify try catch and throw in the code
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the reader and randomAccessFile
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
