/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.poitest;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author smirn
 */
public class Group {
    private ArrayList<Student> students;
    private String name;
    private ArrayList<String> tasknames;
    private Student activestudent;
    private int markcounter;

    Group(String name) {
        this.name = name;
        students = new ArrayList();
    }

    public void setTasknames(ArrayList<String> tasknames) {
        this.tasknames = tasknames;
    }

    public void addStudent(int number, String FIO) {
        Student student = new Student(number, FIO);
        students.add(student);
        activestudent = student;
        markcounter = 0;
    }

    void writeMark(double mark) {
        activestudent.setMark(tasknames.get(markcounter), mark);
        markcounter++;
    }

    void setFinalMark(String fmark) {
        activestudent.setFinalmark(fmark);
    }

    public MutableTreeNode getNode() {
        DefaultMutableTreeNode groupnode = new DefaultMutableTreeNode(name);
        for (Student student : students) {
            groupnode.add(student.getNode());
        }
        return groupnode;
    }
    
     
}
