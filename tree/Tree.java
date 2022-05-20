package tree;

public class Tree {

    private Node rootNode;

    public Tree (){
        //Noeud root
        rootNode = new Node();

        //Fils de 1
        Node node2 = new Node();
        Node node3 = new Node();
        //Fils de 2
        Node node4 = new Node();
        Node node5 = new Node();
        //Fils de 3
        Node node6 = new Node();
        Node node7 = new Node();

        node3.addNode(node6);
        node3.addNode(node7);

        node2.addNode(node4);
        node2.addNode(node5);

        rootNode.addNode(node2);
        rootNode.addNode(node3);

        System.out.println(rootNode);
    }

    public Node getNode(){
        return rootNode;
    }

    public static void main(String[] args) {
        new Tree();
    }
}
