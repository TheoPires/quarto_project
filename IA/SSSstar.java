package IA;

import model.*;
import tree.Node;
import tree.SSSNode;

import java.util.ArrayList;
import java.util.List;

public class SSSstar extends Player implements Algorithm {

    private Node rootNode;
    private ArrayList<Entity> priorityQueue;

    @Override
    protected Move play(Board board, Piece selectedPiece) {
        Node n = new Node(1, null, board, DEPTH);
        double minimaxValue = run(n,DEPTH);
        for(Node succ : n.getNodes())
            if(succ.getWeight() == minimaxValue)
                return succ.getMove();
        return null;
    }

    public double run(Node node, int depth){
        SSSNode.init();
        return sssStar(new SSSNode(node, depth));
    }

    public double sssStar(SSSNode node){
        //Depth in execution
        this.priorityQueue = new ArrayList<>();
        insertEntity(new Entity(node,'v',Double.POSITIVE_INFINITY));
        int n = node.getNumber();
        Entity current;
        do{
//            System.out.println("-------------------------------");
            //System.out.println(priorityQueue);
            current = priorityQueue.remove(0);
            current.getNode().setExplored();
            //System.out.println(current + " ["+current.getNode().getDepth()+"]");
//            System.out.println("affichage l.33 : " + priorityQueue);
            if(current != null && current.isALive()) {
                current.getNode().generateChild();
                //ALIVE
                if(current.getNode().getDepth() == 0 || current.getNode().isLeaf()){
                    //LEAF
                    Couple tmp = new Couple(current.getNode().getMove().getX(), current.getNode().getMove().getY());
                    int weight = Heuristic.calulateWeight(current.getNode().getBoard(),tmp,current.getNode().getMove().getPiece());
                    current.getNode().setWeight(weight);
                    current.setRevolve();
                    current.setValue(Double.min(current.getValue(), weight));
                    //System.out.println("LEAF: "+current.getNode().getNumber());
                    insertEntity(current);
                } else {
                    if(current.getNode().isMax()){
                        // System.out.print("MAX NODE: "+current.getNode().getNumber()+" : ");
                        for(SSSNode succ : current.getNode().getSSSNodes()){
                            insertEntity(new Entity(succ,'v',current.getValue()));
                            //System.out.print(succ.getNumber()+", ");
                        }
                        //System.out.println();
                    } else {
                        //Insert the first left child not explored
                        //System.out.print("MIN NODE: "+current.getNode().getNumber()+" : ");
                        for (SSSNode succ : current.getNode().getSSSNodes()) {
                            if (!succ.isExplored()) {
                                insertEntity(new Entity(succ, 'v', current.getValue()));
                                break;
                            }
                        }
                        //System.out.println();
                    }
                }
//                System.out.println(priorityQueue);
            }else if (current != null){ //current est résolu
                if (current.getNode().isMin()){
                    Entity parent = new Entity(current.getNode().getParent(),'r', current.getValue());
                    removeAllChild(parent);
                    insertEntity(parent);
                }else{
                    //Get all brothers
                    List<SSSNode> brothers = current.getNode().getParent().getSSSNodes();
                    //Get current index node in parent nodes
                    int currentIndexInParent = brothers.indexOf(current.getNode());
                    //Compare current node if is last child
                    if(currentIndexInParent < brothers.size()-1) { //Not last child
                        for (int i = currentIndexInParent + 1; i < brothers.size() - 1; i++) {
                            SSSNode brother = brothers.get(i);
                            if (!brother.isExplored()) {
                                insertEntity(new Entity(brother, 'v', current.getValue()));
                                break;
                            }
                        }
                    } else { //Last child
                        Entity parent = new Entity(current.getNode().getParent(), 'r', current.getValue());
                        insertEntity(parent);
                    }
                }
            }else{
                throw new RuntimeException("Error get head of PriorityQueue: current is null.");
            }

        }while(priorityQueue.size() > 0 && priorityQueue.get(0).getNode().getNumber() != n);
        return current.getValue();
    }

    public List<Entity> testPriorityQ(SSSNode n) {
        priorityQueue = new ArrayList<>();
        Entity firstEntity = new Entity(n, 'v', Double.POSITIVE_INFINITY);
        Entity scdEntity = new Entity(n, 'r', 23);
        Entity thirdEntity = new Entity(n, 'v', 23);
        Entity fourthEntity = new Entity(n, 'v', 22);
        Entity fifthEntity = new Entity(n, 'r', 23);
        Entity sixEntity = new Entity(n, 'v', 97);

        insertEntity(firstEntity);
        insertEntity(scdEntity);
        insertEntity(thirdEntity);
        insertEntity(fourthEntity);
        insertEntity(fifthEntity);
        insertEntity(sixEntity);


        return priorityQueue;
    }

//    public double sssStar_TestTheo(SSSNode node, int depth){
//        priorityQueue = new PriorityQueue<>();
//        Entity root = new Entity(node, 'v', Double.POSITIVE_INFINITY); //Extraire premier de G
//        // DéBUT
//        priorityQueue.add(new Entity(node, 'v', Double.POSITIVE_INFINITY));
//        // TANT QUE
//        Entity extractHead = priorityQueue.poll();
//        if (extractHead == null) {
//            throw new AssertionError("extractHead is null");
//        }
//        while (extractHead != null && !extractHead.equals(new Entity(node, 'r', node.getWeight()))) {
//            if (extractHead.isALive()) { //Si l'élément retirer est vivant
//                if (extractHead.isTerminal()) { //Si l'élément retirer est terminal
//                    Couple coord = new Couple(extractHead.getNode().getMove().getX(), extractHead.getNode().getMove().getY());
//                    int weight = Heuristic.calulateWeight(extractHead.getNode().getBoard(), coord, extractHead.getNode().getMove().getPiece());
//                    priorityQueue.add(new Entity(extractHead.getNode(), 'r', Math.min(extractHead.getValue(), weight)));
//                } else { //L'élément retiré n'est pas terminal
//                    if (extractHead.getNode().isMax()) { // extractHead est Max
//                        // insère tout les fils de extraHead
//                        for (int j = 0; j < extractHead.getNode().getSSSNodes().size(); j++) {
//                            SSSNode child = extractHead.getNode().getSSSNodes().get(j);
//                            priorityQueue.add(new Entity(child, 'v', extractHead.getValue()));
//                        }
//                    } else { // extractHead est Min
//                        //Insertion fils gauche non exploré
//
//                    }
//                }
//            } else { //
//
//            }
//        }
//        return 0;
//    }

