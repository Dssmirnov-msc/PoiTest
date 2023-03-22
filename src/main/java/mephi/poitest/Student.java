/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.poitest;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author smirn
 */
public class Student {
    private int number;
    private String FIO;
    private LinkedHashMap<String, Double> Marks;
    private String finalmark;

    public Student(int number, String FIO) {
        this.number = number;
        this.FIO = FIO;
        Marks = new LinkedHashMap();
    }

    void setMark(String task, double mark) {
        Marks.put(task, mark);
    }

    public void setFinalmark(String finalmark) {
        this.finalmark = finalmark;
    }

    public MutableTreeNode getNode() {
        DefaultMutableTreeNode snode = new DefaultMutableTreeNode(FIO);// Корневой узел Студента
        snode.add(new DefaultMutableTreeNode(finalmark)); 
        DefaultMutableTreeNode mnode = new DefaultMutableTreeNode("Оценки"); // Узел Оценок
        for (Map.Entry<String, Double> entry : Marks.entrySet()) {
            String key = entry.getKey();
            Double val = entry.getValue();
            String record =  key + " : "+ val;
            mnode.add(new DefaultMutableTreeNode(record));
        }
        snode.add(mnode);
        return snode;
    }
    
    
}
