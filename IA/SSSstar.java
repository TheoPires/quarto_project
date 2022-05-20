package IA;

import model.Couple;
import tree.Node;

import java.util.PriorityQueue;

public class SSSstar {

    private Node rootNode;
    private PriorityQueue<Entity> priorityQueue;

    public Entity sssStar(Node node){
        int i;
        this.priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Entity(node,'v',Double.POSITIVE_INFINITY));
        int n = node.getNumber();
        do{
            Entity current = priorityQueue.poll();
            if(current != null && current.isALive()){
                //ALIVE
                if(current.getNode().isLeaf()){
                    //LEAF
                    Couple tmp = new Couple(node.getMove().getX(), node.getMove().getY());
                    int weight = Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece());
                    node.setWeight(weight);
                    current.setRevolve();
                    current.setValue(Double.min(current.getValue(),weight));
                    priorityQueue.add(current);
                }else{
                    if(current.getNode().isMax()){
                        for(Node succ : node.getNodes()){
                            priorityQueue.add(new Entity(succ,'v',current.getValue()));
                        }
                    }else{
                        Node leftBrother = null; //= current.getNode().getNodes().indexOf();
                        priorityQueue.add(new Entity(leftBrother,'v',current.getValue()));
                    }
                }
            }else if( current != null){
                if(!current.getNode().isMax()){
                    Node parent = current.getNode().getParent();
                    priorityQueue.add(new Entity(parent,'r', current.getValue()));
                    for(Node succ : parent.getNodes()){

                    }
                }else{

                }
            }else{
                throw new RuntimeException("Error get head of PriorityQueue");
            }

        }while(priorityQueue.peek().getNode().getNumber() != n);
        return null;
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
    }
}

