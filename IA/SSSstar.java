package IA;

import model.*;
import tree.Node;
import tree.SSSNode;

import java.util.ArrayList;
import java.util.List;

public class SSSstar extends Algorithm implements Player {

    private ArrayList<Entity> priorityQueue;

    private int depth = 1;

    public SSSstar(int who, String name) {
        super(who, name);
    }

    public double run(Node node, int depth, int level){
        SSSNode.init();
        return sssStar(new SSSNode(node, this.depth), level);
    }

    public double sssStar(SSSNode node, int level){
        //Depth in execution
        this.priorityQueue = new ArrayList<>();
        insertEntity(new Entity(node,'v',Double.POSITIVE_INFINITY));
        int n = node.getNumber();
        Entity current;
        do{
            current = priorityQueue.remove(0);
            current.getNode().setExplored();
            if(current != null && current.isALive()) {
                current.getNode().generateChild();
                //ALIVE
                if(current.getNode().getDepth() == 0 || current.getNode().isLeaf()){
                    //LEAF
                    Couple tmp = new Couple(current.getNode().getMove().getX(), current.getNode().getMove().getY());
                    int weight = Heuristic.calulateWeight(current.getNode().getBoard(),tmp,current.getNode().getMove().getPiece(), level);
                    current.getNode().setWeight(weight);
                    current.setRevolve();
                    current.setValue(Double.min(current.getValue(), weight));
                    insertEntity(current);
                } else {
                    if(current.getNode().isMax()){
                        for(SSSNode succ : current.getNode().getSSSNodes()){
                            insertEntity(new Entity(succ,'v',current.getValue()));
                            current.getNode().setWeight(current.getValue());
                        }
                    } else {
                        //Insert the first left child not explored
                        for (SSSNode succ : current.getNode().getSSSNodes()) {
                            if (!succ.isExplored()) {
                                insertEntity(new Entity(succ, 'v', current.getValue()));
                                current.getNode().setWeight(current.getValue());
                                break;
                            }
                        }
                    }
                }
            }else if (current != null){ //current est résolu
                if (current.getNode().isMin()){
                    Entity parent = new Entity(current.getNode().getParent(),'r', current.getValue());
                    removeAllChild(parent);
                    insertEntity(parent);
                    current.getNode().getParent().setWeight(current.getValue());
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
                                brother.setWeight(current.getValue());
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
    }

    @Override
    public String toString() {
        return "SSS*";
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

