package IA;

import model.Couple;
import model.Player;
import tree.Node;

import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class SSSstar extends Player implements Algorithm {

    private Node rootNode;
    private PriorityQueue<Entity> priorityQueue;

    public double run(Node node, int depth){
        return sssStar(node, depth);
    }

    public double sssStar(Node node, int depth){
        int i;
        //Depth in execution
        int algoDepth = depth;
        this.priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Entity(node,'v',Double.POSITIVE_INFINITY));
        int n = node.getNumber();
        Entity current;
        do{
            current = priorityQueue.poll();
            System.out.println(priorityQueue);
            if(current != null && current.isALive()){
                current.getNode().generateChild();
                //ALIVE
                if(current.getNode().isLeaf() || algoDepth == 0){
                    //LEAF
                    Couple tmp = new Couple(current.getNode().getMove().getX(), current.getNode().getMove().getY());
                    int weight = Heuristic.calulateWeight(current.getNode().getBoard(),tmp,current.getNode().getMove().getPiece());
                    current.getNode().setWeight(weight);
                    current.setRevolve();
                    current.setValue(Double.min(current.getValue(),weight));
                    priorityQueue.add(current);
                }else{
                    if(current.getNode().isMax()){
                        for(Node succ : current.getNode().getNodes()){
                            priorityQueue.add(new Entity(succ,'v',current.getValue()));
                        }
                    }else{
                        //Get brothers of current node
                        if(current.getNode().getParent() != null) {
                            List<Node> brothers = current.getNode().getParent().getNodes();
                            //Get index of left brother
                            int currentIndexInParent = brothers.indexOf(current.getNode());
                            //Get left brother node
                            System.out.println(currentIndexInParent + " / " + brothers.size());
                            if (currentIndexInParent < brothers.size() - 1) {
                                Node leftBrother = brothers.get(currentIndexInParent + 1);
                                priorityQueue.add(new Entity(leftBrother, 'v', current.getValue()));
                            }
                        }
                    }
                    //
                    algoDepth--;
                }
            }else if( current != null){
                if(current.getNode().isMin()){
                    Entity parent = new Entity(current.getNode().getParent(),'r', current.getValue());
                    priorityQueue.add(parent);
                    removeAllChild(parent);
                }else{
                    //Get all brothers
                    List<Node> brothers = current.getNode().getParent().getNodes();
                    //Get index of right brother
                    int currentIndexInParent = brothers.indexOf(current.getNode());
                    //Get current node in parent nodes
                    if(currentIndexInParent < brothers.size()-1) {
                        Entity rightBrother = new Entity(brothers.get(currentIndexInParent + 1), 'v', current.getValue());
                        priorityQueue.add(rightBrother);
                    }else {
                        Entity parent = new Entity(current.getNode().getParent(), 'r', current.getValue());
                        priorityQueue.add(parent);
                    }
                }
            }else{
                throw new RuntimeException("Error get head of PriorityQueue: current is null.");
            }

        }while(priorityQueue.peek().getNode().getNumber() != n);
        return current.getValue();
    }

    private void removeAllChild(Entity parent){
        for(Node succ : parent.getNode().getNodes()){
            if(parent.isParentOf(succ)){
                //Remove succ in priotity queue -> if entity node and succ are same
                priorityQueue.removeIf(entity -> entity.getNode().equals(succ));
            }
        }
    }

    private void insertEntity(Entity ent){
        this.priorityQueue.add(ent);
    }
    private void removeEntity(Entity ent){
        this.priorityQueue.remove(ent);
    }


    private class Entity implements Comparable{
        private Node node;
        private char type;
        private double value;
        Entity(Node node, char type, double value){
            this.node = node;
            this.type = type;
            this.value = value;
        }

        @Override
        //Permet de définir l'ordre décroissant dans la file de priorité
        public int compareTo(Object o) {
            Entity compareToEmp = (Entity) o;
            if (this.value == compareToEmp.value) return 0;
            if (this.value < compareToEmp.value) return 1;
            return -1;
        }
        //REQUEST

        public boolean isALive(){
            return this.type == 'v';
        }
        //COMMANDS
        public void setRevolve(){
            this.type = 'r';
        }

        public boolean isParentOf(Node child){
            return child.getParent().equals(this.getNode());

        }

        // GETTER
        public Node getNode() {
            return node;
        }

        public char getType() {
            return type;
        }

        public double getValue() {
            return value;
        }

        // SETTER
        public void setNode(Node node) {
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