    public void testRemoveAllChild() {
        priorityQueue = new ArrayList<>();

        SSSNode root= new SSSNode(1,null, null,null,3);
        Entity rootEnt = new Entity(root,'v',Double.POSITIVE_INFINITY);//1
        priorityQueue.add(rootEnt);

        SSSNode n1= new SSSNode(-1,root, null,null,2);
        SSSNode n2= new SSSNode(-1,root, null,null,2);
        SSSNode n3= new SSSNode(-1,root, null,null,2);
        root.addSSSNode(n1);
        root.addSSSNode(n2);
        root.addSSSNode(n3);
        Entity n1Ent = new Entity(n1,'v',Double.POSITIVE_INFINITY);//2
        Entity n2Ent = new Entity(n2,'v',Double.POSITIVE_INFINITY);//3
        Entity n3Ent = new Entity(n3,'v',Double.POSITIVE_INFINITY);//4
        priorityQueue.add(n1Ent);
        priorityQueue.add(n2Ent);
        priorityQueue.add(n3Ent);


        SSSNode n11= new SSSNode(1,n1, null,null,1);
        SSSNode n12= new SSSNode(1,n1, null,null,1);
        n1.addSSSNode(n11);
        n1.addSSSNode(n12);
        Entity n11Ent = new Entity(n11,'v',Double.POSITIVE_INFINITY);//5
        Entity n12Ent = new Entity(n12,'v',Double.POSITIVE_INFINITY);//6
        priorityQueue.add(n11Ent);
        priorityQueue.add(n12Ent);

        SSSNode n21= new SSSNode(1,n2, null,null,1);
        SSSNode n22= new SSSNode(1,n2, null,null,1);
        n2.addSSSNode(n21);
        n2.addSSSNode(n22);
        Entity n21Ent = new Entity(n21,'v',Double.POSITIVE_INFINITY);//7
        Entity n22Ent = new Entity(n22,'v',Double.POSITIVE_INFINITY);//8
        priorityQueue.add(n21Ent);
        priorityQueue.add(n22Ent);

        SSSNode n31= new SSSNode(1,n3, null,null,1);
        SSSNode n32= new SSSNode(1,n3, null,null,1);
        n3.addSSSNode(n31);
        n3.addSSSNode(n32);
        Entity n31Ent = new Entity(n31,'v',Double.POSITIVE_INFINITY);//9
        Entity n32Ent = new Entity(n32,'v',Double.POSITIVE_INFINITY);//10
        priorityQueue.add(n31Ent);
        priorityQueue.add(n32Ent);

        System.out.println("Full Priority Queue :");
        System.out.println(priorityQueue);
        removeAllChild(n2Ent);
//        removeAllChild(n3Ent);
        System.out.println("Removed node Priority Queue :");
        System.out.println(priorityQueue);
    }

    private void removeAllChild(Entity parent){

        for(SSSNode succ : parent.getNode().getSSSNodes()){
            //Remove succ in priotity queue -> if entity node and succ are same
            List<Entity> removed = new ArrayList<>();
            priorityQueue.removeIf(entity -> {
                if(entity.getNode().equals(succ)){
                    removed.add(entity);
                    return true;
                }
                return false;
            });
            for(Entity e : removed) {
                removeAllChild(e);
            }
        }
    }

    private void insertEntity(Entity ent){
        int insertIndex = 0;
        if(priorityQueue.size() > 0){
            for (int i = 0; i < priorityQueue.size(); i++) {
                if (ent.getValue() > priorityQueue.get(i).getValue()) {
                    insertIndex = i;
                    break;
                }else{
                    insertIndex = i+1;
                }
            }
        }
        this.priorityQueue.add(insertIndex, ent);
//        System.out.println(priorityQueue);
    }


    private class Entity{
        private SSSNode node;
        private char type;
        private double value;
        Entity(SSSNode node, char type, double value){
            this.node = node;
            this.type = type;
            this.value = value;
        }
        //REQUEST

        public boolean isALive(){
            return this.type == 'v';
        }

        public boolean isTerminal() { return this.getNode().isLeaf(); }

        //COMMANDS
        public void setRevolve(){
            this.type = 'r';
        }

        public boolean isParentOf(SSSNode child){
            return child.getParent().equals(this.getNode());

        }

        // GETTER
        public SSSNode getNode() {
            return node;
        }

        public char getType() {
            return type;
        }

        public double getValue() {
            return value;
        }

        // SETTER
        public void setNode(SSSNode node) {
            this.node = node;
        }

        public void setType(char type) {
            this.type = type;
        }

        public void setValue(double value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "("+node.getNumber()+", "+type+", "+value+")";
        }
    }
}

