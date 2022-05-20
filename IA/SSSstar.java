package IA;

import tree.Node;

import java.util.PriorityQueue;

public class SSSstar {

    private Node rootNode;
    private PriorityQueue<Entity> priorityQueue;

    public Entity sssStar(Node n){
        int i;
        this.priorityQueue = new PriorityQueue<>();
        return null;
    }


    private void insertEntity(Entity ent){
        this.priorityQueue.add(ent);
        //ent.getNode().generateChild();
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

