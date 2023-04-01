import java.io.FileNotFoundException;
import java.util.Scanner;

public class idk 
{
        public static void main(String[] args) throws FileNotFoundException 
        {

        new tree("textfile.txt");
        
        Scanner scanner = new Scanner(System.in); // pour choisir l'animal
        String animal = scanner.nextLine();
        
        Scanner scan;
        String answ;

        int i=0;
        while(true) // On coupera la boucle avec un break
        {

                if(/* la ligne i débute par = */)
                {
                        System.out.println("Is it a " + tree.nodeArray[i].data + " ?");
                        scan = new Scanner(System.in);
                        answ = scan.nextLine();
                        
                        while(!answ.equals("yes") && !answ.equals("no") ) // Si l'utilisateur tappe autre chose que yes/no
                        {
                                System.out.println("Is it yes or no ?");
                                scan = new Scanner(System.in);
                                answ = scan.nextLine();
                        }
                        if (answ.equals("yes"))
                        {
                                System.out.println("I have won !");
                                break;
                        }
                        if (answ.equals("no"))
                        {
                                System.out.println("I am unable to guess, you have won !");
                                System.out.println("What did you chose ?");
                                scan = new Scanner(System.in);
                                String nwanimal = scan.nextLine();
                                Node new_animal = new Node(nwanimal);
                                System.out.println("What question could I ask to distinguish a " + nwanimal + " from a "+tree.nodeArray[i].data + " ?");
                                scan = new Scanner(System.in);
                                String quest = scan.nextLine();
                                System.out.println("For a "+nwanimal+", would you answer yes or no to this question ?");
                                scan = new Scanner(System.in);
                                answ = scan.nextLine();
                                while(!answ.equals("yes") && !answ.equals("no") ) // Si l'utilisateur tappe autre chose que yes/no
                                {
                                        System.out.println("Is it yes or no ?");
                                        scan = new Scanner(System.in);
                                        answ = scan.nextLine();
                                }
                                
                                if(answ.equals("yes"))
                                        NodeBranch new_question = new NodeBranch(/*indice du nouveau */ 0 ,/*indice de l'erreur */0,quest);

                                if(answ.equals("no"))
                                        NodeBranch new_question = new NodeBranch(/*indice de l'erreur */0,/*indice du nouveau */0, quest);
                                
                                // Ajouter new_animal et new_question au vecteur nodeArray
                                // Actualiser les indices des éléments des l'arbre 

                                break;
                        }

                }

                System.out.println(tree.nodeArray[i].data);
                
                scan = new Scanner(System.in);
                answ = scan.nextLine();

                while(!answ.equals("yes") && !answ.equals("no") ) // Si l'utilisateur tappe autre chose que yes/no
                {
                        System.out.println("Is it yes or no ?");
                        scan = new Scanner(System.in);
                        answ = scan.nextLine();
                }

                if (answ.equals("yes"))
                {
                        // i devient le premier chiffre des deux
                }
                if (answ.equals("no"))
                {
                        // i devient le deuxième chiffre des deux 
                }
     
        }
        }
}
