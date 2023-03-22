/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.poitest;

import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author smirn
 */
public class Journal {
    private ArrayList<Group> groups;
    private Group activegroup;
    public int a; // Active Group

    public Journal() {
        groups = new ArrayList();   
    }

    public void addGroup(String name) {
        Group group = new Group(name);
        groups.add(group);
        activegroup = group;
    }

    public void setTasks(ArrayList<String> tasknames) {
        activegroup.setTasknames(tasknames);
    }

    public void setA(int a) {
        this.a = a;
    }

    void addStudent(int number, String FIO) {
        activegroup.addStudent(number,FIO);
    }

    void writeMark(double mark) {
        activegroup.writeMark(mark);
    }

    public DefaultMutableTreeNode addInfo2GUI() {
        DefaultMutableTreeNode main = new DefaultMutableTreeNode();
        for (Group group : groups) {
            main.add(group.getNode());
        }
        return main;
    }

    void setFinalMark(String fmark) {
        activegroup.setFinalMark(fmark);
    }
    
    
}
